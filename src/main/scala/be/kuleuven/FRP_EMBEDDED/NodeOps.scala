package be.kuleuven.FRP_EMBEDDED

import be.kuleuven.LMS_extensions.ScalaOpsPkgExt

trait NodeOps extends ScalaOpsPkgExt {

  private[FRP_EMBEDDED] type NodeID = Int
  class ModuleName private[FRP_EMBEDDED] (val str: String) { // packet private constructor
    override def toString(): String = str
  }

  trait Node[A] {

    // all private
    /*private[FRP_EMBEDDED]*/ val id: NodeID = Node.nextid //TODO: not packet private because of unit tests
    /*private[FRP_EMBEDDED]*/ val level: Int // for topological order
    /*private[FRP_EMBEDDED]*/ val childNodeIDs: scala.collection.mutable.Set[NodeID]
    /*private[FRP_EMBEDDED]*/ def buildGraphTopDown(): Unit

    private[FRP_EMBEDDED] val inputNodeIDs: Set[NodeID]
    private[FRP_EMBEDDED] val moduleName: ModuleName
    private[FRP_EMBEDDED] def addChild(id: NodeID): Unit

    private[FRP_EMBEDDED] def getFunction(): Rep[(Unit)=>Unit]

  }

  object Node {
    private var id: Int = 0
    private def nextid = {id += 1;id}
  }

}

trait NodeOpsImpl extends NodeOps {
  private val nodeMap: scala.collection.mutable.Map[NodeID,NodeImpl[_]] = scala.collection.mutable.HashMap()
  private val behaviorIDs: scala.collection.mutable.Set[NodeID] = scala.collection.mutable.HashSet()

  def addNodeToNodemap(id: NodeID, node: NodeImpl[_]): Unit = {
    nodeMap += ((id, node))
  }

  def getNodeMap: Map[NodeID,NodeImpl[_]] = {
    nodeMap.toMap
  }

  def addBehaviorID(id: NodeID): Unit = { behaviorIDs += id }
  def getBehaviorIDs(): Set[NodeID] = behaviorIDs.toSet

  def getOutputNodes: Map[NodeID,NodeImpl[_]] = {
    getNodeMap.filter(
      x => x match {
        case (_, n) => if(n.childNodeIDs.size == 0) true else false
        case _ => throw new IllegalStateException("Unsupported Node type")
      }
    )
  }

  def getMaxLevel: Int = {
    var maxlevel: Int = 0
    getNodeMap.foreach(
      x => x match {
        case (_, e) => maxlevel = scala.math.max(maxlevel, e.level)
        case _ => throw new IllegalStateException("Unsupported Node type")
      }
    )
    maxlevel
  }

  def getNodesOnLevel(nodes: List[NodeImpl[_]], level: Int): List[NodeImpl[_]] = {
    nodes.filter(
      x => x match {
        case node => if(node.level == level) true else false
      }
    )
  }

  def getDecendantNodeIDs(node: NodeImpl[_]): List[NodeID] = {
    val listbuilder = scala.collection.mutable.ListBuffer.empty[NodeID]

    listbuilder += node.id
    for(childid <- node.childNodeIDs) {
      val childnode = getNodeMap.get(childid) match {
        case Some(cn) => cn
        case None => throw new IllegalStateException("Unknown event ID") }
      listbuilder ++= getDecendantNodeIDs(childnode)
    }
    listbuilder.toList
  }

  def getNodesWithIDs(ids: List[NodeID]): Map[NodeID, NodeImpl[_]] = {
    getNodeMap.filter( x => x match {
      case (id, e) => ids.contains(id)
    })
  }

  trait NodeImpl[A] extends Node[A] {
    def generateNode(): Unit
    //def getFunction(): Rep[(Unit)=>Unit]
    def getInitializer(): Rep[Unit]

    override def toString: String = {
      "Node with id: " + this.id
    }
  }

}

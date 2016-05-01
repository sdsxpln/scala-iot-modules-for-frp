package be.kuleuven.FRP_EMBEDDED

trait FRPDSLOptImpl extends FRPDSL_Impl with EventOpsOptImpl with BehaviorOpsOptImpl {


  override def printGraph: Unit = {
    val inputevents = getInputEventNodes
    System.err.println("InputEvents:")
    inputevents.foreach(System.err.println )

    //get all end nodes
    val leafNodes = getOutputNodes.values.toList
    System.err.println("LeafNodes:")
    leafNodes.foreach(System.err.println )
  }

  override def generateGlobalFRPInits(module: Module[_]): Unit = {
    // nothing to do since not global anymore
  }

  override def generateModuleSpecifics(module: Module[_], initModule: Rep[(Unit) => Unit]): Unit = {

    //get all input events
    val inputs = getInputEventNodes
    val modinputs = inputs.filter(n => n.moduleName == module.name)

    // generate top functions
    for( ie <- modinputs) {
      System.err.println("Generate dependencies of inputnode " + ie.id)
      generateTopFunction(ie, initModule, module)
    }

    System.err.println("End of generateModule")
  }

  private def generateTopFunction[X](input: InputEvent[X], initModule: => Rep[(Unit)=>Unit], m: Module[_]): Unit = {
    System.err.println("top" + input.id)

    val descendantIDs = getDecendantNodeIDs(input).filter(id => id != input.id)
    val descendantNodes = getNodesWithIDs(descendantIDs)

    // get topological ordering
    val listbuilder = scala.collection.mutable.ListBuffer.empty[Node[_]]
    for( i <- 0 to getMaxLevel)
      listbuilder ++= getNodesOnLevel(descendantNodes.values.toList,i)
    val nodesTO = listbuilder.toList
    nodesTO.foreach(x => System.err.println(x.id))

    //TODO: maybe get rid of concrete output
    m.output match {
      case coe @ ConcreteOutputEvent(_) => System.err.println("Output for: " + coe.parent.id)
      case _ => System.err.println("No outputs for this module")
    }
    val behaviorsInModule = getBehaviorNodes.values.filter( node => node.moduleName == input.moduleName)

    val top = inputfun(input.moduleName.str, "top"+input.id) { (data: Rep[Ptr[Byte]], len: Rep[Int]) =>
      if(behaviorsInModule.size > 0) initModule()

      resetSymMap()
      input.useInputNode(data, len)

      nodesTO.foreach( x => { x.useFunction() } ) // apply the functions in this context

      m.output match {
        case coe @ ConcreteOutputEvent(_) =>
          if (coe.inputNodeIDs.contains(input.id) ) coe.useFunction
        case _ => // we didn't had an output, it was None
      }

      unitToRepUnit( () )
    }
    doApplyDecl(top)
  }
}

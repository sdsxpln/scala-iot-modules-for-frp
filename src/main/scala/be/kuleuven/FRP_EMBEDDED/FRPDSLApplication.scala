package be.kuleuven.FRP_EMBEDDED

/*
 * trait provided by the DSL that defines the DSL interface
 */
trait FRPDSLApplication extends FRPDSL {

  def createApplication(): List[Module[_]]
  def createModule[A] (graphfun: (ModuleName)=>Option[OutputEvent[A]] ): Module[A]
}

trait FRPDSLApplication_Impl extends FRPDSLApplication {

  object ModuleNumber {
    private var id: Int = 0
    def nextid = {id += 1;id}
  }

  override def createModule[A] (graphfun: (ModuleName)=>Option[OutputEvent[A]] ): Module[A] = {
    val name: String = new String("mod").concat(ModuleNumber.nextid.toString)
    val mod = new ModuleName(name)

    val out = graphfun(mod) match {
      case Some(output) =>
        output
      case None =>
        null
    }

    new Module[A] {
      override val name = mod
      override val output = out
    }
  }
}

trait FRPDSLApplicationRunner extends FRPDSLApplication_Impl with FRPDSLImpl
trait FRPDSLOptApplicationRunner extends FRPDSLApplication_Impl with FRPDSLOptImpl

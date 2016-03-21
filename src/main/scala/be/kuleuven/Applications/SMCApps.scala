package be.kuleuven.Applications

import be.kuleuven.FRP_EMBEDDED.CFRPDSLApplicationRunner
import be.kuleuven.FRP_EMBEDDED.OutputGenerator._

object SMCAppsRunner {

  def main(args: Array[String]): Unit = {

    withOutFile("SMCInputApp.c") {
      new CFRPDSLApplicationRunner {
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%")

        val program: () => Rep[Unit] =
          () => {
            val xx = var_new[Int](5)

            val infun = inputfunInner ("module1") { (x:Rep[Int]) =>
              val yy = var_new[Int](10)
              println(x+1)
              println(xx)
              println(yy)
              unitToRepUnit( () )
            }
            doApplyDecl(infun)

            unitToRepUnit( () )
          }

        System.err.println("\n")
        emitProgram(program)
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%")
        System.err.println("\n\n")
      }
    }

    withOutFile("SMCTupledInputApp.c") {
      new CFRPDSLApplicationRunner {
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%")

        val program: () => Rep[Unit] =
          () => {
            val xx = var_new[Int](5)
            val modvar = vardeclmod_new[Int]("module2")

            val infun = inputfun ("module2") { (x:Rep[Int],y:Rep[Int]) =>
              val yy = var_new[Int](10)
              println(x+1)
              println(xx)
              println(yy)
              println(modvar)
              unitToRepUnit( () )
            }
            doApplyDecl(infun)

            unitToRepUnit( () )
          }

        System.err.println("\n")
        emitProgram(program)
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%")
        System.err.println("\n\n")
      }
    }

  }
}
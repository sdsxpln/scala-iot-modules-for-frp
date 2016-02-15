package be.kuleuven.FRP_EMBEDDED

import java.io.{OutputStream, FileOutputStream, PrintStream, File}

object OutputGenerator {

  def withOutFile(name: String)(func: => Unit): Unit = {
    val home = "/home/ben/thesis/FRP_embedded/generated/" // TODO: remove hard coding

    val file = new File(home+name)
    file.getParentFile.mkdirs()
    withOutput(new PrintStream(new FileOutputStream(file)))(func)
  }

  def withOutput(out: PrintStream)(func: => Unit): Unit = {
    val oldStdOut = System.out
    val oldStdErr = System.err
    try {
      System.setOut(out)
      //System.setErr(out) //to set to same file
      System.setErr(new PrintStream(new OutputStream() { //to suppress
        override def write(b: Int) {}}))
      // comment both to direct to terminal

      Console.withOut(out)(Console.withErr(out)(func))
    } finally {
      out.flush()
      out.close()
      System.setOut(oldStdOut)
      System.setErr(oldStdErr)
    }
  }
}

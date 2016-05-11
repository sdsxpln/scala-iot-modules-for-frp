package be.kuleuven.Applications

import be.kuleuven.FRP_EMBEDDED._


trait Counter1App extends FRPDSLApplication {

  override def createApplication: List[Module[_]] = {
    val mod1 = createModule[Int] { implicit n: ModuleName =>
      val input1 = TimerEvent(1)
      val input2 = TimerEvent(1)
      val negate2 = input2.map( (i: Rep[Int]) => 0-i)
      val merged =
        input1.merge(negate2, (x:Rep[Int],y:Rep[Int]) => x + y)
      val filtered =
        merged.filter( x => Math.abs(x) < 10)
      val counter =
        filtered.foldp((x:Rep[Int], state:Rep[Int])=>state + x, 0)
      Some(out("counterOut", counter.changes()))

    }

    mod1::Nil
  }

}

trait Counter2App extends FRPDSLApplication {

  override def createApplication: List[Module[_]] = {
    val mod1 = createModule[Int] { implicit n: ModuleName =>
      val input1 = TimerEvent(1)
      val input2 = TimerEvent(1)
      val filtered1 =
        input1.filter( _ < 10)
      val filtered2 =
        input2.filter( _ < 10)
      val counter =
        filtered1.foldp2(filtered2,
          (x:Rep[Int], state:Rep[Int])=>state + x,
          (y:Rep[Int], state:Rep[Int])=>state - y,
          (x:Rep[Int], y:Rep[Int], state:Rep[Int])=>state + x - y,
          0)
      Some(out("counterOut", counter.changes()))

    }

    mod1::Nil
  }

}

trait Counter3App extends FRPDSLApplication {

  override def createApplication: List[Module[_]] = {
    val mod1 = createModule[Int] { implicit n: ModuleName =>
      val input1 = ButtonEvent(Buttons.button1)
      val input2 = ButtonEvent(Buttons.button2)
      val negate2 = input2.map( (i: Rep[Int]) => 0-i)
      val merged =
        input1.merge(negate2, (x:Rep[Int],y:Rep[Int]) => x + y)
      val filtered =
        merged.filter( x => Math.abs(x) < 10)
      val counter =
        filtered.foldp((x:Rep[Int], state:Rep[Int])=>state + x, 0)
      val printed = counter.changes.printIntLCD( (x:Rep[Int]) => x )
      Some(out("counterOut", printed))

    }

    mod1::Nil
  }

}

trait TestApp extends FRPDSLApplication {

  override def createApplication: List[Module[_]] = {
    val mod1 = createModule[Int] { implicit n: ModuleName =>
      val inc = TimerEvent(5)
      val inc1 = inc.constant(1)
      val incdecValue = inc1.foldp( (x,state:Rep[Int]) => state + x, 0)

      val plus = TimerEvent(5)
      val snapPlus = incdecValue.snapshot(plus)

      val filtered =
        snapPlus.filter( x => Math.abs(x) < 10)
      val counter =
        filtered.foldp((x:Rep[Int], state:Rep[Int])=>state + x, 0)
      //val printed = counter.changes.printIntLCD( (x:Rep[Int]) => x )
      Some(out("counterOut", counter.changes()))

    }

    mod1::Nil
  }

}

trait Counter4App extends FRPDSLApplication {

  override def createApplication: List[Module[_]] = {
    val mod1 = createModule[Int] { implicit n: ModuleName =>
      val inc = ButtonEvent(Buttons.button3)
      val inc1 = inc.constant(1)
      val dec = ButtonEvent(Buttons.button4)
      val dec1 = dec.constant(-1)
      val mergeIncDec = inc1.merge(dec1, (x,y)=>x+y)
      val incdecValue = mergeIncDec.foldp( (x,state:Rep[Int]) => state + x, 0)

      val plus = ButtonEvent(Buttons.button1)
      val snapPlus = incdecValue.snapshot(plus)

      val min = ButtonEvent(Buttons.button2)
      val snapMin = incdecValue.snapshot(min)


      val negate2 = snapMin.map( (i: Rep[Int]) => 0-i)
      val merged =
        snapPlus.merge(negate2, (x:Rep[Int],y:Rep[Int]) => x + y)
      val filtered =
        merged.filter( x => Math.abs(x) < 10)
      val counter =
        filtered.foldp((x:Rep[Int], state:Rep[Int])=>state + x, 0)
      //val printed = counter.changes.printIntLCD( (x:Rep[Int]) => x )
      Some(out("counterOut", counter.changes()))

    }

    mod1::Nil
  }

}

import OutputGenerator.withOutFile

object CounterAppRunner {
  def main(args: Array[String]): Unit = {

    withOutFile("Counter1App.c") {
      System.err.println("Counter1App:")
      (new Counter1App with CFRPDSLApplicationRunner).run
    }

    withOutFile("Counter1OptApp.c") {
      System.err.println("Counter1OptApp:")
      (new Counter1App with CFRPDSLOptApplicationRunner).run
    }

    withOutFile("Counter2App.c") {
      System.err.println("Counter2App:")
      (new Counter2App with CFRPDSLApplicationRunner).run
    }

    withOutFile("Counter3App.c") {
      System.err.println("Counter3App:")
      (new Counter3App with SMCFRPDSLApplicationRunner).run
    }

    /*
    withOutFile("TestApp.c") {
      System.err.println("TestApp:")
      (new TestApp with CFRPDSLApplicationRunner).run
    }*/

    /*withOutFile("Counter4App.c") {
      System.err.println("Counter4App:")
      (new Counter4App with SMCFRPDSLApplicationRunner).run
    }*/
  }
}
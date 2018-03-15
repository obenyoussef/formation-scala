package example

object Hello extends App {

  /*
  Write a program that prints the numbers from 1 to 100.
  But for multiples of three print "Fizz" instead of the number
  and for the multiples of five print "Buzz".
  For numbers which are multiples of both three and five print "FizzBuzz".
   */

  def fizzbuzz(): Unit = {
    (1 to 100).foreach { n =>
      if (n % 3 == 0 && n % 5 == 0)
        println("FizzBuzz")
      else if (n % 3 == 0)
        println("Fizz")
      else if (n % 5 == 0)
        println("Buzz")
      else
        println(n)
    }
  }

  def fizzbuzzAvecMap(): Unit = {
    (1 to 100).map { n =>
      if (n % 3 == 0 && n % 5 == 0)
        "FizzBuzz"
      else if (n % 3 == 0)
        "Fizz"
      else if (n % 5 == 0)
        "Buzz"
      else
        n
    }.foreach(println)
  }

  def fizzbuzzAvecPatternMatching(): Unit = {
    (1 to 100).map { n =>
      (n % 3 == 0, n % 5 == 0) match {
        case (true, true) => "FizzBuzz"
        case (true, false) => "Fizz"
        case (false, true) => "Buzz"
        case _ => n
      }
    }.foreach(println)
  }

  fizzbuzz()
}

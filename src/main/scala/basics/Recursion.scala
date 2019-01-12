package basics

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + "I first need factorial of" + (n-1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  println(factorial(10))


  def anotherFactorial(n: BigInt): BigInt = {
    @annotation.tailrec
    def factHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x -1, x * accumulator) // TAIL RECURSION = use recursive call as the Last expression

    factHelper(n, 1)
  }

  /*
   * anotherFactorial(10 = factHelper(10, 1)
   * + FactHelper( 9, 10 * 1)
   * + FactHelper( 8, 9 * 10 * 1)
   * + FactHelper( 7, 8 * 9 * 10 * 1)
   * + FactHelper( 9, 10 * 1)
   * + FactHelper( 1, 1 * 2 * 3 * 4 * ... * 10)
   * = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
   */
  println(anotherFactorial(200000))

  //WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

  /*
   * 1. Concatenate a string ntimes
   * 2. IsPrime function tail recursive
   * 3. Fibonacci function, tail recursive.
   */

  def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailrec(aString, n - 1, aString + accumulator)

  println(concatenateTailrec("hello", 3, ""))


  def isPrime(n: Int): Boolean = {
    def isPrimeTailrec(n: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)
    isPrimeTailrec(n / 2, true)
  }

  println(isPrime(2003))


  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailrec( i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(3, 1, 1)
  }

  println(fibonacci(8)) // 1 1 2 3 5 8 13, 21


}

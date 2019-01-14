package functionprograming

object TuplesAndMaps extends App {

  // tuples = finite orderd "lists"
  val aTuple = new Tuple2(2, " hello, Scala") // Tuple2[Int, String = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> Values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) //syntax sugar

  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim")) // => Boolean
  println(phoneBook("Jim")) // => Boolean
  println(phoneBook("Mary")) // => Boolean key not hit

  // add a paring
  val newParing = "Mary" -> 678
  val newPhoneBook = phoneBook + newParing
  println(newPhoneBook)

  // functional on maps
  // map, flatMap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  // filterKeys
  println(phoneBook.filterKeys(_.startsWith("J")))
  // mapValues
  println(phoneBook.mapValues(number => "0245-" + number))

  // conversions to other collections
  println(phoneBook.toList) // List(Tuple2, Tuple2)
  println(List(("Danie", 555)).toMap) // List(Tuple2, Tuple2)

  val names = List("kumagai", "take", "nakajima", "くま", "くま")
  println(names.groupBy(name => name.charAt(0))) // => Map(Char -

  /*
  2
(2,goodbye Java)
( hello, Scala,2)
Map(Jim -> 555, Daniel -> 789)
true
555
-1
Map(Jim -> 555, Daniel -> 789, Mary -> 678)
Map(jim -> 555, daniel -> 789)
Map(Jim -> 555)
Map(Jim -> 0245-555, Daniel -> 0245-789)
List((Jim,555), (Daniel,789))
Map(Danie -> 555)
Map(n -> List(nakajima), t -> List(take), k -> List(kumagai), く -> List(くま, くま))
   */

  // Exereisse
  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 999
   */
  val phoneBook2 = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 900).withDefaultValue(-1) //syntax sugar

  /*
    2. Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend

   */
  object Network {
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      network + (person -> Set())

    def friend(netowrk: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = netowrk(a)
      val friendsB = netowrk(a)
      netowrk + (a -> (friendsA + b)) + (b -> (friendsB + a))

    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(a)
      network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
      def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
        if (friends.isEmpty) networkAcc
        else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
      }
      val unfriended = removeAux(network(person), network) // network removed
      unfriended - person  // remove map
    }

    // number of friends of a person
    def nFriends(network: Map[String, Set[String]], person: String): Int = ???

    // person with most friends
    def mostFriends(network: Map[String, Set[String]]): String = ???

    // how many people have NO friends
    def nPeopleWithNoFriends(network: Map[String, Set[String]]) = ???

    // If there is social connection between two people (direct or not)
    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = ???
  }

  {
    import Network._
    println("\nnetwork test started.")

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Bob"), "Mary")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

    // Jim,Bob,Mary
    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  }



}

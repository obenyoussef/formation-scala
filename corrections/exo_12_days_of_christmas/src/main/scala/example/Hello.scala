package example

object Hello extends App {

  // Afficher les paroles de la comptine "Twelves days of christmas"
  // https://en.wikipedia.org/wiki/The_Twelve_Days_of_Christmas_(song)
  // Exemple :
  /*

  On the First day of Christmas my true love sent to me
  a Partridge in a Pear Tree.

  On the Second day of Christmas my true love sent to me
  Two Turtle Doves
  and a Partridge in a Pear Tree.

  On the Third day of Christmas my true love sent to me
  Three French Hens
  Two Turtle Doves
  and a Partridge in a Pear Tree.

  (...)

  On the Twelfth day of Christmas my true love sent to me
  Twelve Drummers Drumming
  Eleven Pipers Piping
  Ten Lords a-Leaping
  Nine Ladies Dancing
  Eight Maids a-Milking
  Seven Swans a-Swimming
  Six Geese a-Laying
  Five Gold Rings
  Four Calling Birds
  Three French Hens
  Two Turtle Doves
  And a Partridge in a Pear Tree

   */
  def twelveDaysOfChristmas(): Unit = {
    days.foreach { day =>
      println(couplet(day))
      println()
    }
  }

  // Donne la String finale pour un couplet donné
  // Attention au "And (...)" et aux majuscules !
  def couplet(day: Day): String = {
    val gifts = allGifts(day)
    val firstGifts = gifts.init
    val lastGift = gifts.last
    (
      firstLine(day) +: (
        if (firstGifts.isEmpty)
          Seq(lastGift)
        else
          firstGifts :+ ("And " + lastGift)
      )
    )
      .map(_.capitalize)
      .mkString("\n")
  }

  // Donne la première ligne du couplet ("On the ... sent to me")
  def firstLine(day: Day): String =
    s"On the ${day.numberAdjective} day of Christmas my true love sent to me"

  // Liste (dans l'ordre inversé, en remontant) tous les cadeaux donnés pour un certain jour
  def allGifts(day: Day): Seq[String] = {
    days
      .takeWhile(_ != day)
      .:+(day)
      .map(_.gift)
      .reverse
  }

  case class Day(
    numberAdjective: String,
    gift: String
  )

  val days = Seq(
    Day("First", "a Partridge in a Pear Tree"),
    Day("Second", "two Turtle Doves"),
    Day("Third", "three French Hens"),
    Day("Fourth", "four Calling Birds"),
    Day("Fifth", "five Gold Rings"),
    Day("Sixth", "six Geese a-Laying"),
    Day("Seventh", "seven Swans a-Swimming"),
    Day("Eighth", "eight Maids a-Milking"),
    Day("Ninth", "nine Ladies Dancing"),
    Day("Tenth", "ten Lords a-Leaping"),
    Day("Eleventh", "eleven Pipers Piping"),
    Day("Twelfth", "twelve Drummers Drumming")
  )


  twelveDaysOfChristmas()

}

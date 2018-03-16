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
  A Partridge in a Pear Tree
  Two Turtle Doves
  Three French Hens
  Four Calling Birds
  Five Gold Rings
  Six Geese a-Laying
  Seven Swans a-Swimming
  Eight Maids a-Milking
  Nine Ladies Dancing
  Ten Lords a-Leaping
  Eleven Pipers Piping
  And twelve Drummers Drumming

   */
  def twelveDaysOfChristmas(): Unit =
    ???

  // Donne la String finale pour un couplet donné
  def couplet(day: Day): String =
    ???

  // Donne la première ligne du couplet ("On the ... sent to me")
  def firstLine(day: Day): String =
    ???

  // Liste (dans l'ordre) tous les cadeaux donnés pour un certain jour
  def allGifts(day: Day): Seq[String] =
    ???

  // Indique quel jour était le précédent de celui passé en paramètre
  // (ou None si c'était le premier jour)
  def getPreviousDay(day: Day): Option[Day] =
    ???

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

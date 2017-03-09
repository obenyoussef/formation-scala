
import bataille.BatailleFermee
import bataille.Models.Joueur

object Main {

  val batailleFermee = new BatailleFermee

  def main(args: Array[String]): Unit = {
    val joueurs = Seq(
      Joueur("Lulu"),
      Joueur("Momo"),
      Joueur("Gégé")
    )

    val (gagnant, nbPlis) = batailleFermee.joue(joueurs)
    println(s"Le gagnant est ${gagnant.nom} en $nbPlis plis")


  }

}

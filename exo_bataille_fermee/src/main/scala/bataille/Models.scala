package bataille

object Models {

  sealed trait Couleur
  object Couleur {
    case object Pique extends Couleur
    case object Trefle extends Couleur
    case object Carreau extends Couleur
    case object Coeur extends Couleur
    val all = Seq(Pique, Trefle, Carreau, Coeur)
  }

  sealed trait Hauteur
  object Hauteur {
    case object Deux extends Hauteur
    case object Trois extends Hauteur
    case object Quatre extends Hauteur
    case object Cinq extends Hauteur
    case object Six extends Hauteur
    case object Sept extends Hauteur
    case object Huit extends Hauteur
    case object Neuf extends Hauteur
    case object Dix extends Hauteur
    case object Valet extends Hauteur
    case object Roi extends Hauteur
    case object As extends Hauteur
    val all: Seq[Hauteur] = Seq(
      Deux,
      Trois,
      Quatre,
      Cinq,
      Six,
      Sept,
      Huit,
      Neuf,
      Dix,
      Valet,
      Roi,
      As
    )
  }

  case class Carte(couleur: Couleur, hauteur: Hauteur)

  object Carte {
    val all: Seq[Carte] = for {
      couleur <- Couleur.all
      hauteur <- Hauteur.all
    } yield Carte(couleur, hauteur)
  }

  case class Joueur(nom: String)

  case class EtatDuJeu(tasParJoueur: Map[Joueur, Seq[Carte]], nbPlis: Int)


}

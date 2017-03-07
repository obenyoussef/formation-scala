package bataille

import Models._

import scala.annotation.tailrec
import scala.util.Random


class BatailleFermee {


  // démarre un jeu de bataille entre plusieurs joueurs (2 ou plus)
  // distribue les cartes aléatoirement
  // joue tous les plis jusqu'à ce qu'un des joueurs a gagné toutes les cartes
  // retourne le joueur qui a gagné
  def joue(joueurs: Seq[Joueur]): Joueur = {
    val etatInitial = construitEtatInitial(joueurs)
    val etatFinal = jouePlisJusquaVictoire(etatInitial)
    etatFinal.tasParJoueur.keys.head
  }


  // initialisation
  // ----------------

  // distribue les cartes à travers tous les joueurs
  private def construitEtatInitial(joueurs: Seq[Joueur]): EtatDuJeu = {

    val cartesADistribuerInitial = Carte.all
    val tasParJoueursInitial = Map.empty[Joueur, Seq[Carte]]

    val nbCartesParJoueur = cartesADistribuerInitial.size / joueurs.size

    val (_, tasParJoueurs) =
      joueurs.foldLeft((cartesADistribuerInitial, tasParJoueursInitial)) { case ((cartesADistribuer, tasParJoueur), joueur) =>
        val (tasPourCeJoueur, cartesRestantes) = distribueCartesPourUnJoueur(cartesADistribuer, nbCartesParJoueur)
        (cartesRestantes, tasParJoueur + (joueur -> tasPourCeJoueur))
      }
    EtatDuJeu(tasParJoueurs)
  }




  // params
  // - le tas de cartes à distribuer
  // - combien il faut en distribuer
  // retourne
  // - (les cartes pour le joueur, les cartes restantes)
  private def distribueCartesPourUnJoueur(cartesDispo: Seq[Carte], combien: Int): (Seq[Carte], Seq[Carte]) =
    Random.shuffle(cartesDispo).splitAt(combien)




  // Jeu
  // -----------

  // Enchaine les plis jusqu'à ce que l'un des joueurs ait toutes les cartes
  @tailrec
  private def jouePlisJusquaVictoire(etatDuJeu: EtatDuJeu): EtatDuJeu =
    if (etatDuJeu.tasParJoueur.size == 1) etatDuJeu
    else jouePlisJusquaVictoire(jouePli(etatDuJeu))

  // Effectue la logique du prochain pli
  // Retourne l'état du jeu tel qu'il doit se trouver à l'issue du pli
  //
  // Note : cette méthode par du principe que les joueurs ont tous au moins une carte dans
  // leur tas
  //
  // Note : cette méthode doit virer les joueurs qui n'ont plus de carte à l'issue du pli
  //
  // REGLES : chaque joueur joue la premiere carte de son tas
  // on compare la meilleure carte
  // SI IL Y A UNE MEILLEURE CARTE : le joueur qui a la meilleure carte
  //   ramasse les cartes qui ont été jouées et les replace à la fin de son tas
  // SI IL Y A EGALITE SUR LA MEILLEURE CARTE : ici on va juste dire qu'on annule le pli
  //   et que chaque joueur va juste mélanger son tas
  //
  private def jouePli(etatDuJeu: EtatDuJeu): EtatDuJeu = {

    val joueursParHauteurJouee: Map[Hauteur, Seq[Joueur]] =
      etatDuJeu.tasParJoueur
      // prenons la première carte
      .mapValues(_.head)
      // groupons par hauteur et "inversons" la map
      .groupBy(_._2.hauteur)
      .mapValues(_.keys.toSeq)

    val joueursAvecMeilleureHauteur = joueursParHauteurJouee.maxBy(_._1)._2

    joueursAvecMeilleureHauteur match {
      case Seq(gagnant) =>
        // OK on a un gagnant
        // sortons les cartes jouees des tas
        val cartesJouees = etatDuJeu.tasParJoueur.values.map(_.head)
        // reconstruisons les tas finaux
        EtatDuJeu(
          tasParJoueur = etatDuJeu.tasParJoueur
              // virons la carte jouee
              .mapValues(_.tail)
              .map {
                // pour le gagnant, on lui met les cartes jouees à la fin de son tas
                case (`gagnant`, tas) => (gagnant, tas ++ cartesJouees)
                case other => other
              }
              // Virons les joueurs dont le tas est devenu vide
              .filter(_._2.nonEmpty)
        )
      case _ =>
        // plusieurs gagnants
        // on mélange juste les tas
        EtatDuJeu(
          tasParJoueur = etatDuJeu.tasParJoueur.mapValues { tas =>
            Random.shuffle(tas)
          }
        )
    }
  }


  implicit val hauteurOrdering: Ordering[Hauteur] = Ordering.fromLessThan(isHauteurLessThanOther)

  // Return true si a est une hauteur strictement plus petite que b
  private def isHauteurLessThanOther(a: Hauteur, b: Hauteur): Boolean =
    Hauteur.all.indexOf(a) < Hauteur.all.indexOf(b)




}



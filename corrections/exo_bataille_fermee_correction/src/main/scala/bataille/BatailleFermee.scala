package bataille

import bataille.Models._

import scala.annotation.tailrec
import scala.util.Random


class BatailleFermee {


  // démarre un jeu de bataille entre plusieurs joueurs (2 ou plus)
  // distribue les cartes aléatoirement
  // joue tous les plis jusqu'à ce qu'un des joueurs a gagné toutes les cartes
  // retourne :
  // - (le joueur qui a gagné, combien de plus étaient nécessaire)
  def joue(joueurs: Seq[Joueur]): (Joueur, Int) = {
    val etatInitial = construitEtatInitial(joueurs)
    val etatFinal = jouePlisJusquaVictoire(etatInitial)
    (etatFinal.tasParJoueur.keys.head, etatFinal.nbPlis)
  }


  // initialisation
  // ----------------

  // distribue les cartes à travers tous les joueurs
  private def construitEtatInitial(joueurs: Seq[Joueur]): EtatDuJeu = {
    val cartesAPartager = Random.shuffle(Carte.all)
    val nbCartesParJoueur = cartesAPartager.size / joueurs.size
    val tas = cartesAPartager.grouped(nbCartesParJoueur).toSeq
    EtatDuJeu(tasParJoueur = joueurs.zip(tas).toMap, nbPlis = 0)
  }


  // Jeu
  // -----------

  // Enchaine les plis jusqu'à ce que l'un des joueurs ait toutes les cartes
  @tailrec
  private def jouePlisJusquaVictoire(etatDuJeu: EtatDuJeu): EtatDuJeu = {
    logEtatDuJeu(etatDuJeu)
    if (etatDuJeu.tasParJoueur.size == 1) etatDuJeu
    else jouePlisJusquaVictoire(jouePli(etatDuJeu))
  }

  // Effectue la logique du prochain pli
  // Retourne l'état du jeu tel qu'il doit se trouver à l'issue du pli
  //
  // Note : cette méthode part du principe que les joueurs ont tous au moins une carte dans
  // leur tas
  //
  // Note : cette méthode doit virer les joueurs qui n'ont plus de carte à l'issue du pli
  //
  // REGLES : chaque joueur joue la premiere carte de son tas
  // on compare la meilleure carte
  // le joueur qui a la meilleure carte ramasse les cartes qui ont été jouées
  // et les replace à la fin de son tas
  //
  // A la fin du pli on remélange le tas de chaque joueur pour contourner
  // le pb des situations qui peuvent tourner en boucle
  //
  private def jouePli(etatDuJeu: EtatDuJeu): EtatDuJeu = {
    val carteJoueeParJoueur = etatDuJeu.tasParJoueur.mapValues(_.head)
    val tasRestantParJoueur = etatDuJeu.tasParJoueur.mapValues(_.tail)
    val joueurGagnant = carteJoueeParJoueur.maxBy(_._2)._1
    val cartesGagnees = carteJoueeParJoueur.values
    EtatDuJeu(
      tasParJoueur =
        tasRestantParJoueur.map {
          // pour le gagnant, on lui met les cartes jouees à la fin de son tas
          case (`joueurGagnant`, tas) => (joueurGagnant, tas ++ cartesGagnees)
          case other => other
        }
        // Virons les joueurs dont le tas est devenu vide
        .filter(_._2.nonEmpty)
        // Remélangeons les tas de tout le monde pour éviter des cas qui peuvent tourner en boucle
        .map { case (joueur, tas) => (joueur, Random.shuffle(tas)) },
      nbPlis = etatDuJeu.nbPlis + 1
    )
  }

  implicit val carteOrdering: Ordering[Carte] =
    Ordering.by { carte =>
      (Hauteur.all.indexOf(carte.hauteur), Couleur.all.indexOf(carte.hauteur))
    }

  // Affiche un résumé de l'état actuel du jeu, pour avoir une idée de où on en est
  // Exemple de ce qui devrait être affiché :
  // Gégé: 10 / Lulu: 31 / Momo: 10
  private def logEtatDuJeu(etatDuJeu: EtatDuJeu): Unit =
    println {
      etatDuJeu.tasParJoueur.toSeq.sortBy(_._1.nom).map { case (joueur, tas) =>
        s"${joueur.nom}: ${tas.size}"
      }.mkString(" / ")
    }


}



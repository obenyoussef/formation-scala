package bataille

import bataille.Models._

class BatailleFermee {


  // démarre un jeu de bataille entre plusieurs joueurs (2 ou plus)
  // distribue les cartes aléatoirement
  // joue tous les plis jusqu'à ce qu'un des joueurs a gagné toutes les cartes
  // retourne :
  // - (le joueur qui a gagné, combien de plus étaient nécessaire)
  def joue(joueurs: Seq[Joueur]): (Joueur, Int) = ???

  // initialisation
  // ----------------

  // distribue les cartes à travers tous les joueurs
  private def construitEtatInitial(joueurs: Seq[Joueur]): EtatDuJeu = ???

  // Jeu
  // -----------

  // Enchaine les plis jusqu'à ce que l'un des joueurs ait toutes les cartes
  private def jouePlisJusquaVictoire(etatDuJeu: EtatDuJeu): EtatDuJeu = ???
  
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
  private def jouePli(etatDuJeu: EtatDuJeu): EtatDuJeu = ???

  implicit val carteOrdering: Ordering[Carte] =
    Ordering.by { carte =>
      (Hauteur.all.indexOf(carte.hauteur), Couleur.all.indexOf(carte.hauteur))
    }

  // Affiche un résumé de l'état actuel du jeu, pour avoir une idée de où on en est
  // Exemple de ce qui devrait être affiché :
  // Gégé: 10 / Lulu: 31 / Momo: 10
  private def logEtatDuJeu(etatDuJeu: EtatDuJeu): Unit = ???




}



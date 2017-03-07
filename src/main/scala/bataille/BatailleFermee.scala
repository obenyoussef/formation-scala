package bataille

import bataille.Models._


class BatailleFermee {


  // démarre un jeu de bataille entre plusieurs joueurs (2 ou plus)
  // distribue les cartes aléatoirement
  // joue tous les plis jusqu'à ce qu'un des joueurs a gagné toutes les cartes
  // retourne le joueur qui a gagné
  def joue(joueurs: Seq[Joueur]): Joueur = ???

  // initialisation
  // ----------------

  // distribue les cartes à travers tous les joueurs
  private def construitEtatInitial(joueurs: Seq[Joueur]): EtatDuJeu = ???



  // params
  // - le tas de cartes à distribuer
  // - combien il faut en distribuer
  // retourne
  // - (les cartes pour le joueur, les cartes restantes)
  private def distribueCartesPourUnJoueur(cartesDispo: Seq[Carte], combien: Int): (Seq[Carte], Seq[Carte]) = ???




  // Jeu
  // -----------

  // Enchaine les plis jusqu'à ce que l'un des joueurs ait toutes les cartes
  private def jouePlisJusquaVictoire(etatDuJeu: EtatDuJeu): EtatDuJeu = ???

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
  private def jouePli(etatDuJeu: EtatDuJeu): EtatDuJeu = ???

  implicit val hauteurOrdering: Ordering[Hauteur] = ???

  // Return true si a est une hauteur strictement plus petite que b
  private def isHauteurLessThanOther(a: Hauteur, b: Hauteur): Boolean = ???




}



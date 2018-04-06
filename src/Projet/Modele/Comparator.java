package Projet.Modele;

import Projet.Metier.Enseignant;

public class Comparator {

    public abstract class compareEns implements java.util.Comparator<Enseignant> {

        public int compare(Enseignant c1, Enseignant c2) {
            if (!c1.getNom().equals(c2.getNom())) {
                return c1.getNom().compareTo(c2.getNom());
            }
            if (!c1.getPrenom().equals(c2.getPrenom())) {
                return c1.getPrenom().compareTo(c2.getPrenom());
            }
            return c1.getMatricule().compareTo(c2.getMatricule());

        }
    }
}

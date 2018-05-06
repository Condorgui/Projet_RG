/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import Projet.Metier.Enseignant;
import Projet.Metier.Classe;
import Projet.Metier.Attribution;
import java.util.*;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseModele {

    /**
     * Liste de tous les enseignants
     */
    public List<Enseignant> tousLesEns;

    /**
     * Liste de toutes les classes
     */
    public List<Classe> toutesLesClasses;

    /**
     * Listes des attributions
     */
    public List<Attribution> toutesLesAttributions;

    /**
     * constructeur par défaut
     */
    private static ClasseModele instance = null;

    public static ClasseModele getInstance() {
        if (instance == null) {
            return instance = new ClasseModele();
        } else {
            return instance;
        }
    }

    public ClasseModele() {

        tousLesEns = new ArrayList<>();
        toutesLesClasses = new ArrayList<>();
        toutesLesAttributions = new ArrayList<>();

    }

    /**
     * Méthode ajouterClasse Ajoute une classe à la liste des classes
     *
     * @param c la classe à ajouter
     * @return le résultat de l'ajout
     */
    public String ajouterClasse(Classe c) {
        if (c == null) {
            return "Classe inexistante";
        }
        if (toutesLesClasses.contains(c)) {
            return "Classe existe déjà";
        } else {
            toutesLesClasses.add(c);
        }
        return "Création de la classe effectuée";
    }

    /**
     * Méthode ajouterEnseignant Permet d'ajouter un enseignant à la liste des
     * enseignants
     *
     * @param e l'enseignant à ajouter
     * @return le résultat de l'ajout
     */
    public String ajouterEnseignant(Enseignant e) {
        if (e == null) {
            return "Enseignant inexistant";
        }
        if (tousLesEns.contains(e)) {
            return "Enseignant déjà crée";
        }
        tousLesEns.add(e);
        return "Ajout de l'enseignant";
    }

    /**
     * Méthode ajouterAttribution Permet d'ajouter une attribution
     *
     * @param a l'attribution à ajouter
     * @return le résultat de l'ajout
     */
    public String ajouterAttribution(Attribution a) {
        if (a == null) {
            return "Attribution inexistante";
        }
        Enseignant e = a.getEnseignant(); 
        e.getTitulaire();
        
        if (toutesLesAttributions.contains(a) || toutesLesAttributions.contains(e)) {
            return "Attribution déjà créée";
        }
        toutesLesAttributions.add(a);
        return "Ajout de l'attribution";
    }

    /**
     * Méthode getClasse Permet de retrouver une classe
     *
     * @param cl la classe à retrouver (son sigle)
     * @return la classe trouvée ou null
     */
    public Classe getClasse(Classe cl) {

        int i = toutesLesClasses.indexOf(cl);
        if (i < 0) {
            return null;
        } else {
            return toutesLesClasses.get(i);
        }
    }

    /**
     * Méthode getEnseignant Permet de retrouver un enseignant
     *
     * @param aRech l'enseignant à retrouver (son matricule)
     * @return l'enseignant trouvé ou null
     */
    public Enseignant getEnseignant(Enseignant aRech) {
        int i = tousLesEns.indexOf(aRech);
        if (i < 0) {
            return null;
        } else {
            return tousLesEns.get(i);
        }
    }

    /**
     * Méthode ModifyC Permet de modifier une classe
     *
     * @param nvClasse la nouvelle classe
     * @param tmpC la classe qui sera remplacée par nvClasse
     * @return l'état de la modification
     */
    public String modifyC(Classe nvClasse, Classe tmpC) {

        int i = toutesLesClasses.indexOf(tmpC);
        if (i < 0) {
            return "Classe introuvable";

        } else {
            toutesLesClasses.set(i, nvClasse);
        }
        return "Modification effectuée";

    }

    /**
     * Méthode modifyE Permet de modifier un enseignant
     *
     * @param nvEns le nouvel enseignant
     * @param tmpE l'enseignant qui sera remplacé par nvEns
     * @return l'état de la modification
     */
    public String modifyE(Enseignant nvEns, Enseignant tmpE) {
        int i = tousLesEns.indexOf(tmpE);
        if (i < 0) {
            return "Enseignant introuvable";

        } else {
            tousLesEns.set(i, nvEns);
        }
        return "Modification effectuée";

    }

    /**
     * Méthode deleteE Permet de supprimer un enseignant de la liste Enseignant
     *
     * @param es l'enseignant à supprimer
     * @return le résultat de la suppression
     */
  public String deleteE(Enseignant es) {
        int i = tousLesEns.indexOf(es);
        if (i < 0) {
            return "Enseignant introuvable";

        }
        Enseignant e = tousLesEns.get(i);
        for (Enseignant f : tousLesEns) {
            Classe a = e.getRemplacant();
            Classe b = e.getTitulaire();
            if (!e.equals(a) && !e.equals(b)) {
                
                return "Supprimez d'abord les attributions de cet enseignant";

            }
        }

        tousLesEns.remove(i);

        return "Suppression effectuée";
    }



    /**
     * Méthode deleteCl Permet de supprimer une classe
     *
     * @param cl la classe à supprimer
     * @return le résultat de la suppression
     */
    public String deleteCl(Classe cl) {
        int i = toutesLesClasses.indexOf(cl);
        if (i < 0) {
            return "Classe introuvable";
        }
        Classe c = toutesLesClasses.get(i);

        for (Enseignant e : tousLesEns) {
            Classe c1 = e.getTitulaire();
            Classe c2 = e.getRemplacant();
            if (!c.equals(cl) && !c.equals(c2)) {
            } else {
                return "Supprimez d'abord les attributions de l'enseignant";
            }
        }
        toutesLesClasses.remove(i);
        return "Suppression effectuée";
    }

    /**
     * Méthode deleteA permet de supprimer une attribution
     *
     * @param aDel l'attribution à supprimer La méthode vérifie que
     * l'attribution existe Si un enseignant est titulaire ou remplacant de
     * l'attribution -> réinitialise le statut de titulaire ou remplacant de
     * l'enseignant à null
     * @return le résultat de la suppression
     */
    public String deleteA(Attribution aDel) {

        int i = toutesLesAttributions.indexOf(aDel);

        if (i < 0) {
            return "Attribution à supprimer introuvable";
        } else {
            Enseignant e = new Enseignant();
            e = toutesLesAttributions.get(i).getEnseignant();

            if (e.getTitulaire() != null) {
                e.setTitulaire(null);
                e.getTitulaire();
            }
            if (e.getRemplacant() != null) {
                e.setRemplacant(null);
                e.getRemplacant();
            }
            toutesLesAttributions.remove(i);
        }
        return "Suppression effectuée";
    }

    /**
     * Méthode tousEns
     *
     * @return la liste tousLesEns
     */
    public List<Enseignant> tousEns() {
        return tousLesEns;

    }

    /**
     * Méthode toutesClasses
     *
     * @return la liste toutesLesClasses
     */
    public List<Classe> toutesClasses() {

        return toutesLesClasses;

    }

    /**
     * Méthode toutesLesAttributions
     *
     * @return la liste toutesLesAttributions
     */
    public List<Attribution> toutesLesAttributions() {

        return toutesLesAttributions;
    }

    /**
     * Méthode getAttribution
     *
     * @param aRech l'attribution recherchée (le sigle de la classe, le
     * matricule de l'enseignant)
     * @return l'attribution ou null
     */
    public Attribution getAttribution(Attribution aRech) {
        int i = toutesLesAttributions.indexOf(aRech);
        if (i < 0) {
            return null;
        } else {
            return toutesLesAttributions.get(i);
        }
    }

    /**
     * Méthode modifyA
     *
     * @param nvA la nouvelle attribution
     * @param tmpA l'ancienne attribution à modifier
     * @return le résultat de la modification
     */
    public String modifyA(Attribution nvA, Attribution tmpA) {
        int i = toutesLesAttributions.indexOf(tmpA);
        if (i < 0) {
            return null;

        } else {
            toutesLesAttributions.set(i, nvA);
        }
        return "Modification effectuée";

    }

    /**
     * Méthode populate permet de remplir les list Enseignants et Classes
     * automatiquement
     */
    public void populate() {
        tousLesEns.addAll(Arrays.asList(
                new Enseignant("MAT007", "Rigaux", "Guillaume"),
                new Enseignant("MAT123", "Crombez", "Rodrigue"),
                new Enseignant("MAT456", "Lété", "Quentin"),
                new Enseignant("MAT100", "Urbain", "Jérome"),
                new Enseignant("MAT111", "Rigaux", "Baptiste"))
        );
        try {
            toutesLesClasses.addAll(Arrays.asList(
                    new Classe.ClasseBuilder().setSigle("MA123").setAnnee(4).setOrientation("eee").build(),
                    new Classe.ClasseBuilder().setSigle("BIO4").setAnnee(3).setOrientation("Biologie").build(),
                    new Classe.ClasseBuilder().setSigle("PHYS1").setAnnee(1).setOrientation("Physique").build(),
                    new Classe.ClasseBuilder().setSigle("POO5").setAnnee(6).setOrientation("Progra").build())
            );

        } catch (Exception e) {
            System.out.println("erreur de création " + e);
        }

    }
}

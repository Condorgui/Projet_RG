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
    private final List<Enseignant> tousLesEns;

    /**
     * Liste de toutes les classes
     */
    private final List<Classe> toutesLesClasses;

    /**
     * Listes des attributions
     */
    private final List<Attribution> toutesLesAttributions;

    /**
     * constructeur par défaut
     */
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

    public String ajouterAttribution(Attribution a) {
        if (a == null) {
            return "Attribution inexistante";
        }
        if (toutesLesAttributions.contains(a)) {
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

        } else {
            tousLesEns.remove(i);
        }
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

        } else {
            toutesLesClasses.remove(i);
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

    public List<Attribution> toutesLesAttributions() {

        return toutesLesAttributions;
    }

}

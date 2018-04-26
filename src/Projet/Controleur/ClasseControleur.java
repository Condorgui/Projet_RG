/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Controleur;

import Projet.Metier.Attribution;
import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import Projet.Modele.ClasseModele;

import Projet.Vue.Pvue;
import java.util.List;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseControleur {

    /**
     * Variable cm de type ClasseModele
     */
    private ClasseModele cm;

    /**
     * Variable pv de type Pvue
     */
    private Pvue pv;

    /**
     * Constructeur par défaut
     */
    public ClasseControleur() {

    }

    /**
     * Constructeur paramétré
     *
     * @param cm le modèle
     * @param pv la vue
     */
    public ClasseControleur(ClasseModele cm, Pvue pv) {

        this.cm = cm;
        this.pv = pv;
    }

    /**
     * Méthode gestion
     *
     */
    public void gestion() {

        int ch = 0;
        do {
            try {

                ch = pv.menuPrincipal();

                switch (ch) {
                    case 1:
                        ajoutEnseignant();
                        break;
                    case 2:
                        ajoutClasse();
                        break;
                    case 3:
                        modificationE();
                        //SuppE();
                        break;

                    case 4:
                        modificationC();
                        //  SuppC(); 
                        break;
                    case 5:

                        rechEnseignant();
                        break;
                    case 6:

                        rechClasse();
                        break;
                    case 7:
                        affichage();
                        break;

                    case 8:
                        gestionAttribution();

                        break;
                    case 9:
                        pv.affichageMessage("Fin");

                    default:
                        pv.affichageMessage("Choix invalide");
                }

            } catch (Exception e) {
                pv.affichageMessage(e);
            }

        } while (ch != 9);
    }

    /**
     * Méthode ajoutClasse Permet l'ajout d'une classe Appelle le formulaire de
     * création d'une classe de la vue Appelle le formulaire d'ajout d'une
     * classe du modèle
     *
     */
    private void ajoutClasse() {
        Classe cl = pv.newClasse();
        String msg = cm.ajouterClasse(cl);
        //ajouterClasse retourne un message 
        pv.affichageMessage(msg);
        //affiche le message retourné

    }

    /**
     * Méthode ajoutEnseignant Permet l'ajout d'un enseignant Appelle le
     * formulaire de création de la vue Appelle le formulaire d'ajout du
     * controleur
     */
    private void ajoutEnseignant() {
        Enseignant es = pv.newEnseignant();
        String msg = cm.ajouterEnseignant(es);
        pv.affichageMessage(msg);

    }

    /**
     * Méthode gestionAttribution Permet de gérer les attribution ajout d'une
     * attribution modification d'une attribution suppression d'une attribution
     */
    private void gestionAttribution() {

        try {
            int choix = Integer.parseInt(pv.getMessage(""
                    + "1. Ajouter une attribution"
                    + "      2. Modifier une attribution "
                    + "             3. Supprimer une attribution"));

            if (choix == 1) {

                Attribution a = pv.newAttribution(cm.toutesClasses(), cm.tousEns());
                String msg = cm.ajouterAttribution(a);
                pv.affichageMessage(msg);

            }
            if (choix == 2) {
                Attribution a = pv.rechAttribution();
                pv.affichageMessage(cm.getAttribution(a));

            }
            if (choix == 3) {

                Attribution a = pv.rechAttribution();
                pv.affichageMessage(cm.getAttribution(a));
                // pv.affichageMessage(a);
                pv.affichageMessage(cm.deleteA(a));
            }
        } catch (Exception e) {
            pv.affichageMessage("Entrez un nombre");
        }
    }

    /**
     * Méthode rechClasse Appelle le formulaire de recherche d'une classe de la
     * vue Affiche le résultat de la recherche
     */
    private void rechClasse() {
        Classe cl = pv.rechClasse();
        pv.affichageMessage(cm.getClasse(cl));
    }

    /**
     * Méthode rechEnseignant Appelle le formulaire de recherche d'un enseignant
     * de la vue Affiche le résultat de la recherche
     */
    private void rechEnseignant() {
        Enseignant es = pv.rechEnseignant();
        pv.affichageMessage(cm.getEnseignant(es));
    }

    /**
     * Méthode modificationC Modifie ou supprime une classe Appelle le
     * formulaire de recherche d'une classe sur base du sigle Appelle le
     * formulaire de modification (modifyC) ou suppression (deleteCl)
     *
     */
    private void modificationC() {

        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Modification de la classe"
                + "       2. Suppression de la classe"));

        Classe cRech = pv.rechClasse();
        //  Classe test = cm.getClasse(pv.rechClasse());
        Classe tmpC = cm.getClasse(cRech);
        switch (choix) {
            case 1:
                pv.affichageMessage("Modification de la classe");
                pv.affichageMessage(tmpC);
                Classe nvClasse = pv.newClasse();
                pv.affichageMessage(cm.modifyC(nvClasse, tmpC));
                break;
            case 2:
                pv.affichageMessage("--- Suppression de la classe --- ");
                pv.affichageMessage(tmpC);
                pv.affichageMessage(cm.deleteCl(tmpC));
                break;
            default:
                pv.affichageMessage("Entrez un choix valide ");
                break;
        }

    }

    /*  private void SuppE() {

        Enseignant aDel = pv.rechEnseignant();
        Enseignant sec = cm.getEnseignant(aDel);
        pv.affichageMessage(sec);
        pv.affichageMessage(cm.deleteE(sec));
    }
     */
 /*  private void SuppC() {

        Classe aDel = pv.rechClasse();
        Classe sec = cm.getClasse(aDel);
        pv.affichageMessage(sec);
        pv.affichageMessage(cm.deleteC(sec));
    }
     */
    
    
    /**
     * Méthode modificationE Modifie ou supprime un enseignant Appelle le
     * formulaire de recherche d'un enseignant sur base du matricule Appelle le
     * formulaire de modification (modifyE) ou suppression (deleteE)
     *
     */
    private void modificationE() {
        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Modification de l'enseignant"
                + "      2. Suppression de l'enseignant"));
        Enseignant eRech = pv.rechEnseignant();
        Enseignant tmpE = cm.getEnseignant(eRech);
        switch (choix) {
            case 1:
                pv.affichageMessage(" --- Modification de l'enseignant ---");
                pv.affichageMessage(tmpE);
                Enseignant nvEns = pv.newEnseignant();
                pv.affichageMessage(cm.modifyE(nvEns, tmpE));
                break;
            case 2:
                pv.affichageMessage("--- Suppression de l'enseignant ---");
                pv.affichageMessage(tmpE);
                pv.affichageMessage(cm.deleteE(tmpE));
                break;
            default:
                pv.affichageMessage("Entrez un choix valide");
                break;
        }

    }

    /**
     * Méthode affichage Affichage les enseignant ou les classes récupère les
     * enseignants dans une liste et affiche son contenu récupère les classes
     * dans une liste et affiche son contenu
     *
     */
    private void affichage() {

        try {
            int choix = Integer.parseInt(pv.getMessage(""
                    + "1. Affichage des enseignants"
                    + "      2. Affichage des classes"
                    + "             3. Affichage des attributions"));

            if (choix == 1) {

                List<Enseignant> le = cm.tousEns();
                pv.affichageMessage(le);

            }
            if (choix == 2) {
                List<Classe> lc = cm.toutesClasses();
                pv.affichageMessage(lc);
            }
            if (choix == 3) {
                List<Attribution> la = cm.toutesLesAttributions();
                pv.affichageMessage(la);
            }
        } catch (Exception e) {
            pv.affichageMessage("Entrez un nombre");
        }

    }


}



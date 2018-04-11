/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue;

import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Pvue {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Menu principal
     *
     * @return le choix de la section du menu
     */
    public int menuPrincipal() {

        List<String> menu = new ArrayList<>(Arrays.asList(
                " Ajout Enseignant",
                " Ajout Classe",
                " Modif/suppression des enseignants",
                " Modif/suppression des classes",
                " Recherche Enseignant",
                " Recherche d'une classe",
                " Affichage",
                " Gérer les attributions",
                //"__8. Affichage Enseignants", 
                //"__9. Affichage Classes",
                "Quitter"));
        affichageListe(menu);

        int choix;
        do {
            String ch1 = getMessage("Choix ?");
            choix = Integer.parseInt(ch1);
            if (choix > 0 && choix <= menu.size()) {
                break;
            } else {
                affichageMessage("Recommencez");
            }

        } while (true);
        return choix;
    }

    /**
     * Méthode newClasse encode les informations d'une classe
     *
     * @return c = la classe créée sur base de l'encodage
     */
    public Classe newClasse() {
        String sigle = getMessage("Entrez le sigle : ");
        String orientation = getMessage("Quelle est l'orientation ? ");
        String annee = getMessage("Quelle est l'année de la classe : ");
        int annee2 = Integer.parseInt(annee);

        Classe c = new Classe(sigle, annee2, orientation);

        return c;
    }

    /**
     * Méthode newEnseignant encode les informations d'un enseignant
     *
     * @return e = l'enseignant crée sur base de l'encodage
     */
    public Enseignant newEnseignant() {

        String nom = getMessage("Entrez le nom : ");
        String prenom = getMessage("Entrez le prénom : ");
        String matricule = getMessage("Entrez son matricule : ");
        Enseignant e = new Enseignant(matricule, nom, prenom);

        return e;
    }

    /**
     * Méthode affClasses Affiche les informations d'une classe == toString
     * Classe
     *
     * @param c = la classe à afficher
     */
    public void affClasses(Classe c) {

        affichageMessage("Classe : " + c.getSigle());
        affichageMessage("C'est une classe de " + c.getAnnee() + " ère/ème  année");
        //préciser classe 
        affichageMessage("Classe d'orientation : " + c.getOrientation());

        affichageMessage("L'enseignant de cette classe :" + c.getE());

    }

    /**
     * Méthode affEnseignant Affiche les informations d'un enseignant ==
     * toString Enseignant
     *
     * @param e = l'enseignant à afficher
     */
    public void affEnseignant(Enseignant e) {
        affichageMessage("Nom de l'enseignant :" + e.getNom());
        affichageMessage("Prénom de l'enseignant :" + e.getPrenom());
        affichageMessage("Matricule  :" + e.getMatricule());

    }

    /**
     * Méthode getMessage permet à l'utilisateur d'encoder le message
     *
     * @return le message entré
     */
    public String getMessage() {
        String msg = sc.nextLine();
        return msg;
    }

    /**
     * Méthode afficheMessage permet d'afficher le message reçu en paramètre
     *
     * @param msg == message à afficher
     */
    public void affichageMessage(Object msg) {
        System.out.println(msg);
    }

    /**
     * Surcharge de getMessage Permet d'afficher un message et d'en encoder un
     *
     * @param msg le message à afficher
     * @return la méthode getMessage pour l'encodage
     */
    public String getMessage(String msg) {
        affichageMessage(msg);
        return getMessage();
    }

    /**
     * Méthode affichageListe Permet d'afficher une liste
     *
     * @param liste la liste à afficher
     */
    public void affichageListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affichageMessage((i++) + "." + o);
        }
    }

    /**
     * Méthode rechClass Permet de rechercher une classe sur base de son sigle
     *
     * @return cRech == la classe trouvée
     */
    public Classe rechClasse() {

        String sigle = getMessage("Quel est le sigle à rechercher ? ");
        Classe cRech = new Classe(sigle);
        return cRech;

    }

    /**
     * Méthode rechEnseignant Permet de retrouver un enseignant sur base de son
     * matricule
     *
     * @return eRech == l'enseignant trouvé
     */
    public Enseignant rechEnseignant() {

        String matricule = getMessage("Matricule de l'enseignant à rechercher :");
        Enseignant eRech = new Enseignant(matricule);
        return eRech;
    }

}

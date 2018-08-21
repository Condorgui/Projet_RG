
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launch;

import Projet.Controleur.ClasseControleur;
import Projet.Modele.*;
import Projet.Vue.Pvue;
import java.util.Scanner;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Launcher {

    private final ClasseControleur ctrl;
    private ClasseModele cm;
    private Pvue pv;

    /**
     * Méthode launcher Créer des objets de type vue, modèle, controleur appelle
     * les méthodes populate et gestion du controleur
     *
     * @param mode le mode à afficher (jdbc ou liste)
     */
    public Launcher(int mode) {

        pv = new Pvue();
        cm = new ClasseModele();
                
        switch (mode) {
            case 1:
                cm = new ClasseModele();
                break;
            case 2:
                cm = new ClasseModeleJDBC();
                break;
            default:
                System.out.println("Choisissez un mode");
                System.exit(1);
        }

        ctrl = new ClasseControleur(cm, pv);
        cm.populate();
        ctrl.gestion();
        

        if (cm instanceof ClasseModeleJDBC) {
            ((ClasseModeleJDBC) cm).close();
        }

    }

    /**
     * Lanceur du programme
     *
     * @param args lanceur du main
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1 : SANS BDD / 2 : AVEC BDD");
        int mode = sc.nextInt();
        Launcher launch = new Launcher(mode);
    }

}

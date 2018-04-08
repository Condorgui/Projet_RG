/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launch;

import Projet.Controleur.ClasseControleur;
import Projet.Modele.ClasseModele;
import Projet.Vue.Pvue; 

/**
 *
 * @author Guillaume.Rigaux
 */
public class Launcher {
    
    private ClasseControleur ctrl; 
    private ClasseModele cml; 
    private Pvue pv; 
    
    public Launcher(){
        
        pv = new Pvue(); 
        cml = new ClasseModele(); 
        ctrl = new ClasseControleur(cml,pv);
        ctrl.gestion();
        
    }
    
    public static void main(String[] args) {
        
        Launcher l = new Launcher();
    }
}

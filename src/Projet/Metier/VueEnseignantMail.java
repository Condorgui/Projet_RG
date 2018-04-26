/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Metier; 

/**
 *
 * @author Michel
 */
public class VueEnseignantMail implements Observateur {
    
   String texte="";
    public VueEnseignantMail(){
        
    }
    
    @Override
     public void actualise(String texte){
        this.texte=texte;
         redessine();
     }
     
     public void redessine(){
          System.out.println("===>"+texte+"<====");
     }
}

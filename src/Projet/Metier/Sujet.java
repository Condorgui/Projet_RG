package Projet.Metier;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michel
 */
import java.util.*;
public abstract class Sujet {
    protected ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
    public void ajoute(Observateur o){
        observateurs.add(o);
    }
    
    public void retire(Observateur o){
        observateurs.remove(o);
    }
    
    public void notifie(String texte){
        for(Observateur o:observateurs){
            o.actualise(texte);
        }
    }
}

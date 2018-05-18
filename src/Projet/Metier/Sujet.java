package Projet.Metier;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**

 */
import java.util.*;

/**
 *
 * @author guill
 */
public abstract class Sujet {
    
    /**
     *
     */
    protected ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
    
    /**
     *
     * @param o
     */
    public void ajoute(Observateur o){
        observateurs.add(o);
    }
    
    /**
     *
     * @param o
     */
    public void retire(Observateur o){
        observateurs.remove(o);
    }
    
    /**
     *
     * @param texte
     */
    public void notifie(String texte){
        observateurs.forEach((o) -> {
            o.actualise(texte);
        });
    }
}

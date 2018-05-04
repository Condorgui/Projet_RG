/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import myconnections.DBConnection;
import Projet.Metier.*;

/**
 *
 * @author guill
 */
public class ClasseModeleJDBC extends ClasseModele {

    Connection dbconnect;
//Connexion à la BDD

    /**
     *
     */
    public ClasseModeleJDBC() {
        dbconnect = DBConnection.getConnection();
        if (dbconnect == null) {
            System.err.println("erreur de connexion => arrêt du pgm");
            //err = canal d'affichage des erreurs, affichera en rouge
            System.exit(1);
        }
    }

    /**
     *
     */
    public void close() {
        try {
            dbconnect.close();
        } catch (Exception e) {
            System.err.println("erreur lors de la fermeture de la connexion " + e);
        }
    }

    @Override
    public void populate() {
        //ne rien faire car données déjà présentes dans DB
    }

    /**
     *
     * @param mode
     * @return
     */
    public List<Enseignant> tousEns(int mode) {
        //mode = soit tri par numéro chassis soit par prix soit par prenom
        String critere = "";
        switch (mode) {
            case 1:
                critere = "order by nom";
                break;
            case 2:
                critere = "order by matricule";
                break;
            case 3:
                critere = "order by prenom";
                break;

        }
        String query = "select * from ENSEIGNANT " + critere;
        List<Enseignant> le = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                String matricule = rs.getString(1);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                /* int km = rs.getInt(5);
                int annee = rs.getInt(6);
                double prix = rs.getDouble(7);
                 */

                Enseignant e = new Enseignant(matricule, nom, prenom);

                le.add(e);
            }
        } catch (SQLException e) {
            System.err.println("erreur lors de la recherche de l'enseignant " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de statement " + e);
            }
        }
        return le;
    }

    /**
     *
     * @param mode
     * @return
     */
    public List<Classe> toutesLesClasses(int mode) {
        //mode = soit tri par sigle soit par année, soit orientation
        String critere = "";
        switch (mode) {
            case 1:
                critere = "order by sigle";
                break;
            case 2:
                critere = "order by annee";
                break;
            case 3:
                critere = "order by orientation";
                break;

        }
        String query = "select * from CLASSE " + critere;
        List<Classe> lc = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                String sigle = rs.getString(1);
                int annee = rs.getInt(2);
                String orientation = rs.getString(3);
                Classe c = new Classe(sigle, annee, orientation);

                lc.add(c);
            }
        } catch (SQLException e) {
            System.err.println("erreur lors de la recherche de la classe " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de statement " + e);
            }
        }
        return lc;
    }

    /**
     *
     * @param mode
     * @return
     */
    public List<Attribution> toutesLesAttributions(int mode) {
        //mode = tri
        String critere = "";
        switch (mode) {
            case 1:
                critere = "order by matricule"; //tri sur matricule de l'enseignant
                break;
            case 2:
                critere = "order by sigle"; //tri sur le sigle de la classe
                break;         
        }
        String query = "select * from ATTRIBUTION " + critere;
        List<Attribution> la = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                
                int matricule = rs.getInt(1); 
                String sigle = rs.getString(2); ;
                
               

                la.add(a);
            }
        } catch (SQLException e) {
            System.err.println("erreur lors de la recherche de la classe " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de statement " + e);
            }
        }
        return la;
    }

}

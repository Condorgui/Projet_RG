/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

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
        } catch (SQLException e) {
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
                Classe classe = null;

                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
                try {
                    classe = c.build();

                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }

                lc.add(classe);
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

    @Override
    public Enseignant getEnseignant(Enseignant aRech) {
        //Faire switch pour recherche sur 2 critères
        String query = "SELECT * FROM ENSEIGNANT WHERE MATRICULE = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, aRech.getMatricule());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(1);
                String prenom = rs.getString(2);
                //     String cl_titulaire = rs.getString(3);
                String matricule = rs.getString(4);
                //     String cl_remplacant = rs.getString(5);
                Enseignant e = new Enseignant(nom, prenom, matricule);
                return e;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur de recherche de l'enseignant " + e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
            }
        }

    }

    public Classe getClasse(Classe aRech) {
        //Faire switch pour recherche sur 2 critères
        Classe classe = null; 
        String query = "SELECT * FROM CLASSE WHERE SIGLE = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, aRech.getSigle());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString(1);
                int annee = rs.getInt(2);
                //     String cl_titulaire = rs.getString(3);
                String orientation = rs.getString(3);
                //     String cl_remplacant = rs.getString(5);
                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
                try {
                    classe = c.build();
                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }
                return classe;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur de recherche de la classe " + e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
            }
        }

    }

}

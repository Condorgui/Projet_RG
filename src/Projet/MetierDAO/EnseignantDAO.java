package Projet.MetierDAO;

/**
 * classe de mappage poo-relationnel contact
 *
 * @author Nagy Valentin
 * @version 1.0
 * @see Contact
 */
import Projet.Metier.Enseignant;
import java.sql.*;
import java.util.*;

public class EnseignantDAO extends DAO<Enseignant> {

    /**
     * création d'un contact sur base des valeurs de son objet métier
     *
     * @throws Exception erreur de création
     * @param obj contact à créer
     * @return contact créé
     */
    @Override
    public Enseignant create(Enseignant obj) throws Exception {
        CallableStatement cstmt = null;
        try {
            String req = "call PROJETJAVA_CREATECONTACT(?,?,?,?)";
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, obj.getNom());
            cstmt.setString(3, obj.getPrenom());
            cstmt.setString(4, obj.getMatricule());
            cstmt.executeUpdate();
            int id_enseignant = cstmt.getInt(1);
            return read(id_enseignant);

        } catch (Exception e) {

            throw new Exception("Erreur de création " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * récupération des données d'un contact sur base de son identifiant
     *
     * @throws Exception code inconnu
     * @param id_contact identifiant du contact
     * @return contact trouvé
     */
    @Override
    public Enseignant read(String matricule) throws Exception {

        String req = "{?=call PROJETJAVA_READCONTACT(?)}";

        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, id_contact);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            if (rs.next()) {
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String datenaissance = rs.getString(4);
                String ville = rs.getString(5);
                String tel = rs.getString(6);
                String mail = rs.getString(7);
                return new Contact(id_contact,nom,prenom,datenaissance,ville,tel,mail);

            } else {
                throw new Exception("Code inconnu");
            }

        } catch (Exception e) {

            throw new Exception("Erreur de lecture " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }
    
 /**
 * méthode statique permettant de récupérer le contact associé au numéro de téléphone
 * @param tel téléphone recherché
 * @return contact
 * @throws Exception téléphone inconnu
 */
   
   public Enseignant readtel(String tel) throws Exception {

        String req = "{?=call PROJETJAVA_READCONTACTTEL(?)}";

        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tel);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            if (rs.next()) {
                int id_contact = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String date = rs.getString(4);
                String ville = rs.getString(5);
                String mail = rs.getString(7);
                
                return new Contact(id_contact,nom, prenom, date, ville, tel, mail);

            } else {
                throw new Exception("Code inconnu");
            }

        } catch (Exception e) {

            throw new Exception("Erreur de lecture " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * mise à jour des données du contact sur base de son identifiant
     *
     * @return Contact
     * @param obj contact à mettre à jour
     * @throws Exception erreur de mise à jour
     */
    @Override
    public Enseignant update(Contact obj) throws Exception {
        CallableStatement cstmt = null;

        try {
            String req = "call PROJETJAVA_UPDATECONTACT(?,?,?,?,?,?,?)";
            cstmt = dbConnect.prepareCall(req);
            PreparedStatement pstmt = dbConnect.prepareStatement(req);
            cstmt.setInt(1, obj.getIdcontact());
            cstmt.setString(2, obj.getNom());
            cstmt.setString(3, obj.getPrenom());
            cstmt.setString(4, obj.getDatenaissance());
            cstmt.setString(5, obj.getVille());
            cstmt.setString(6, obj.getTel());
            cstmt.setString(7, obj.getMail());
            cstmt.executeUpdate();
            return read(obj.getIdcontact());

        } catch (Exception e) {

            throw new Exception("Erreur de mise à jour " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * effacement du contact sur base de son identifiant
     *
     * @throws Exception erreur d'effacement
     * @param obj contact à effacer
     */
    @Override
    public void delete(Enseignant obj) throws Exception {

        CallableStatement cstmt = null;
        try {
            String req = "call PROJETJAVA_DELETECONTACT(?)";
            cstmt = dbConnect.prepareCall(req);
            cstmt.setInt(1, obj.getIdcontact());
            cstmt.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erreur d'effacement " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * récupération des données de tous les contacts
     *
     * @throws Exception code inconnu
     * @return contact trouvé
     */
    
    @Override
    public ArrayList<Enseignant> read_all() throws Exception {
        String req = "{?=call PROJETJAVA_READALLCONTACT()}";
        boolean flag = false;
        ArrayList<Contact> liste_contact = new ArrayList();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            flag = true;
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs=(ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                int id_contact = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String datenaissance = rs.getString(4);
                String ville = rs.getString(5);
                String tel = rs.getString(6);
                String mail = rs.getString(7);
                liste_contact.add(new Contact(id_contact,nom,prenom,datenaissance,ville,tel,mail));

            }
            if (!flag) {
                throw new Exception("aucun contact");
            } else {
                return liste_contact;
            }

        } catch (Exception e) {

            throw new Exception("Erreur de lecture " + e.getMessage());
        } finally {//effectué dans tous les cas 
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

}

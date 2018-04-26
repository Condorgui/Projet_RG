package Projet.MetierDAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {

    protected Connection dbConnect;

    /**
     * méthode permettant d'injecter la connexion à la DB venue de l'application
     * principale
     *
     * @param nouvdbConnect connexion à la base de données
     */
    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id identifiant de l'objet recherché
     * @return T objet trouvé
     * @throws Exception objet non trouvé
     */
    public abstract T read(int id) throws Exception;

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj objet à créer
     * @return T objet créé
     * @throws Exception exception de création
     */
    public abstract T create(T obj) throws Exception;

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj objet à mettre à jour
     * @throws Exception exception mise à jour
     * @return T objet mis à jour
     */
    public abstract T update(T obj) throws Exception;

    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj objet à effacer
     * @throws Exception exception d'effacement
     */
    public abstract void delete(T obj) throws Exception;

    /**
     * Permet de récupérer tous les objets 
     *
     * @return T objet trouvé
     * @throws Exception objet non trouvé
     */
    public abstract ArrayList<T> read_all() throws Exception;
}

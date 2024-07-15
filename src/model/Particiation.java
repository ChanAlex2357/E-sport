package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class Particiation {
    private int idParticipation;
    private int idJoueur;
    private int idTournoi;
    private int idState;
    private String state;
    public Particiation(int idJoueur,int idTournoi) throws ClassNotFoundException, SQLException{
        setIdJoueur(idJoueur);
        setIdTournoi(idTournoi);
        getId();
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
/// SQL GETTERS
private void getId() throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    try {
    /// Preparation du statement
        pStatement = connection.prepareStatement("select * from Particiation Where idjoueur = ? AND idtournoi = ?");
        pStatement.setInt(1, getIdJoueur());
        pStatement.setInt(2, getIdTournoi());
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idParticipation");
            setIdParticipation(id);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally{
        DataAcces.dispose(rSet, pStatement, connection);
    }
}

static public List<Tournoi> getTournoisParticipation(int idjoueur) throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    List<Tournoi> list = new ArrayList<>();
    try {
        pStatement = connection.prepareStatement("select * from get_tournois_pour_joueur(?)");
        pStatement.setInt(1, idjoueur);
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idTournoi");
            final String nom = rSet.getString("nomTournoi");
            final Date date = rSet.getDate("dateTournoi");
            final int duree = rSet.getInt("duree");
            final String lieu = rSet.getString("lieuTournoi");
            final int idJeux = rSet.getInt("idJeux");
            final String p_status = rSet.getString("participationstatus");

            Tournoi tournoi = new Tournoi(id, nom, date, duree, lieu, idJeux,p_status);
            list.add( tournoi );
        }
    } catch (Exception e) {
        System.out.println("Probleme getAll Tournoi : ");
        e.printStackTrace();
    }
    finally{
        DataAcces.dispose(rSet, pStatement, connection);
    }
    return list;
}

/// CRUD

    public void save() throws ClassNotFoundException , SQLException{ 
        Connection connection = DataAcces.getConnection();
        /// Preparation de la requete avec un state registred
        setIdState(1);
        PreparedStatement pStatement = connection.prepareStatement("insert into Participation values (DEFAULT,?,?,?)");
        /// Insertion des valeurs
        pStatement.setInt(1, getIdJoueur());
        pStatement.setInt(2, getIdTournoi());
        pStatement.setInt(3, getIdState());
        /// Enregistrement dans la base
        try {
            pStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        }
        finally {
            DataAcces.dispose(null, pStatement, connection);
        }
    }
    public void update() throws ClassNotFoundException , SQLException{ 
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("UPDATE Participation setIdState=?");
        /// Insertion des valeurs
        pStatement.setInt(1, getIdState());
        /// Enregistrement dans la base
        try {
            pStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        }
        finally {
            DataAcces.dispose(null, pStatement, connection);
        }
    }
}

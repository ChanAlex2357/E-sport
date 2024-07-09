package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Particiation {
    private int idParticipation;
    private int idJoueur;
    private int idTournoi;
    private int idState;
    private String state;
    public Particiation(int idJoueur,int idTournoi){
        setIdJoueur(idJoueur);
        setIdTournoi(idTournoi);
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

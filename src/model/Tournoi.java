package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tournoi {
    int idTournoi;
    String nomTournoi;
    Date dateTournoi;
    int duree;
    String lieuTournoi;
    int idJeux;
    public Tournoi(int id , String nom , Date date , int duree , String lieu , int idJeux) {
        setIdTournoi(id);
        setNomTournoi(nom);
        setDateTournoi(date);
        setDuree(duree);
        setLieuTournoi(lieu);
        setIdJeux(idJeux);
    }
    public Tournoi(int id) throws ClassNotFoundException, SQLException{
        setIdTournoi(id);
        getById(getIdTournoi());
    }
    public int getIdTournoi() {
        return idTournoi;
    }
    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }
    public String getNomTournoi() {
        return nomTournoi;
    }
    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }
    public Date getDateTournoi() {
        return dateTournoi;
    }
    public void setDateTournoi(Date dateTournoi) {
        this.dateTournoi = dateTournoi;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getLieuTournoi() {
        return lieuTournoi;
    }
    public void setLieuTournoi(String lieuTournoi) {
        this.lieuTournoi = lieuTournoi;
    }
    public int getIdJeux() {
        return idJeux;
    }
    public void setIdJeux(int idJeux) {
        this.idJeux = idJeux;
    }
    public Jeux getJeux(){
        Jeux j = null;  
        try {
            j = new Jeux(getIdJeux());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

/// sql getteurs
public void getById(int id) throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    try {
    /// Preparation du statement
        pStatement = connection.prepareStatement("select * from Tournoi Where idTournoi = ?");
        pStatement.setInt(1, getIdTournoi());
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final String nom = rSet.getString("nomTournoi");
            final Date date = rSet.getDate("dateTournoi");
            final int duree = rSet.getInt("duree");
            final String lieu = rSet.getString("lieuTournoi");
            final int idJeux = rSet.getInt("idJeux");

            setNomTournoi(nom);
            setDateTournoi(date);
            setDuree(duree);
            setLieuTournoi(lieu);
            setIdJeux(idJeux);
        }
    } catch (Exception e) {
        System.out.println("Probleme getById Tournoi : ");
        e.printStackTrace();
    }
    finally{
        DataAcces.dispose(rSet, pStatement, connection);
    }
}
static public List<Tournoi> getAll() throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    List<Tournoi> list = new ArrayList<>();
    try {
        pStatement = connection.prepareStatement("select * from Tournoi ");
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idTournoi");
            final String nom = rSet.getString("nomTournoi");
            final Date date = rSet.getDate("dateTournoi");
            final int duree = rSet.getInt("duree");
            final String lieu = rSet.getString("lieuTournoi");
            final int idJeux = rSet.getInt("idJeux");

            Tournoi tournoi = new Tournoi(id, nom, date, duree, lieu, idJeux);
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
private static PreparedStatement getStatement( String nom,int idJeux , String debut , String fin ,Connection connection) throws SQLException{
    PreparedStatement pStatement = null;
    String sql = "select * from Tournoi where (1=1) ";
    if (nom != null) {
        sql+= "and (nomtournoi like '%"+nom+"%')";
    }

    if (idJeux > 0) {
        sql += "and (idjeux = ?)";
    }
    
    if (debut != null  && debut != "") {
        sql += "and (datetournoi >= ?)";
    }

    if (fin != null && fin != "") {
        sql += "and (datetournoi <= ?)";
    }
    pStatement = connection.prepareStatement(sql);
    init_statement( idJeux, debut, fin, pStatement);
    System.out.println("sql = "+sql);
    return pStatement;
}
private static void init_statement(int idJeux , String debut , String fin , PreparedStatement pStatement){
    int start = 1;
    if (idJeux > 0) {
        System.out.println("set idJeuxquipe to statement "+start);
        try {
            pStatement.setInt(start, idJeux);
            start++;
        } catch (Exception e) {}
    }
    if (debut != null && debut != "") {
        System.out.println("set age min to statement "+start);
        try {
            pStatement.setDate(start,Date.valueOf(debut));
            start ++;
        } catch (Exception e) {
        }
    }
    if (fin != null && fin != "") {
        System.out.println("set age max to statement "+start);
        try {
            pStatement.setDate(start,Date.valueOf(fin));
            start++;
        }catch(Exception e){
        }
    }
}
static public List<Tournoi> search( String nom_ref , int  id_jeux , String debut , String fin) throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    List<Tournoi> list = new ArrayList<>();
    try {
        pStatement = getStatement(nom_ref, id_jeux, debut, fin, connection);
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idTournoi");
            final String nom = rSet.getString("nomTournoi");
            final Date date = rSet.getDate("dateTournoi");
            final int duree = rSet.getInt("duree");
            final String lieu = rSet.getString("lieuTournoi");
            final int idJeux = rSet.getInt("idJeux");

            Tournoi tournoi = new Tournoi(id, nom, date, duree, lieu, idJeux);
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
    public void update() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("UPDATE Tournoi set nomTournoi = ? , dateTournoi = ?  , lieuTournoi = ? , idJeux = ? , duree = ?  WHERE idTournoi = ? ");
   /// Insertion de valeur  
        pStatement.setString(1, getNomTournoi());
        pStatement.setDate(2, getDateTournoi());
        pStatement.setString(3, getLieuTournoi());
        pStatement.setInt(4, getIdJeux());
        pStatement.setInt(5, getDuree());
        pStatement.setInt(6, getIdTournoi());
   /// Controle d_update
        try {
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" updated on Tournoi ");
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
        }
        finally{
            DataAcces.dispose(null, pStatement, connect);
        }
    }
    public void save() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("insert into Tournoi  values (DEFAULT,?,?,?,?,?)");
    /// insertion des valeurs
        pStatement.setString(1, getNomTournoi());
        pStatement.setDate(2, getDateTournoi());
        pStatement.setInt(3, getDuree());
        pStatement.setString(4, getLieuTournoi());
        pStatement.setInt(5, getIdJeux());
   /// Controle de save
        try {
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" inserted on Tournoi ");
            connect.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connect.rollback();
            throw e;
        }
        finally{
            DataAcces.dispose(null, pStatement, connect);
        }
    }
    public void delete() throws SQLException, ClassNotFoundException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("DELETE From Tournoi Where idTournoi = ?");
        pStatement.setInt(1, getIdTournoi());

        try {
            int nRow = pStatement.executeUpdate();
            System.out.println( nRow+" has been deleted from Tournoi");
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
            e.printStackTrace();
        }
        finally {
            DataAcces.dispose(null, pStatement, connect);
        }
    }
}
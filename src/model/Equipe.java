package model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Equipe {
    int idEquipe;
    String nomEquipe;
    String initial;
    int idCategory;
    public Equipe(int id , String nom, String initial , int idCategory){
        setIdEquipe(id);
        setNomEquipe(nom);
        setInitial(initial);
        setIdCategory(idCategory);
    }
    public Equipe (int id) throws ClassNotFoundException, SQLException{
        setIdEquipe(id);
        getById(getIdEquipe());
    }
    public int getIdEquipe() {
        return idEquipe;
    }
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    public String getNomEquipe() {
        return nomEquipe;
    }
    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
    
    public String getInitial() {
        return initial;
    }
    public void setInitial(String initial) {
        this.initial = initial;
    }
    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public CategoryEquipe getCategoryEquipe(){
        CategoryEquipe cat = null;
        try {
            cat = new CategoryEquipe( getIdCategory() );            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cat;
    }

/// sql getteurs
public void getById(int id) throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    try {
        pStatement = connection.prepareStatement("select * from Equipe Where idEquipe = ? ");
    /// Preparation du statement
        pStatement.setInt(1, getIdEquipe());
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final String nom = rSet.getString("nomEquipe");
            final String initial = rSet.getString("initial");
            final int idCategory = rSet.getInt("idCategory");
            
            setNomEquipe( nom);
            setInitial(initial);
            setIdCategory(idCategory);
        }
    } catch (Exception e) {
        System.out.println("Probleme getById Equipe : ");
        e.printStackTrace();
    }
    finally{
        DataAcces.dispose(rSet, pStatement, connection);
    }
}
static public List<Equipe> getAll() throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    List<Equipe> list = new ArrayList<>();
    try {
        pStatement = connection.prepareStatement("select * from Equipe Order by idEquipe");
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idEquipe");
            final String nom = rSet.getString("nomEquipe");
            final String initial = rSet.getString("initial");
            final int idCategory = rSet.getInt("idCategory");
            
            final Equipe equipe = new Equipe(id, nom, initial, idCategory);
            list.add( equipe );
        }
    } catch (Exception e) {
        System.out.println("Probleme getAll Equipe : ");
        e.printStackTrace();
    }
    finally{
        DataAcces.dispose(rSet, pStatement, connection);
    }
    return list;
}
static public List<Equipe> search(String ref , int idCat) throws SQLException, ClassNotFoundException{
    Connection connection = DataAcces.getConnection();
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    List<Equipe> list = new ArrayList<>();
    String query = "select * from Equipe Where (1=1) ";
    if (ref != null) {
        query += "and (nomEquipe like '%"+ref+"%')";
    }
    if ( idCat > 0) {
        query += "and (idCategory = ?)";
    }
    try {
        pStatement = connection.prepareStatement(query+" Order by idEquipe");
        try {
            pStatement.setInt(1, idCat);
        } catch (Exception e) {
        }
    /// Traitement du resultat
        rSet = pStatement.executeQuery();
        while ( rSet.next() ) {
            final int id = rSet.getInt("idEquipe");
            final String nom = rSet.getString("nomEquipe");
            final String initial = rSet.getString("initial");
            final int idCategory = rSet.getInt("idCategory");
            
            final Equipe equipe = new Equipe(id, nom, initial, idCategory);
            list.add( equipe );
        }
    } catch (Exception e) {
        System.out.println("Probleme Search Equipe : ");
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
        PreparedStatement pStatement = connect.prepareStatement("UPDATE Equipe set nomEquipe = ? , initial = ? , idCategory = ? WHERE idEquipe = ? ");
   /// Insertion de valeur  
        pStatement.setString(1, getNomEquipe());
        pStatement.setString(2, getInitial());
        pStatement.setInt(3, getIdCategory());
        pStatement.setInt(4, getIdEquipe());
   /// Controle d_update
        
        try {
            int nRow = pStatement.executeUpdate();    
            System.out.println(nRow+" updated on Equipe ");
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
        }
        finally{
            DataAcces.getConnection();
        }
    }
    public String fullInfo(){
        return getIdEquipe()+" , "+getNomEquipe()+" , "+getInitial()+" , "+getIdCategory();
    }
    public void save(PrintWriter writer) throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("insert into Equipe  values (DEFAULT,?,?,?)");
    /// insertion des valeurs
        pStatement.setString(1, getNomEquipe());
        pStatement.setString(2, getInitial());
        pStatement.setInt(3, getIdCategory());
   /// Controle de save
        try {
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" inserted on Equipe ");
            connect.commit();
            writer.println("Seuccess with commit normalement");
        } catch (Exception e) {
            writer.println("Failed create");
            writer.println("=> "+fullInfo());
            connect.rollback();
        }
        finally {
            DataAcces.dispose(null, pStatement, connect);
        }
    }
    public void delete() throws SQLException, ClassNotFoundException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("DELETE From Equipe Where idEquipe = ?");
        pStatement.setInt(1, getIdEquipe());

        try {
            int nRow = pStatement.executeUpdate();
            System.out.println( nRow+" has been deleted from Equipe");
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

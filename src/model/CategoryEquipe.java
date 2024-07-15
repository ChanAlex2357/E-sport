package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class CategoryEquipe {
    int idCategory;
    String nomCategory;
    public CategoryEquipe (int id , String nom){
        setIdCategory(id);
        setNomCategory(nom);   
    }
    public CategoryEquipe(int id) throws ClassNotFoundException, SQLException {
        setIdCategory(id);
        getById(id);
    }
/// gets , sets
    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public String getNomCategory() {
        return nomCategory;
    }
    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }
/// sql getteurs
    public void getById(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement("select * from CategoryEquipe Where idCategory = ?");
        /// Preparation du statement
            pStatement.setInt(1, getIdCategory());
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ){
                setNomCategory(rSet.getString("nomCategory"));
            }
        } catch (Exception e) {
            System.out.println("Probleme getById CategoryEquipe : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
    }
    static public List<CategoryEquipe> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        List<CategoryEquipe> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement("select * from CategoryEquipe ");
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final int id = rSet.getInt("idCategory");
                final String nom = rSet.getString( "nomCategory");
                final CategoryEquipe cat = new CategoryEquipe(id, nom);
                list.add( cat );
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll CategoryEquipe : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
        return list;
    }
}

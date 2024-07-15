package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class TypeJeux {
    int idType;
    String nomType;
    public TypeJeux (int id , String nom){
        setIdType(id);
        setNomType(nom);
    }
    public TypeJeux(int id) throws ClassNotFoundException, SQLException{
        setIdType(id);
        getById(getIdType());
    }
/// gets , sets
    public int getIdType() {
        return idType;
    }
    public void setIdType(int idType) {
        this.idType = idType;
    }
    public String getNomType() {
        return nomType;
    }
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
/// sql getteurs
    public void getById(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement("select * from TypeJeux Where idType = ?");
        /// Preparation du statement
            pStatement.setInt(1, getIdType());
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                setNomType( rSet.getString("nomType"));
            }
        } catch (Exception e) {
            System.out.println("Probleme getById TypeJeux : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
    }
    static public List<TypeJeux> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        List<TypeJeux> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement("select * from TypeJeux ");
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final int id = rSet.getInt("idType");
                final String nom = rSet.getString("nomType"); 
                final TypeJeux typeJeux = new TypeJeux(id, nom);
                list.add(typeJeux);
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll TypeJeux : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
        return list;
    }

}

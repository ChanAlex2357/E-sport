package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class Jeux {
    private int idJeux;
    private String nomJeux;
    private int idType;
    private TypeJeux typeJeux;
    public Jeux(int idJeux, String nomJeux, int idType) {
        setIdJeux( idJeux);
        setNomJeux( nomJeux);
        setIdType( idType);
    }
    public Jeux(int idJeux) throws ClassNotFoundException, SQLException{
        setIdJeux(idJeux);
        getById(getIdJeux());
    }

    public int getIdJeux() {
        return idJeux;
    }
    public void setIdJeux(int idJeux) {
        this.idJeux = idJeux;
    }
    public String getNomJeux() {
        return nomJeux;
    }
    public void setNomJeux(String nomJeux) {
        this.nomJeux = nomJeux;
    }
    public int getIdType() {
        return idType;
    }
    public void setIdType(int idType) {
        this.idType = idType;
    }

    public TypeJeux getTypeJeux() {
        if (this.typeJeux != null) {
            return this.typeJeux;
        }
        try {
            setTypeJeux(new TypeJeux(getIdType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.typeJeux;
    }
    private void setTypeJeux(TypeJeux typeJeux) {
        this.typeJeux = typeJeux;
    }
    public void getById(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement("select * from Jeux Where idJeux = ? ");
        /// Preparation du statement
            pStatement.setInt(1, getIdJeux());
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                setNomJeux( rSet.getString("nomJeux"));
                setIdType( rSet.getInt("idType"));
            }
        } catch (Exception e) {
            System.out.println("Probleme getById Jeux : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
    }
    static public List<Jeux> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        List<Jeux> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement("select * from Jeux order by idJeux ");
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final int id = rSet.getInt("idJeux");
                final String nom = rSet.getString("nomJeux"); 
                final int typeJeux = rSet.getInt("idType");
                final Jeux jeux = new Jeux(id, nom, typeJeux);
                list.add(jeux);
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll Jeux : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
        return list;
    }
    static public List<Jeux> search(String nomLike , int typeJeux) throws ClassNotFoundException, SQLException{
        List<Jeux> list = new ArrayList<>() ;
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;

        String sql_query = "Select * from Jeux Where (1=1) ";
        if (nomLike != null) {
            sql_query += "and (nomJeux like '%"+nomLike+"%')";
        }
        if (typeJeux != 0) {
            sql_query += "and (idType = ?)";
        }
        try {
            pStatement = connect.prepareStatement(sql_query);
            try {
                pStatement.setInt(1, typeJeux);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final int id = rSet.getInt("idJeux");
                final String nom = rSet.getString("nomJeux"); 
                final int t_jeux = rSet.getInt("idType");
                final Jeux jeux = new Jeux(id, nom, t_jeux);
                list.add(jeux);
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll Jeux : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connect);
        }
        return list;
    }
/// CRUD
    public void update() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("UPDATE Jeux set nomJeux = ? , idType = ? WHERE idJeux = ? ");
    /// Insertion de valeur  
        pStatement.setString(1, getNomJeux());
        pStatement.setInt(2, getIdType());
        pStatement.setInt(3, getIdJeux());
    /// Controle d_update
        try {
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" updated on Jeux ");
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
        }
        finally{
            DataAcces.dispose(null, pStatement, connect);
        }
    }

    public void save() throws ClassNotFoundException, SQLException {
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("insert into Jeux values (DEFAULT,?,?)");
    /// insertion des valeurs
        pStatement.setString(1, getNomJeux());
        pStatement.setInt(2, getIdType());
    /// Controle de save
        try {
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" inserted on Jeux ");
            connect.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connect.rollback();
        }
        finally{
            DataAcces.dispose(null, pStatement, connect);
        }
    }

    public void delete() throws SQLException, ClassNotFoundException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("DELETE From Jeux Where idJeux = ?");
        pStatement.setInt(1, idJeux);

        try {
            int nRow = pStatement.executeUpdate();
            System.out.println( nRow+" has been deleted from Jeux");
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

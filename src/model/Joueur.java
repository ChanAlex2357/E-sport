package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class Joueur {
    private int idJoueur;
    private String nomJoueur;
    private String pseudo;
    private Date dateNaissance;
    private int idEquipe;
    private int age;
    private Equipe equipe;
    public Joueur(int idJoueur, String nomJoueur, String pseudo, Date dateNaissance, int idEquipe ) {
        setIdJoueur(idJoueur);
        setPseudo(pseudo);
        setNomJoueur(nomJoueur);
        setDateNaissance(dateNaissance);
        setIdEquipe(idEquipe);
    }
    protected Joueur (int idJoueur, String nomJoueur, String pseudo, Date dateNaissance, int idEquipe , int age) {
        setIdJoueur(idJoueur);
        setPseudo(pseudo);
        setNomJoueur(nomJoueur);
        setDateNaissance(dateNaissance);
        setIdEquipe(idEquipe);
        setAge(age);
    }
    public Joueur(int id) throws ClassNotFoundException, SQLException{
        setIdJoueur(id);
        getById(getIdJoueur());
    }
    public int getIdJoueur() {
        return idJoueur;
    }
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
    public String getNomJoueur() {
        return nomJoueur;
    }
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public int getIdEquipe() {
        return idEquipe;
    }
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Equipe getEquipe(){
        if (this.equipe != null) {
            return this.equipe;
        }
        try {
            setEquipe(new Equipe(getIdEquipe()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.equipe;
    }
    private void setEquipe(Equipe equipe){
        this.equipe = equipe;
    }
/// Statement Methods
    private static PreparedStatement getStatement( String ref,int idE , int ageMin , int ageMax ,Connection connection) throws SQLException{
        PreparedStatement pStatement = null;
        String sql = "select * from v_joueur where (1=1)";
        if (ref != null) {
            sql+= "and (nomJoueur like '%"+ref+"%')";
        }
    
        if (idE > 0) {
            sql += "and (idEquipe = ?)";
        }
        
        if (ageMin > 0) {
            sql += "and (age >= ?)";
        }
    
        if (ageMax > 0) {
            sql += "and (age <= ?)";
        }
        pStatement = connection.prepareStatement(sql);
        init_statement(ref, idE, ageMin, ageMax, pStatement);
        System.out.println("sql = "+sql);
        return pStatement;
    }
    private static void init_statement(String ref,int idE , int ageMin , int ageMax , PreparedStatement pStatement){
        int start = 1;
        if (idE > 0) {
            System.out.println("set idEquipe to statement "+start);
            try {
                pStatement.setInt(start, idE);
                start++;
            } catch (Exception e) {}
        }
        if (ageMin > 0) {
            System.out.println("set age min to statement "+start);
            try {
                pStatement.setInt(start, ageMin);
                start ++;
            } catch (Exception e) {
            }
        }
        if (ageMax > 0) {
            System.out.println("set age max to statement "+start);
            try {
                pStatement.setInt(start, ageMax);
                start++;
            }catch(Exception e){
            }
        }
    }
/// sql getteurs
    public void getById(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement("select * from v_joueur Where idJoueur = ?");
        /// Preparation du statement
            pStatement.setInt(1, getIdJoueur());
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final String nom = rSet.getString("nomJoueur");
                final String pseudo = rSet.getString("pseudo");
                final Date naissance = rSet.getDate("dateNaissance");
                final int idEquipe = rSet.getInt("idEquipe");

                setNomJoueur(nom);
                setPseudo(pseudo);
                setDateNaissance(naissance);
                setIdEquipe(idEquipe);
            }
        } catch (Exception e) {
            System.out.println("Probleme getById Joueur : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
    }
    static public List<Joueur> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        List<Joueur> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement("select * from v_joueur ");
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                
                final int idJoueur = rSet.getInt("idJoueur");
                final String nom = rSet.getString("nomJoueur");
                final String pseudo = rSet.getString("pseudo");
                final Date naissance = rSet.getDate("dateNaissance");
                final int idEquipe = rSet.getInt("idEquipe");
                final int age = rSet.getInt("age");

                Joueur joueur = new Joueur(idJoueur, nom, pseudo, naissance, idEquipe , age);
                list.add( joueur );
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll Joueur : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
        return list;
    }
    static public List<Joueur> search(String ref,int idE , int ageMin , int ageMax) throws SQLException, ClassNotFoundException{
        Connection connection = DataAcces.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        List<Joueur> list = new ArrayList<>();
        try {
            pStatement = getStatement(ref, idE, ageMin, ageMax, connection);
        /// Traitement du resultat
            rSet = pStatement.executeQuery();
            while ( rSet.next() ) {
                final int idJoueur = rSet.getInt("idJoueur");
                final String nom = rSet.getString("nomJoueur");
                final String pseudo = rSet.getString("pseudo");
                final Date naissance = rSet.getDate("dateNaissance");
                final int idEquipe = rSet.getInt("idEquipe");
                final int age = rSet.getInt("age");

                Joueur joueur = new Joueur(idJoueur, nom, pseudo, naissance, idEquipe,age);
                list.add( joueur );
            }
        } catch (Exception e) {
            System.out.println("Probleme getAll Joueur : ");
            e.printStackTrace();
        }
        finally{
            DataAcces.dispose(rSet, pStatement, connection);
        }
        System.out.println(list.size());
        return list;
    }
/// CRUD
    public void update() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        
        PreparedStatement pStatement = null;
   /// Controle d_update
        try {
            if ( getIdEquipe() <= 0) {
                pStatement = connect.prepareStatement("UPDATE Joueur set nomJoueur = ?, pseudo = ?, dateNaissance = ? WHERE idJoueur = ? ");
                pStatement.setString(1, getNomJoueur());
                pStatement.setString(2, getPseudo());
                pStatement.setDate(3, getDateNaissance());
                pStatement.setInt(4, getIdJoueur());
            }
            else{
                pStatement = connect.prepareStatement("UPDATE Joueur set nomJoueur = ?, pseudo = ?, dateNaissance = ?, idEquipe = ? WHERE idJoueur = ? ");
                pStatement.setString(1, getNomJoueur());
                pStatement.setString(2, getPseudo());
                pStatement.setDate(3, getDateNaissance());
                pStatement.setInt(4, getIdEquipe());
                pStatement.setInt(5, getIdJoueur());
            }
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" updated on Joueur ");
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
        PreparedStatement pStatement = null;
   /// Controle de save
        try {
            if (getIdEquipe() <= 0) {
                pStatement = connect.prepareStatement("insert into Joueur(idJoueur,nomJoueur,pseudo,dateNaissance)  values (DEFAULT,?,?,?)");
            /// insertion des valeurs
                pStatement.setString(1, getNomJoueur());
                pStatement.setString(2, getPseudo());
                pStatement.setDate(3, getDateNaissance());
            }
            else{
                pStatement = connect.prepareStatement("insert into Joueur values (DEFAULT,?,?,?,?)");
            /// insertion des valeurs
                pStatement.setString(1, getNomJoueur());
                pStatement.setString(2, getPseudo());
                pStatement.setDate(3, getDateNaissance());
                pStatement.setInt(4, getIdEquipe());
            }
            int nRow = pStatement.executeUpdate();
            System.out.println(nRow+" inserted on Joueur ");
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
        }
        finally{
            DataAcces.dispose(null, pStatement, connect);
        }
    }
    public void delete() throws SQLException, ClassNotFoundException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("DELETE From Joueur Where idJoueur = ?");
        pStatement.setInt(1, getIdJoueur());

        try {
            int nRow = pStatement.executeUpdate();
            System.out.println( nRow+" has been deleted from Joueur");
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

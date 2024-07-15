package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpers.DataAcces;

public class Organisateur {
    int idOrganisateur = -1;
    String nom;
    String prenom;
    int age;
    String mail;
    String password;
    
    public Organisateur(int idOrganisateur, String nom, String prenom, int age, String mail, String password) {
        setIdOrganisateur( idOrganisateur);
        setNom( nom );
        setPrenom( prenom );
        setAge( age );
        setMail( mail );
        setPassword( password );
    }
    
    public Organisateur(int idOrganisateur)throws Exception{
        setIdOrganisateur(idOrganisateur);
        getById(idOrganisateur);
    }
    public Organisateur(String mail , String password) throws Exception{
        getByMailPassword(mail,password);
    }

    Organisateur(){}

    // Gets & Sets
    public int getIdOrganisateur() {
        return this.idOrganisateur;
    }
    public void setIdOrganisateur(int idOrganisateur) {
        this.idOrganisateur = idOrganisateur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
// Sql getteurs 
public void getById(int idOrganisateur) throws Exception {
    Connection connect = DataAcces.getConnection();
    PreparedStatement pStatement = connect.prepareStatement("Select * from Organisateur where idOrganisateur = ?");
    pStatement.setInt(1, idOrganisateur);
    ResultSet resultSet = pStatement.executeQuery();
    while ( resultSet.next() ) {
        setNom(resultSet.getString("nom"));
        setPrenom(resultSet.getString("prenom"));
        setAge( resultSet.getInt("age"));
        setMail( resultSet.getString("mail"));
        setPassword( resultSet.getString("password"));
    }
    DataAcces.dispose(resultSet, pStatement, connect);
}

public static List<Organisateur> getAll() throws Exception {
    Connection connect = DataAcces.getConnection();
    PreparedStatement pStatement = connect.prepareStatement("Select * from Organisateur");
    ResultSet resultSet = pStatement.executeQuery();

    List<Organisateur> list = new ArrayList<>(1);
    
    
    while ( resultSet.next() ) {
        Organisateur orga = new Organisateur();

        orga.setIdOrganisateur( resultSet.getInt("idOrganisateur"));
        orga.setNom(resultSet.getString("nom"));
        orga.setPrenom(resultSet.getString("prenom"));
        orga.setAge( resultSet.getInt("age"));
        orga.setMail( resultSet.getString("mail"));
        orga.setPassword( resultSet.getString("password"));

        list.add(orga);
    }
    DataAcces.dispose(resultSet, pStatement, connect);
    return list;
}
/// Filtre
    void getByMailPassword(String mail , String password) throws Exception {
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("Select * from Organisateur where mail = ? AND password = ?");
        pStatement.setString(1, mail);
        pStatement.setString(2, password);

        ResultSet resultSet = pStatement.executeQuery();
        if ( resultSet.next() ) {
            setIdOrganisateur(resultSet.getInt("idOrganisateur"));
            setNom(resultSet.getString("nom"));
            setPrenom(resultSet.getString("prenom"));
            setAge( resultSet.getInt("age"));
        }
        DataAcces.dispose(resultSet, pStatement, connect);
    }
/// CRUD
    public void update() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("UPDATE Organisateur set nom=? , prenom =? , age =? , mail =? , password =? WHERE idOrganisateur=? ");
        pStatement.setString(1, getNom());
        pStatement.setString(2,getPrenom());
        pStatement.setInt(3,getAge());
        pStatement.setString(4,getMail() );
        pStatement.setString(5,getPassword());
        pStatement.setInt(6, getIdOrganisateur());

        int nRow = pStatement.executeUpdate();
        System.out.println(nRow+" updated on Organisateur");
    }
    public void save() throws ClassNotFoundException, SQLException{
        Connection connect = DataAcces.getConnection();
        PreparedStatement pStatement = connect.prepareStatement("insert into Organisateur values (DEFAULT,?,?,?,?,?)");
        pStatement.setString(1, getNom());
        pStatement.setString(2,getPrenom());
        pStatement.setInt(3,getAge());
        pStatement.setString(4,getMail() );
        pStatement.setString(5,getPassword());

        int nRow = pStatement.executeUpdate();
        System.out.println(nRow+" inserted on Organisateur");
    }
    public String fullInfo(){
        String result ="";
        result+="Id = "+getIdOrganisateur();
        result+="\nMail = "+getMail();
        result+="\npass = "+getPassword();

        return result;
    }
}
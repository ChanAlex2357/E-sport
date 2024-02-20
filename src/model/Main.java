package model;

public class Main {
    public static void main(String[] args) {
        try {
            Organisateur roga = new Organisateur("jean.dupont@example.com", "password1");
            // Organisateur roga = new Organisateur(1);
            System.out.println( roga.fullInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

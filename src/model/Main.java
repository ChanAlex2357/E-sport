package model;

import java.sql.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            List<Tournoi> joueurs = Tournoi.search("",0,"2024-03-09","2024-03-12" );
            for (Tournoi j : joueurs) {
                System.out.println(j.idTournoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

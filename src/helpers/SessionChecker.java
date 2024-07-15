package helpers;

import jakarta.servlet.http.HttpServletRequest;
import model.Joueur;
import model.Organisateur;

public class SessionChecker {
    /// VALIDATION DE CONNECTION
    public static boolean isOrgaConnected(HttpServletRequest request){
        return request.getSession(true).getAttribute("user_orga") == null;
    }
    public static void deconnectOrganisateur(HttpServletRequest request){
        request.getSession(true).removeAttribute("user_orga");
    }
    public static void connectOrganisateur(HttpServletRequest request,Organisateur organisateur){
        request.getSession(true).setAttribute("user_orga", organisateur);
    }

    public static Organisateur getConnectedOrganisateur(HttpServletRequest request){
        return (Organisateur)   request.getSession(true).getAttribute("user_orga");
    }


    public static boolean isPlayerConnected(HttpServletRequest request){
        return request.getSession(true).getAttribute("user_player") == null;
    }
    public static void deconnectPlayer(HttpServletRequest request){
        request.getSession(true).removeAttribute("user_player");
    }
    public static void connectPlayer(HttpServletRequest request,Joueur joueur){
        request.getSession(true).setAttribute("user_player", joueur);
    }
    public static Joueur getConnectedPlayer(HttpServletRequest request){
        return (Joueur)   request.getSession(true).getAttribute("user_player");
    }
}

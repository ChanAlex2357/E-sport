package controler.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import helpers.SessionChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Joueur;

public class LoginPlayer extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dispatchPath = "pages/login/login-player.jsp";
        req.getRequestDispatcher(dispatchPath).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("playerid"));
        String redirection = "playerlogin";
        try {
            Joueur joueur  = new Joueur(id);
            if (joueur.getIdJoueur() > 0) {
                redirection = "participation";
            }
            SessionChecker.connectPlayer(req,joueur);
        }
        catch (Exception err) {
            resp.getWriter().println("erorr ");    
        }
        resp.sendRedirect(redirection);
    }
}

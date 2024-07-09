package controler.tournoi;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jeux;
import model.Organisateur;
import model.Tournoi;

public class TournoiServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Organisateur.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }

        List<Jeux> jeux = null;
        List<Tournoi> tournois = new ArrayList<>();

        String action  = req.getParameter("action");
        String filtre = req.getParameter("filtre");
    /// Traitement delete
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt( req.getParameter("id"));
            try {
                Tournoi tournoi = new Tournoi(id);
                tournoi.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    /// La liste des tournois avec filtre
        try {
            if (
                (filtre != null) 
                && 
                (filtre.equals("search"))
            ) {
                String ref = req.getParameter("nom");
                String dateMin = null;
                try {
                    dateMin = req.getParameter("dateMin");
                } catch (Exception e) {}
                String dateMax = null;
                try {
                    dateMax = req.getParameter("dateMax");
                } catch (Exception e) {}
                int idJ = 0;
                try {
                    idJ = Integer.parseInt(req.getParameter("jeux"));
                } catch (Exception e) {}
                tournois = Tournoi.search(ref, idJ, dateMin, dateMax);
            }
            else {
                tournois = Tournoi.getAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    /// La liste des jeux
    try {
        jeux = Jeux.getAll();
    } catch (Exception e) {
        e.printStackTrace();
    }
    /// 
        req.setAttribute("listJeux", jeux);
        req.setAttribute("listTournois", tournois);
        req.getRequestDispatcher("pages/tournoi/tournoi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String nom ;
        Date date ;
        int duree;
        String lieu;
        int idJeux ;

        String action = req.getParameter("action");
        try {
            /// Taitement de participation
            /// Recuperation des valeurs en input
            nom = req.getParameter("nomTournoi");
            date = Date.valueOf( req.getParameter("date"));
            duree = Integer.parseInt(req.getParameter("duree"));
            lieu = req.getParameter("lieu");
            idJeux = Integer.parseInt(req.getParameter("idJeux"));
            Tournoi t  = new Tournoi(id, nom, date, duree, lieu, idJeux);
            /// Modification
            if ((action != null) && (action.equals("update"))) {
                id = Integer.parseInt(req.getParameter("id"));
                t.setIdTournoi(id);
                t.update();
            }
            /// Creation 
            else if ((action != null) && (action.equals("create"))) {
                resp.getWriter().println(action);
                t.save();
            }
            resp.sendRedirect("tournoi");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}

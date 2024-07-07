package controler;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Equipe;
import model.Joueur;

public class JoueurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Equipe> listEquipe = null;
        List<Joueur> listJoueur = null;
        String action  = req.getParameter("action");
        String filtre = req.getParameter("filtre");

        if ((action!=null)&&(action.equals("delete"))) {
            int id = Integer.parseInt( req.getParameter("id"));
            try {
                Joueur j = new Joueur(id);
                j.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            listEquipe = Equipe.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if ((filtre != null ) && (filtre.equals("search"))) {
                String nomInput = req.getParameter("nom");
                int idType = 0;
                int ageMin = -1;
                int ageMax = -1;
                try {
                    ageMin = Integer.parseInt(req.getParameter("ageMin"));
                }
                catch(Exception e){}
                try {
                    ageMax = Integer.parseInt(req.getParameter("ageMax"));
                }
                catch(Exception e){}
                try {
                    idType = Integer.parseInt(req.getParameter("idEquipe"));
                } catch (Exception e) {}

                listJoueur = Joueur.search(nomInput, idType, ageMin, ageMax);
            }
            else {
                listJoueur = Joueur.getAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("listEquipe", listEquipe);
        req.setAttribute("listJoueur", listJoueur);
        req.getRequestDispatcher("pages/joueur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action  = req.getParameter("action");

        int id = 0;
        String nom = req.getParameter("nom");
        String pseudo = req.getParameter("pseudo");
        Date date = Date.valueOf(req.getParameter("date"));
        int idEquipe = Integer.parseInt(req.getParameter("idEquipe"));
        Joueur joueur = new Joueur(id, nom, pseudo, date, idEquipe);

        try {
            if ((action != null) && (action.equals("update"))) {
                id = Integer.parseInt(req.getParameter("id"));
                joueur.setIdJoueur(id);
                joueur.update();
            }
            else if ((action != null) && (action.equals("create"))) {
                joueur.save();
            }
            resp.sendRedirect("joueur");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

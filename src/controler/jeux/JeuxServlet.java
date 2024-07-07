package controler.jeux;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controler.HomeServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jeux;
import model.Organisateur;
import model.TypeJeux;

public class JeuxServlet extends HomeServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Organisateur.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }
        List<TypeJeux> typeJeux = null;
        List<Jeux> jeux = null;
    /// Traitement des actions
        String action = req.getParameter("action");
        String filtre = req.getParameter("filtre");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt( req.getParameter("id"));
            try {
                Jeux j = new Jeux(id);
                j.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    /// Recuperer tout les types de jeux
        try {
            typeJeux = TypeJeux.getAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    /// REcuperation de la liste des jeux selon la demande
        try {
            if ((filtre != null ) && (filtre.equals("search"))) {
                String nomInput = req.getParameter("nom");
                int idType = 0;
                try {
                    idType = Integer.parseInt(req.getParameter("idType"));
                } catch (Exception e) {}
                jeux = Jeux.search(nomInput, idType);
            }
            else {
                jeux = Jeux.getAll();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    /// Envoie des donnees
        req.setAttribute("listJeux", jeux);
        req.setAttribute("listTypeJeux", typeJeux);
        req.getRequestDispatcher("pages/jeux/jeux.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String nom ;
        int idType ;

        String action = req.getParameter("action");
    /// Recuperation des valeurs en input

        nom = req.getParameter("nomJeux");
        idType = Integer.parseInt(req.getParameter("idType"));
        Jeux jeux = new Jeux(id, nom, idType);
        try {
            if ((action != null) && (action.equals("update"))) {
                id = Integer.parseInt(req.getParameter("id"));
                jeux.setIdJeux(id);
                jeux.update();
            }
            else if ((action != null) && (action.equals("create"))) {
                jeux.save();
            }
            resp.sendRedirect("jeux");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

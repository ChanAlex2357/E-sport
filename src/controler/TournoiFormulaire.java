package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jeux;
import model.Tournoi;

public class TournoiFormulaire extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Jeux> jeux = null;
        String action = req.getParameter("action");
    /// Traitement Update
        if ((action != null) && (action.equals("update"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Tournoi tournoi = new Tournoi(id);
                req.setAttribute("toUpdate",tournoi);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    /// Recuperer tout les types de jeux
        try {
            jeux = Jeux.getAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    /// Rediriger vers le formulaire
        req.setAttribute("listJeux", jeux);
        req.getRequestDispatcher("pages/tournoi-formulaire.jsp").forward(req, resp);
    }
}

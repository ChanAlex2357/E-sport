package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoryEquipe;
import model.Equipe;

public class EquipeFormulaire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryEquipe> cat = null;
        String action = req.getParameter("action");
    /// Traitement Update
        if ((action != null) && (action.equals("update"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Equipe j = new Equipe(id);
                req.setAttribute("toUpdate",j);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    /// Recuperer tout les types de jeux
        try {
            cat = CategoryEquipe.getAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    /// Rediriger vers le formulaire
        req.setAttribute("listCategory", cat);
        req.getRequestDispatcher("pages/equipe-formulaire.jsp").forward(req, resp);
    }
}
package controler.equipe;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import helpers.SessionChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoryEquipe;
import model.Equipe;

public class EquipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionChecker.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }
        List<CategoryEquipe> cat = new ArrayList<>();
        List<Equipe> equipe = new ArrayList<>();
        String action = req.getParameter("action");
        String filtre = req.getParameter("filtre");
    /// Traitement Update
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Equipe j = new Equipe(id);
                j.delete();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    /// Recuperer tout les category d'equipe
        try {
            cat = CategoryEquipe.getAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            if ((filtre != null) && (filtre.equals("search"))) {
                String ref = req.getParameter("nom");
                int idCategory= Integer.parseInt(req.getParameter("idType"));
                equipe = Equipe.search(ref,idCategory);  
            }
            else{
                equipe = Equipe.getAll();
            }
        } catch (Exception e) {
            try {
                equipe = Equipe.getAll();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    /// Rediriger vers le formulaire
        req.setAttribute("listCategory", cat);
        req.setAttribute("listEquipe", equipe);
        req.getRequestDispatcher("pages/equipe/equipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        int id = 0;
        String nom  = req.getParameter("nomEquipe");
        String initial = req.getParameter("initial");
        int idCat = Integer.parseInt(req.getParameter("idCategory"));

        Equipe equipe = new Equipe(id, nom, initial, idCat);
        try {
            if ((action != null) && (action.equals("create"))) {
                equipe.save( resp.getWriter());
            }
            else if ((action != null) && (action.equals("update"))) {
                id = Integer.parseInt(req.getParameter("id"));
                equipe.setIdEquipe(id);
                equipe.update();
            }
            resp.sendRedirect("equipe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

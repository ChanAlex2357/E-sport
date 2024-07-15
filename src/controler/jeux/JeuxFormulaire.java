package controler.jeux;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import helpers.SessionChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jeux;
import model.TypeJeux;
public class JeuxFormulaire extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionChecker.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }
        List<TypeJeux> typeJeux = null;
        String action = req.getParameter("action");
    /// Traitement Update
        if ((action != null) && (action.equals("update"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Jeux j = new Jeux(id);
                req.setAttribute("toUpdate",j);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    /// Recuperer tout les types de jeux
        try {
            typeJeux = TypeJeux.getAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    /// Rediriger vers le formulaire
        req.setAttribute("listTypeJeux", typeJeux);
        req.getRequestDispatcher("pages/jeux/jeux-formulaire.jsp").forward(req, resp);
    }
}

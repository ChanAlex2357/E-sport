package controler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JeuxFormulaire extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /// Recuperer tout les types de jeux

    /// Rediriger vers le formulaire
        req.getRequestDispatcher("pages/jeux-formulaire.jsp").forward(req, resp);
    }
}

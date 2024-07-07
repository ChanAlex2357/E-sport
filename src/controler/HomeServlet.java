package controler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Organisateur;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Organisateur.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }
        req.getRequestDispatcher("pages/home.jsp").forward(req, resp);
    }
}
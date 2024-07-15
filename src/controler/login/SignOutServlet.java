package controler.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import helpers.SessionChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type =  req.getParameter("type");
        if (type.equals("player")) {
            SessionChecker.deconnectPlayer(req);
            resp.sendRedirect("playerlogin");
        }
        else if (type.equals("organisateur")) {
            SessionChecker.deconnectOrganisateur(req);
            resp.sendRedirect("login");
        }
        else {
            resp.sendRedirect("home");
        }
    }
}

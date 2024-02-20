package controler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Organisateur;

public class LoginServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String dispatchPath = "pages/login.jsp";
    req.getRequestDispatcher(dispatchPath).forward(req, resp);
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mail = req.getParameter("loginMail");
    String pass = req.getParameter("password");
    String redirection = "login";
    try {
        Organisateur orga = new Organisateur(mail, pass);
        if (orga.getIdOrganisateur() != 0) {
            redirection = "home";
        }
    }
    catch (Exception err) {
        resp.getWriter().println("erorr ");    
    }
    resp.getWriter().println(redirection);
    resp.sendRedirect(redirection);
}
}
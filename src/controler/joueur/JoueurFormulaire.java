package controler.joueur;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Equipe;
import model.Joueur;

public class JoueurFormulaire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Equipe> listEquipe = null;
        String action  = req.getParameter("action");
        if ((action!=null)&&(action.equals("update"))) {
            int id = Integer.parseInt( req.getParameter("id"));
            try {
                Joueur j = new Joueur(id);
                req.setAttribute("toUpdate", j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            listEquipe = Equipe.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("listEquipe", listEquipe);
        req.getRequestDispatcher("pages/joueur/joueur-formulaire.jsp").forward(req, resp);
    }
}

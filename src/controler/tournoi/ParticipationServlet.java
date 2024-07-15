package controler.tournoi;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import helpers.SessionChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Particiation;

public class ParticipationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionChecker.isOrgaConnected(req)) {
            resp.sendRedirect("login");
            return;
        }
        req.getRequestDispatcher("pages/tournoi/participation.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int idTournoi = Integer.parseInt(req.getParameter("idTournoi"));
        int idJoueur = Integer.parseInt(req.getParameter("idJoueur"));
        try {
            Particiation participation = new Particiation(idJoueur, idTournoi);
            /// Taitement de participation
            /// Participation existante
            if (participation.getIdParticipation() > 0) {
                /// Modification de la registration
                if ((action != null) && (action.equals("register"))) {
                    participation.setIdState(1);
                }
                else if ((action != null) && (action.equals("unregister"))) {
                    participation.setIdState(2);
                }
                participation.update();
            }
            /// Participation inexistante
            else {
                /// Faire une registration
                if ((action != null) && (action.equals("register"))) {
                    participation.save();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
        resp.sendRedirect("paritcipation");
    }
}

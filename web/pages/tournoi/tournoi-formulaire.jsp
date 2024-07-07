<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Jeux"%>
<%@ page import="model.Tournoi"%>
<%@ page import="java.sql.Date"%>
<%
    List<Jeux> jeux = (List<Jeux>) request.getAttribute("listJeux");
    List<Tournoi> tournois = ( List<Tournoi> )request.getAttribute("listTournois");
    String validation = "Nouveau Tournoi";

    Tournoi toUpdate = (Tournoi) request.getAttribute("toUpdate");
    String action = "create";

    String nom = "";
    Date date = null;
    int duree = 1;
    String lieu = "";
    int idJeux = 0;
    if( toUpdate != null) {
        action = "update";
        nom = toUpdate.getNomTournoi();
        date = toUpdate.getDateTournoi();
        duree = toUpdate.getDuree();
        lieu = toUpdate.getLieuTournoi();
        idJeux = toUpdate.getIdJeux();
        validation = "Modifier Tournoi";
    }
%>
<div id="hero py-5">
    <div class="container mt-5">
        <div class="row py-5">
            <div class="col-12 col-lg-6">
                <div class="mx-auto">
                    <form action="tournoi?action=<%=action%>" method="post" class="">
                        <h2> Formulaire de Tournoi</h2>
                        <% if(toUpdate != null ) { %> 
                            <input type="hidden" name="id" value="<%=toUpdate.getIdTournoi()%>">    
                        <% } %>
                        <div class="bg-dark p-1 w-100"></div>
                        <div class="form-group mb-3">
                            <label for="nomTournoiInput" class="form-label">Nom du tournoi</label>
                            <input type="text" name="nomTournoi" id="nomTournoiInput" class="form-control" required value="<%=nom%>">
                        </div>
                        <div class="row">
                            <div class="col-12 col-lg-6 form-group mb-3">
                                <label for="dateInput" class="form-label">Date</label>
                                <input type="date" name="date" id="dateInput" required value="<%=date%>">
                            </div>
                            <div class="col-12 col-lg-6 form-group mb-3">
                                <label for="dureeInput" class="form-label">Duree (en jour)</label>
                                <input type="number" name="duree" min="1" id="dureeInput" required value="<%=duree%>">
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <label for="lieuInput" class="form-label">Lieu</label>
                            <input type="text" name="lieu" id="lieuInput" class="form-control" required value="<%=lieu%>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="idJeuxInput" class="form-label">Jeux a jouer</label>
                            <select name="idJeux" id="idJeuxInput" class="form-select" required>
                                <option value="">Choisir un jeu</option>
                                <% for(Jeux j : jeux) {
                                    String state = "";
                                    if( idJeux == j.getIdJeux() ){
                                        state = "selected";
                                    } 
                                %>
                                    <option value="<%=j.getIdJeux()%>" <%=state%> ><%=j.getNomJeux()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div>
                            <input type="submit" class=" text-light btn bg-prim-clr" value="<%=validation%>">
                            <a href="tournoi" role="button" class="btn btn-danger">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-12 col-lg-6 d-none d-lg-block">
                <img src="../assets/images/video_game_night.svg" alt="" class="img-fluid">
            </div>
        </div>
    </div>
</div>
<%@ include file="../static/footer.html"%>
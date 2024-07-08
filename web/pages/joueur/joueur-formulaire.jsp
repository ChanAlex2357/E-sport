<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Joueur"%>
<%@ page import="model.Equipe"%>
<%@ page import="java.sql.Date"%>
<%
    List<Equipe> equipes = ( List<Equipe> )request.getAttribute("listEquipe");
    Joueur toUpdate = (Joueur) request.getAttribute("toUpdate");
    String validation = "Nouveau Joueur";

    String action = "create";
    String nom = "";
    Date date = null;
    String pseudo = "";
    int idEquipe = -1;

    if( toUpdate != null){
        action = "update";
        nom = toUpdate.getNomJoueur();
        date = toUpdate.getDateNaissance();
        pseudo = toUpdate.getPseudo();
        idEquipe = toUpdate.getIdEquipe();
        validation = "Modifier Joueur";
    }
%>
<div id="hero py-5">
    <div class="container mt-5">
        <div class="row py-5">
            <div class="col-12 col-lg-6">
                <div class="mx-auto">
                    <form action="joueur?action=<%=action%>" method="post" class="">
                        <% if(toUpdate != null) { %>
                            <input type="hidden" name="id" value="<%=toUpdate.getIdJoueur()%>">
                        <% } %>
                        <h2> Formulaire de Joueur</h2>
                        <div class="bg-dark p-1 w-100"></div>
                        <div class="form-group mb-3">
                            <label for="nomJoueurInput" class="form-label">Nom du joueur</label>
                            <input required type="text" name="nom" id="nomJoueurInput" class="form-control" value="<%=nom%>">
                        </div>
                        
                        <div class="form-group mb-3">
                            <label for="dateInput" class="form-label">Date Naissance</label>
                            <input required type="date" name="date" id="dateInput" class="form-control" value="<%=date%>">
                        </div>

                        <div class="row">
                            <div class="col-12 col-lg-6 form-group mb-3">
                                <label for="pseudoInput" class="form-label">Pseudo</label>
                                <input required type="text" name="pseudo" id="pseudoInput" class="form-control" value="<%=pseudo%>">
                            </div>
                            <div class="col-12 col-lg-6 form-group mb-3">
                                <label for="idEquipeInput" class=" col-12 col-md-3 form-label">Equipe</label>
                                <select name="idEquipe" id="idEquipeInput" class="col  form-select">
                                    <option value="">Choisir une equipe</option>
                                    <%
                                        String noTeamLaout = ""; 
                                        if( idEquipe == 0) {
                                            noTeamLaout = "selected";
                                        }  
                                    %>
                                    <option value="0" <%= noTeamLaout%> >Pas d'equipe</option>
                                    <% for( Equipe eq : equipes) { 
                                        String etat = "";
                                        if( eq.getIdEquipe() == idEquipe) {
                                            etat = "selected";
                                        }
                                    %>
                                        <option value="<%=eq.getIdEquipe()%>" <%=etat%> ><%=eq.getNomEquipe()%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div>
                            <input required type="submit" class=" text-light btn bg-prim-clr" value="<%=validation%>">
                            <a href="joueur" role="button" class="btn btn-danger">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-12 col-lg-6 d-none d-lg-block">
                <img src="assets/images/player_ninja.svg" alt="" class="img-fluid">
            </div>
        </div>
    </div>
</div>
<%@ include file="../static/footer.html"%>
<script>
    activeCurrentPage("joueur-link");
</script>
<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Joueur"%>
<%@ page import="model.Equipe"%>
<%@ page import="java.sql.Date"%>
<%
    List<Equipe> equipes = ( List<Equipe> )request.getAttribute("listEquipe");
    List<Joueur> joueurs = (List<Joueur>) request.getAttribute("listJoueur");
%>

<div id="hero" class=" py-5">
  <!-- * FILTRE -->
    <div id="filtre" class="p-5 bg-light mb-3">
        <div class="container-fluid">
            <form action="joueur" method="get" class="mx-auto col-12 col-lg-6">
                <input type="hidden" name="filtre" value="search">
                <div class="row mb-3">
                    <label for="ageInput" class=" col-12 col-md-2 form-label">Age</label>
                    <div id="ageInput" class="col">
                      <div class="row">
                        <input type="number" name="ageMin" class="col form-control" placeholder="age min">
                        <input type="number" name="ageMax" class="col form-control" placeholder="age max">
                      </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="idEquipeInput" class=" col-12 col-md-3 form-label">Equipe</label>
                    <select name="idEquipe" id="idEquipeInput" class="col  form-select">
                        <option value="">Choisir une equipe</option>
                        <option value="0">Pas d'equipe</option>
                        <% for( Equipe eq : equipes) {
                        %>
                            <option value="<%=eq.getIdEquipe()%>"><%=eq.getNomEquipe()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="row mb-3">
                  <label for="nomInput" class=" col-12 col-md-3 form-label">Nom du joueur</label>
                  <input type="text" name="nom" id="nomInput" class="col form-control">
              </div>
                <div>
                    <input type="submit" class=" text-light btn bg-prim-clr" value="Filtrer">
                </div>
            </form>
        </div>
    </div>
  <!-- ? END FILTRE -->
    <div class="container">
      <!-- * BIG TITLE -->
        <div class="row">
          <div class="col-12 col-md-9">
            <h1 class="h1" id="">Liste des joueurs</h1>
          </div>
          <div class="col">
              <a href="joueur-formulaire" role="button" class="btn btn-success"> Ajouter </a>
          </div>
        </div>
      <!-- ? END BIG TITLE -->
      <!-- * DATA LIST -->
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nom</th>
              <th scope="col">Age</th>
              <th scope="col">Pseudo</th>
              <th scope="col">Equipe</th>
              <th scope="col" colspan="2">Action</th>
            </tr>
          </thead>
          <tbody>
            <% for( Joueur joueur : joueurs) { 
                final Equipe e = joueur.getEquipe();
                String equipeLayout = "Pas d'equipe" ;
                if( e.getIdEquipe() > 0){
                  equipeLayout = e.getNomEquipe();
                }
              %>
              <tr>
                <th scope="row"> <%= joueur.getIdJoueur() %> </th>
                <td> <%= joueur.getNomJoueur() %> </td>
                <td> <%= joueur.getAge() %> </td>
                <td> <%= joueur.getPseudo() %> </td>
                <td> <%= equipeLayout %> </td>
                <td>
                  <a href="joueur-formulaire?action=update&id=<%= joueur.getIdJoueur() %>" class="btn btn-outline-dark" role="button">
                    <i class="bi bi-pen-fill"></i>
                  </a>
                </td>
                <td>
                  <a href="joueur?action=delete&id=<%= joueur.getIdJoueur() %>" class="btn btn-outline-dark" role="button">
                    <i class="bi bi-trash"></i>
                  </a>
                </td>
              </tr>
            <% } %>
          </tbody>
        </table>
      <!-- ? END DATA LIST -->
    </div>
</div>
<%@ include file="../static/footer.html"%>
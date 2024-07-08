<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Jeux"%>
<%@ page import="model.Tournoi"%>
<%
  List<Jeux> jeux = (List<Jeux>) request.getAttribute("listJeux");
  List<Tournoi> tournois = ( List<Tournoi> )request.getAttribute("listTournois");
%>
<div id="hero" class=" py-5">
  <!-- * FILTRE -->
    <div id="filtre" class="p-5 bg-light mb-3">
        <div class="container-fluid">
          <!-- FILTRE PAR DATE -->
          <form action="tournoi" method="get" class="mx-auto col-12 col-lg-6" >
              <input type="hidden" name="filtre" value="search">
              <div class="row mb-3">
                  <label for="dateInput" class=" col-12 col-md-3 form-label">Date de tournoi</label>
                  <div id="dateInput" class="col">
                      <div class="row">
                        <input type="date" name="dateMin" class="col form-control" placeholder="date min">
                        <input type="date" name="dateMax" class="col form-control" placeholder="date max">
                      </div>
                  </div>
              </div>
              <div class="row mb-3">
                  <label for="jeuxInput" class=" col-12 col-md-3 form-label">Jeux</label>
                  <select name="jeux" id="jeuxInput" class="col  form-select">
                      <option value="">Choisir une jeu</option>
                      <% for(Jeux j : jeux){ %>
                        <option value="<%=j.getIdJeux()%>"><%=j.getNomJeux()%></option>
                      <% }%>
                  </select>
              </div>
            <div class="row mb-3">
              <label for="nomInput" class=" col-12 col-md-3 form-label">Nom de Tournoi</label>
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
            <h1 class="h1" id="">Liste des tournois</h1>
          </div>
          <div class="col">
            <a href="tournoi-formulaire" role="button" class="btn btn-success"> Ajouter </a>
          </div>
        </div>
        <!-- ? END BIG TITLE -->
        <!-- * DATA LIST -->
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nom </th>
                <th scope="col">Date</th>
                <th scope="col">Duree</th>
                <th scope="col">Lieu</th>
                <th scope="col">Jeux</th>
                <th scope="col" colspan="2">Action</th>
              </tr>
            </thead>
            <tbody>
              <% for(Tournoi t : tournois){ %>
                <tr>
                  <th scope="row"> <%=t.getIdTournoi()%> </th>
                  <td> <%=t.getNomTournoi()%> </td>
                  <td> <%=t.getDateTournoi().toString() %> </td>
                  <td> <%=t.getDuree()%>J</td>
                  <td> <%=t.getLieuTournoi()%></td>
                  <td> <%=t.getJeux().getNomJeux()%> </td>
                  <td>
                    <a href="tournoi-formulaire?action=update&id=<%=t.getIdTournoi()%>" class="btn btn-outline-dark" role="button">
                      <i class="bi bi-pen-fill"></i>
                    </a>
                  </td>
                  <td>
                    <a href="tournoi?action=delete&id<%=t.getIdTournoi()%>" class="btn btn-outline-dark" role="button">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>
              <% }%>
            </tbody>
          </table>
        <!-- ? DATA LIST -->
    </div>
</div>
<%@ include file="../static/footer.html"%>
<script>
  activeCurrentPage("tournoi-link");
</script>
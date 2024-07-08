<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.CategoryEquipe"%>
<%@ page import="model.Equipe"%>
<%
  List<CategoryEquipe> categories = (List<CategoryEquipe>) request.getAttribute("listCategory");
  List<Equipe> equipes = ( List<Equipe> )request.getAttribute("listEquipe");
%>
<div id="hero" class=" py-5">
  <!-- * FILTRE -->
    <div id="filtre" class="p-5 bg-light mb-3">
        <div class="container-fluid">
            <form action="equipe" method="get" class="mx-auto col-12 col-lg-6" >
              <input type="hidden" name="filtre" value="search">
              <div class="row mb-3">
                  <label for="idTypeInput" class=" col-12 col-md-3 form-label">Categories</label>
                  <select name="idType" id="idTypeInput" class="col  form-select">
                      <option value="0">Choisir une categorie</option>
                      <% for( CategoryEquipe cat : categories ) {
                      %>
                        <option value="<%=cat.getIdCategory()%>"> <%=cat.getNomCategory()%> </option>
                      <% } %>
                  </select>
              </div>
              <div class="row mb-3">
                  <label for="nomInput" class=" col-12 col-md-3 form-label">Nom d'equipe</label>
                  <input type="text" name="nom" id="nomInput" class="col form-control">
              </div>
              <div>
                  <input type="submit" class=" text-light btn bg-prim-clr" value="Filtrer par nom">
              </div>
          </form>
        </div>
    </div>
  <!--? END FILTRE -->
    <div class="container">
        <!-- * BIG TITLE -->
          <div class="row">
            <div class="col-12 col-md-9">
              <h1 class="h1" id="">Liste des equipes E-Sport</h1>
            </div>
            <div class="col">
                <a href="equipe-formulaire" role="button" class="btn btn-success"> Ajouter </a>
            </div>
          </div>
        <!-- ? END BIG TITLE -->
          <!-- * DATA LIST -->
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Initiale</th>
                <th scope="col">Categorie</th>
                <th scope="col" colspan="2">Action</th>
              </tr>
            </thead>
            <tbody>
              <% for( Equipe e : equipes ){ %>
                <tr>
                  <th scope="row"> <%=e.getIdEquipe()%> </th>
                  <td> <%=e.getNomEquipe()%> </td>
                  <td> <%=e.getInitial()%> </td>
                  <td> <%=e.getCategoryEquipe().getNomCategory()%> </td>
                  <td>
                    <a href="equipe-formulaire?action=update&id=<%=e.getIdEquipe()%>" class="btn btn-outline-dark" role="button">
                      <i class="bi bi-pen-fill"></i>
                    </a>
                  </td>
                  <td>
                    <a href="equipe?action=delete&id=<%=e.getIdEquipe()%>" class="btn btn-outline-dark" role="button">
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
<script>
  activeCurrentPage("equipe-link");
</script>
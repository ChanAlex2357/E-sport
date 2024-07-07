<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Jeux"%>
<%@ page import="model.TypeJeux"%>
<%
  List<TypeJeux> listTypeJeux = (List<TypeJeux>) request.getAttribute("listTypeJeux"); 
  List<Jeux> listJeux = (List<Jeux>) request.getAttribute("listJeux");
%>
<div id="hero" class=" py-5">
  <!--* FILTRE -->
    <div id="filtre" class="p-5 bg-light mb-3">
        <div class="container-fluid">
          <!-- FILTRE PAR TYPE DE JEU -->
            <form action="jeux" method="get" class="mx-auto col-12 col-lg-6" >
              <input type="hidden" name="filtre" value="search">
              <div class="row mb-3">
                  <label for="idTypeInput" class=" col-12 col-md-3 form-label">Type de jeux</label>
                  <select name="idType" id="idTypeInput" class="col  form-select">
                      <option value="">Choisir le type du jeu</option>
                      <% for( TypeJeux type : listTypeJeux ) { %>
                        <option value="<%=type.getIdType()%>"><%= type.getNomType()%></option>
                      <%
                      } %>
                  </select>
              </div>
              <div class="row mb-3">
                  <label for="nomInput" class=" col-12 col-md-3 form-label">Nom du jeu</label>
                  <input type="text" name="nom" id="nomInput" class="col form-control">
              </div>
              <div>
                  <input type="submit" class=" text-light btn bg-prim-clr" value="Rechercher">
              </div>
          </form>
        </div>
    </div>
  <!--? END FILTRE -->
  <!--* MAIN CONTENT -->
    <div class="container">
        <!-- * BIG TITLE -->
          <div class="row">
            <div class="col-12 col-md-9">
              <h1 class="h1 col-12 col-md" id="">Liste des jeux</h1>
            </div>
            <div class="col">
                <a href="jeux-formulaire" role="button" class="btn btn-success"> Ajouter </a>
            </div>
          </div>
        <!-- ? END BIG TITLE  -->
          <!-- * DATA LISTE -->
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nom du Jeu</th>
                <th scope="col">Type du Jeux</th>
                <th scope="col" colspan="2">Action</th>
              </tr>
            </thead>
            <tbody>
              <% for( Jeux jeux : listJeux ) { %>
                <tr>
                  <th scope="row"> <%= jeux.getIdJeux() %> </th>
                  <td> <%= jeux.getNomJeux() %> </td>
                  <td> <%= jeux.getTypeJeux().getNomType() %> </td>
                  <td>
                    <a href="jeux-formulaire?action=update&id=<%= jeux.getIdJeux() %>" class="btn btn-outline-dark" role="button">
                      <i class="bi bi-pen-fill"></i>
                    </a>
                  </td>
                  <td>
                    <a href="jeux?action=delete&id=<%= jeux.getIdJeux() %>" class="btn btn-outline-dark" role="button">
                      <i class="bi bi-trash"></i>
                    </a>
                  </td>
                </tr>  
              <%
              } %>
            </tbody>
          </table>
          <!-- ? END DATA LISTE -->
    </div>
  <!--? END MAIN CONTENT -->
</div>
<%@ include file="../static/footer.html"%>
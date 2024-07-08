<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.CategoryEquipe"%>
<%@ page import="model.Equipe"%>
<%
    List<CategoryEquipe> categories = (List<CategoryEquipe>) request.getAttribute("listCategory");
    String validation = "Nouvelle Equipe";

    Equipe toUpdate = (Equipe) request.getAttribute("toUpdate");
    String nom = "";
    String initial = "";
    int idCat = 0;
    String action = "create";
    if(toUpdate != null){
        action = "update";
        nom = toUpdate.getNomEquipe();
        initial = toUpdate.getInitial();
        idCat = toUpdate.getIdCategory();
        validation = "Modifier";
    }
%>
<div id="hero py-5">
    <div class="container mt-5">
        <div class="row py-5">
            <div class="col-12 col-lg-6">
                <div class="mx-auto">
                    <form action="equipe?action=<%=action%>" method="post" class="">
                        <% if(toUpdate != null) { %>
                            <input type="hidden" name="id" value="<%=toUpdate.getIdEquipe()%>">
                        <% } %>
                        <h2> Formulaire Equipe</h2>
                        <div class="bg-dark p-1 w-100"></div>
                        <div class="form-group mb-3">
                            <label for="nomEquipeInput" class="form-label">Nom de l'equipe</label>
                            <input type="text" name="nomEquipe" id="nomEquipeInput" class="form-control" value="<%=nom%>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="initialInput" class="form-label">Les initiales</label>
                            <input type="text" name="initial" id="initialInput" class="form-control" value="<%=initial%>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="idCategoryInput" class="form-label">Categorie de l'equipe</label>
                            <select name="idCategory" id="idCategoryInput" class="form-select">
                                <option value="">Choisir une categorie</option>
                                <% for( CategoryEquipe cat : categories ){
                                    String etat = "";
                                    if( idCat == cat.getIdCategory() ){
                                        etat = "selected";
                                    }
                                %>
                                    <option value="<%= cat.getIdCategory() %>" <%=etat%> > <%=cat.getNomCategory()%> </option>
                                <% } %>
                            </select>
                        </div>
                        <div>
                            <input type="submit" class=" text-light btn bg-prim-clr" value="<%=validation%>">
                            <a href="equipe" role="button" class="btn btn-danger">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-12 col-lg-6 d-none d-lg-block">
                <img src="assets/images/undraw_team_re_0bfe.svg" alt="" class="img-fluid">
            </div>
        </div>
    </div>
</div>
<%@ include file="../static/footer.html"%>
<script>
    activeCurrentPage("equipe-link");
</script>
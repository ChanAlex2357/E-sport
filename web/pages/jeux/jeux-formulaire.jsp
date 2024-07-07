<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<%@ page import="model.TypeJeux"%>
<%@ page import="model.Jeux"%>
<%
    List<TypeJeux> listTypeJeux = (List<TypeJeux>) request.getAttribute("listTypeJeux");
    String validation = "Nouveau Jeu";

    Jeux toUpdate = (Jeux) request.getAttribute("toUpdate");
    String nom = "" ;
    int idType = 0 ;
    String action = "create";
    if(toUpdate != null){
        nom = toUpdate.getNomJeux();
        out.println(nom);
        idType = toUpdate.getIdType();
        out.println(idType);
        action = "update"; 
        validation =  "Modifier Jeu"
    }
%>
<div id="hero py-5">
    <div class="container mt-5">
        <div class="row py-5">
            <div class="col-12 col-lg-6">
                <div class="mx-auto">
                    <form action="jeux?action=<%=action%>" method="post" class="">
                        <h2> Formulaire de Jeu</h2>
                        <% if( toUpdate != null ) { %>
                            <input type="hidden" name="id" value="<%= toUpdate.getIdJeux() %>">
                        <% } %>
                        <div class="bg-dark p-1 w-100"></div>
                        <div class="form-group mb-3">
                            <label for="nomJeuxInput" class="form-label">Nom du jeu</label>
                            <input type="text" name="nomJeux" id="nomJeuxInput" class="form-control" value="<%= nom %>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="idTypeInput" class="form-label">Type de jeux</label>
                            <select name="idType" id="idTypeInput" class="form-select">
                                <option value="">Choisir le type du jeu</option>
                                <% for( TypeJeux type : listTypeJeux ) { 
                                    String state = "";
                                    if( idType == type.getIdType() ){
                                        state = "selected";
                                    }
                                %>
                                    <option value="<%=type.getIdType()%>" <%= state %> ><%= type.getNomType()%></option>
                                <%
                                } %>
                            </select>
                        </div>
                        <div>
                            <input type="submit" class=" text-light btn bg-prim-clr" value="<%=validation%>">
                            <a href="jeux" role="button" class="btn btn-danger">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-12 col-lg-6 d-none d-lg-block">
                <img src="assets/images/gaming.svg" alt="" class="img-fluid">
            </div>
        </div>
    </div>
</div>
<%@ include file="../static/footer.html"%>
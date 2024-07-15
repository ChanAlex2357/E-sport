<%@ include file="../static/header.html"%>
<%@ page import="java.util.List"%>
<div id="hero" class=" py-5">
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
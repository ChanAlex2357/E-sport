<%@ include file="static/header.html"%>
<div id="hero py-5">
    <div class="container mt-5">
        <div class="row py-5">
            <div class="col-12 col-lg-6">
                <div class="mx-auto">
                    <form action="jeux" method="post" class="">
                        <h2> Formulaire de Jeu</h2>
                        <div class="bg-dark p-1 w-100"></div>
                        <div class="form-group mb-3">
                            <label for="nomJeuxInput" class="form-label">Nom du jeu</label>
                            <input type="text" name="nomJeux" id="nomJeuxInput" class="form-control">
                        </div>
                        <div class="form-group mb-3">
                            <label for="idTypeInput" class="form-label">Type de jeux</label>
                            <select name="idType" id="idTypeInput" class="form-select">
                                <option value="">Choisir le type du jeu</option>
                                <option value="1">type 1</option>
                            </select>
                        </div>
                        <div>
                            <input type="submit" class=" text-light btn bg-prim-clr" value="Nouveau Jeu">
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
<%@ include file="static/footer.html"%>
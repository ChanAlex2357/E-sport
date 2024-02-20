<%@ include file="static/header.html"%>
<div id="hero" class=" py-5">
    <div id="filtre" class="p-5 bg-light mb-3">
        <div class="container-fluid">
            <form action=""class="mx-auto col-12 col-lg-6" >
                <div class="row mb-3">
                    <label for="idTypeInput" class=" col-12 col-md-3 form-label">Type de jeux</label>
                    <select name="idType" id="idTypeInput" class="col  form-select">
                        <option value="">Choisir le type du jeu</option>
                        <option value="1">type 1</option>
                    </select>
                </div>
                <div>
                    <input type="submit" class=" text-light btn bg-prim-clr" value="Filtrer">
                </div>
            </form>
        </div>
    </div>
    <div class="container">
        <div id="">
          <h1 class="h1" id="">Liste des jeux</h1>
          <!-- * Version tableau simple * -->
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
              <tr>
                <th scope="row"> 1 </th>
                <td> Nom </td>
                <td> type </td>
                </td>
                <td>
                  <a href="#" class="btn btn-outline-dark" role="button">
                    <i class="bi bi-pen-fill"></i>
                  </a>
                </td>
                <td>
                  <a href="#" class="btn btn-outline-dark" role="button">
                    <i class="bi bi-trash"></i>
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
    </div>
</div>
<%@ include file="static/footer.html"%>
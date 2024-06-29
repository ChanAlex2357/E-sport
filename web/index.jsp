<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/main.css">
    <title>Team Manager</title>
    <style>
        .admin-card{
            height: 300px;
        }
        .circle {
            width: 100px;
            height: 100px;
            border-radius:100%;
        }
        #plans {
            transform: translateY(-50px);
        }
    </style>
</head>
<body>
    <header class=""> 
        <nav class="navbar navbar-dark bg-prim-clr">
            <div class="container d-flex justify-content-center">
                <a class="navbar-brand"> E-sport Manager </a>
            </div>
        </nav>
    </header>
    <div id="hero" style="min-height:60vh;" class="p-5 bg-light">
        <div class="container">
            <div class="row">
                <!-- Hero text -->
                <div class="col-12 col-lg-6 d-flex flex-column justify-content-center">
                    <div>

                    <h1> Lets Organise </h1>
                    <p>
                        Si vous etes un organisateur d'evenement e-sport
                        vous etes au bon endroit . Sur ce site vous pouvez vous connectez en tant qu'organisateur
                        <span class="text-scd-clr">pour administrer vos evenements</span> 
                    </p>
                    <div class="d-flex gap-2">
                        <a type="button" href="login" class="btn bg-prim-clr text-light fw-bold">Connexion</a>
                    </div>
                </div>
            </div>
            <!-- Hero Ilustration -->
            <div class="col-12 col-lg-6">
                <img src="assets/images/undraw_businessman_e7v0.svg" alt="" class="img-fluid">
            </div>
        </div>
        </div>
    </div>
    <div id="plans" class="p-3">
        <div class="container">
            <div class="row gap-3">
                <div class="col-lg col-12 card admin-card">
                    <div class="card-title d-flex justify-content-center p-2"> 
                        <span class="circle bg-prim-clr d-flex flex-column justify-content-center">
                            <div class=" d-flex justify-content-center "> 
                                <h1 class="text-light">1</h1> 
                            </div>
                        </span>
                    </div>
                    <div class="card-body admin-info">
                        <div class="text-center card-text">
                            Administration des <p class="fw-bold text-scd-clr">Jeux</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg col-12 card admin-card">
                    <div class="card-title d-flex justify-content-center p-2"> 
                        <span class="circle bg-prim-clr d-flex flex-column justify-content-center">
                            <div class=" d-flex justify-content-center "> 
                                <h1 class="text-light">2</h1> 
                            </div>
                        </span>
                    </div>
                    <div class="card-body admin-info">
                        <div class="text-center card-text">
                            Administration des <p class="fw-bold text-scd-clr">Equipes E-sport</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg col-12 card admin-card">
                    <div class="card-title d-flex justify-content-center p-2"> 
                        <span class="circle bg-prim-clr d-flex flex-column justify-content-center">
                            <div class=" d-flex justify-content-center "> 
                                <h1 class="text-light">3</h1> 
                            </div>
                        </span>
                    </div>
                    <div class="card-body admin-info">
                        <div class="text-center card-text">
                            Administration des <p class="fw-bold text-scd-clr">Joueurs E-sport</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg col-12 card admin-card">
                    <div class="card-title d-flex justify-content-center p-2"> 
                        <span class="circle bg-prim-clr d-flex flex-column justify-content-center">
                            <div class=" d-flex justify-content-center "> 
                                <h1 class="text-light">4</h1> 
                            </div>
                        </span>
                    </div>
                    <div class="card-body admin-info">
                        <div class="text-center card-text">
                            Administration des <p class="fw-bold text-scd-clr">Tournois</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
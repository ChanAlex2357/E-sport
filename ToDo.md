# To do list : Site pour des coach ou il peut gerer ses team ( Team Manager )

## Exigences

1. 4 Entites
2. 2 Servlet par Entite
  -Acces Formulaire
  -GET( Read / Delete) et Post( Update / Create )
MODEL => gets , sets , getById , List getAll , update , delete , save

- affichage (VIEW)
- fonction (MODEL)
- donnee (SQL)
- Integraton (SERVLET)

### Login

- affichage (VIEW)
  - [x]formulaire ( mail , password )
- fonction (MODEL)
- donnee (SQL)
- Integraton (SERVLET)
  - LoginServlet
    - [x]Get
      - [x]dispatcher login.htlm
    - Post
      - redirection pageD'acueil
      - sinon redirection login.html

### index

- affichage (VIEW)
  - [x] index.html
    - [x] hero( presentation du site , cta)
    - [x] list des entites
- fonction (MODEL)
- donnee (SQL)
- Integraton (SERVLET)

### jeux

- affichage (VIEW)
  - [x] formulaire-jeux.html
    - [x] formulaire (nom , type)
  - read-jeux.html
    - [x] formulaire de filtre ( choisir le type )
    - [x] tableau de la liste des jeux ( idJeux , nom , type)
- fonction (MODEL)
  - TypeJeux (idType , nomType)
    - constructeurs
      - (id , nom) => sets
      - (id) => getById ()
    - gets , sets
    - getAll
    - getById
  - Jeux (idJeux , nomJeux , idType)
    - gets , sets
    - getAll
    - getById
    - Filtres
      - getByType
- donnee (SQL)
  - data test de type
  - creation de jeux (affichage)
- Integraton (SERVLET)
  - JeuxFormulaire
    - doGet()
      - getAll type de jeux
      - [x] dispatch => pages/formulaire-jeux.jsp
    - web.xml
      - [x] association sevlet
  - JeuxServlet
    - doGet() read , delete
      - getAll type de jeux
      - Filtre idType
        - if  not null => Jeux getByType
        - else => getAll Jeux
      - [x] dispatch la liste => jeux-read.jsp
    - doPost() create , update
      - recuperer valeur des champs
      - if action == create
        - save
      - elese action == update
        - recuperer l'id
        - update
    - redirection jeux

## Equipe

- affichage (VIEW)
  - equipe-formulaire.jsp
    - champs( nom , category[] )
  - equipe-read
    - formulaire filtre par category
    - tableau liste des equipes
- fonction (MODEL)
  - CategoryEquipe
    - constructeurs
      - id , nom , initial
      - id
    - get , set
    - getById
    - getAll
  - Equipe
    - constructeurs
      - id , nom , idCategory
      - id
    - get , set
    - getById
    - getAll
    - save
    - delete
    - update
    - Filtres
      - getByIdCategory
- donnee (SQL)
  - CategoryEquipe
    - amateur
    - semi-pro
    - pro
  - Equipe
    - Insquare (IS) semi
    - Exelcior (ExE) pro
    - Inviktus (IK) semi
    - Gascom Esport (GES) pro
    - Kudeta (KDT) pro
- Integraton (SERVLET)
  - EquipeFormulaire
    - doGet
      - recuperer la liste des Category
        - CategoryEquipe getAll()
      - dispatch => equipe-formulaire
  - EquipeServlet
    - doGet
      - Category Equipe getAll()
      - action = read
        - Equipe.getAll()
      - else action = delete && equipe != null
        - Equipe.delete()
      - else filtre idCategory
        - Equipe.getByIdCategory(idCategory)
    - doPost
      - recuperation des valeurs des champs
      - if action = create
        - Equipe.save
      - else action = update && idEquipe != NULL
        - Equipe.update

## Joueur

- affichage (VIEW)
  - joueur-formulaire.jsp
    - champs ( nom , pseudo , dateNaissance , equipe[] )
  - joueur-read.jsp
    - formulaire de filtre
      - age min - age max
      - equipe[]
    - liste des Joueurs
- fonction (MODEL)
  - Joueur
    - constructeurs
      - id , nom , pseudo , dateNaissance , idEquipe
      - id => getById
    - get , set
    - getById
    - getAll
    - save
    - delete
    - update
    - Filtre
      - getByAge ( min , max)
        - Exeption <> min > max
      - getByEquipe( idEquipe )
      - getByAgeAndEquipe(min , max , idEquipe)
- donnee (SQL)
  - ChatGpt existe
- Integraton (SERVLET)
  - JoueurFormulaire
    - doGet
      - recuperer la liste des equipes
        - Equipe.getAll
      - dispatch => joueur-formulaire
  - JoueurServlet
    - doGet
      - recuperer la liste des equipes
        - Equipe.getAll
      - action = read
        - Joueur.getAll
      - action = delete
        - Joueur.delete
      - filtre = age.min-max
        - Joueur.getByAge(ageMin , ageMax)
      - filtre = idEquipe
        - Joueur.getByEquipe(idEquipe)
      - filtre = age.min-max , idEquipe
        - Joueur.getByAgeEquipe( min , max , idE)
    - doPost
      - recuperer les valeurs des champs => new Joueur()
      - action = create
        - Joueur.save
      - action = update
        - Joueur.update

## Tournoi

- affichage (VIEW)
- fonction (MODEL)
- donnee (SQL)
- Integraton (SERVLET)

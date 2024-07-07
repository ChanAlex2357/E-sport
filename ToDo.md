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
      - [x] redirection pageD'acueil
      - [x] sinon redirection login.html

### index

- affichage (VIEW)
  - [x] index.html
    - [x] hero( presentation du site , cta)
    - [x] list des entites
- fonction (MODEL)
- donnee (SQL)
- Integraton (SERVLET)

### jeux

- [x] affichage (VIEW)
  - [x] formulaire-jeux.html
    - [x] formulaire (nom , type)
  - [x] read-jeux.html
    - [x] formulaire de filtre ( choisir le type )
    - [x] tableau de la liste des jeux ( idJeux , nom , type)
- [x] fonction (MODEL)
  - [x] TypeJeux (idType , nomType)
    - [x] constructeurs
      - [x] (id , nom) => sets
      - [x] (id) => getById ()
    - [x] gets , sets
    - [x] getAll
    - [x] getById
  - [x] Jeux (idJeux , nomJeux , idType)
    - [x] gets , sets
    - [x] getAll
    - [x] getById
    - [x] save
    - [x] delete
    - [x] update
    - [x] Filtres
      - [x] getByType
- [x] donnee (SQL)
  - [x] data test de type
  - [x] creation de jeux (affichage)
- [x] Integraton (SERVLET)
  - JeuxFormulaire
    - [x] doGet()
      - [x] getAll type de jeux
      - [x] dispatch => pages/formulaire-jeux.jsp
    - [x] web.xml
      - [x] association sevlet
  - [x] JeuxServlet
    - [x] doGet() read , delete
      - [x] getAll type de jeux
      - [x] Filtre idType
        - [x] if  not null => Jeux getByType
        - [x] else => getAll Jeux
      - [x] dispatch la liste => jeux-read.jsp
    - [x] doPost() create , update
      - [x] recuperer valeur des champs
      - [x] if action == create
        - [x] save
      - [x] elese action == update
        - [x] recuperer l'id
        - [x] update
    - [x] redirection jeux

## Equipe

- [x] affichage (VIEW)
  - [x] equipe-formulaire.jsp
    - [x] champs( nom , category[] )
  - [x] equipe-read
    - [x] formulaire filtre par category
    - [x] tableau liste des equipes
- [x] fonction (MODEL)
  - [x] CategoryEquipe
    - [x] constructeurs
      - [x] id , nom , initial
      - [x] id
    - [x] get , set
    - [x] getById
    - [x] getAll
  - [x]  Equipe
    - [x] constructeurs
      - [x] id , nom , idCategory
      - [x] id
    - [x] get , set
    - [x] getById
    - [x] getAll
    - [x] save
    - [x] delete
    - [x] update
    - Filtres
      - getByIdCategory
- [x] donnee (SQL)
  - [x] CategoryEquipe
    - [x] amateur
    - [x] semi-pro
    - [x] pro
  - [x] Equipe
    - [x] Insquare (IS) semi
    - [x] Exelcior (ExE) pro
    - [x] Inviktus (IK) semi
    - [x] Gascom Esport (GES) pro
    - [x] Kudeta (KDT) pro
- [x] Integraton (SERVLET)
  - [x] EquipeFormulaire
    - [x] doGet
      - [x] recuperer la liste des Category
        - [x] CategoryEquipe getAll()
      - [x] dispatch => equipe-formulaire
  - [x] EquipeServlet
    - [x] doGet
      - [x] Category Equipe getAll()
      - [x] action = read
        - [x] Equipe.getAll()
      - [x] else action = delete && equipe != null
        - [x] Equipe.delete()
      - [x] else filtre idCategory
        - [x] Equipe.getByIdCategory(idCategory)
    - [x] doPost
      - [x] recuperation des valeurs des champs
      - [x] if action = create
        - [x] Equipe.save
      - [x] else action = update && idEquipe != NULL
        - [x] Equipe.update

## Joueur

- [x] affichage (VIEW)
  - [x] joueur-formulaire.jsp
    -[x] champs ( nom , pseudo , dateNaissance , equipe[] )
  - [x] joueur-read.jsp
    - [x] formulaire de filtre
      - [x] age min - age max
      - [x] equipe[]
    - [x] liste des Joueurs
- fonction (MODEL)
  - Joueur
    - [x] constructeurs
      - [x] id , nom , pseudo , dateNaissance , idEquipe
      - [x] id => getById
    - [x] get , set
    - [x] getById
    - [x] getAll
    - [x] save
    - [x] delete
    - [x] update
    - Filtre
      - getByAge ( min , max)
        - Exeption <> min > max
      - getByEquipe( idEquipe )
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

- [x] affichage (VIEW)
  - [x] tournoi-formulaire
    - [x] champs (nom , date debut , lieu , duree , jeux[] )
  - [x] tournoi
    - [x] filtre date , duree
    - [x] liste des tournois cree
- fonction (MODEL)
  - [x] constructeurs
    - [x] id , nom , date , lieu , idJeux
    - [x] id => getById
  - [x] get , set
  - [x] getById
  - [x] getAll
  - [x] save
  - [x] delete
  - [x] update
  - Filtre
    - getByDate (min , max)
    - getByJeux
- donnee (SQL)
- Integraton (SERVLET)

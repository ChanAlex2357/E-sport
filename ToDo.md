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
    - formulaire de filtre ( choisir le type )
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

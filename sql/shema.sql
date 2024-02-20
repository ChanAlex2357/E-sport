Create table Organisateur (
    idOrganisateur Serial primary key ,
    nom varchar(50),
    prenom varchar(50),
    age integer,
    mail varchar(50),
    password varchar(50)
)


CREATE TABLE TypeJeux(
   idType INTEGER,
   nomType VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idType)
);

CREATE TABLE CategoryEquipe(
   idCategory INTEGER,
   nomCategory VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idCategory)
);

CREATE TABLE Jeux(
   idJeux INTEGER,
   nomJeux VARCHAR(50)  NOT NULL,
   idType INTEGER,
   PRIMARY KEY(idJeux),
   FOREIGN KEY(idType) REFERENCES TypeJeux(idType)
);

CREATE TABLE Tournoi(
   idTournoi INTEGER,
   nomTournoi VARCHAR(50)  NOT NULL,
   dateTournoi DATE NOT NULL,
   lieuTournoi VARCHAR(50)  NOT NULL,
   idJeux INTEGER NOT NULL,
   PRIMARY KEY(idTournoi),
   FOREIGN KEY(idJeux) REFERENCES Jeux(idJeux)
);

CREATE TABLE Equipe(
   idEquipe INTEGER,
   nomEquipe VARCHAR(50)  NOT NULL,
   idCategory INTEGER NOT NULL,
   PRIMARY KEY(idEquipe),
   FOREIGN KEY(idCategory) REFERENCES CategoryEquipe(idCategory)
);

CREATE TABLE Joueur(
   idJoueur INTEGER,
   nomJoueur VARCHAR(50)  NOT NULL,
   pseudo VARCHAR(50)  NOT NULL,
   dateNaissance DATE NOT NULL,
   idEquipe INTEGER NOT NULL,
   PRIMARY KEY(idJoueur),
   FOREIGN KEY(idEquipe) REFERENCES Equipe(idEquipe)
);

CREATE TABLE Participation(
   idJoueur INTEGER,
   idTournoi INTEGER,
   PRIMARY KEY(idJoueur, idTournoi),
   FOREIGN KEY(idJoueur) REFERENCES Joueur(idJoueur),
   FOREIGN KEY(idTournoi) REFERENCES Tournoi(idTournoi)
);

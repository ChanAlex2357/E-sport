CREATE TABLE TypeJeux(
   idType Serial,
   nomType VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idType)
);

CREATE TABLE CategoryEquipe(
   idCategory Serial,
   nomCategory VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idCategory)
);

CREATE TABLE Jeux(
   idJeux Serial ,
   nomJeux VARCHAR(50)  NOT NULL,
   idType INTEGER,
   PRIMARY KEY(idJeux),
   FOREIGN KEY(idType) REFERENCES TypeJeux(idType)
);

CREATE TABLE Tournoi(
   idTournoi Serial,
   nomTournoi VARCHAR(50)  NOT NULL,
   dateTournoi DATE NOT NULL,
   duree INTEGER NOT NULL,
   lieuTournoi VARCHAR(50)  NOT NULL,
   idJeux INTEGER NOT NULL,
   PRIMARY KEY(idTournoi),
   FOREIGN KEY(idJeux) REFERENCES Jeux(idJeux)
);

CREATE TABLE Equipe(
   idEquipe Serial,
   nomEquipe VARCHAR(50)  NOT NULL,
   initial VARCHAR(50) NOT NULL,
   idCategory INTEGER NOT NULL,
   PRIMARY KEY(idEquipe),
   FOREIGN KEY(idCategory) REFERENCES CategoryEquipe(idCategory)
);

CREATE TABLE Joueur(
   idJoueur Serial,
   nomJoueur VARCHAR(50)  NOT NULL,
   pseudo VARCHAR(50)  NOT NULL,
   dateNaissance DATE NOT NULL,
   idEquipe INTEGER ,
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


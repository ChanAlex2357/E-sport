INSERT INTO Organisateur (nom, prenom, age, mail, password) 
VALUES 
    ('Dupont', 'Jean', 35, 'jean.dupont@example.com', 'password1'),
    ('Martin', 'Marie', 28, 'marie.martin@example.com', 'password2'),
    ('Lefevre', 'Pierre', 42, 'pierre.lefevre@example.com', 'password3'),
    ('Dubois', 'Sophie', 31, 'sophie.dubois@example.com', 'password4'),
    ('Moreau', 'Thomas', 37, 'thomas.moreau@example.com', 'password5');

-- Type Jeux
Insert into TypeJeux (nomType) values  
('Combat'),
('Aventure'),
('Sport'),
('Moba'),
('Fps');

-- Category Equipe
INSERT INTO CategoryEquipe (idCategory, nomCategory)
VALUES
    (1, 'Amateur'),
    (2, 'Semi-Pro'),
    (3, 'Pro');

-- Participation state
INSERT INTO ParticipationState (state) values
('registred'),
('unregistred');

CREATE OR REPLACE VIEW v_participation as
select Participation.* , ParticipationState.state from Participation join ParticipationState on Participation.idState = ParticipationState.idState;

CREATE OR REPLACE FUNCTION get_tournois_pour_joueur(id_joueur INTEGER)
RETURNS TABLE (
    idTournoi INTEGER,
    nomTournoi VARCHAR,
    dateTournoi DATE,
    duree INTEGER,
    lieuTournoi VARCHAR,
    idJeux INTEGER,
    participationStatus VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        t.idTournoi,
        t.nomTournoi,
        t.dateTournoi,
        t.duree,
        t.lieuTournoi,
        t.idJeux,
        ps.state AS participationStatus
    FROM 
        Tournoi t
    LEFT JOIN 
        Participation p ON t.idTournoi = p.idTournoi AND p.idJoueur = id_joueur
    LEFT JOIN 
        ParticipationState ps ON p.idState = ps.idState;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculer_age(date_naissance date)
RETURNS integer AS $$
DECLARE
    age integer;
BEGIN
    SELECT EXTRACT(YEAR FROM age(current_date, date_naissance)) INTO age;
    RETURN age;
END;
$$ LANGUAGE plpgsql;

Create or replace view v_joueur as
select * , calculer_age(dateNaissance) as age 
from Joueur;


Drop table Participation;
Drop table Joueur;
Drop table Equipe;
Drop table Tournoi;
Drop table Jeux;
Drop table CategoryEquipe;
Drop table TypeJeux;
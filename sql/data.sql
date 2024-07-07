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
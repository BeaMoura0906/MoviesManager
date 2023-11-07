/* creation suppression des tables */

DROP TABLE IF EXISTS  movies;
DROP TABLE IF EXISTS genres ;

CREATE TABLE genres (
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL

);

CREATE TABLE movies (
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   releaseDate YEAR NOT NULL,
   genre_id INT ,
   FOREIGN KEY(genre_id) REFERENCES genres(id) ON DELETE SET NULL ON UPDATE CASCADE
);

/*  insertion */
    INSERT INTO genres (nom) VALUES('aucun');
    INSERT INTO genres (nom) VALUES('fantastique');
    INSERT INTO genres (nom) VALUES('policier');
    INSERT INTO genres (nom) VALUES('romantique');
    INSERT INTO genres (nom) VALUES('animation');

    INSERT INTO movies (nom,releaseDate,genre_id) VALUES("Harry  Potter à l'ecole des sorcier","2001",2);
    INSERT INTO movies (nom,releaseDate,genre_id) VALUES("Hercule Poirot","1995",3);
    INSERT INTO movies (nom,releaseDate,genre_id) VALUES("Titanic","1998",4);
    INSERT INTO movies (nom,releaseDate,genre_id) VALUES("Megamind","2010",5);

/* selection des données */

SELECT m.nom,releaseDate,g.nom  FROM movies m join genres g on m.genre_id = g.id;



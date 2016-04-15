CREATE TABLE languages(
	LangId INT PRIMARY KEY AUTO_INCREMENT,
	Lang VARCHAR(20) NOT NULL
	
);

INSERT INTO languages VALUES(1,'Hindi');
INSERT INTO languages VALUES(2,'English');
INSERT INTO languages VALUES(3,'Marathi');
INSERT INTO languages VALUES(4,'Gujarati');
INSERT INTO languages VALUES(5,'Tamil');
INSERT INTO languages VALUES(6,'Telegu');
INSERT INTO languages VALUES(7,'Punjabi');

SELECT * FROM languages;
DROP TABLE languages;

CREATE TABLE actors(
	actorId INT PRIMARY KEY AUTO_INCREMENT,
	firstName VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL
);                       

INSERT INTO actors VALUES(1,'Shah Rukh', 'Khan');
INSERT INTO actors VALUES(2'Salman'. 'Khan');
INSERT INTO actors VALUES(3,'Saif Ali', 'Khan');
INSERT INTO actors VALUES(4,'Amir','Khan');
INSERT INTO actors VALUES(5,'Amitabh','Bacchan');
INSERT INTO actors VALUES(6,'Ranbir','Kapoor');
INSERT INTO actors VALUES(7,'Ranveer','Singh');
INSERT INTO actors VALUES(8,'Arjun','Kapoor');
INSERT INTO actors VALUES(9,'Siddharth','Kapoor');
INSERT INTO actors VALUES(10,'Hrithik',' Roshan');
INSERT INTO actors VALUES(11,'Sanjay',' Dutt');
INSERT INTO actors VALUES(12,'Akshay',' Kumar');
INSERT INTO actors VALUES(13,'Varun',' Dhavan');
INSERT INTO actors VALUES(14,'Deepika',' Padukone');
INSERT INTO actors VALUES(15,'Katrina',' Kaif');
INSERT INTO actors VALUES(16,'Alia',' Bhatt');
INSERT INTO actors VALUES(17,'Parineeti',' Chopra');
INSERT INTO actors VALUES(18,'Kriti ','Senon');
INSERT INTO actors VALUES(19,'Priyanka ','Chopra');
INSERT INTO actors VALUES(20,'Kareena ','Kapoor');
INSERT INTO actors VALUES(22,'Madhuri',' Dixit');
INSERT INTO actors VALUES(23,'Juhi ','Chawla');
INSERT INTO actors VALUES(24,'Siddharth',' Malhotra');
INSERT INTO actors VALUES(25,'Sri',' Devi');
INSERT INTO actors VALUES(26,'Aishwarya',' Rai Bacchan');
INSERT INTO actors VALUES(27,'Bipasha',' Basu');
INSERT INTO actors VALUES(29,'Jacqulene ','Farnandis');
 
SELECT * FROM actors;
DROP TABLE actors;

CREATE TABLE category(
	categoryId INT PRIMARY KEY,
	category VARCHAR(20)
);

INSERT INTO category VALUES(1,'Horror');
INSERT INTO category VALUES(2,'Comedy');
INSERT INTO category VALUES(3,'Romance');
INSERT INTO category VALUES(4,'Action');
INSERT INTO category VALUES(5,'Drama');
INSERT INTO category VALUES(6,'Bio-Pic');
INSERT INTO category VALUES(7,'Documentary');

SELECT * FROM category;
DROP TABLE category;

CREATE TABLE film(
	filmId INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description VARCHAR(100),
	releaseYear DATE NOT NULL,
	originalLanguage INT REFERENCES languages(languageId),
	rentalduration DATE NOT NULL,
	LENGTH INT NOT NULL,
	replacementCost INT NOT NULL,
	rating INT NOT NULL,
	specialFeatures VARCHAR(100),
	category INT REFERENCES category(categoryId)
);
SELECT * FROM film;
DROP TABLE film;

CREATE TABLE film_actors(
	filmId INT REFERENCES film(filmId),
	actorId INT REFERENCES actors(actorId)

);
SELECT * FROM film_actors;
DROP TABLE film_actors;


CREATE TABLE film_languages(
	filmId INT REFERENCES film(filmId),
	languageId INT REFERENCES languages(languageId)

);
SELECT * FROM film_languages;
DROP TABLE film_languages;



                                                                                                                                                                                                               
                                                                                                                                                     
CREATE TABLE 'COCKTAIL' (
	'ID_COCKTAIL'	INT,
	'NAME'	VARCHAR(200) NOT NULL,
	'AUTHOR'	VARCHAR(200),
	'IMAGE_URL'	VARCHAR(200) NOT NULL,
	'INFO'	VARCHAR(2000),
	'PREPARATION'	VARCHAR(5000) NOT NULL,
	PRIMARY KEY('ID_COCKTAIL')
);
CREATE TABLE 'INGREDIENT' (
	'ID_INGREDIENT'	INT,
	'NAME'	VARCHAR(200) NOT NULL,
	PRIMARY KEY('ID_INGREDIENT')
);
CREATE TABLE 'REL_COCKTAIL_INGREDIENT' (
	'ID_COCKTAIL_INGREDIENT'	INT,
	'ID_COCKTAIL'	INT NOT NULL,
	'ID_INGREDIENT'	INT NOT NULL,
	'AMOUNT'	VARCHAR(200) NOT NULL,
	FOREIGN KEY('ID_INGREDIENT') REFERENCES 'INGREDIENT'('ID_INGREDIENT'),
	FOREIGN KEY('ID_COCKTAIL') REFERENCES 'COCKTAIL'('ID_COCKTAIL'),
	PRIMARY KEY('ID_COCKTAIL_INGREDIENT')
);
INSERT INTO 'COCKTAIL' ('ID_COCKTAIL','NAME','AUTHOR','IMAGE_URL','INFO','PREPARATION') VALUES (1,'Bellini','antoniosa','c01.png','Uno de los principales cócteles fáciles de preparar es el bellini, un trago típico de Venecia creado en 1948. Se dice que su nombre viene del pintor renacentista Giovanni Bellini, famoso por emplear el color rosa en sus pinturas. Además, se convirtió en la bebida favorita de grandes personajes como Ernest Hemingway y Orson Welles.','Prepara el zumo licuando los duraznos. Puedes agregar hielo mientras licuas. Este cocktail se sirve en una copa de champán. Agrega el zumo y luego el champán. Cuidadosamente, mezcla con una cucharilla larga hasta que todo se una. Para darle el tono rosa que tradicionalmente lleva, agrega zumo de cereza. También puedes utilizar un champán rosado.'),
 (2,'Tequila sunrise','cechope','c02.png','Como su nombre indica, el tequila sunrise se prepara con tequila. Se creó en un hotel de Phoenix en Estados Unidos y su nombre ''sunrise'' se debe a los colores que se ven en el vaso, que son semejantes a los del amanecer.','Coloca el hielo en un vaso largo y agrega el tequila y el zumo de naranja. Incorpora la granadina y remueve. Así crearás el efecto cromático. Decora con una cereza o un trozo de naranja.'),
 (3,'Daiquiri','armandoroa','c03.png','Entre los cócteles fáciles de preparar con ron destaca el daiquiri, una bebida originaria de Cuba. La creó un ingeniero estadounidense que trabajaba en unas minas con el nombre de este trago.','Licua todos los ingredientes con el hielo y coloca la mezcla en una coctelera. Agita, cuela y sirve en una copa para margaritas.');
INSERT INTO 'INGREDIENT' ('ID_INGREDIENT','NAME') VALUES (1,'champan'),
 (2,'zumo de durazno'),
 (3,'zumo de cerezas'),
 (4,'tequila'),
 (5,'zumo de naranja'),
 (6,'granada'),
 (7,'hielo'),
 (8,'pulpa de fruta'),
 (9,'ron blanco'),
 (10,'zumo de limón'),
 (11,'azúcar'),
 (12,'licor de frutas');
INSERT INTO 'REL_COCKTAIL_INGREDIENT' ('ID_COCKTAIL_INGREDIENT','ID_COCKTAIL','ID_INGREDIENT','AMOUNT') VALUES (1,1,1,'2 OZ'),
 (2,1,2,'2 OZ'),
 (3,1,3,'1 OZ'),
 (4,2,4,'2 OZ'),
 (5,2,5,'4 OZ'),
 (6,2,6,'1/4'),
 (7,2,7,' '),
 (8,3,8,'1/2 taza'),
 (9,3,9,'2 OZ'),
 (10,3,10,'1 OZ'),
 (11,3,11,'2 CUCHARADAS'),
 (12,3,12,'1 OZ'),
 (13,3,7,' ');
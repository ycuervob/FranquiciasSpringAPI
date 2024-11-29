CREATE TABLE franquicias_db.franquicia (
	franq_id INT UNSIGNED auto_increment NOT NULL,
	franq_nombre varchar(1000) NOT NULL,
	CONSTRAINT franquicia_PK PRIMARY KEY (franq_id)
)
CREATE TABLE franquicias_db.sucursal (
	suc_id INT UNSIGNED auto_increment NOT NULL,
	suc_nombre varchar(1000) NOT NULL,
	franq_id INT UNSIGNED NOT NULL,
	CONSTRAINT sucursal_pk PRIMARY KEY (suc_id),
	CONSTRAINT sucursal_franquicia_FK FOREIGN KEY (franq_id) REFERENCES franquicias_db.franquicia(franq__id)
)
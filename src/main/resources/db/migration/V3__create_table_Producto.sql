CREATE TABLE franquicias_db.producto (
	prod_id INT UNSIGNED auto_increment NOT NULL,
	prod_stock INTEGER NOT NULL,
	prod_nombre varchar(1000) NOT NULL,
	suc_id INT UNSIGNED NOT NULL,
	CONSTRAINT producto_pk PRIMARY KEY (prod_id),
	CONSTRAINT producto_sucursal_FK FOREIGN KEY (suc_id) REFERENCES franquicias_db.sucursal(suc_id)
)
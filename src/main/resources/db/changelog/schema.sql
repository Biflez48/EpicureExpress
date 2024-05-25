-- DROP SEQUENCE bucket_idbuc_seq;

CREATE SEQUENCE bucket_idbuc_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE categories_idcateg_seq;

CREATE SEQUENCE categories_idcateg_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE listcateg_idlist_seq;

CREATE SEQUENCE listcateg_idlist_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE nomenclatures_idnom_seq;

CREATE SEQUENCE nomenclatures_idnom_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE orders_idord_seq;

CREATE SEQUENCE orders_idord_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE prodsord_idpr_seq;

CREATE SEQUENCE prodsord_idpr_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE roles_idrol_seq;

CREATE SEQUENCE roles_idrol_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE statuses_idst_seq;

CREATE SEQUENCE statuses_idst_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE typesprod_idtype_seq;

CREATE SEQUENCE typesprod_idtype_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE users_idus_seq;

CREATE SEQUENCE users_idus_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- categories определение

-- Drop table

-- DROP TABLE categories;

CREATE TABLE categories (
	idcateg serial4 NOT NULL,
	namecateg text NOT NULL,
	codecateg text NULL,
	CONSTRAINT categories_pkey PRIMARY KEY (idcateg)
);


-- roles определение

-- Drop table

-- DROP TABLE roles;

CREATE TABLE roles (
	idrol serial4 NOT NULL,
	namerol text NOT NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (idrol)
);


-- statuses определение

-- Drop table

-- DROP TABLE statuses;

CREATE TABLE statuses (
	idst serial4 NOT NULL,
	namest text NOT NULL,
	CONSTRAINT statuses_pkey PRIMARY KEY (idst)
);


-- typesprod определение

-- Drop table

-- DROP TABLE typesprod;

CREATE TABLE typesprod (
	idtype serial4 NOT NULL,
	nametype text NOT NULL,
	codetype text NOT NULL,
	CONSTRAINT typesprod_pkey PRIMARY KEY (idtype)
);


-- nomenclatures определение

-- Drop table

-- DROP TABLE nomenclatures;

CREATE TABLE nomenclatures (
	idnom serial4 NOT NULL,
	idtype int4 NULL,
	namenom text NOT NULL,
	priceprod numeric NOT NULL,
	countpur int4 NULL,
	imgnom bytea NULL,
	CONSTRAINT nomenclatures_pkey PRIMARY KEY (idnom),
	CONSTRAINT nomenclatures_fkey_idtype FOREIGN KEY (idtype) REFERENCES typesprod(idtype) ON DELETE SET NULL ON UPDATE CASCADE
);


-- users определение

-- Drop table

-- DROP TABLE users;

CREATE TABLE users (
	idus serial4 NOT NULL,
	loginus text NOT NULL,
	passwordus text NULL,
	idrol int4 NULL,
	CONSTRAINT users_pkey PRIMARY KEY (idus),
	CONSTRAINT users_fkey_idrol FOREIGN KEY (idrol) REFERENCES roles(idrol) ON DELETE SET NULL ON UPDATE CASCADE
);


-- bucket определение

-- Drop table

-- DROP TABLE bucket;

CREATE TABLE bucket (
	idbuc serial4 NOT NULL,
	idus int4 NOT NULL,
	idnom int4 NOT NULL,
	cntprod int4 NOT NULL,
	CONSTRAINT bucket_pkey PRIMARY KEY (idbuc),
	CONSTRAINT bucket_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT bucket_fkey_idus FOREIGN KEY (idus) REFERENCES users(idus) ON DELETE CASCADE ON UPDATE CASCADE
);


-- listcateg определение

-- Drop table

-- DROP TABLE listcateg;

CREATE TABLE listcateg (
	idlist serial4 NOT NULL,
	idcateg int4 NULL,
	idnom int4 NULL,
	CONSTRAINT listcateg_pkey PRIMARY KEY (idlist),
	CONSTRAINT listcateg_fkey_idcateg FOREIGN KEY (idcateg) REFERENCES categories(idcateg) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT listcateg_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE
);


-- orders определение

-- Drop table

-- DROP TABLE orders;

CREATE TABLE orders (
	idord serial4 NOT NULL,
	idus int4 NOT NULL,
	idst int4 NULL,
	dateord date NOT NULL,
	sumprice numeric NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (idord),
	CONSTRAINT orders_fkey_idst FOREIGN KEY (idst) REFERENCES statuses(idst) ON DELETE SET NULL ON UPDATE CASCADE,
	CONSTRAINT orders_fkey_idus FOREIGN KEY (idus) REFERENCES users(idus) ON DELETE CASCADE ON UPDATE CASCADE
);


-- prodsord определение

-- Drop table

-- DROP TABLE prodsord;

CREATE TABLE prodsord (
	idpr serial4 NOT NULL,
	idord int4 NOT NULL,
	idnom int4 NOT NULL,
	cntprod int4 NOT NULL,
	CONSTRAINT prodsord_pkey PRIMARY KEY (idpr),
	CONSTRAINT prodsord_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT prodsord_fkey_idord FOREIGN KEY (idord) REFERENCES orders(idord) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO roles (namerol)
VALUES ('admin'),
	   ('customer'),
	   ('courier');

INSERT INTO statuses (namest)
VALUES ('Передача в доставку'),
	   ('В пути'),
	   ('Доставлен');

INSERT INTO categories (namecateg, codecateg)
VALUES ('Овощи и фрукты','vegetables'),
	   ('Булки','bread'),
	   ('Ингредиенты','ingredients'),
	   ('Напитки','drinks'),
	   ('Снеки','snacks'),
	   ('Мясо','meat'),
	   ('Бакалея','groceries');

INSERT INTO typesprod (nametype, codetype)
VALUES ('Готовые блюда','readymeals'),
	   ('Напитки','drinks'),
	   ('Продукты','products');

INSERT INTO users (loginus, passwordus, idrol)
VALUES ('admin','admin',1),
	   ('user01','user01',2),
	   ('courier01','courier01',3);
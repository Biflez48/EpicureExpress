CREATE SEQUENCE bucket_idbuc_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE categories_idcateg_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE listcateg_idlist_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE nomenclatures_idnom_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE orders_idord_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE prodsord_idpr_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE roles_idrol_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE statuses_idst_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE typesprod_idtype_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE users_idus_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE categories (
							idcateg serial4 NOT NULL,
							namecateg text NOT NULL,
							codecateg text NULL,
							CONSTRAINT categories_pkey PRIMARY KEY (idcateg)
);

CREATE TABLE roles (
					   idrol serial4 NOT NULL,
					   namerol text NOT NULL,
					   CONSTRAINT roles_pkey PRIMARY KEY (idrol)
);

CREATE TABLE statuses (
						  idst serial4 NOT NULL,
						  namest text NOT NULL,
						  CONSTRAINT statuses_pkey PRIMARY KEY (idst)
);

CREATE TABLE typesprod (
						   idtype serial4 NOT NULL,
						   nametype text NOT NULL,
						   codetype text NOT NULL,
						   CONSTRAINT typesprod_pkey PRIMARY KEY (idtype)
);

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

CREATE TABLE users (
					   idus serial4 NOT NULL,
					   loginus text NOT NULL,
					   passwordus text NULL,
					   idrol int4 NULL,
					   CONSTRAINT users_pkey PRIMARY KEY (idus),
					   CONSTRAINT users_fkey_idrol FOREIGN KEY (idrol) REFERENCES roles(idrol) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE bucket (
						idbuc serial4 NOT NULL,
						idus int4 NOT NULL,
						idnom int4 NOT NULL,
						cntprod int4 NOT NULL,
						CONSTRAINT bucket_pkey PRIMARY KEY (idbuc),
						CONSTRAINT bucket_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE,
						CONSTRAINT bucket_fkey_idus FOREIGN KEY (idus) REFERENCES users(idus) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE listcateg (
						   idlist serial4 NOT NULL,
						   idcateg int4 NULL,
						   idnom int4 NULL,
						   CONSTRAINT listcateg_pkey PRIMARY KEY (idlist),
						   CONSTRAINT listcateg_fkey_idcateg FOREIGN KEY (idcateg) REFERENCES categories(idcateg) ON DELETE CASCADE ON UPDATE CASCADE,
						   CONSTRAINT listcateg_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orders (
						idord serial4 NOT NULL,
						idus int4 NOT NULL,
						idst int4 NULL,
						dateord date NOT NULL,
						sumprice numeric NOT NULL,
						addressord text NULL,
						CONSTRAINT orders_pkey PRIMARY KEY (idord),
						CONSTRAINT orders_fkey_idst FOREIGN KEY (idst) REFERENCES statuses(idst) ON DELETE SET NULL ON UPDATE CASCADE,
						CONSTRAINT orders_fkey_idus FOREIGN KEY (idus) REFERENCES users(idus) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE prodsord (
						  idpr serial4 NOT NULL,
						  idord int4 NOT NULL,
						  idnom int4 NOT NULL,
						  cntprod int4 NOT NULL,
						  CONSTRAINT prodsord_pkey PRIMARY KEY (idpr),
						  CONSTRAINT prodsord_fkey_idnom FOREIGN KEY (idnom) REFERENCES nomenclatures(idnom) ON DELETE CASCADE ON UPDATE CASCADE,
						  CONSTRAINT prodsord_fkey_idord FOREIGN KEY (idord) REFERENCES orders(idord) ON DELETE CASCADE ON UPDATE CASCADE
);

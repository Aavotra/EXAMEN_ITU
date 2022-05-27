DROP VIEW plat_commande;
DROP VIEW pourboire;
DROP VIEW detail_livraison;
DROP VIEW livraison_client;
DROP VIEW user_info;
DROP VIEW addition_table;
DROP VIEW addition_table_temp;
DROP VIEW menu;

DROP TABLE livraison cascade;
DROP TABLE utilisateur cascade;
DROP TABLE profil cascade;
DROP TABLE inventaire_produit cascade;
DROP TABLE inventaire_ingredient cascade;
DROP TABLE details_commande cascade;
DROP TABLE commande cascade;
-- DROP TABLE livreur cascade;
DROP TABLE stock_ingredient cascade;
DROP TABLE stock_produit cascade;
DROP TABLE recette cascade;
DROP TABLE prix_produit cascade;
DROP TABLE produit cascade;
DROP TABLE categorie cascade;
DROP TABLE prix_ingredient cascade;
DROP TABLE ingredient cascade;
DROP TABLE point_livraison cascade;

CREATE TABLE "point_livraison" (
  "id" SERIAL PRIMARY KEY,
  "designation" varchar,
  "categorie" varchar
);

CREATE TABLE "ingredient" (
  "id" SERIAL PRIMARY KEY,
  "nom" varchar
);

CREATE TABLE "prix_ingredient" (
  "id" SERIAL PRIMARY KEY,
  "id_ingredient" int,
  "montant" double precision,
  "date" timestamp
);

CREATE TABLE "categorie" (
  "id" SERIAL PRIMARY KEY,
  "nom" varchar
);

CREATE TABLE "produit" (
  "id" SERIAL PRIMARY KEY,
  "id_categorie" int,
  "nom" varchar,
  "image" varchar
);

CREATE TABLE "prix_produit" (
  "id" SERIAL PRIMARY KEY,
  "id_produit" int,
  "montant" double precision,
  "date" timestamp
);

CREATE TABLE "recette" (
  "id" SERIAL PRIMARY KEY,
  "id_produit" int,
  "id_ingredient" int,
  "quantite" double precision
);

CREATE TABLE "stock_produit" (
  "id" SERIAL PRIMARY KEY,
  "id_produit" int,
  "designation" varchar,
  "quantite" double precision,
  "date" timestamp
);

CREATE TABLE "stock_ingredient" (
  "id" SERIAL PRIMARY KEY,
  "id_ingredient" int,
  "designation" varchar,
  "quantite" double precision,
  "date" timestamp
);

-- CREATE TABLE "livreur" (
--   "id" SERIAL PRIMARY KEY,
--   "nom" varchar,
--   "categorie_livraison" varchar
-- );

CREATE TABLE "commande" (
  "id" SERIAL PRIMARY KEY,
  "date_heure" timestamp,
  "id_table" int
);

CREATE TABLE "details_commande" (
  "id" SERIAL PRIMARY KEY,
  "id_commande" int,
  "id_produit" int,
  "id_serveur" int,
  "etat" int,
  "est_paye" int
);

CREATE TABLE "inventaire_ingredient" (
  "id" SERIAL PRIMARY KEY,
  "id_ingredient" int,
  "qte" double precision,
  "date" timestamp
);

CREATE TABLE "inventaire_produit" (
  "id" SERIAL PRIMARY KEY,
  "id_produit" int,
  "qte" double precision,
  "date" timestamp
);

CREATE TABLE "profil" (
  "id" SERIAL PRIMARY KEY,
  "nom" varchar
);

CREATE TABLE "utilisateur" (
  "id" SERIAL PRIMARY KEY,
  "username" varchar,
  "password" varchar,
  "id_profil" int
);

CREATE TABLE "livraison" (
  "id" SERIAL PRIMARY KEY,
  "numero" varchar,
  "id_livreur" int references utilisateur(id),
  "id_commande" int references commande(id),
  "contact" varchar,
  "lieu" varchar
);

ALTER TABLE "prix_ingredient" ADD FOREIGN KEY ("id_ingredient") REFERENCES "ingredient" ("id");

ALTER TABLE "produit" ADD FOREIGN KEY ("id_categorie") REFERENCES "categorie" ("id");

ALTER TABLE "prix_produit" ADD FOREIGN KEY ("id_produit") REFERENCES "produit" ("id");

ALTER TABLE "recette" ADD FOREIGN KEY ("id_produit") REFERENCES "produit" ("id");

ALTER TABLE "recette" ADD FOREIGN KEY ("id_ingredient") REFERENCES "ingredient" ("id");

ALTER TABLE "stock_produit" ADD FOREIGN KEY ("id_produit") REFERENCES "produit" ("id");

ALTER TABLE "stock_ingredient" ADD FOREIGN KEY ("id_ingredient") REFERENCES "ingredient" ("id");

ALTER TABLE "commande" ADD FOREIGN KEY ("id_table") REFERENCES "point_livraison" ("id");

ALTER TABLE "details_commande" ADD FOREIGN KEY ("id_commande") REFERENCES "commande" ("id");

ALTER TABLE "details_commande" ADD FOREIGN KEY ("id_produit") REFERENCES "produit" ("id");

ALTER TABLE "details_commande" ADD FOREIGN KEY ("id_serveur") REFERENCES "utilisateur" ("id");

ALTER TABLE "inventaire_ingredient" ADD FOREIGN KEY ("id_ingredient") REFERENCES "ingredient" ("id");

ALTER TABLE "inventaire_produit" ADD FOREIGN KEY ("id_produit") REFERENCES "produit" ("id");

ALTER TABLE "utilisateur" ADD FOREIGN KEY ("id_profil") REFERENCES "profil" ("id");

CREATE VIEW menu AS
SELECT  p.id as id_produit, p.nom as produit, c.nom as categorie, pp.montant as prix, pp.date
        FROM produit p
        JOIN prix_produit pp ON( pp.id_produit=p.id)
        JOIN categorie c ON (c.id=p.id_categorie)
        WHERE pp.date = (select max(date) from prix_produit);

CREATE VIEW addition_table_temp AS
SELECT  p.id id_produit, c.id id_commande, c.id_table id_point_livraison, t.designation, p.nom nom_produit, COUNT(p.id) quantite, c.date_heure date_commande
        FROM Produit p
        JOIN details_commande dc ON (dc.id_produit = p.id)
        JOIN commande c ON (c.id = dc.id_commande)
        JOIN point_livraison t ON (t.id = c.id_table)
        -- WHERE c.date_heure = (select max(date_heure) from Commande)
        GROUP BY p.id, c.id, c.id_table, t.designation, p.nom;

CREATE VIEW addition_table AS
SELECT  a.*, m.prix prix_unitaire, a.quantite*m.prix montant
        FROM addition_table_temp a
        JOIN menu m ON (m.id_produit = a.id_produit);

CREATE VIEW user_info AS
select  u.*, p.nom as profil
        from utilisateur u
        join profil p on (p.id = u.id_profil);

create view livraison_client as
select	l.numero n_livraison, p.nom nom_produit, dc.id_produit
		from livraison l
		join commande c on (c.id = l.id_commande)
		join details_commande dc on (dc.id_commande = c.id)
		join produit p on (p.id = dc.id_produit);

create view detail_livraison as
select	l.numero n_livraison, p.nom nom_produit, dc.id_produit, dc.id id_detail_commande, dc.est_paye
		from livraison l
		join commande c on (c.id = l.id_commande)
		join details_commande dc on (dc.id_commande = c.id)
		join produit p on (p.id = dc.id_produit);


INSERT INTO categorie VALUES
(DEFAULT, 'Entrée'),
(DEFAULT, 'Plat de résistance'),
(DEFAULT, 'Dessert');

INSERT INTO produit VALUES
(DEFAULT, 1, 'Pate a choux', null),
(DEFAULT, 2, 'Ravitoto sy henakisoa', null),
(DEFAULT, 3, 'Glace', null),
(DEFAULT, 3, 'Salade de fruit', null),
(DEFAULT, 3, 'Yaourt', null);

INSERT INTO prix_produit VALUES
(DEFAULT, 1, 2000, '2022-03-22'),
(DEFAULT, 2, 3000, '2022-03-22'),
(DEFAULT, 3, 2500, '2022-03-22'),
(DEFAULT, 4, 3500, '2022-03-22'),
(DEFAULT, 5, 4000, '2022-03-22');

INSERT INTO ingredient VALUES
(DEFAULT, 'farine'),
(DEFAULT, 'poivre'),
(DEFAULT, 'sucre'),
(DEFAULT, 'sel'),
(DEFAULT, 'carotte');

INSERT INTO prix_ingredient VALUES
(DEFAULT, 1, 100, '2022-03-22'),
(DEFAULT, 2, 200, '2022-03-22'),
(DEFAULT, 3, 300, '2022-03-22'),
(DEFAULT, 4, 150, '2022-03-22'),
(DEFAULT, 5, 250, '2022-03-22');

INSERT INTO recette VALUES
(DEFAULT, 1, 1, 2),
(DEFAULT, 1, 2, 3),
(DEFAULT, 2, 1, 4),
(DEFAULT, 2, 2, 5),
(DEFAULT, 2, 3, 2),
(DEFAULT, 3, 4, 2),
(DEFAULT, 3, 5, 1),
(DEFAULT, 4, 1, 4),
(DEFAULT, 4, 2, 2),
(DEFAULT, 4, 3, 1),
(DEFAULT, 5, 1, 8);

INSERT INTO profil VALUES
(DEFAULT, 'Admin'),
(DEFAULT, 'Livreur'),
(DEFAULT, 'Cuisine'),
(DEFAULT, 'Serveur'),
(DEFAULT, 'Caisse');

INSERT INTO utilisateur VALUES
(DEFAULT, 'Rakoto', 'Rakoto', 2),
(DEFAULT, 'Aline', 'Aline', 2),
(DEFAULT, 'Rasoa', 'Rasoa', 2),
(DEFAULT, 'Rabe', 'Rabe', 2),
(DEFAULT, 'Rajao', 'Rajao', 2);

INSERT INTO point_livraison VALUES
(DEFAULT, 'table 1', 'restaurant'),
(DEFAULT, 'table 2', 'restaurant'),
(DEFAULT, 'table 3', 'restaurant'),
(DEFAULT, 'table 4', 'restaurant'),
(DEFAULT, 'table 5', 'restaurant');

INSERT INTO commande VALUES
(DEFAULT, '2022-03-28 16:46:27', 1),
(DEFAULT, '2022-03-28 16:50:27', 2),
(DEFAULT, '2022-03-28 15:46:27', 3),
(DEFAULT, '2022-03-28 10:46:27', 4),
(DEFAULT, '2022-03-28 12:46:27', 5);

INSERT INTO details_commande VALUES
(DEFAULT, 1, 1, 1, 3, 0),
(DEFAULT, 1, 1, 1, 3, 0),
(DEFAULT, 2, 1, 2, 3, 0),
(DEFAULT, 2, 3, 2, 3, 0),
(DEFAULT, 3, 1, 3, 3, 0),
(DEFAULT, 3, 4, 3, 3, 0),
(DEFAULT, 4, 2, 4, 3, 0),
(DEFAULT, 4, 3, 4, 3, 0),
(DEFAULT, 5, 5, 5, 3, 0);

INSERT INTO commande VALUES
(DEFAULT, '2022-03-26 10:46:27', 1),
(DEFAULT, '2022-03-26 18:46:27', 2),
(DEFAULT, '2022-03-26 19:46:27', 2),
(DEFAULT, '2022-03-26 20:46:27', 4),
(DEFAULT, '2022-03-26 21:46:27', 5);

INSERT INTO details_commande VALUES
(DEFAULT, 6, 2, 1, 3, 0),
(DEFAULT, 6, 3, 1, 3, 0),
(DEFAULT, 7, 5, 2, 3, 0),
(DEFAULT, 7, 1, 2, 3, 0),
(DEFAULT, 8, 1, 3, 3, 0),
(DEFAULT, 8, 2, 3, 3, 0),
(DEFAULT, 9, 5, 4, 3, 0),
(DEFAULT, 9, 4, 4, 3, 0),
(DEFAULT, 10, 1, 5, 3, 0);

INSERT INTO stock_ingredient VALUES
(DEFAULT, 1, 'entree', 20, '2022-04-01 15:57:27'),
(DEFAULT, 2, 'entree', 20, '2022-04-02 16:50:27'),
(DEFAULT, 3, 'entree', 20, '2022-04-03 15:20:27'),
(DEFAULT, 4, 'entree', 20, '2022-04-05 17:45:27'),
(DEFAULT, 5, 'entree', 20, '2022-04-06 18:30:27');

INSERT INTO stock_ingredient VALUES
(DEFAULT, 1, 'sortie', 10, '2022-04-01 10:46:27'),
(DEFAULT, 2, 'sortie', 5, '2022-04-02 9:46:27'),
(DEFAULT, 3, 'sortie', 9, '2022-04-03 8:46:27'),
(DEFAULT, 4, 'sortie', 17, '2022-04-04 7:46:27'),
(DEFAULT, 5, 'sortie', 15, '2022-04-05 11:46:27');

INSERT INTO livraison VALUES
(DEFAULT, 'Livraison 001', 1, 1, '034 23 589 14', 'Andoharanofotsy'),
(DEFAULT, 'Livraison 002', 2, 2, '034 32 690 15', 'Andoharanofotsy'),
(DEFAULT, 'Livraison 003', 3, 3, '034 45 701 16', 'Andoharanofotsy'),
(DEFAULT, 'Livraison 004', 4, 4, '034 75 812 17', 'Andoharanofotsy'),
(DEFAULT, 'Livraison 005', 5, 5, '034 17 923 18', 'Andoharanofotsy');


create view pourboire as
select	c.date_heure::date date, dc.id_serveur, sum(pp.montant * 0.01) montant
        from details_commande dc
        join produit p on (p.id = dc.id_produit)
        join prix_produit pp on (pp.id_produit = p.id)
        join commande c on (c.id = dc.id_commande)
        where pp.date = (select max(date) from prix_produit where id_produit = p.id)
        group by c.date_heure::date, dc.id_serveur;

create view plat_commande as
select  dc.id_produit, p.nom nom_produit, l.lieu, dc.etat, dc.id_serveur, c.date_heure date
        from details_commande dc
        join commande c on (c.id = dc.id_commande)
        join produit p on (p.id = dc.id_produit)
        join livraison l on (l.id_commande = c.id);
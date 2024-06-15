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
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS productCategory;
DROP TABLE IF EXISTS productSupplier;




CREATE TABLE productCategory(
    id SERIAL UNIQUE ,
    name text,
    department text
);

CREATE TABLE productSupplier(
    id SERIAL UNIQUE ,
    name text,
    description text
);

CREATE TABLE product(
                        id SERIAL,
                        name text,
                        description text,
                        price int,
                        currency text,
                        category_id int,
                        supplier_id int,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_product
                            FOREIGN KEY (category_id) REFERENCES productCategory(id),
                            FOREIGN KEY (supplier_id) REFERENCES productSupplier(id)
                            ON DELETE CASCADE
);


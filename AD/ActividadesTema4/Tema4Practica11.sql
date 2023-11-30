-- Exercise 1
DROP TYPE address_type;
/

CREATE OR REPLACE TYPE address_type AS OBJECT(
    street VARCHAR(100),
    postal_code NUMBER(5)
);
/

DESC address_type;

-- Exercise 2
DROP TYPE contact_type;
/

CREATE OR REPLACE TYPE contact_type AS OBJECT (
    phone_number NUMBER,
    email VARCHAR(100)
);
/

-- Exercise 3
DROP TYPE person_type;
DROP TYPE customer_type;
/

CREATE TYPE person_type AS OBJECT (
    id VARCHAR(20),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    home_address address_type,
    contact_info contact_type
) NOT FINAL;

CREATE TYPE customer_type UNDER person_type (
    order_count NUMBER
);
/

-- Exercise 4
DESC address_type;
DESC contact_type;
DESC person_type;
DESC customer_type;

-- Exercise 5
DROP TYPE item_type;
DROP TYPE item_table_type;
/

CREATE OR REPLACE TYPE item_type AS OBJECT (
    item_id NUMBER,
    name VARCHAR(30),
    description VARCHAR(100),
    price NUMBER,
    tax_percentage NUMBER
);

CREATE TYPE item_table_type AS TABLE OF item_type;
/

-- Exercise 6
DESC item_type;
DESC item_table_type;

-- Exercise 7
DROP TYPE shopping_list_item_type;
DROP TYPE shopping_list_detail_table_type;
DROP TYPE shopping_list_type;
/

CREATE OR REPLACE TYPE shopping_list_item_type AS OBJECT (
    item_number NUMBER,
    item item_type,
    quantity NUMBER
);

CREATE TYPE shopping_list_detail_table_type AS TABLE OF shopping_list_item_type;

CREATE TYPE shopping_list_type AS OBJECT (
    id NUMBER,
    purchase_date DATE,
    customer REF customer_type,
    details shopping_list_detail_table_type,
    MEMBER FUNCTION total RETURN NUMBER
);

CREATE TYPE BODY shopping_list_type AS
    MEMBER FUNCTION total RETURN NUMBER IS
        total_amount NUMBER := 0;
    BEGIN
        FOR i IN 1..details.COUNT LOOP
            total_amount := total_amount +
                           (details(i).quantity * details(i).item.price) *
                           (1 + (details(i).item.tax_percentage / 100));
        END LOOP;
        RETURN total_amount;
    END total;
END;
/

-- Exercise 8
DESC shopping_list_item_type;
DESC shopping_list_detail_table_type;
DESC shopping_list_type;

-- Exercise 9
DROP TABLE customers;
/

CREATE TABLE customers OF customer_type;

INSERT INTO customers VALUES (
    1,
    'Pedro',
    'Suarez',
    address_type('Paseo del museo, 15', 28009),
    contact_type(938383838, 'psuarez@ono.es'),
    0
);

INSERT INTO customers VALUES (
    2,
    'Juana',
    'Gomez',
    address_type('Gran Via, 15', 28005),
    contact_type(98888888, 'jgomez@ono.es'),
    0
);

-- Exercise 10
DROP TABLE shopping_lists;
/

CREATE TABLE shopping_lists OF shopping_list_type
    NESTED TABLE details STORE AS list_details;

INSERT INTO shopping_lists
SELECT 1, SYSDATE, REF(c), shopping_list_detail_table_type(
    shopping_list_item_type(1, item_type(1, 'bread', 'baguette type', 1, 7), 4),
    shopping_list_item_type(2, item_type(2, 'ham slices', 'iberian type', 6, 7), 4)
)
FROM customers c WHERE c.id = 1;

INSERT INTO shopping_lists VALUES (
    3,
    SYSDATE,
    (SELECT REF(c) FROM customers c WHERE c.id = 1),
    shopping_list_detail_table_type(
        shopping_list_item_type(1, item_type(1, 'bread', 'baguette type', 1, 7), 4),
        shopping_list_item_type(2, item_type(2, 'ham slices', 'iberian type', 6, 7), 4)
    )
);

-- Exercise 11
SELECT * FROM shopping_lists;

-- Exercise 12
SELECT id, purchase_date, DEREF(customer), details FROM shopping_lists;

-- Exercise 13
SELECT id, c.total() FROM shopping_lists c;
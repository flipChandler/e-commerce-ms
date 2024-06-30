
INSERT INTO CATEGORY (ID, DESCRIPTION, NAME) VALUES (NEXTVAL('SEQ_CATEGORY'), 'Computer Keyboards', 'Keyboards');
INSERT INTO CATEGORY (ID, DESCRIPTION, NAME) VALUES (NEXTVAL('SEQ_CATEGORY'), 'Computer Monitors', 'Monitors');
INSERT INTO CATEGORY (ID, DESCRIPTION, NAME) VALUES (NEXTVAL('SEQ_CATEGORY'), 'Display Screens', 'Screens');
INSERT INTO CATEGORY (ID, DESCRIPTION, NAME) VALUES (NEXTVAL('SEQ_CATEGORY'), 'Computer Mice', 'Mice');
INSERT INTO CATEGORY (ID, DESCRIPTION, NAME) VALUES (NEXTVAL('SEQ_CATEGORY'), 'Computer Accessories', 'Accessories');


-- Assuming you already have a sequence named 'product_seq'

-- Insert products for the 'Keyboards' CATEGORY
INSERT INTO PRODUCT (ID, AVAILABLE_QUANTITY, DESCRIPTION, NAME, PRICE, CATEGORY_ID)
VALUES
    (NEXTVAL('SEQ_PRODUCT'), 10, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard 1', 99.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Keyboards')),
    (NEXTVAL('SEQ_PRODUCT'), 15, 'Wireless compact keyboard', 'Wireless Compact Keyboard 1', 79.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Keyboards')),
    (NEXTVAL('SEQ_PRODUCT'), 20, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard 1', 129.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Keyboards')),
    (NEXTVAL('SEQ_PRODUCT'), 25, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard 1', 109.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Keyboards')),
    (NEXTVAL('SEQ_PRODUCT'), 18, 'Wireless keyboard and mouse combo', 'Wireless Combo 1', 69.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Keyboards'));

-- Insert products for the 'Monitors' CATEGORY
INSERT INTO PRODUCT (ID, AVAILABLE_QUANTITY, DESCRIPTION, NAME, PRICE, CATEGORY_ID)
VALUES
    (NEXTVAL('SEQ_PRODUCT'), 30, '27-inch IPS monitor with 4K resolution', '4K Monitor 1', 399.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Monitors')),
    (NEXTVAL('SEQ_PRODUCT'), 25, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor 1', 499.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Monitors')),
    (NEXTVAL('SEQ_PRODUCT'), 22, '24-inch LED monitor for office use', 'Office Monitor 1', 179.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Monitors')),
    (NEXTVAL('SEQ_PRODUCT'), 28, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor 1', 329.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Monitors')),
    (NEXTVAL('SEQ_PRODUCT'), 35, 'Portable USB-C monitor for laptops', 'Portable Monitor 1', 249.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Monitors'));

-- Insert products for the 'Screens' CATEGORY
INSERT INTO PRODUCT (ID, AVAILABLE_QUANTITY, DESCRIPTION, NAME, PRICE, CATEGORY_ID)
VALUES
    (NEXTVAL('SEQ_PRODUCT'), 15, 'Curved OLED gaming screen with 240Hz refresh rate', 'Curved OLED Gaming Screen 1', 799.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Screens')),
    (NEXTVAL('SEQ_PRODUCT'), 18, 'Flat QLED monitor with 1440p resolution', 'QLED Monitor 1', 599.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Screens')),
    (NEXTVAL('SEQ_PRODUCT'), 22, '27-inch touch screen display for creative work', 'Touch Screen Display 1', 699.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Screens')),
    (NEXTVAL('SEQ_PRODUCT'), 20, 'Ultra-slim 4K HDR display for multimedia', 'Ultra-slim 4K HDR Display 1', 449.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Screens')),
    (NEXTVAL('SEQ_PRODUCT'), 25, 'Gaming projector with low input lag', 'Gaming Projector 1', 899.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Screens'));

-- Insert products for the 'Mice' CATEGORY
INSERT INTO PRODUCT (ID, AVAILABLE_QUANTITY, DESCRIPTION, NAME, PRICE, CATEGORY_ID)
VALUES
    (NEXTVAL('SEQ_PRODUCT'), 30, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse 1', 59.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Mice')),
    (NEXTVAL('SEQ_PRODUCT'), 28, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse 1', 29.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Mice')),
    (NEXTVAL('SEQ_PRODUCT'), 32, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse 1', 69.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Mice')),
    (NEXTVAL('SEQ_PRODUCT'), 26, 'Travel-sized compact mouse for laptops', 'Travel Mouse 1', 19.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Mice')),
    (NEXTVAL('SEQ_PRODUCT'), 35, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse 1', 39.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Mice'));

-- Insert products for the 'Accessories' CATEGORY
INSERT INTO PRODUCT (ID, AVAILABLE_QUANTITY, DESCRIPTION, NAME, PRICE, CATEGORY_ID)
VALUES
    (NEXTVAL('SEQ_PRODUCT'), 25, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand 1', 34.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Accessories')),
    (NEXTVAL('SEQ_PRODUCT'), 20, 'Wireless charging pad for smartphones', 'Wireless Charging Pad 1', 24.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Accessories')),
    (NEXTVAL('SEQ_PRODUCT'), 28, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand 1', 49.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Accessories')),
    (NEXTVAL('SEQ_PRODUCT'), 22, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad 1', 39.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Accessories')),
    (NEXTVAL('SEQ_PRODUCT'), 30, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure 1', 29.99, (SELECT ID FROM CATEGORY WHERE NAME = 'Accessories'));
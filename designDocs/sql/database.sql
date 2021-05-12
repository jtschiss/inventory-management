CREATE TABLE 'items' (
    'id' int(11) NOT NULL AUTO_INCREMENT,
    'name' varchar(100) NOT NULL,
    'price' varchar(9),
    'quantity' int(5),
    PRIMARY KEY ('id')
);

CREATE TABLE 'locations' (
    'id' int(11) NOT NULL AUTO_INCREMENT,
    'section' varchar(11) NOT NULL,
    'shelf' varchar(11) NOT NULL,
    PRIMARY KEY ('id')
);

CREATE TABLE 'item_locations' (
    'item_id' int(11) NOT NULL,
    'location_id' int(11) NOT NULL,
    PRIMARY KEY ('item_id', 'location_id')
);
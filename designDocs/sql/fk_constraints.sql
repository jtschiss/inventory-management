ALTER TABLE item_locations
    ADD CONSTRAINT FK_itemID
        FOREIGN KEY (item_id) REFERENCES items(id);

ALTER TABLE item_locations
    ADD CONSTRAINT FK_locationID
        FOREIGN KEY (location_id) REFERENCES locations(id);
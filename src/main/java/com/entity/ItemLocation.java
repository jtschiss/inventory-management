package com.entity;

public class ItemLocation {

    private int itemId;
    private int locationId;
    private int active;

    public ItemLocation() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ItemLocation{" +
                "itemId=" + itemId +
                ", locationId=" + locationId +
                ", active=" + active +
                '}';
    }
}

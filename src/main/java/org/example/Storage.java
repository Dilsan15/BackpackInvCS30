package org.example;

import java.util.ArrayList;

public class Storage {

    private String sName;
    private int currentCapacity;
    private int maxCapacity;
    private int tWeight;
    private ArrayList<Items> storageItems = new ArrayList<>();

    private boolean checkCapacity() {
        return this.currentCapacity < this.maxCapacity;
    }

    public void addItem(Items item) {

        if (checkCapacity()) {
            this.storageItems.add(item);
            this.currentCapacity++;
            this.tWeight += item.getWeight();
        } else {
            System.out.println("Not enough space in the storage");
        }

    }


    public String getName() {
        return this.sName;
    }

    public void setName(String name) {
        this.sName = name;
    }


    public void removeItem(Items item) {
        this.storageItems.remove(item);
        this.currentCapacity--;
        this.tWeight -= item.getWeight();
    }

    public String getCapacityLeft() {
        return "Current capacity: " + this.currentCapacity + " / " + this.maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ArrayList<Items> getItems() {
        return this.storageItems;
    }

    public int getWeight() {
        for (Items item : storageItems) {
            this.tWeight += item.getWeight();
        }
        return this.tWeight;
    }


}





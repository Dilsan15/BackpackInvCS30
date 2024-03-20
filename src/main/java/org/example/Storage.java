//Storage.java

/*
* Title: Backpack Inventory Assignment - Storage.java
* Author: Dilshaan Sandhu
* Date: March 18th, 2024
* */



package org.example;

import java.util.ArrayList;

public class Storage {
    /*Creates a storage object with a name, max capacity, current capacity, total weight and a list of items (each item)
    is based of a class called Items*/

    // Input
    private String sName;
    private int currentCapacity;
    private int maxCapacity;
    private int tWeight;
    private ArrayList<Items> storageItems = new ArrayList<>();

    // Processing
    public void addItem(Items item) {
        /* adds an item if the capacity does not exceed the max*/
        if (checkCapacity()) {
            this.storageItems.add(item);
            this.currentCapacity++;
            this.tWeight += item.getWeight();
        } else {
            System.out.println("Not enough space in the storage");
        }

    }
    public void removeItem(Items item) {
        /* removes an item from the storage*/
        this.storageItems.remove(item);
        this.currentCapacity--;
        this.tWeight -= item.getWeight();
    }

    public void setName(String name) {
        // sets the name of the storage
        this.sName = name;
    }

    public void setMaxCapacity(int maxCapacity) {
        // sets the max capacity of the storage
        this.maxCapacity = maxCapacity;
    }

    private boolean checkCapacity() {
        // checks if the current capacity is less than the max capacity
        return this.currentCapacity < this.maxCapacity;
    }

    // Output
    public ArrayList<Items> getItems() {
        // returns the list of items in the storage
        return this.storageItems;
    }

    public String getName() {
        // returns the name of the storage
        return this.sName;
    }

    public String getCapacityLeft() {
        // returns the capacity left in the storage
        return "Current capacity: " + this.currentCapacity + " / " + this.maxCapacity;
    }

    public int getWeight() {
        // returns the total weight of the storage
        return this.tWeight;
    }

}





//Items.java

/*
 * Title: Backpack Inventory Assignment - Items.java
 * Author: Dilshaan Sandhu
 * Date: March 18th, 2024
 * */


package org.example;

public class Items {
    /*This class creates an item object with a name and weight*/

    // Input
    private String iName;
    private int iWeight;

    // Processing
    public void setName(String name) {
        // sets the name of the item
        this.iName = name;
    }
    public void setWeight(int weight) {
        // sets the weight of the item
        this.iWeight = weight;
    }


    // Output
    public String getName() {
        // returns the name of the item
        return this.iName;
    }

    public int getWeight() {
        // returns the weight of the item
        return this.iWeight;
    }

}

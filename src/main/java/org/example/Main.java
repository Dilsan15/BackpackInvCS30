package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main {

    private static final Scanner sScanner = new Scanner(System.in);
    private static Storage cStorage;
    private static List<Storage> storageList = new ArrayList<>();

    public static void main(String[] args) {
        createStorage();
        System.out.println("Welcome to the storage system\n");
        selectOptions();
    }

    public static void selectOptions() {
        cStorage = selectStorage(disStorageMenu());
        assert cStorage != null;
        selectAction(disActionMenu());
    }

    public static void createStorage() {
        ArrayList<ArrayList<String>> savedData = loadStorageData();

        Storage sLocker = new Storage();
        sLocker.setName("Locker");
        sLocker.setMaxCapacity(10);

        Storage sPencilCase = new Storage();
        sPencilCase.setName("Pencil Case");
        sPencilCase.setMaxCapacity(5);

        Storage sBackpack = new Storage();
        sBackpack.setName("Backpack");
        sBackpack.setMaxCapacity(15);

        storageList.add(sLocker);
        storageList.add(sPencilCase);
        storageList.add(sBackpack);


        if (savedData == null) {
            System.out.println("No saved data found!\n");
            return;
        }
        System.out.println(savedData);
        for (ArrayList<String> line : savedData) {
            if (!line.isEmpty()) {

                for (int i = 0; i <= line.size()-1; i += 2) {

                    Items item = new Items();

                    item.setName(line.get(i));

                    item.setWeight(Integer.parseInt(line.get(i + 1)));
                    storageList.get(savedData.indexOf(line)).addItem(item);

                }
            }
        }


    }


    public static int disStorageMenu() {
        System.out.println("1. Locker\n" +
                "2. Pencil Case\n" +
                "3. Backpack\n" +
                "4. Exit\n");

        System.out.print("Enter your storage choice: ");
        return sScanner.nextInt();
    }


    public static Storage selectStorage(int sChoice) {
        if (sChoice >= 1 && sChoice <= storageList.size()) {
            return storageList.get(sChoice - 1);
        } else if (sChoice == 4) {
            System.out.println("Exiting...");
            saveStorageData();
            System.exit(0);
            return null;
        } else {
            System.out.println("Invalid choice");
            return null;
        }
    }

    public static int disActionMenu() {
        System.out.print("\n");
        System.out.println(String.format(
                "1. Add an item to %1$s\n" +
                        "2. Remove an item from %1$s\n" +
                        "3. List all items from %1$s\n" +
                        "4. Move an Item from %1$s\n" +
                        "5. Check %1$s capacity\n" +
                        "6. Get %1$s Weight details\n", cStorage.getName()));

        System.out.print("Enter your action choice: ");
        return sScanner.nextInt();
    }

    public static void selectAction(int aChoice) {
        switch (aChoice) {
            case 1 -> addItem();
            case 2 -> removeItem();
            case 3 -> listItems();
            case 4 -> moveItem();
            case 5 -> checkCapacity();
            case 6 -> getWeightDetails();
            default -> {
                System.out.println("Invalid choice");
                selectAction(disActionMenu());
            }
        }
        selectOptions();

    }

    public static void addItem() {
        System.out.println("You have chosen to add an item\n");
        System.out.print("Enter the name of the item: ");

        String name = sScanner.next();
        System.out.print("Enter the weight of the item (kg): ");

        int weight = sScanner.nextInt();
        Items item = new Items();
        item.setName(name);
        item.setWeight(weight);

        cStorage.addItem(item);
        System.out.print("\n");
    }

    public static void removeItem() {
        System.out.println("You have chosen to remove an item");
        System.out.print("Enter the name of the item: ");
        String name = sScanner.next();

        for (Items item : cStorage.getItems()) {
            if (item.getName().equals(name)) {
                cStorage.removeItem(item);
                break; // Exit loop once item is removed
            }
        }
    }

    public static void listItems() {
        System.out.println("You have chosen to list the items\n");
        if (!cStorage.getItems().isEmpty()) {

            for (Items item : cStorage.getItems()) {
                System.out.println("Name: " + item.getName() + " | Weight: " + item.getWeight());
            }
        } else {
            System.out.println("No items in " + cStorage.getName());
            return;
        }

        System.out.print("\n");
    }

    public static void moveItem() {
        System.out.println("You have chosen to move an item");
        System.out.println("Where do you want to move the item?");
        int sChoice = disStorageMenu();
        Storage tempStorage = selectStorage(sChoice);

        System.out.print("Enter the name of the item: ");
        String name = sScanner.next();

        for (Items item : cStorage.getItems()) {
            if (item.getName().equals(name)) {
                tempStorage.addItem(item);
                cStorage.removeItem(item);
                System.out.println("Item moved successfully.");
                return;
            }
        }
        System.out.println("Item not found in " + cStorage.getName());
    }

    public static void checkCapacity() {
        System.out.println("You have chosen to check the capacity");
        System.out.println("Capacity left in " + cStorage.getName() + ": " + cStorage.getCapacityLeft());
    }

    public static void getWeightDetails() {
        System.out.println("You have chosen to get the weight \n");

        System.out.println("Total weight of items in the storage is: " + cStorage.getWeight() + "\n");
    }

    public static void saveStorageData() {
        try {
            PrintWriter writer = new PrintWriter("storage.csv", "UTF-8");
            for (Storage storage : storageList) {
                writer.print(storage.getName());

                for (Items item : storage.getItems()) {
                    writer.print("," + item.getName());
                    writer.print("," + item.getWeight());
                }
                writer.println();
            }
            writer.close();
        } catch (Exception e) {

            System.out.println("An error occurred while saving the data");
        }


    }

    public static ArrayList<ArrayList<String>> loadStorageData() {
        ArrayList<ArrayList<String>> lineData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("storage.csv"));
            while (scanner.hasNextLine()) {

                List<String> rawLine = new ArrayList<>(List.of(scanner.nextLine().split(",")));
                rawLine.remove(0);

                lineData.add(new ArrayList<>(rawLine));


            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading the data");
        }
        return lineData.isEmpty() ? null : lineData;
    }


}

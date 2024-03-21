import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
/**
 * Inventory.java
 * <br>
 * Inventory represents a collection of devices.
 * <br>
 * It provides methods for adding, removing, restocking, and updating devices.
 * <br>
 * It also provides methods for exporting inventory report, finding the cheapest device, sorting devices by price, calculating total value of all devices, loading and saving inventory to a file, and listing all devices in the inventory.
 */
public class Inventory {
    private ArrayList<ArrayList<Device>> devices; // The list of devices
    private int totalDevices = 0; // The total number of devices
    private final String[] categories = {"Laptop", "Tablet", "Tv"}; // The list of categories
    private final exportFile = "export.txt"; // The file to export to
    public static inportFile = "inventory.txt"; // The file to import from
    /**
     * Inventory constructor
     * <br>
     * Time Complexity: O(1)
     */
    public Inventory(){
        devices = new ArrayList<ArrayList<Device>>();
    }
    /**
     * Add a device to the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of linked lists in the devices list
     * 
     * @param scanner The scanner object to take input from
     * @throws IllegalArgumentException if the category is invalid
     */
    public void addDevice(Scanner scanner) throws IllegalArgumentException{
        Device device = Inventory.takeDeviceInput(scanner);
        String category = device.getCategory();
        if (category == null){
            throw new IllegalArgumentException("Invalid category");
        }
        int index = -1;
        for (int i = 0; i < devices.size(); i++){
            if (devices.get(i).get(0).getCategory().equals(category)){
                index = i;
                break;
            }
        }
        if (index == -1){
            ArrayList<Device> newCategory = new ArrayList<Device>();
            newCategory.add(device);   // Time Complexity: O(1)
            devices.add(newCategory); // Time Complexity: O(1)
        } else {
            devices.get(index).add(device); // Time Complexity: O(1)
        }
        totalDevices++;
        System.out.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice() + ", " + device.getQuantity() + " amount added.");
    }
    /**
     * Add a device to the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of linked lists in the devices list
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param device The device to add
     * @throws IllegalArgumentException if the category is invalid
     */
    private void addDevice(Device device) throws IllegalArgumentException{
        String category = device.getCategory();
        if (category == null){
            throw new IllegalArgumentException("Invalid category");
        }
        int index = -1;
        for (int i = 0; i < devices.size(); i++){
            if (devices.get(i).get(0).getCategory().equals(category)){
                index = i;
                break;
            }
        }
        if (index == -1){
            ArrayList<Device> newCategory = new ArrayList<Device>();
            newCategory.add(device);
            devices.add(newCategory);
        } else {
            devices.get(index).add(device);
        }
        totalDevices++;
    }
    /**
     * Remove a device from the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * 
     * @param scanner The scanner object to take input from
     */
    public void removeDevice(Scanner scanner){
        String name = Inventory.getStringInput(scanner, "Enter name of device to remove: ");
        boolean isRemoved = false;
        // Time Complexity: O(n) because number of nodes times number of devices in each node is n
        for (int i = 0; i < devices.size(); i++){ // Time Complexity: O(n) where n is the number of linked lists in the devices list
            for (int j = 0; j < devices.get(i).size(); j++){ // Time Complexity: O(n) where n is the number of devices in the node of the linked list
                if (devices.get(i).get(j).getName().equals(name)){ // Time Complexity: O(1)
                    devices.get(i).remove(j);
                    isRemoved = true;
                    break;
                }
            }
        }
        if (isRemoved){
            printGreen(name + " removed.");
            totalDevices--;
        } else {
            printRed(name + " not found.");
        }
    }
    /**
     * Take input from the user to determine if they want to add or remove stock
     * <br>
     * Time Complexity: O(1)
     * <br>
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param scanner The scanner object to take input from
     * @return true if the user wants to add stock, false if the user wants to remove stock
    */
    private static boolean isAddDevice(Scanner scanner){
        String input = "";
        do {
            System.out.print("Do you want to add or remove stock? (Add/Remove): ");
            input = scanner.nextLine();
            if (input.equals("Add") || input.equals("Remove")){
                return input.equals("Add");
            } else {
                printRed("Invalid input, please try again.");
            }
        } while (true);
    }
    /**
     * Restock a device in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * 
     * @param scanner The scanner object to take input from
     */
    public void restockDevice(Scanner scanner){
        String name = Inventory.getStringInput(scanner, "Enter name of device to restock: ");
        boolean isAdd = isAddDevice(scanner);
        boolean isRestocked = false;
        // Time Complexity: O(n) because number of nodes times number of devices in each node is n
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                if (devices.get(i).get(j).getName().equals(name)){
                    int quantity = Inventory.getIntInput(scanner, (isAdd ? "Enter quantity to add: " : "Enter quantity to remove: "));
                    if (isAdd){
                        devices.get(i).get(j).addStock(quantity); // Time Complexity: O(1)
                    } else {
                        devices.get(i).get(j).removeStock(quantity); // Time Complexity: O(1)
                    }
                    isRestocked = true;
                    break;
                }
            }
        }
        if (isRestocked){
            System.out.println(name + " restocked.");
        } else {
            System.out.println(name + " not found.");
        }
    }
    /**
     * Get the total value of all devices in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * @return The total value of all devices in the inventory
    */
    private double getTotalValue(){
        double totalValue = 0;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                totalValue += devices.get(i).get(j).getPrice() * devices.get(i).get(j).getQuantity();
            }
        }
        return totalValue;
    }
    /**
     * Return the current date
     * <br>
     * Time Complexity: O(1)
     * @return The current date
    */
    private String returnDate(){
        LocalDate date = LocalDate.now();
        String month = date.getMonth().toString().substring(0, 1) + date.getMonth().toString().substring(1).toLowerCase();
        String day = date.getDayOfMonth() + (date.getDayOfMonth() % 10 == 1 ? "st" : (date.getDayOfMonth() % 10 == 2 ? "nd" : (date.getDayOfMonth() == 3 ? "rd" : "th")));
        int year = date.getYear();
        return day + " " + month + " " + year;
    }
    /**
     * Export an inventory report to the console with the details of all devices in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
    */
    public void exportInventoryReport(){
        FileWriter writer = new FileWriter(exportFile);
        System.out.println("Electronics Shop Inventory Report");
        writer.write("Electronics Shop Inventory Report\n");
        System.out.println("Generated on: " + returnDate() + "\n");
        writer.write("Generated on: " + returnDate() + "\n\n");
        System.out.println("| No. | Category | Name | Price | Quantity |");
        writer.write("| No. | Category | Name | Price | Quantity |\n");
        drawLine();
        int index = 1;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                devices.get(i).get(j).exportPrint(index);
                writer.write(devices.get(i).get(j).exportString(index));
                index++;
            }
        }
        drawLine();
        System.out.println("Summary: ");
        writer.write("Summary: \n");
        System.out.println("- Total Number of Devices: " + totalDevices);
        writer.write("- Total Number of Devices: " + totalDevices + "\n");
        System.out.println("- Total Inventory Value: " + "$" + getTotalValue()); // Time Complexity: O(n) where n is the number of devices in the inventory
        writer.write("- Total Inventory Value: " + "$" + getTotalValue() + "\n");
        System.out.println("\nEnd of Report");
        writer.write("\nEnd of Report");
        write.write("-----------------------------\n");;
        drawLine();
        writer.close();

    }
    /**
     * Find the cheapest device in the inventory and prints it to the console with its details
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
    */
    public void findCheapestDevice(){
        double cheapestPrice = Double.MAX_VALUE;
        Device cheapestDevice = null;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                if (devices.get(i).get(j).getPrice() < cheapestPrice){
                    cheapestPrice = devices.get(i).get(j).getPrice();
                    cheapestDevice = devices.get(i).get(j);
                }
            }
        }
        if (cheapestDevice != null){
            System.out.println("The cheapest device is:\n" + cheapestDevice);
        } else {
            printRed("No devices found.");
        }
    }
    /**
     * Sort devices by price
     * <br>
     * Time Complexity: O(n^2) where n is the number of devices in the inventory
    */
    public void sortDevicesByPrice(){
        ArrayList<Device> allDevices = new ArrayList<Device>();
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                allDevices.add(devices.get(i).get(j));
            }
        }
        for (int i = 0; i < allDevices.size(); i++){
            for (int j = i + 1; j < allDevices.size(); j++){
                if (allDevices.get(i).getPrice() > allDevices.get(j).getPrice()){
                    Device temp = allDevices.get(i);
                    allDevices.set(i, allDevices.get(j));
                    allDevices.set(j, temp);
                }
            }
        }
        System.out.println("Devices sorted by price:");
        for (int i = 0; i < allDevices.size(); i++){
            System.out.println(allDevices.get(i));
        }
    }
    /**
     * Calculate the total value of all devices in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
    */
    public void calculateTotalValue(){
        double totalValue = getTotalValue(); // Time Complexity: O(n) where n is the number of devices in the inventory
        System.out.println("Total value of all devices: " + totalValue);
    }
    /**
     * Update a device in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * 
     * @param scanner The scanner object to take input from
     * @throws Exception if the device is not found
    */
    public void updateDevice(Scanner scanner) throws Exception{
        String name = Inventory.getStringInput(scanner, "Enter the name of the device to update: ");
        boolean isUpdated = false;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                if (devices.get(i).get(j).getName().equals(name)){
                    String price = Inventory.getStringInput(scanner, "Enter new price (leave blank to keep current price): ");
                    if (!price.equals("")){
                        try {
                            devices.get(i).get(j).setPrice(Double.parseDouble(price));
                            printGreen("Price updated.");
                        } catch (NumberFormatException e){
                            printRed("Invalid price, not updated.");
                        }
                    }
                    String quantity = Inventory.getStringInput(scanner, "Enter new quantity (leave blank to keep current quantity): ");
                    if (!quantity.equals("")){
                        try {
                            devices.get(i).get(j).setQuantity(Integer.parseInt(quantity));
                            printGreen("Quantity updated.");
                        } catch (NumberFormatException e){
                            printRed("Invalid quantity, not updated.");
                        }
                    }
                    isUpdated = true;
                    System.out.println(name + " details updated: Price - " + devices.get(i).get(j).getPrice() + ", Quantity - " + devices.get(i).get(j).getQuantity());
                    break;
                }
            }
        }
        if (isUpdated){
            printGreen(name + " updated.");
        } else {
            printRed(name + " not found.");
        }
    }
    /**
     * List all devices in the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
    */
    public void listDevices(){
        System.out.println("Device List:");
        System.out.print(this);
    }
    /**
     * Return the string representation of the inventory
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * @return The string representation of the inventory
    */
    public String toString(){
        String result = "";
        int totalDevices = 1;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                result +=  totalDevices + ". " + devices.get(i).get(j).toString() + "\n";
                totalDevices++;
            }
        }
        return result;
    }
    /**
     * Take input from the user to add a device to the inventory
     * <br>
     * Time Complexity: O(1)
     * 
     * @param scanner The scanner object to take input from
     * @return The device to add
     * @throws IllegalArgumentException if the scanner is invalid
     * @throws IllegalArgumentException if the any of the inputs are invalid
    */
    public static Device takeDeviceInput(Scanner scanner) throws IllegalArgumentException{
        if (scanner == null){
            throw new IllegalArgumentException("Invalid scanner");
        }
        String category = getStringInput(scanner, "Enter category: ");
        String name = getStringInput(scanner, "Enter name: ");
        double price = getDoubleInput(scanner, "Enter price: ");
        int quantity = getIntInput(scanner, "Enter quantity: ");

        if (category.equals("Laptop")){
            return new Laptop(category, name, price, quantity);
        } else if (category.equals("Tablet")){
            return new Tablet(category, name, price, quantity);
        } else if (category.equals("Tv")){
            return new Tv(category, name, price, quantity);
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
    }
    /**
     * Take String input from the user
     * <br>
     * Time Complexity: O(1)
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param scanner The scanner object to take input from
     * @param message The message to display to the user
     * @return The string input from the user
     * @throws IllegalArgumentException if the scanner is invalid
    */
    private static String getStringInput(Scanner scanner, String message) throws IllegalArgumentException{
        String result = "";
        if (scanner == null){
            throw new IllegalArgumentException("Invalid scanner");
        }
        do {
            System.out.print(message);
            result = scanner.nextLine();
            if (result.equals("")){
                printRed("Invalid input, please try again.");
            }
        } while (result.equals(""));
        return result;
    }
    /**
     * Take double input from the user
     * <br>
     * Time Complexity: O(1)
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param scanner The scanner object to take input from
     * @param message The message to display to the user
     * @return The double input from the user
     * @throws IllegalArgumentException if the scanner is invalid
    */
    private static double getDoubleInput(Scanner scanner, String message) throws IllegalArgumentException{
        double result = -1;
        if (scanner == null){
            throw new IllegalArgumentException("Invalid scanner");
        }
        do {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                result = Double.parseDouble(input);
            } catch (NumberFormatException e){
                printRed("Invalid input, please try again.");
                continue;
            }
            if (result < 0)
                printRed("Invalid input, please try again.");
        } while (result < 0);
        return result;
    }
    /**
     * Take integer input from the user
     * <br>
     * Time Complexity: O(1)
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param scanner The scanner object to take input from
     * @param message The message to display to the user
     * @return The integer input from the user
     * @throws IllegalArgumentException if the scanner is invalid
    */
    private static int getIntInput(Scanner scanner, String message) throws IllegalArgumentException{
        int result = -1;
        if (scanner == null){
            throw new IllegalArgumentException("Invalid scanner");
        }
        do {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                result = Integer.parseInt(input);
            } catch (NumberFormatException e){
                printRed("Invalid input, please try again.");
                continue;
            }
            if (result < 0)
                printRed("Invalid input, please try again.");
        } while (result < 0);
        return result;
    }
    /**
     * Print a message in red
     * <br>
     * Time Complexity: O(1)
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param message The message to print
    */
    private static void printRed(String message){
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
    /**
     * Print a message in green
     * <br>
     * Time Complexity: O(1)
     * It makes no sense to write javadoc for a private method but I did it for assignment.
     * 
     * @param message The message to print
    */
    private static void printGreen(String message){
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }
    /**
     * Load inventory from a file
     * <br>
     * Time Complexity: O(k * n) where k is the number of nodes in the devices list and n is the number of devices in the text file
     * 
     * @param str The file name to load from
     * @throws IllegalArgumentException if the file format is invalid
    */
    public void loadFromFile(String str){
        System.out.println("Loading from file...");
        try {
            Scanner scanner = new Scanner(new File(str));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length != 9)
                    throw new IllegalArgumentException("Invalid file format");
                String category = parts[2].substring(0, parts[2].length() - 1);
                String name = parts[4].substring(0, parts[4].length() - 1);
                double price = Double.parseDouble(parts[6].substring(0, parts[6].length() - 1));
                int quantity = Integer.parseInt(parts[8]);
                if (category.equals("Laptop")){
                    addDevice(new Laptop(category, name, price, quantity)); // Time Complexity: O(k) where k is the number of nodes in the devices list
                } else if (category.equals("Tablet")){
                    addDevice(new Tablet(category, name, price, quantity));
                } else if (category.equals("Tv")){
                    addDevice(new Tv(category, name, price, quantity));
                } else {
                    throw new IllegalArgumentException("Invalid category");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }
    /**
     * Save inventory to a file
     * <br>
     * Time Complexity: O(n) where n is the number of devices in the inventory
     * 
     * @param str The file name to save to
    */
    public void saveToFile(String str){
        System.out.println("Saving to file...");
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(str));
            writer.print(this.toString());
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
    private static void drawLine(){
        System.out.println("-------------------------------------------------");
    }
}
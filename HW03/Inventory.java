import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
public class Inventory {
    private ArrayList<ArrayList<Device>> devices;

    private int totalDevices = 0;
    public final String[] categories = {"Laptop", "Tablet", "Tv"};

    public Inventory(){
        devices = new ArrayList<ArrayList<Device>>();
    }
    public void addDevice(Scanner scanner){
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
            newCategory.add(device);
            devices.add(newCategory);
        } else {
            devices.get(index).add(device);
        }
        totalDevices++;
        System.out.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice() + ", " + device.getQuantity() + " amount added.");
    }
    private void addDevice(Device device){
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

    public void removeDevice(Scanner scanner){
        String name = Inventory.getStringInput(scanner, "Enter name of device to remove: ");
        boolean isRemoved = false;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                if (devices.get(i).get(j).getName().equals(name)){
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
    public void restockDevice(Scanner scanner){
        String name = Inventory.getStringInput(scanner, "Enter name of device to restock: ");
        boolean isAdd = isAddDevice(scanner);
        boolean isRestocked = false;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                if (devices.get(i).get(j).getName().equals(name)){
                    int quantity = Inventory.getIntInput(scanner, (isAdd ? "Enter quantity to add: " : "Enter quantity to remove: "));
                    if (isAdd){
                        devices.get(i).get(j).addStock(quantity);
                    } else {
                        devices.get(i).get(j).removeStock(quantity);
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
    private double getTotalValue(){
        double totalValue = 0;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                totalValue += devices.get(i).get(j).getPrice() * devices.get(i).get(j).getQuantity();
            }
        }
        return totalValue;
    }
    private String returnDate(){
        LocalDate date = LocalDate.now();
        String month = date.getMonth().toString().substring(0, 1) + date.getMonth().toString().substring(1).toLowerCase();
        String day = date.getDayOfMonth() + "th";
        int year = date.getYear();
        return day + " " + month + " " + year;
    }
    public void exportInventoryReport(){
        System.out.println("Electronics Shop Inventory Report");
        System.out.println("Generated on: " + returnDate());
        System.out.println("| No. | Category | Name     | Price | Quantity |");
        drawLine();
        int index = 1;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                devices.get(i).get(j).exportPrint(index);
                index++;
            }
        }
        drawLine();
        System.out.println("Summary: ");
        System.out.println("- Total Number of Devices: " + totalDevices);
        System.out.println("- Total Inventory Value: " + "$" + getTotalValue());
        System.out.println("\nEnd of Report");
        drawLine();
    }

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
    // add while loop to sort devices by price
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
    public void calculateTotalValue(){
        double totalValue = getTotalValue();
        System.out.println("Total value of all devices: " + totalValue);
    }

    public void updateDevice(Scanner scanner){
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
    public void listDevices(){
        System.out.println("Device List:");
        System.out.print(this);
    }
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
    public static Device takeDeviceInput(Scanner scanner){
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
    private static String getStringInput(Scanner scanner, String message){
        String result = "";
        do {
            System.out.print(message);
            result = scanner.nextLine();
            if (result.equals("")){
                printRed("Invalid input, please try again.");
            }
        } while (result.equals(""));
        return result;
    }
    private static double getDoubleInput(Scanner scanner, String message){
        double result = -1;
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
    private static int getIntInput(Scanner scanner, String message){
        int result = -1;
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
    private static void printRed(String message){
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
    private static void printGreen(String message){
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }
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
                    addDevice(new Laptop(category, name, price, quantity));

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
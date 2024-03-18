import java.util.ArrayList;
import java.util.Scanner;
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
            printGreenn(name + " removed.");
            totalDevices--;
        } else {
            printRed(name + " not found.");
        }
    }
    public void restockDevice(Scanner scanner){
        //String name = Inventory.getStringInput(scanner, "Enter name of device to restock: ");
        //boolean isRestocked = false;
        //for (int i = 0; i < devices.size(); i++){
        //    for (int j = 0; j < devices.get(i).size(); j++){
        //        if (devices.get(i).get(j).getName().equals(name)){
        //            int quantity = Inventory.getIntInput(scanner, "Enter quantity to restock: ");
        //            devices.get(i).get(j).setQuantity(devices.get(i).get(j).getQuantity() + quantity);
        //            isRestocked = true;
        //            break;
        //        }
        //    }
        //}
        //if (isRestocked){
        //    System.out.println(name + " restocked.");
        //} else {
        //    System.out.println(name + " not found.");
        //}
    }
    public void exportInventoryReport(){
        System.out.println("Exporting inventory report...");
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
        //ArrayList<Device> allDevices = new ArrayList<Device>();
        //Device cheapestDevice = new Tv("Tv", "Tv", Double.MAX_VALUE, 2);
        //for (int i = 0; i < totalDevices; i++){
        //    cheapestDevice = new Tv("Tv", "Tv", Double.MAX_VALUE, 2);
        //    for (int j = 0; j < devices.get(i).size(); j++){
        //        for (int k = 0; k < devices.get(i).size(); k++){
        //            if (devices.get(i).get(j).getPrice() < devices.get(i).get(k).getPrice()){
        //                cheapestDevice = devices.get(i).get(j);
        //            }
        //        }
        //    }
        //    allDevices.add(cheapestDevice);
        //}
    }
    public void calculateTotalValue(){
        double totalValue = 0;
        for (int i = 0; i < devices.size(); i++){
            for (int j = 0; j < devices.get(i).size(); j++){
                totalValue += devices.get(i).get(j).getPrice() * devices.get(i).get(j).getQuantity();
            }
        }
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
}
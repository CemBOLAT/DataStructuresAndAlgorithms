import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        try{
            Scanner scanner = new Scanner(System.in);
            boolean     isExit = false;
            int option = -1;
            
            inventory.loadFromFile("inventory.txt");
            while (!isExit){
                try {
                    do {
                        printMenu();
                        String input = scanner.nextLine();
                        try {
                            option = Integer.parseInt(input);
                        } catch (NumberFormatException e){
                            System.out.println("Invalid option, please try again.");
                            continue;
                        }
                        if (option < 0 || option > 9)
                            System.out.println("Invalid option, please try again.");
                    } while (option < 0 || option > 9);
                    switch(option){
                        case 1: 
                            inventory.addDevice(scanner);
                        break;
                        case 2: 
                            inventory.removeDevice(scanner);
                        break;
                        case 3:
                            inventory.updateDevice(scanner);
                        break;
                        case 4:
                            inventory.listDevices();
                        break;
                        case 5:
                            inventory.findCheapestDevice();
                        break;
                        case 6:
                            inventory.sortDevicesByPrice();
                        break;
                        case 7:
                            inventory.calculateTotalValue();
                        break;
                        case 8:
                            inventory.restockDevice(scanner);
                        break;
                        case 9:
                            inventory.exportInventoryReport();
                        break;
                        case 0: 
                            isExit = true;
                            inventory.saveToFile("inventory.txt");
                            System.out.println("Goodbye!");
                        break;
                    }
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        } catch (Exception e){
            System.out.println(e);
            inventory.saveToFile("inventory.txt");
        }
    }

    private static void printMenu(){
        drawLine();
        System.out.println("Welcome to the Electronics Inventory Management System!\n");
        System.out.println("Please select an option:");
        System.out.println("1. Add a new device");
        System.out.println("2. Remove a device");
        System.out.println("3. Update device details");
        System.out.println("4. List all devices");
        System.out.println("5. Find the cheapest device");
        System.out.println("6. Sort devices by price");
        System.out.println("7. Calculate total inventory value");
        System.out.println("8. Restock a device");
        System.out.println("9. Export inventory report");
        System.out.println("0. Exit");
        drawLine();
        System.out.print("Your Choice >> ");
    }

    private static void drawLine(){
        System.out.println("-------------------------------------------------");
    }
}
import java.io.FileWriter;
import java.io.IOException;
/**
 * AbsDevice.java
 * <br>
 * AbsDevice represents an abstract implementation of a device.
 * <br>
 * It implements the Device interface providing basic functionalities
 * such as setting and getting category, name, price, and quantity,
 * as well as methods for managing stock.
 */
public class AbsDevice implements Device {
    private String  category; // The category of the device
    private String  name; // The name of the device
    private double  price; // The price of the device
    private int     quantity; // The quantity of the device
    /**
     * Constructor for AbsDevice
     * <br>
     * Time Complexity: O(1)
     * <br>
     * Because the constructor has constant time complexity
     * @param category The category of the device
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public AbsDevice(String category, String name, double price, int quantity) throws IllegalArgumentException {
        if (category == null || category.isEmpty()){
            throw new IllegalArgumentException("Invalid category");
        }
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Invalid name");
        }
        if (price < 0){
            throw new IllegalArgumentException("Invalid price");
        }
        if (quantity < 0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    /**
        * This method is used to get the category of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because getting the category has constant time complexity
        *
        * @return String - category of the device
    */
    @Override
    public String getCategory(){
        return category;
    }
    /**
        * This method is used to get the name of the device
        * <br>
        * Time Complexity: O(1)
        * Because getting the name has constant time complexity
        * @return String - name of the device
    */
    @Override
    public String getName(){
        return name;
    }
    /**
        * This method is used to get the price of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because getting the price has constant time complexity
        * @return double - price of the device
    */
    @Override
    public double getPrice(){
        return price;
    }
    /**
        * This method is used to get the quantity of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because getting the quantity has constant time complexity
        *
        * @return int - quantity of the device
    */
    @Override
    public int getQuantity(){
        return quantity;
    }
    /**
        * This method is used to set the category of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because setting the category has constant time complexity
        * @param category - category of the device
        * @throws IllegalArgumentException - if the category is not valid
    */
    @Override
    public void setCategory(String category) throws Exception {
        if (category == null || category.isEmpty()){
            throw new IllegalArgumentException("Invalid category");
        }
        this.category = category;
    }
    /**
        * This method is used to set the name of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because setting the name has constant time complexity
        * @param name - name of the device
        * @throws IllegalArgumentException - if the name is not valid
    */
    @Override
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }
    /**
        * This method is used to set the price of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because setting the price has constant time complexity
        * @param price - price of the device
        * @throws IllegalArgumentException - if the price is not valid
    */
    @Override
    public void setPrice(double price) throws IllegalArgumentException{
        if (price < 0){
            throw new IllegalArgumentException("Invalid price");
        }
        this.price = price;
    }
    /**
        * This method is used to set the quantity of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because setting the quantity has constant time complexity
        * @param quantity - quantity of the device
        * @throws IllegalArgumentException - if the quantity is not valid
    */
    @Override
    public void setQuantity(int quantity) throws IllegalArgumentException{
        if (quantity < 0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        this.quantity = quantity;
    }
    /**
        * This method is used to increase the quantity of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because increasing the quantity has constant time complexity
        * @param stock - stock to be added
        * @throws IllegalArgumentException - if the stock is not valid
    */
    @Override
    public void addStock(int stock){
        if (stock < 0){
            throw new IllegalArgumentException("Invalid stock");
        }
        quantity += stock;
        System.out.println(name + " restocked. New quantity: " + quantity);
    }
    /**
        * This method is used to decrease the quantity of the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because decreasing the quantity has constant time complexity
        * @param stock - stock to be removed
        * @throws IllegalArgumentException - if the stock is not valid
        * @throws IllegalArgumentException - if the stock is more than the quantity
    */
    @Override
    public void removeStock(int stock){
        if (stock < 0){
            throw new IllegalArgumentException("Invalid stock");
        }
        if (stock > quantity){
            throw new IllegalArgumentException("Not enough stock");
        }
        else
            quantity -= stock;
        System.out.println(name + " reduced. New quantity: " + quantity);
    }
    /**
        * This method is used to print the device
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because printing the device information has constant time complexity
        * @return String - the device
    */
    @Override
    public String toString(){
        return String.format("Category: %s, Name: %s, Price: %.2f$, Quantity: %d", category, name, price, quantity);
    }
    /**
        * This method is used to print the device information for the export format
        * <br>
        * Time Complexity: O(1)
        * <br>
        * Because writing the device information to the file has constant time complexity
        * @param index - index of the device
        * @param fileWriter - fileWriter to write to the file
        * @throws IOException - if the fileWriter is not valid
    */
    @Override
    public      void exportFilePrint(int index, FileWriter fileWriter) throws IOException{
        fileWriter.write(String.format("| %-3d | %-10s | %s | $%.2f | %d |\n", index, category, name, price, quantity));
    }
}

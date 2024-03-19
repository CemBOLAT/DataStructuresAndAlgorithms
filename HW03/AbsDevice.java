/**
 * AbsDevice.java
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
     
     * @param category The category of the device
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public AbsDevice(String category, String name, double price, int quantity) throws IllegalArgumentException {
        if (category == null || category.equals("")){
            throw new IllegalArgumentException("Invalid category");
        }
        if (name == null || name.equals("")){
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
        * 
        * @return String - category of the device
    */
    public String getCategory(){
        return category;
    }
    /**
        * This method is used to get the name of the device
        * <br>
        * Time Complexity: O(1)
        * @return String - name of the device
    */
    public String getName(){
        return name;
    }
    /**
        * This method is used to get the price of the device
        * <br>
        * Time Complexity: O(1)
        * @return double - price of the device
    */
    public double getPrice(){
        return price;
    }
    /**
        * This method is used to get the quantity of the device
        * <br>
        * Time Complexity: O(1)
        * @return int - quantity of the device
    */
    public int getQuantity(){
        return quantity;
    }
    /**
        * This method is used to set the category of the device
        * <br>
        * Time Complexity: O(1)
        * 
        * @param String - category of the device
        * @throws IllegalArgumentException - if the category is not valid
    */
    public void setCategory(String category) throws IllegalArgumentException {
        if (category == null || category.equals("")){
            throw new IllegalArgumentException("Invalid category");
        }
        this.category = category;
    }
    /**
        * This method is used to set the name of the device
        * <br>
        * Time Complexity: O(1)
        * 
        * @param String - name of the device
        * @throws IllegalArgumentException - if the name is not valid
    */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }
    /**
        * This method is used to set the price of the device
        * <br>
        * Time Complexity: O(1)
        * 
        * @param double - price of the device
        * @throws IllegalArgumentException - if the price is not valid
    */
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
        * 
        * @param int - quantity of the device
        * @throws IllegalArgumentException - if the quantity is not valid
    */
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
        * 
        * @param int - stock to be added
    */
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
        * 
        * @param int - stock to be removed
    */
    public void removeStock(int stock){
        if (stock < 0){
            throw new IllegalArgumentException("Invalid stock");
        }
        if (stock > quantity){
            quantity = 0;
        }
        else 
            quantity -= stock;
        System.out.println(name + " reduced. New quantity: " + quantity);
    }
    /**
        * This method is used to print the device
        * <br>
        * Time Complexity: O(1)
        * 
        * @return String - the device
    */
    public String toString(){
        return "Category: " + category + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
    /**
        * This method is used to print the device information for the export format
        * <br>
        * Time Complexity: O(1)
        * 
        * @param int - index of the device
    */
    public      void exportPrint(int index){
        System.out.println("| " + index + " | " + category + " | " + name + " | $" + price + " | " + quantity + " |");
    }
}
public class AbsDevice implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;
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
    public String getCategory(){
        return category;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setCategory(String category) throws IllegalArgumentException {
        if (category == null || category.equals("")){
            throw new IllegalArgumentException("Invalid category");
        }
        this.category = category;
    }
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }
    public void setPrice(double price) throws IllegalArgumentException{
        if (price < 0){
            throw new IllegalArgumentException("Invalid price");
        }
        this.price = price;
    }
    public void setQuantity(int quantity) throws IllegalArgumentException{
        if (quantity < 0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        this.quantity = quantity;
    }
    public void addStock(int stock){
        if (stock < 0){
            throw new IllegalArgumentException("Invalid stock");
        }
        quantity += stock;
        System.out.println(name + " restocked. New quantity: " + quantity);
    }
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
    public String toString(){
        return "Category: " + category + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
    public      void exportPrint(int index){
        System.out.println("| " + index + " | " + category + " | " + name + " | $" + price + " | " + quantity + " |");
    }
}
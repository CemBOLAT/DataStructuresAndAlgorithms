public class Tablet implements Device {
    protected   String category = "";
    protected   String name = "";
    protected   double price = 0;
    protected   int quantity = 0;
    public Tablet(String category, String name, double price, int quantity) throws IllegalArgumentException{
        if (category == null || name == null || price < 0 || quantity < 0){
            throw new IllegalArgumentException("Invalid input");
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
    public void setCategory(String category){
        if (category == null){
            throw new IllegalArgumentException("Invalid Category");
        }
        this.category = category;
    }
    public void setName(String name){
        if (name == null){
            throw new IllegalArgumentException("Invalid Name");
        }
        this.name = name;
    }
    public void setPrice(double price){
        if (price < 0){
            throw new IllegalArgumentException("Invalid Price");
        }
        this.price = price;
    }
    public void setQuantity(int quantity){
        if (quantity < 0){
            throw new IllegalArgumentException("Invalid Quantity");
        }
        this.quantity = quantity;
    }
    public String toString(){
        return "Category: " + category + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }

}
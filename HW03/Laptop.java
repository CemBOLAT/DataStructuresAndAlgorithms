public class Laptop extends AbsDevice {
    protected   String category = "";
    protected   String name = "";
    protected   double price = 0;
    protected   int quantity = 0;
    public Laptop(String category, String name, double price, int quantity) throws IllegalArgumentException{
        super(category, name, price, quantity);
    }  

}
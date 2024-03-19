/*
category,name, price, and quantity
*/
public interface Device {
    public      String getCategory();
    public      String getName();
    public      double getPrice();
    public      int getQuantity();
    public      void setCategory(String category);
    public      void setName(String name);
    public      void setPrice(double price);
    public      void setQuantity(int quantity);
    public      String toString();
    public      void addStock(int stock);
    public      void removeStock(int stock);
    public      void exportPrint(int index);
}
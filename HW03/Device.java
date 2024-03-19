/**
 * This interface is used to define the methods that are used in any device
 * @author - Cemal BOLAT
 */

public interface Device {
    /**
     * This method is used to get the category of the device
     * 
     * @return String - category of the device
     */
    public      String getCategory();
    /**
        * This method is used to get the name of the device
        * 
        * @return String - name of the device
    */
    public      String getName();
    /**
        * This method is used to get the price of the device
        * 
        * @return double - price of the device
    */
    public      double getPrice();
    /**
        * This method is used to get the quantity of the device
        * 
        * @return int - quantity of the device
    */
    public      int getQuantity();
    /**
        * This method is used to set the category of the device
        * 
        * @param String - category of the device
        * @throws Exception - if the category is not valid
    */
    public      void setCategory(String category) throws Exception;
    /**
        * This method is used to set the name of the device
        * 
        * @param String - name of the device
        * @throws Exception - if the name is not valid
    */
    public      void setName(String name) throws Exception;
    /**
        * This method is used to set the price of the device
        * 
        * @param double - price of the device
        * @throws Exception - if the price is not valid
    */
    public      void setPrice(double price) throws Exception;
    /**
        * This method is used to set the quantity of the device
        * 
        * @param int - quantity of the device
        * @throws Exception - if the quantity is not valid
    */
    public      void setQuantity(int quantity) throws Exception;
    /**
        * This method is used to increase the stock of the device
        * 
        * @param int - stock to be added
    */
    public      void addStock(int stock);
    /**
        * This method is used to decrease the stock of the device
        * 
        * @param int - stock to be removed
    */
    public      void removeStock(int stock);
    /**
        * This method is used to print the device information for the export format
        *
        * @param int - index of the device
    */
    public      void exportPrint(int index);
}
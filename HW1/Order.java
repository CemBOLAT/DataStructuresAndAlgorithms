public class Order
{
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public static final String STATUS[] = {"Initialized", "Processing", "Completed", "Cancelled"};

    public Order(String _product_name, int _count, int _total_price, int _status, int _customer_ID)
    {
        this.product_name = _product_name;
        this.count = _count;
        this.total_price = _total_price;
        this.status = _status;
        this.customer_ID = _customer_ID;
    }
    public String getProductName()
    {
        return this.product_name;
    }
    public int getCount()
    {
        return this.count;
    }
    public int getTotalPrice()
    {
        return this.total_price;
    }
    public int getStatus()
    {
        return this.status;
    }
    public int getCustomerID()
    {
        return this.customer_ID;
    }
    public void setProductName(String _product_name)
    {
        this.product_name = _product_name;
    }
    public void setCount(int _count)
    {
        this.count = _count;
    }
    public void setTotalPrice(int _total_price)
    {
        this.total_price = _total_price;
    }
    public void setStatus(int _status)
    {
        this.status = _status;
    }
    public void setCustomerID(int _customer_ID)
    {
        this.customer_ID = _customer_ID;
    }
    public void print_order()
    {
        System.out.println("Product Name: " + this.product_name + " - " + "Count: " + this.count + " - " + "Total Price: " + this.total_price + " - " + "Status: " + STATUS[status]);
    }
}
public class Customer extends Person
{
    private Order [] orders;
    private int operator_ID;
    private int orderCount;

    public Customer(String _name, String _surName, String _address, String _phoneNumber, int _ID, int _operator_ID)
    {
        super(_name, _surName, _address, _phoneNumber, _ID);
        this.operator_ID = _operator_ID;
        this.orders = new Order[100];
        this.orderCount = 0;
    }
    public int getOperatorID()
    {
        return this.operator_ID;
    }
    public void setOperatorID(int _operator_ID)
    {
        this.operator_ID = _operator_ID;
    }
    public int getOrderCount()
    {
        return this.orderCount;
    }
    public void print_customer()
    {
        System.out.println("Name & Surname: " + this.getName() + " " + this.getSurName() + "\n" + "Address: " + this.getAddress() + "\n" + "Phone Number: " + this.getPhoneNumber() + "\n" + "ID: " + this.getID() + "\n" + "Operator ID: " + this.operator_ID);
    }
    public void print_orders()
    {
        for(int i = 0; i < this.orderCount; i++)
        {
            System.out.print("Order #" + (i+1) + " => ");
            orders[i].print_order();
        }
    }
    public void define_orders(Order [] _orders)
    {
        int i = 0;
        while (_orders[i] != null)
        {
            if (_orders[i].getCustomerID() == this.getID())
            {
                this.orders[this.orderCount] = _orders[i];
                this.orderCount++;
            }
            i++;
        }
    }
}
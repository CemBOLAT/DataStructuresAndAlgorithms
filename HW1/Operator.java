public class Operator extends Person
{
    private int wage;
    private Customer [] customers;
    private int customerCount;
    public Operator(String _name, String _surName, String _address, String _phoneNumber, int _ID, int _wage)
    {
        super(_name, _surName, _address, _phoneNumber, _ID);
        this.wage = _wage;
        this.customers = new Customer[100];
        this.customerCount = 0;
    }
    public int getWage()
    {
        return this.wage;
    }
    public void setWage(int _wage)
    {
        this.wage = _wage;
    }
    public int getCustomerCount()
    {
        return this.customerCount;
    }
    public void print_operator()
    {
        System.out.println("Name & Surname: " + this.getName() + " " + this.getSurName() + "\n" + "Address: " + this.getAddress() + "\n" + "Phone Number: " + this.getPhoneNumber() + "\n" + "ID: " + this.getID() + "\n" + "Wage: " + this.wage);
    }
    public void print_customers()
    {
        for(int i = 0; i < this.customerCount; i++)
        {
            if (customers[i] instanceof Retail_customer){
                System.out.println("Customer #" + (i+1) + " (a retail customer) :");
            }
            else if (customers[i] instanceof Corporate_customer){
                System.out.println("Customer #" + (i+1) + " (a corporate customer) :");
            }
            customers[i].print_customer();
            customers[i].print_orders();
            Test.drawline();
        }
    }
    public void define_customers(Person [] _customers)
    {
        int i = 0;
        while (_customers[i] != null)
        {
            if (_customers[i] instanceof Customer)
            {
                if (((Customer)_customers[i]).getOperatorID() == this.getID())
                {
                    this.customers[this.customerCount] = (Customer)_customers[i];
                    this.customerCount++;
                }
            }
            i++;
        }
    }

}
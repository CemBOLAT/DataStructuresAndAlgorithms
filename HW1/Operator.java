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
            customers[i].print_customer();
        }
    }
    public void define_customer(String _name, String _surName, String _address, String _phoneNumber, int _ID)
    {
        customers[customerCount] = new Customer(_name, _surName, _address, _phoneNumber, _ID, this.getID());
        customerCount++;
    }

}
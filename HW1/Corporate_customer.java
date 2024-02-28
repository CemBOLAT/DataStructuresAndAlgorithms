public class Corporate_customer extends Customer
{
    private String company_name;

    public Corporate_customer(String _name, String _surName, String _address, String _phoneNumber, int _ID, int _operator_ID, String _company_name)
    {
        super(_name, _surName, _address, _phoneNumber, _ID, _operator_ID);
        this.company_name = _company_name;
    }
    public String getCompanyName()
    {
        return this.company_name;
    }
    public void setCompanyName(String _company_name)
    {
        this.company_name = _company_name;
    }
    @Override
    public void print_customer()
    {
        System.out.println("Name & Surname: " + this.getName() + " " + this.getSurName() + "\n" + "Address: " + this.getAddress() + "\n" + "Phone Number: " + this.getPhoneNumber() + "\n" + "ID: " + this.getID() + "\n" + "Operator ID: " + this.getOperatorID() + "\n" + "Company Name: " + this.company_name);
    }
}
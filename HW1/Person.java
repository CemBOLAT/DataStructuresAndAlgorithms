/*
    Person class is the base class for all the other classes. It has the basic attributes of a person.
    It has a constructor, getter and setter methods for each attribute.
*/
public class Person 
{
    private String name;
    private String surName;
    private String address;
    private String phoneNumber;
    private int ID;

    public Person(String _name, String _surName, String _address, String _phoneNumber, int _ID)
    {
        this.name = _name;
        this.surName = _surName;
        this.address = _address;
        this.phoneNumber = _phoneNumber;
        this.ID = _ID;
    }
    public String getName()
    {
        return this.name;
    }
    public String getSurName()
    {
        return this.surName;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    public int getID()
    {
        return this.ID;
    }
    public void setName(String _name)
    {
        this.name = _name;
    }
    public void setSurName(String _surName)
    {
        this.surName = _surName;
    }
    public void setAddress(String _address)
    {
        this.address = _address;
    }
    public void setPhoneNumber(String _phoneNumber)
    {
        this.phoneNumber = _phoneNumber;
    }
    public void setID(int _ID)
    {
        this.ID = _ID;
    }
}
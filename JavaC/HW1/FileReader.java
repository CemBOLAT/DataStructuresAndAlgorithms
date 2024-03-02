import java.io.File;
import java.util.Scanner;

/*
* This class reads the file and fills the arrays with the data in the file.
* It also checks if the data in the file is in the correct format.
*/

public class FileReader 
{
    private static final int ORDER_SPLIT_LENGTH = 6; // order;product_name;count;total_price;status;customer_id
    private static final int RETAIL_CUSTOMER_SPLIT_LENGTH = 7; // retail_customer;name;surname;address;phone;ID;operator_ID
    private static final int CORPORATE_CUSTOMER_SPLIT_LENGTH = 8; // retail_customer;name;surname;address;phone;ID;operator_ID;company_name
    private static final int OPERATOR_SPLIT_LENGTH = 7; // operator;name;surname;address;phone;ID;wage

    public static boolean isValidUIntAsString(String str)
    {
        if (str.length() == 0 || str.length() > 10) // max int is 214 748 3647
            return false;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }
        long d = Long.parseLong(str); // it might be 10 digits but it can be over max int
        if (d <= Integer.MAX_VALUE && d >= Integer.MIN_VALUE)
            return true;
        return false;
    }

    public static boolean isValidOrder(String [] order){
        if (order.length != ORDER_SPLIT_LENGTH)
            return false;
        if (order[1].length() == 0 || order[2].length() == 0 || order[3].length() == 0 || order[4].length() == 0 || order[5].length() == 0)
            return false;
        for (int i = 2; i < ORDER_SPLIT_LENGTH; i++)
        {
            if (!isValidUIntAsString(order[i]))
                return false;
        }
        if (Integer.parseInt(order[2]) == 0)
            return false;
        return true;
    }

    public static boolean isValidRetailCustomer(String [] retail_customer){
        if (retail_customer.length != RETAIL_CUSTOMER_SPLIT_LENGTH)
            return false;
        if (retail_customer[1].length() == 0 || retail_customer[2].length() == 0 || retail_customer[3].length() == 0 || retail_customer[4].length() == 0 || retail_customer[5].length() == 0 || retail_customer[6].length() == 0)
            return false;
        for (int i = 5; i < RETAIL_CUSTOMER_SPLIT_LENGTH; i++)
        {
            if (!isValidUIntAsString(retail_customer[i]))
                return false;
        }
        return true;
    }

    public static boolean isValidCorporateCustomer(String [] corporate_customer){
        if (corporate_customer.length != CORPORATE_CUSTOMER_SPLIT_LENGTH)
            return false;
        if (corporate_customer[1].length() == 0 || corporate_customer[2].length() == 0 || corporate_customer[3].length() == 0 || corporate_customer[4].length() == 0 || corporate_customer[5].length() == 0 || corporate_customer[6].length() == 0 || corporate_customer[7].length() == 0)
            return false;
        for (int i = 5; i < CORPORATE_CUSTOMER_SPLIT_LENGTH - 1; i++)
        {
            if (!isValidUIntAsString(corporate_customer[i]))
                return false;
        }
        return true;
    }

    public static boolean isValidOperator(String [] operator){
        if (operator.length != OPERATOR_SPLIT_LENGTH)
            return false;
        if (operator[1].length() == 0 || operator[2].length() == 0 || operator[3].length() == 0 || operator[4].length() == 0 || operator[5].length() == 0 || operator[6].length() == 0)
            return false;
        for (int i = 5; i < OPERATOR_SPLIT_LENGTH; i++)
        {
            if (!isValidUIntAsString(operator[i]))
                return false;
        }
        return true;
    }
    /*
    * This method checks if the ID is unique.
    * It takes an ID and an array of customers and an array of operators as parameters.
    * It returns true if the ID is unique, otherwise it returns false.
    */
    public static boolean isUniqueID(int ID, Customer [] customer, Operator [] operators)
    {
        for (int i = 0; i < customer.length; i++) // check if the ID is unique
        {
            if (customer[i] == null)
                break;
            if (customer[i].getID() == ID)
                return false;
        }
        for (int i = 0; i < operators.length; i++)
        {
            if (operators[i] == null)
                break;
            if (operators[i].getID() == ID)
                return false;
        }
        return true;
    }
    /*
    * This method fills the orders, customers and operators arrays with the data in the file.
    * It takes a file and arrays of orders, customers and operators as parameters.
    * It throws an exception if the file is not found or if there is an error while reading the file.
    * It ingores the lines that are not in the correct format.
    * It also ingores the lines that have the same ID with the other customers or operators.
    */
    public static void fillData(File file, Order[] orders, Customer [] customers, Operator [] operators) throws Exception
    {
        try {
            Scanner scanner = new Scanner(file);
            String line;
            String [] tokens;
            int orderCount = 0, customerCount = 0, operatorCount = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                tokens = line.split(";");
                if (tokens[0].equals("order"))
                {
                    if (isValidOrder(tokens) == false)
                    {
                        continue;
                    }

                    orders[orderCount] = new Order(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                    orderCount++;
                }
                else if (tokens[0].equals("retail_customer"))
                {
                    if (isValidRetailCustomer(tokens) == false)
                    {
                        continue;
                    }
                    if (isUniqueID(Integer.parseInt(tokens[5]), customers, operators) == false)
                    {
                        continue;
                    }
                    Retail_customer retail_customer = new Retail_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    retail_customer.define_orders(orders);
                    customers[customerCount] = retail_customer;
                    customerCount++;
                }
                else if (tokens[0].equals("corporate_customer"))
                {
                    if (isValidCorporateCustomer(tokens) == false)
                    {
                        continue;
                    }
                    if (isUniqueID(Integer.parseInt(tokens[5]), customers, operators) == false)
                    {
                        continue;
                    }
                    Corporate_customer corporate_customer = new Corporate_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                    corporate_customer.define_orders(orders);
                    customers[customerCount] = corporate_customer;
                    customerCount++;
                }
                else if (tokens[0].equals("operator"))
                {
                    if (isValidOperator(tokens) == false)
                    {
                        continue;
                    }
                    if (isUniqueID(Integer.parseInt(tokens[5]), customers, operators) == false)
                    {
                        continue;
                    }
                    Operator operator = new Operator(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    operator.define_customers(customers);
                    operators[operatorCount] = operator;
                    operatorCount++;
                }
                else {
                    continue;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
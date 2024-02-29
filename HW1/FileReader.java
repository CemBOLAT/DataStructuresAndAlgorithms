import java.io.File;
import java.util.Scanner;

public class FileReader 
{
    private static final int ORDER_SPLIT_LENGTH = 6;
    private static final int RETAIL_CUSTOMER_SPLIT_LENGTH = 7;
    private static final int CORPORATE_CUSTOMER_SPLIT_LENGTH = 8;
    private static final int OPERATOR_SPLIT_LENGTH = 7;

    public static boolean isValidUIntAsString(String str)
    {
        if (str.length() == 0 || str.length() > 10) // max int is 214 748 3647
            return false;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }
        return true;
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

    public static boolean isUniqueID(int ID, Person[] people)
    {
        for (int i = 0; i < people.length; i++)
        {
            if (people[i] == null)
                break;
            if (people[i].getID() == ID)
                return false;
        }
        return true;
    }

    public static void fillData(File file, Order[] orders, Person[] people) throws Exception
    {
        try {
            Scanner scanner = new Scanner(file);
            String line;
            String [] tokens;
            int orderCount = 0, personCount = 0;
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
                    if (isUniqueID(Integer.parseInt(tokens[5]), people) == false)
                    {
                        continue;
                    }
                    Retail_customer retail_customer = new Retail_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    retail_customer.define_orders(orders);
                    people[personCount] = retail_customer;
                    personCount++;
                }
                else if (tokens[0].equals("corporate_customer"))
                {
                    if (isValidCorporateCustomer(tokens) == false)
                    {
                        continue;
                    }
                    if (isUniqueID(Integer.parseInt(tokens[5]), people) == false)
                    {
                        continue;
                    }
                    Corporate_customer corporate_customer = new Corporate_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                    corporate_customer.define_orders(orders);
                    people[personCount] = corporate_customer;
                    personCount++;
                }
                else if (tokens[0].equals("operator"))
                {
                    if (isValidOperator(tokens) == false)
                    {
                        continue;
                    }
                    if (isUniqueID(Integer.parseInt(tokens[5]), people) == false)
                    {
                        continue;
                    }
                    Operator operator = new Operator(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    operator.define_customers(people);
                    people[personCount] = operator;
                    personCount++;
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
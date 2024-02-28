import java.io.File;
import java.util.Scanner;

public class FileReader 
{
    private static final int ORDER_SPLIT_LENGTH = 6;
    private static final int RETAIL_CUSTOMER_SPLIT_LENGTH = 7;
    private static final int CORPORATE_CUSTOMER_SPLIT_LENGTH = 8;
    private static final int OPERATOR_SPLIT_LENGTH = 7;
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
                    if (tokens.length != ORDER_SPLIT_LENGTH)
                        throw new Exception("Invalid order data");
                    orders[orderCount] = new Order(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                    orderCount++;
                }
                else if (tokens[0].equals("retail_customer"))
                {
                    if (tokens.length != RETAIL_CUSTOMER_SPLIT_LENGTH)
                        throw new Exception("Invalid retail customer data");
                    people[personCount] = new Retail_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    personCount++;
                }
                else if (tokens[0].equals("corporate_customer"))
                {
                    if (tokens.length != CORPORATE_CUSTOMER_SPLIT_LENGTH)
                        throw new Exception("Invalid corporate customer data");
                    people[personCount] = new Corporate_customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                    personCount++;
                }
                else if (tokens[0].equals("operator"))
                {
                    if (tokens.length != OPERATOR_SPLIT_LENGTH)
                        throw new Exception("Invalid operator data");
                    people[personCount] = new Operator(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    personCount++;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

// order;tv;2;2000;0;1500
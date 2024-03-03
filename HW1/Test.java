import java.io.File;
import java.util.Scanner;

public class Test
{
    // This method prints the orders in the array.
    public static void printOrders(Order [] order){
        for (int i = 0; i < order.length; i++)
        {
            if (order[i] == null)
                break;
            order[i].print_order();
        }
    }
    // This method prints the customers in the array.
    public static void printCustomers(Customer [] customer){
        for (int i = 0; i < customer.length; i++)
        {
            if (customer[i] == null)
                break;
            customer[i].print_customer();
        }
    }
    // This method prints the operators in the array.
    public static void printOperators(Operator [] operator){
        for (int i = 0; i < operator.length; i++)
        {
            if (operator[i] == null)
                break;
            operator[i].print_operator();
        }
    }
    public static void drawline()
    {
        System.out.println("-------------------------------------------------");
    }
    public static boolean isValidInput(String input) throws Exception
    {
        if(input.length() > 0 && input.length() < 11) // max int is 214 748 3647
        {
            for(int i = 0; i < input.length(); i++)
            {
                if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                    System.out.println("Invalid input. Please enter a valid ID.");
                    return false;
                }
            }
            long d = Long.parseLong(input);
            if (d <= Integer.MAX_VALUE && d >= Integer.MIN_VALUE){
                if (d == 0){
                    System.out.println("Invalid input. Please enter a valid ID.");
                    return false;
                }
                return true;
            }
            System.out.println("Invalid input its over max int. Please enter a valid ID.");
            return false;
        }
        System.out.println("Invalid length of input. Please enter an integer as a valid ID.");
        return false;
    }
    // This method is used for testing the reader class.
    // It prints the orders, customers and operators in the arrays.
    public static void testReader(Order [] orders, Customer [] customers, Operator [] operators) throws Exception
    {
        printOrders(orders);
        drawline();
        printCustomers(customers);
        drawline();
        printOperators(operators);
        drawline();
    }
    public static void main(String[] args)
    {
        try{
            File dosya = new File("./content.txt");
            /*
                As your permisson I used fixed size arrays with size 100.
                -- You can define the size of the arrays you use as 100. (as pdf file says)
            */
            Order [] orders = new Order[100];
            Customer [] customers = new Customer[100];
            Operator [] operators = new Operator[100];
            FileReader.fillData(dosya, orders, customers, operators);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your ID...: ");
            String input = scanner.nextLine();
            if (!isValidInput(input)){
                return;
            }
            int ID = Integer.parseInt(input);
            boolean found = false;
            for (int i = 0; i < operators.length; i++)
            {
                if (operators[i] == null)
                    break;
                if (operators[i].getID() == ID)
                {
                    found = true;
                    System.out.println("*** Operator Screen ***");
                    drawline();
                    Operator operator = operators[i];
                    operator.print_operator();
                    drawline();
                    if (operator.getCustomerCount() == 0)
                    {
                        System.out.println("This operator doesn't have any customer.");
                        drawline();
                        break;
                    }
                    operator.print_customers();
                }
            }
            if (!found)
            {
                for (int i = 0; i < customers.length; i++)
                {
                    if (customers[i] == null)
                        break;
                    if (customers[i].getID() == ID)
                    {
                        found = true;
                        System.out.println("*** Customer Screen ***");
                        Customer customer = customers[i];
                        customer.print_customer();
                        customer.print_orders();
                    }
                }
            }
            if(!found)
                System.out.println("No operator/customer was found with ID " + input + ". Please try again.");

        } catch (Exception e) { /* ... */ }
    }
}

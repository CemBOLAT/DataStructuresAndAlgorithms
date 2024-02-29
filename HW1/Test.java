import java.io.File;
import java.util.Scanner;

public class Test
{
    public static void drawline()
    {
        System.out.println("-------------------------------------------------");
    }
    public static boolean isValidInput(String input)
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
            return true;
        }
        System.out.println("Invalid length of input. Please enter an integer as a valid ID.");
        return false;
    }
    public static void main(String[] args)
    {
        try{
            File dosya = new File("./content.txt");
            Order [] orders = new Order[100];
            Person [] people = new Person[100];
            FileReader.fillData(dosya, orders, people);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your ID...: ");
            String input = scanner.nextLine();
            if (!isValidInput(input)){
                return;
            }
            int ID = Integer.parseInt(input);
            boolean found = false;
            for(int i = 0; i < people.length; i++)
            {
                if(people[i] == null)
                    break;
                if(people[i].getID() == ID)
                {
                    if(people[i] instanceof Operator)
                    {
                        found = true;
                        System.out.println("*** Operator Screen ***");
                        drawline();
                        Operator operator = (Operator) people[i];
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
                    else if(people[i] instanceof Customer)
                    {
                        found = true;
                        System.out.println("*** Customer Screen ***");
                        Customer customer = (Customer) people[i];
                        customer.print_customer();
                        customer.print_orders();
                    }
                }
            }
            if(!found)
                System.out.println("No operator/customer was found with ID " + input + ". Please try again.");

        } catch (Exception e) { /* ... */ }
    }
    public static void fillCustomerArr(){

    }
}
import java.io.File;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        try{
            File dosya = new File("./content.txt");
            Order [] orders = new Order[100];
            Person [] people = new Person[100];
            FileReader.fillData(dosya, orders, people);
            Test.fillArrs();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your ID...: ");
            String input = scanner.nextLine();
            int ID = Integer.parseInt(input);
            boolean isOperator = false;
            for(int i = 0; i < people.length; i++)
            {
                if(people[i] == null)
                    break;
                if(people[i].getID() == ID)
                {
                    if(people[i] instanceof Operator)
                    {
                        isOperator = true;
                        Operator operator = (Operator) people[i];
                        operator.print_operator();
                        operator.print_customers();
                    }
                    else if(people[i] instanceof Customer)
                    {
                        Customer customer = (Customer) people[i];
                        customer.print_customer();
                        customer.print_orders();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void fillCustomerArr(){

    }
}
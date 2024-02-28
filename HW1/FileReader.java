import java.io.File;
import java.util.Scanner;

public class FileReader 
{
    public static int getNumberOfLines(File file) throws Exception
    {
        Scanner scanner = new Scanner(file);
        int count = 0;
        while(scanner.hasNextLine())
        {
            count++;
            scanner.nextLine();
        }
        scanner.close();
        return count;
    }
    public static Person [] readPeople(File file) throws Exception
    {
        int count = getNumberOfLines(file);
        Scanner scanner = new Scanner(file);
        Person [] people = new Person[count];
        for(int i = 0; i < count; i++)
        {
            String line = scanner.nextLine();
            String [] parts = line.split(";");
            people[i] = new Person(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[5]));
        }
        scanner.close();
        return people;
    }
}
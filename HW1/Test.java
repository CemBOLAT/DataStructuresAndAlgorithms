import java.io.File;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        try{
            File dosya = new File("./content.txt");
            Person [] people = FileReader.readPeople(dosya);
            for(int i = 0; i < people.length; i++)
            {
                System.out.println(people[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
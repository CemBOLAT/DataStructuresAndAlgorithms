import  java.util.List;
import  java.util.ArrayList;

public class Array_list
{
    public static void printList(List<Integer> myList){
        for (int i = 0; i < myList.size(); i++){
            System.out.printf("%d - ", myList.get(i));
        }
        System.out.println();   
    }
    public static void main(String[] arr){
        List<Integer> myList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++){
            myList.add((i * 3) + 2);    
        }
        printList(myList);
        myList.remove(1); // remove index
        printList(myList);
        myList.set(0, -1);
        printList(myList);
        System.out.println(myList.indexOf(4)); // -1 for not found
        System.out.println(myList.indexOf(29));
    }
}
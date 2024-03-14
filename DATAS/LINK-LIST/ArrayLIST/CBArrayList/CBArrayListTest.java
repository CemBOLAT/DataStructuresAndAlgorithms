public class CBArrayListTest
{
    public static void printList(CBArrayList<Integer> myList) throws Exception{
        for (int i = 0; i < myList.size(); i++){
            System.out.printf("%d - ", myList.get(i));
        }
        System.out.println();   
    }
    public static void main(String[] args){
        try{
            CBArrayList<Integer> arrayList = new CBArrayList<Integer>();
            for (int i = 0; i < 10; i++){
                arrayList.add((i * 3) + 2);
            }
            printList(arrayList);
            arrayList.remove(1);
            arrayList.remove(0);
            arrayList.remove(5);
            printList(arrayList);
            arrayList.set(0, 33);
            printList(arrayList);
            while (arrayList.size() != 0){
                arrayList.remove(0);
            }
            printList(arrayList);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
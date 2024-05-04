
public class Selection
{
    public static void sort(int[] arr)
    {
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++){
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }
    public static void main(String[] args)
    {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = (int)(Math.random() * 100);
        }
        sort(arr);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
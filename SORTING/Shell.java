
public class Shell
{
    public static void sort(int[] arr)
    {
        int gap = arr.length / 2;
        while (gap > 0){
            for (int i = gap; i < arr.length; i++)
            {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key)
                {
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }
                arr[j] = key;
            }
            if (gap == 2)
            {
                gap = 1;
            }
            else
            {
                gap = (int)(gap / 2.2); // 2.2 is the best value for gap
            }
        
        }
    }
    public static void main(String[] args)
    {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = (int)(Math.random() * 5000);
        }
        sort(arr);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
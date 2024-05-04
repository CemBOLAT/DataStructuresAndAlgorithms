
public class Quicksort
{
    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; // pivot
        int i = low - 1; // Index of smaller element
        for (int j = low; j < high; j++) 
        {
            if (arr[j] < pivot) // If current element is smaller than the pivot
            {
                i++;
                int temp = arr[i]; // swap arr[i] and arr[j] // arr[i] is the element that is smaller than the pivot
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; // swap arr[i+1] and arr[high] (or pivot)
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }   
    public static void quicksort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    public static void sort(int[] arr)
    {
        quicksort(arr, 0, arr.length - 1);   
    }
    public static void main(String[] args)
    {
        int[] arr = new int[55];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = (int)(Math.random() * 1000);
        }
        sort(arr);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
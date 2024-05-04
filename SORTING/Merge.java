
public class Merge
{
    public static void merge(int[] left, int[] right, int[] arr)
    {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length)
        {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }

        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
    }
    public static void sort(int[] arr)
    {
        if (arr.length < 2)
        {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        for (int i = 0; i < mid; i++)
        {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++)
        {
            right[i - mid] = arr[i];
        }
        sort(left);
        sort(right);
        merge(left, right, arr);
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
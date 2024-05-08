public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}

    private int partition(int arr[], int begin, int end){
        // fill this method
        int pivot = arr[end];
        int i = begin - 1;
        int j = begin;
        while (j < end) {
            comparison_counter += 1;
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
            j++;
        }
        swap(i + 1, end);
        return i + 1;
    }

    private void sort(int arr[], int begin, int end){
        // fill this method
        if (begin >= end) {
            return;
        }
        int partitionIndex = partition(arr, begin, end);
        sort(arr, begin, partitionIndex - 1);
        sort(arr, partitionIndex + 1, end);
    }

    @Override
    public void sort() {
        int begin = 0;
        int end = arr.length - 1;
    	sort(arr, begin, end);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}

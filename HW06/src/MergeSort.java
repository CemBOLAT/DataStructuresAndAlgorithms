public class MergeSort extends SortAlgorithm {

	public MergeSort(int input_array[]) {
		super(input_array);
	}

	private void merge(int arr[], int begin, int mid, int end){
        // fill this method
        int leftIndex = begin;
        int rightIndex = mid + 1;
        int tempIndex = 0;
        int temp[] = new int[end - begin + 1];
        while (leftIndex <= mid && rightIndex <= end) {
            comparison_counter += 1;
            if (arr[leftIndex] < arr[rightIndex]) {
                temp[tempIndex] = arr[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = arr[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }
        while (leftIndex <= mid) {
            temp[tempIndex] = arr[leftIndex];
            leftIndex++;
            tempIndex++;
        }
        while (rightIndex <= end) {
            temp[tempIndex] = arr[rightIndex];
            rightIndex++;
            tempIndex++;
        }
        for (int i = 0; i < temp.length; i++) {
            arr[begin + i] = temp[i];
        }
    }

    private void sort(int arr[], int begin, int end){
        // fill this method
        if (begin >= end) {
            return;
        }
        int mid = (end + begin) / 2;
        sort(arr, begin, mid);
        sort(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    @Override
    public void sort() {
        int begin = 0;
        int end = arr.length - 1;
    	sort(arr, begin, end);
    }

    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}

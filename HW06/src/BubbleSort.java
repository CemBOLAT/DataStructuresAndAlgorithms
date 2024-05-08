public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
    	// fill this method
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				comparison_counter += 1;
				if (arr[j] > arr[j + 1]) {
					swap(j, j + 1);
				}
			}
		}
    }

    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}

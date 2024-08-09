import java.util.Random;

class Sorter <T extends Comparable<T>>{

    public static <T extends Comparable<T>> void quickSort(T[] array){
        quickSort(array,0, array.length-1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int start, int end){
        if (array.length > 3) {
            if (start < end) {
                int pivot = partition(array, start, end);
                quickSort(array, start, pivot - 1);
                quickSort(array, pivot + 1, end);
            }
        } else {
            simpleSort(array, array.length);
        }
    }
   // swapping between 2 cells in the array
    private static <T extends Comparable<T>> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j]  = temp;
    }

    private static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
        int randomIndex = new Random().nextInt((end - start) + 1) + start;
        swap(array, randomIndex, end); //  swapping the chosen pivot with the last element of the array
        T pivotValue = array[end];
        int j = end;
        int i = start-1;
        while (true) {
            do {
                i++;
            }  while (i<=end && array[i].compareTo(pivotValue)<0);

            do {
                j--;
            }  while (j>=start && array[j].compareTo(pivotValue) >0);

            if (i>=j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, j+1,  end);
        return j+1;
    }

   // this function sorts small arrays
    private static <T extends Comparable<T>> void simpleSort(T[] array, int size){
        for (int i = 0; i < size ; i++) {
            int count = 0;
            for (int j = 1; j < size - i; j++) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    count++;
                    T temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
                if (count == 0) {
                    return;
                }
            }
        }
    }



    public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] array) {
        int n = array.length;
        T[] tempArray = (T[]) new Comparable[n];

        // Start with subarrays of size 1, then merge them in pairs
        for (int currSize = 1; currSize <= n - 1; currSize *= 2 ) {
            // Pick starting point of different subarrays of current size
            for (int leftStart = 0; leftStart < n - currSize; leftStart += 2 * currSize) { // to check if to change n-1 to curr
                // Find ending point of left subarray
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                // Find ending point of right subarray
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                // Merge subarrays array[leftStart...mid] and array[mid+1...rightEnd]
                merge(array, tempArray, leftStart, mid, rightEnd);
            }
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] tempArray, int leftStart, int mid, int rightEnd) {
        int left = leftStart;
        int right = mid + 1;
        int index = leftStart;

        // Merge the two half into tempArray
        while (left <= mid && right <= rightEnd) {
            if (array[left].compareTo(array[right]) <= 0) {
                tempArray[index] = array[left];
                left++;
            } else {
                tempArray[index] = array[right];
                right++;
            }
            index++;
        }

        // Copy remaining elements of left half, if any
        while (left <= mid) {
            tempArray[index] = array[left];
            left++;
            index++;
        }

        // Copy remaining elements of right half, if any
        while (right <= rightEnd) {
            tempArray[index] = array[right];
            right++;
            index++;
        }

        // Copy the merged subarray back to the original array
        for (index = leftStart; index <= rightEnd; index++) {
            array[index] = tempArray[index];
        }
    }

    public static void radixSort(Long[] array, int bitsPerDigit) {
        Long max = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        int countTotalDigits = 0;
        // checks how many digits
        while (max != 0){
            max = max >> bitsPerDigit;
            countTotalDigits++;
        }
        for (int i = 0; i < countTotalDigits; i++){
            countingSort(array,bitsPerDigit,i);
        }
    }

    public static void countingSort(Long[] arr, int bitsPerDigit, int digitIndex){
        int[] digitArray = new int[arr.length];
        Long[] output = new Long[arr.length];
        int max = 0;
        // finding the digit's max number
        for(int i = 0; i < arr.length; i++){
            if (arr[i] == null) {
                arr[i] = 0L;
            }
            digitArray[i] = extractDigit(arr[i],bitsPerDigit,digitIndex);
            if(digitArray[i] > max){
                max = digitArray[i];
            }
        }
        // setting the count array length to the max number + 1
        int[] countArray = new int[max+1];
        for(int j=0; j < digitArray.length; j++){
            countArray[digitArray[j]]++;
        }
        for (int k=1; k < countArray.length; k++){
            countArray[k] += countArray[k-1];
        }
        for(int m=output.length-1; m >=0; m--){
            output[--countArray[digitArray[m]]] = arr[m];
        }
        for(int h=0; h < output.length; h++){
            arr[h] = output[h];
        }

    }
    public static int extractDigit(Long key, int bitsPerDigit, int digitIndex){

        // Shift the bits of the key to get the required  digit as the least significant bits
        long shiftedKey = key >> (digitIndex * bitsPerDigit);

        // bit mask to extract only the needed bits
        long mask = (1 << bitsPerDigit) - 1;
        int extractedDigit = (int) (shiftedKey & mask);

        return extractedDigit;
    }

}
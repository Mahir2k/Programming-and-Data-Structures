import java.util.ArrayList;
public class Sort{
    public static int[] iterations = new int[8];
    /**
     * Selection sort algorithm
     * @param list to be sorted
     * Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list) {
        iterations[0] = 0;
        int minIndex;
        for (int i=0; i<list.size()-1; i++) {
            iterations[0]++;
            // Find the smallest element from i+1 to N
            E min = list.get(i); 
            minIndex = i;
            for (int j=i+1; j<list.size(); j++){
                iterations[0]++;
                if (list.get(j).compareTo(min)<0){
                    min = list.get(j);
                    minIndex = j;
                }
            }
            // Swap the smallest element with element i
            swap(list, i, minIndex);
        }      
    }  

    /**
     * Insertion sort algorithm
     * @param list to be sorted
     * Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list) {
        iterations[1] = 0;
        for (int i=1; i<list.size(); i++) {
            iterations[1]++;
            //Insert element i in the sorted sub-list
            E currentVal = list.get(i);
            int j = i;
            while (j > 0 && currentVal.compareTo(list.get(j - 1))<0){
                iterations[1]++;
                // Shift element (j-1) into element (j)
                list.set(j, list.get(j - 1));
                j--;
            }
            // Insert currentVal at index j 
            list.set(j, currentVal);
        }
    }
    /**
     * Bubble sort algorithm
     * @param list to be sorted
     * Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> list) { 
        iterations[2] = 0;
        boolean sorted = false; 
        for (int k=1; k < list.size() && !sorted; k++) { 
            sorted = true; 
            iterations[2]++;
            for (int i=0; i<list.size()-k; i++) { 
                iterations[2]++;
                if (list.get(i).compareTo(list.get(i+1))>0) { 
                    swap(list, i, i+1);
                    sorted = false; 
                } 
            } 
        }     
    }
    /**
     * Helper method to swap two elements in a list
     * @param list where to swap the elements
     * @param i1 the index of the first element
     * @param i2 the index of the sedond element
     * Time complexity: O(1)
     */
    public static <E> void swap(ArrayList<E> list, int i1, int i2){
        if (i1 < 0 || i2 < 0 || i1 >= list.size() || i2 >= list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        E temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, temp);
    } 
    /**
     * Merge sort algorithm
     * @param list to be sorted
     * Time complexity: O(nlogn)
     */
    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> list) {
        iterations[3]++; //count the number of recursive calls
        if (list.size() > 1) {
            ArrayList<E> firstHalf = subList(list, 0, list.size()/2);
            ArrayList<E> secondHalf = subList(list, list.size()/2, list.size());
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }
    public static <E> ArrayList<E> subList(ArrayList<E> list, int start, int end){
        if(start < 0 || end < 0 || start >= list.size() || end > list.size() ||
            start > end){
                throw new ArrayIndexOutOfBoundsException();
        }
        ArrayList<E> sub = new ArrayList<>();
        for(int i=start; i<end; i++){
            iterations[3]++;
            sub.add(list.get(i));
        } 
        return sub; 
    } 
    /**
     * Merge method used by the merge sort algorithm
     * @param list1 the first sorted list to be merged
     * @param list2 the second sorted list to be merged
     * @param list the list to contain the result of merging list1 and list2
     * Time complexity: O(n+m), where n is the size of list1 and m the size of list2
     */
    public static <E extends Comparable<E>> void merge(ArrayList<E> list1, ArrayList<E>  list2, ArrayList<E>  list) {
        int list1Index = 0, list2Index = 0, listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            iterations[3]++;
            if (list1.get(list1Index).compareTo(list2.get(list2Index))<0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        // copy the remaining elements from list1 to list if any
        while(list1Index < list1.size()){
            iterations[3]++;
            list.set(listIndex++, list1.get(list1Index++));
        }
        // copy the remaining elements from list2 to list if any
        while(list2Index < list2.size()){
            iterations[3]++;
            list.set(listIndex++, list2.get(list2Index++));
        }
    }

    /**
     * Quick sort algorithm
     * @param list to be sorted
     * Time complexity: O(nlogn) on average, O(n^2) worst case
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list) {
        iterations[4] = 0;
        quickSort(list, 0, list.size()-1);
    }
    /**
     * Quick sort recursive helper method
     * @param list the part to be sorted
     * @param first the index of the first element in this part
     * @param last the index of the last element in this part
     * Time complexity: O(nlogn) on average, O(n^2) worst case
     */
    // Recursive Helper Method
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int first, int last) {
        iterations[4]++;
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex-1);
            quickSort(list, pivotIndex+1, last);
        }
    }

    /**
     * Helper method partition used by the quick sort algorithm
     * @param list the part to be partitioned
     * @param first the index of the first element in this part
     * @param last the index of the last element in this part
     * Time complexity: O(n), n is the size of list
     */
    public static <E extends Comparable<E>> int partition(ArrayList<E> list, int first, int last){
        E pivot;
        int index, pivotIndex;
        pivot = list.get(first);// the first element
        pivotIndex = first;
        for (index = first + 1; index <= last; index++){
            iterations[4]++;
            if (list.get(index).compareTo(pivot)<0){
                pivotIndex++;
                swap(list, pivotIndex, index);
            }
        }
        swap(list, first, pivotIndex);
        return pivotIndex;
    }

    /**
     * Heap sort algorithm
     * @param list to be sorted
     * Time complexity: O(nlogn)
     */
    public static <E extends Comparable<E>> void heapSort(ArrayList<E> list) {
        iterations[5] = 0;
        // Create a min heap 
        PriorityQueue<E> heap = new PriorityQueue<>();
        // Add the elements of list to the heap
        for(int i=0; i<list.size(); i++){
            iterations[5]++;
            heap.offer(list.get(i));
            iterations[5] += PriorityQueue.offerIter;
        }
        // Move the data from the heap back to list
        for (int i=0; i<list.size(); i++) {
            iterations[5]++;
            list.set(i, heap.poll());
            iterations[5] += PriorityQueue.pollIter;
        }
    }
    /**
     * Helper method to find the maximum in a list of integers
     * @param list to be sorted
     * @return the maximum value in list
     * Time complexity: O(n)
     */
    public static int max(ArrayList<Integer> list){
        int m=list.get(0);
        for(int value: list){
            iterations[6]++;
            iterations[7]++;
            if(value > m)
                m = value;
        }
        return m;
    }

    /**
     * QuicBucketk sort algorithm
     * @param list to be sorted
     * Time complexity: O(n+t), where n is the size of list and t the maximum value in list
     */
    public static void bucketSort(ArrayList<Integer> list) {
        iterations[6] = 0;
        int t = max(list);
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>(t+1);
        // create t+1 buckets
        for(int i=0; i<t+1; i++){
            iterations[6]++;
            buckets.add(new ArrayList<>());
        }
                
        //Distribute the data on the buckets
        for(int i=0; i<list.size(); i++) {
            iterations[6]++;
            ArrayList<Integer> bucket = buckets.get(list.get(i));
            bucket.add(list.get(i));
        }
        // Move the data from the buckets back to the list
        for(int i=0,k=0; i<buckets.size(); i++) {
            iterations[6]++;
            ArrayList<Integer> bucket = buckets.get(i);
            for(int j=0; j<bucket.size(); j++){
                iterations[6]++;
                list.set(k++, bucket.get(j));
            }
        }
    }

    /**
     * Radix sort algorithm
     * @param list to be sorted
     * Time complexity: O(d.n) where n is the size of list and
     *                  d is the number of radix positions in the maximum value in the list
     */
    public static void radixSort(ArrayList<Integer> list) {
        iterations[7] = 0;
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>();
        Integer maxValue = max(list); 
        int digits = maxValue.toString().length();
        for(int d=0; d<digits; d++) {
            iterations[7]++;
            // create 10 buckets
            for(int j=0; j<10; j++) { 
                iterations[7]++;
                buckets.add(new ArrayList<>());
            } 
            //Distribute the data on the buckets
            for(int j=0; j<list.size(); j++){
                iterations[7]++;
                int bucketIndex = (list.get(j)%(int)(Math.pow(10, d+1)))/
                                  (int)(Math.pow(10,d));
                ArrayList<Integer> bucket = buckets.get(bucketIndex);
                bucket.add(list.get(j));
            }
            // Move the data from the buckets back to the list
            for(int j=0,k=0; j<10; j++) {
                iterations[7]++;
                ArrayList<Integer> bucket = buckets.get(j);
                for(int l=0; l<bucket.size(); l++){
                    iterations[7]++;
                    list.set(k++, bucket.get(l));
                }
            }
            // clear all the buckets for the next iteration
            buckets.clear(); 
        }
    }
}
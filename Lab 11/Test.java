import java.util.Collections;
import java.util.ArrayList;
public class Test{
    public static final int SIZE = 10000;
    public static void main(String[] args){
        //random List
        ArrayList<Integer> randomList = new ArrayList<>();
        for(int i=0; i<SIZE; i++){
            randomList.add((int)(Math.random()*SIZE));
        }  
        //sorted List
        ArrayList<Integer> sortedList = (ArrayList<Integer>) (randomList.clone());
        Collections.sort(sortedList);

        //reversed List
        ArrayList<Integer> reversedList = (ArrayList<Integer>) (sortedList.clone());
        Collections.reverse(reversedList);

        //Selection Sort
        Sort.selectionSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Selection Sort", Sort.iterations[0]);
        Sort.selectionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[0]);
        Sort.selectionSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[0]);

        //Insertion Sort
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);


        Sort.insertionSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Insertion Sort", Sort.iterations[1]);
        Sort.insertionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[1]);
        Sort.insertionSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[1]);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Bubble Sort
        Sort.bubbleSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Bubble Sort", Sort.iterations[2]);
        Sort.bubbleSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[2]);
        Sort.bubbleSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[2]);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Merge Sort
        Sort.iterations[3] = 0;
        Sort.mergeSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Insertion Sort", Sort.iterations[3]);
        Sort.iterations[3] = 0;
        Sort.mergeSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[3]);
        Sort.iterations[3] = 0;
        Sort.mergeSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[3]);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Quick Sort
        Sort.quickSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Quick Sort", Sort.iterations[4]);
        Sort.quickSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[4]);
        Sort.quickSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[4]);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Heap Sort
        Sort.heapSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Heap Sort", Sort.iterations[5]);
        Sort.heapSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[5]);
        Sort.heapSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[5]);
        
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Bucket Sort
        Sort.bucketSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Heap Sort", Sort.iterations[6]);
        Sort.bucketSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[6]);
        Sort.bucketSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[6]);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        //Radix Sort
        Sort.radixSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Heap Sort", Sort.iterations[7]);
        Sort.radixSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[7]);
        Sort.radixSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations[7]);
    }
}

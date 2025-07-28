import java.util.ArrayList;
/**
 * This is the testing class for the Priority Queue class where we find if the heap is balanced or not
 * @author Mahir Ashab Enan
 * @version 01
 */
public class Test{
    final static int Size = 1000000;
    /**
     * Main method where we add remove and sort values into the heap
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i < Size; i++){
            al.add((int)(Math.random() * Size));
        }
        //add the data to the heap
        int total = 0;
        for(int i = 0; i < al.size(); i++){
            pq.offer(al.get(i));
            total += PriorityQueue.offerIter;
            if(i % (Size/20) == 0){
                System.out.printf("%-10d\t%-10d\n", al.get(i), PriorityQueue.offerIter);
            } 
        }
        System.out.printf("%-10s\t%-10d\n", "Average", total/al.size());

        System.out.println("Height of the heap: " + pq.height());
        System.out.println("Is the heap balanced? " + pq.isBalanced());

        //remove the data
        int i = 0;
        total = 0;
        while(!pq.isEmpty()){
            int number = pq.poll();
            total += PriorityQueue.pollIter;
            if(i % (Size/20) == 0){
                System.out.printf("%-10d\t%-10d\n", number, PriorityQueue.pollIter);
            }
            i++;
        }
        System.out.printf("%-10s\t%-10d\n", "Average", total/al.size());
        System.out.println();
        System.out.println("The Sorted Poll Methods");

        //sort the arraylist of random int and repeat steps c to f
        al.sort(null);
        //add the data to the heap
        total = 0;
        for(i = 0; i < al.size(); i++){
            pq.offer(al.get(i));
            total += PriorityQueue.offerIter;
            if(i % (Size/20) == 0){
                System.out.printf("%-10d\t%-10d\n", al.get(i), PriorityQueue.offerIter);
            } 
        }
        System.out.printf("%-10s\t%-10d\n", "Average", total/al.size());

        System.out.println("Height of the heap: " + pq.height());
        System.out.println("Is the heap balanced? " + pq.isBalanced());

        //remove the data
        i = 0;
        total = 0;
        while(!pq.isEmpty()){
            int number = pq.poll();
            total += PriorityQueue.pollIter;
            if(i % (Size/20) == 0){
                System.out.printf("%-10d\t%-10d\n", number, PriorityQueue.pollIter);
            }
            i++;
        }
        System.out.printf("%-10s\t%-10d\n", "Average", total/al.size());
    }
/*
6. Offer vs. Poll Performance
offer() handles single-digit values efficiently (O(log n) time) because it only performs bubble-up operations without full heap scans.
poll() deals with larger values (15-20 range) requiring more work (O(log n)) due to bubble-down operations after root removal.
Sorted data shows O(1) poll() performance because elements are already in heap-order property, minimizing reorganization.

7. Height & Balance Analysis
Height Calculation: The heap's height (≈20) derives from the formula log₂(1,000,000) ≈ 20, reflecting the tree depth needed to store a million elements. The algorithm recursively measures subtree depths and adds 1 at each level.
Balance Check: isBalanced() returns true because binary heaps maintain structural balance by nature - the difference between left/right subtree heights never exceeds 1 at any node, satisfying AVL-like balance conditions.
 */
}

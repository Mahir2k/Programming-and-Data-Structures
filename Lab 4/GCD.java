/**
 * This class provides different implementations of the Greatest Common Divisor (GCD) algorithm.
 * It also includes methods to compare the execution time and number of iterations for each implementation.
 */
public class GCD {
    /** Counter for iterations in gcd_1 method */
    public static int gcd1Iter;
    /** Counter for iterations in gcd_2 method */
    public static int gcd2Iter;
    /** Counter for iterations in gcd_3 method */
    public static int gcd3Iter;
    /** Counter for iterations in gcd_4 method */
    public static int gcd4Iter;

    /**
     * Calculates GCD using a simple iterative approach.
     * @param m First integer
     * @param n Second integer
     * @return The greatest common divisor of m and n
     */
    public static int gcd_1(int m, int n) {
        gcd1Iter = 0;
        int divisor = 1;
        for (int i = 2; i < m && i < n; i++) {
            gcd1Iter++;
            if (m % i == 0 && n % i == 0)
                divisor = i;
        }
        return divisor;
    }

    /**
     * Calculates GCD using a reverse iterative approach.
     * @param m First integer
     * @param n Second integer
     * @return The greatest common divisor of m and n
     */
    public static int gcd_2(int m, int n) {
        gcd2Iter = 0;
        int divisor = 1;
        for (int i = n; i >= 1; i--) {
            gcd2Iter++;
            if (m % i == 0 && n % i == 0) {
                divisor = i;
                break;
            }
        }
        return divisor;
    }

    /**
     * Calculates GCD using an optimized iterative approach.
     * @param m First integer
     * @param n Second integer
     * @return The greatest common divisor of m and n
     */
    public static int gcd_3(int m, int n) {
        gcd3Iter = 0;
        int divisor = 1;
        if (m % n == 0)
            return n;
        for (int i = n / 2; i >= 1; i--) {
            gcd3Iter++;
            if (m % i == 0 && n % i == 0) {
                divisor = i;
                break;
            }
        }
        return divisor;
    }

    /**
     * Calculates GCD using a recursive approach (Euclidean algorithm).
     * @param m First integer
     * @param n Second integer
     * @return The greatest common divisor of m and n
     */
    public static int gcd_4(int m, int n) {
        gcd4Iter++; // counting the number of recursive calls
        if (m % n == 0)
            return n;
        else
            return gcd_4(n, m % n);
    }

    /**
     * Main method to test and compare different GCD implementations.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test the execution time
        System.out.println("Comparing the execution times");
        for (int i = 0; i < 20; i++) {
            int m = (int) (Math.random() * 100000);
            int n = (int) (Math.random() * 100000);
            int smallest = 0, largest = 0;
            if (n < m) {
                smallest = n;
                largest = m;
            } else {
                smallest = m;
                largest = n;
            }
            long start = System.nanoTime();
            gcd_1(smallest, largest);
            long end = System.nanoTime();
            long gcd1Time = end - start;

            start = System.nanoTime();
            gcd_2(smallest, largest);
            end = System.nanoTime();
            long gcd2Time = end - start;

            start = System.nanoTime();
            gcd_3(smallest, largest);
            end = System.nanoTime();
            long gcd3Time = end - start;

            start = System.nanoTime();
            gcd_4(smallest, largest);
            end = System.nanoTime();
            long gcd4Time = end - start;
            System.out.printf("%-10d\t%-10d\t%-10d\t%-10d\t%-10d\t%-10d\n",
                    smallest, largest, gcd1Time, gcd2Time, gcd3Time, gcd4Time);
        }

        System.out.println("Comparing the number of iterations");
        // Testing the number of iterations
        for (int i = 0; i < 20; i++) {
            int m = (int) (Math.random() * 100000);
            int n = (int) (Math.random() * 100000);
            int smallest = 0, largest = 0;
            if (n < m) {
                smallest = n;
                largest = m;
            } else {
                smallest = m;
                largest = n;
            }
            gcd_1(smallest, largest);
            gcd_2(smallest, largest);
            gcd_3(smallest, largest);
            gcd4Iter = 0;
            gcd_4(smallest, largest);
            System.out.printf("%-10d\t%-10d\t%-10d\t%-10d\t%-10d\t%-10d\n",
                    smallest, largest, gcd1Iter, gcd2Iter, gcd3Iter, gcd4Iter);
        }
    }
}

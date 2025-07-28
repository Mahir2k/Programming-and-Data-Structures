import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Class to implement the external sorting of a large file of doublee
 */
public class ExternalSort{
	/**
	 * Method to implement the split function of merge sort
	 * @param originalFile the file to be splitted in two partially sorted files
	 * @param File1 the first half file
	 * @param File2 the second half file
	 * @return true if File2 is empty, false otherwise
	 */
    private static boolean split(String originalFile, String File1, String File2) {
        try (Scanner in = new Scanner(new File(originalFile));
             PrintWriter out1 = new PrintWriter(File1);
             PrintWriter out2 = new PrintWriter(File2))
        {
            PrintWriter currentOut = out1;
            if (!in.hasNextDouble()) return true;
            double prev = in.nextDouble();
            out1.println(prev);

            while (in.hasNextDouble()) {
                double curr = in.nextDouble();
                // if still in ascending order, stay on same file
                if (curr >= prev) {
                    currentOut.println(curr);
                } else {
                    if(currentOut == out1){
                        currentOut = out2;
                    } else{
                        currentOut = out1;
                    }
                    currentOut.println(curr);
                }
                prev = curr;
            }

            // if file2 never got anything, we’re done
        } catch (FileNotFoundException e) {
            throw new RuntimeException("error in split", e);
        }
        File f = new File(File2);
        //System.out.println(f.length());
        return f.length() == 0L; 
    }
	/**
	 * Method to merge two partially sorted files into one file
	 * @param File1 the first half to be merged
	 * @param File2 the second half to be merged
	 * @param mergedFile the resulting file of the merging
	 */
    private static void merge(String File1, String File2, String mergedFile) {
        try (Scanner in1 = new Scanner(new File(File1));
             Scanner in2 = new Scanner(new File(File2));
             PrintWriter out = new PrintWriter(mergedFile))
        {
            Double d1 = in1.nextDouble();
            Double d2 = in2.nextDouble();

            // merge
            while (in1.hasNextDouble() && in2.hasNextDouble()) {
                if (d1 <= d2) {
                    out.println(d1);
                    d1 = in1.nextDouble();
                } else {
                    out.println(d2);
                    d2 = in2.nextDouble();
                }
            }
            if(d1<=d2){
                out.println(d1);
                out.println(d2);
            }else{
                out.println(d2);
                out.println(d1);
            }
            // finish
            while (in1.hasNextDouble()) {
                out.println(in1.nextDouble());
            }
            while (in2.hasNextDouble()) {
                out.println(in2.nextDouble());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("error in merge", e);
        }
    }
	/**
	 * Recursive Merge Sort algorithm to sort a file externally
	 * @param originalFile the text file to be sorted
	 */
    public static void mergeSort(String oringalFile) {
        String f1 = "tempFile1";
        String f2 = "tempFile2";
        // keep splitting until the second file is empty
        while (!split(oringalFile, f1, f2)) {
            merge(f1, f2, oringalFile);
        }
        // clean up
        new File(f1).delete();
        new File(f2).delete();
    }
}	
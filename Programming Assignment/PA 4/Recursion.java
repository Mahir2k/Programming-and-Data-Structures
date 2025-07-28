import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Recursion {
    /**
     * Method size to return the size of a file or recursively determine the size of a folder
     * @param file the file or directory object
     * @return the size of the file or folder in bytes
     */
    public static long size(File fileObject){
        //return 0;
        if (!fileObject.exists()){
            return -1;
        }
        if (fileObject.isFile()){
            return fileObject.length();
        }
        else if (fileObject.isDirectory()){
            File[] sub = fileObject.listFiles();
            long total = 0; //how do I find the sum of the sizes of the subfolders
            //trying for loop
            for(int i=0; i<sub.length; i++){
                total += size(sub[i]);
            }
            return total;
        }
        return 0;
    }
    /**
     * listContents prints the list of files/folders in a given folder file and their sizes
     * If file is a file it prints the name of the file and its size
     * @param file the file or directory object
     */
    public static void listContents(File fileObject){
        if(!fileObject.exists()){
            //System.out.println("File not found" + fileObject.getAbsolutePath());
        }

        if(fileObject.isFile()){
            long s = size(fileObject);
            System.out.println(fileObject.getName() + formatSize(s));
        }
        else if(fileObject.isDirectory()){
            File[] items = fileObject.listFiles();
            if(items == null){
                System.out.println("Empty Directory" + fileObject.getAbsolutePath());
            }
            for(File f : items){
                long s = size(f);
                System.out.println(f.getName() + "\t" + formatSize(s));
            }
        }
    }
    /**
     * 
     * @param file the file or directory object
     * @param filename the name of the file the method searches for
     * @return true if the file was found anywhere in the hierarchy, false if the file was not found
     * prints the absolute path of all the locations where filename was found
     */
    public static boolean find(File fileObject, String filename){
        //return false;
        if (!fileObject.exists()){
            return false;
        }
        if (fileObject.isFile()){
            if(fileObject.getName().equals(filename)){
                System.out.println(filename + " found in " + fileObject.getAbsolutePath());
                return true;
            }
            else{
                return false;
            }
        }
        if (fileObject.isDirectory()){ //how to deal with hierarchy??
            File[] sub2 = fileObject.listFiles();
            if(sub2 == null){
                return false;
            }
            boolean found = false;
            for(int i=0; i<sub2.length; i++){
                boolean foundfolder = find(sub2[i], filename);
                if(foundfolder){
                    found = true;
                }
            }
            return found;
        }
        return false;
    }
    /**
     * deletes all the empty files in the hierarchy of folders
     * @param file the file or directory object
     * prints the list of empty files deleted
     */
    public static void delete(File fileObject){
        if(!fileObject.exists()){
            return;
        }
        if (fileObject.isDirectory()){
            File[] sub5 = fileObject.listFiles();
            if (sub5 != null) {
                for (File m2subs : sub5) {
                    delete(m2subs);
                }
            }
        }
        else if(fileObject.isFile()){
            if(fileObject.length() == 0){
                boolean done = fileObject.delete();
                if(done){
                    System.out.println("File \"" + fileObject.getAbsolutePath() + "\" removed successfully");
                }
            }
        }  
    }
    /**
     * Searches for the given word in all the files in the hierarchy of files/folder under path
     * @param file the file or directory object
     * @param word the word being looked up
     * prints the files where the word was found and 
     * the number of occurences of the word in each file
     */
    public static void findWord(File fileObject, String word){
        if(!fileObject.exists()){
            return;
        }
        if(fileObject.isFile()){
            int count = 0;
            try(Scanner sc = new Scanner(fileObject)){
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    int index = line.indexOf(word); // search for the word in the line
                    while(index != -1){
                        count++; // count one occurrence
                        index = line.indexOf(word, index+1); // search for word again. This time starting from index+1 to catch all the occurrences of the word in the line
                    }
                }
                /*
                if (sub3 != null) {
                    for (File file : sub3) {
                    System.out.println(file.getName());
                    }
                */
            } catch (FileNotFoundException e) {
                System.out.println("error!");
            }
            if (count > 0) {
                System.out.println(word + " found " + count + " times in " + fileObject.getAbsolutePath());
            }
        }
        if (fileObject.isDirectory()) {
            File[] sub4 = fileObject.listFiles();
            if (sub4 != null) {
                for (File msubs : sub4) {
                    findWord(msubs, word);
                }
            }
        }
    }
    /**
     * formats the size of a file using the appropriate unit (Bytes, KB, MB, GB)
     * @param size the size in bytes
     * @return a string with the size converted to the appropriate unit
     */
    
    public static String formatSize(long size){
        //return "";
        if (size < 1024) {
            return size + " bytes";
        } else if (size < 1024*1024) {
            double kb = (double) size / 1024;
            return String.format("%.2f KB", kb);
        } else if (size < 1024*1024*1024) {
            double mb = (double) size / (1024*1024);
            return String.format("%.2f MB", mb);
        } else {
            double gb = (double) size / (1024*1024*1024);
            return String.format("%.2f GB", gb);
        }
    }
    public static void main(String[] args){
        String path = "test_tree";
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File or Directory " + file.getAbsolutePath() + " not found");
            System.exit(0);
        }

        System.out.println("Test case 1: looking for the word \"Hello\" in all the files under the folder test_tree\\");
        findWord(file, "Hello");

        System.out.println("\nTest case 2: looking for the word \"Test\" in all the files under the folder test_tree\\");
        findWord(file, "Test");


        System.out.println("\nTest case 3: looking for the file \"Test.java\" in the hierarchy under the folder test_tree\\");
        if(!find(file, "Test.java")){
           System.out.println("File \"Test.java\" not found"); 
        }

        System.out.println("\nTest case 4: looking for the file \"dummy.txt\"  in the hierarchy under the folder test_tree\\");
        if(!find(file, "dummy.java")){
            System.out.println("File \"dummy.txt\" not found");
        }

        System.out.println("\nTest case 5: listing the contents of the folder " + file.getAbsolutePath());
        listContents(file);

        System.out.println("\nTest case 6: listing the contents of the file/folder \"dummy\\\"");
        listContents(new File("dummy"));

        System.out.println("\nTest case 7: Deleting all the empty files in the folder " + file.getAbsolutePath());
        delete(file);

    }
}
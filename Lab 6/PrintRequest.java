
/**
 * Class representing a print request.
 * Implements Comparable to prioritize print jobs.
 * @author: Mahir Ashab Enan
 * @version: 1.0
 */
class PrintRequest implements Comparable<PrintRequest> {
    // Data members
    private int userID;
    private String userGroup;
    private long fileSize;
    
    /**
     * Constructor with parameters.
     * @param userID ID of the user making the print request
     * @param userGroup Group of the user
     * @param fileSize Size of the print job in bytes
     */
    public PrintRequest(int userID, String userGroup, long fileSize) {
        this.userID = userID;
        this.userGroup = userGroup;
        this.fileSize = fileSize;
    }
    
    /**
     * Accessor for user ID.
     * @return user ID
     */
    public int getUserID() {
        return userID;
    }
    
    /**
     * Accessor for user group.
     * @return user group
     */
    public String getUserGroup() {
        return userGroup;
    }
    
    /**
     * Accessor for file size.
     * @return file size in bytes
     */
    public long getSize() {
        return fileSize;
    }
    
    /**
     * Compares two print requests based on priority.
     * @param other PrintRequest object to compare
     * @return comparison result
     */
    public int compareTo(PrintRequest other) {
        return Integer.compare(getPriorityIndex(), other.getPriorityIndex());
    }
    
    /**
     * Returns the priority index of the user group.
     * @return priority index
     */
    private int getPriorityIndex() {
        String[] groups = {"root", "admin", "user", "batch"};
        for (int i = 0; i < groups.length; i++) {
            if (this.userGroup.equals(groups[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns a string representation of the print request.
     * @return formatted print request string
     */
    public String toString() {
        return String.format("%-11d\t%-11s\t%-10s", userID, userGroup, formatSize(fileSize));
    }
    
    /**
     * Formats file size into human-readable format.
     * @param size file size in bytes
     * @return formatted file size
     */
    private static String formatSize(long size) {
        if (size < 1000) return size + " Bytes";
        else if (size < 1000000) return String.format("%.1f KB", size / 1000.0);
        else if (size < 1000000000) return String.format("%.1f MB", size / 1000000.0);
        else return String.format("%.1f GB", size / 1000000000.0);
    }
}

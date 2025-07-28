import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Representing an airplane seat map and providing methods for seat reservation management
 */
public class Airplane {
    private char[][] seatMap;

    /**
     * Constructing an empty seat map with all seats initially available
     */
    public Airplane() {
        seatMap = new char[9][8];
        for(int i=0; i<seatMap.length; i++) {
            for(int j=0; j<seatMap[i].length; j++) {
                seatMap[i][j] = '.';
            }
        }
    }

    /**
     * Constructing a seat map from a file
     * @param filename Name of the file containing seat map data
     */
    public Airplane(String filename) {
        this();
        readMap(filename);
    }

    /**
     * Reading seat map data from a file
     * @param filename Name of the file to read from
     */
    private void readMap(String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            for(int i=0; i<seatMap.length; i++) {
                for(int j=0; j<seatMap[i].length; j++) {
                    seatMap[i][j] = read.next().charAt(0);
                }
            }
            read.close();
        }
        catch(FileNotFoundException e) {
            // Empty catch block as per original code
        }
    }

    /**
     * Validating seat number format
     * @param seat Seat number to validate
     * @return true if seat number is valid
     * @throws InvalidSeatException If seat number format is invalid
     */
    private boolean checkSeatNumber(String seat) throws InvalidSeatException {
        if(!seat.matches("[1-9][A-H]"))
            throw new InvalidSeatException("Invalid seat number: " + seat + ". Must be [1-9][A-H]");
        return true;
    }

    /**
     * Reserving a seat if available
     * @param seat Seat number to reserve
     * @return true if reservation was successful, false if seat already occupied
     * @throws InvalidSeatException If seat number is invalid
     */
    public boolean reserveSeat(String seat) throws InvalidSeatException {
        checkSeatNumber(seat);
        int row = seat.charAt(0) - '1';
        int col = seat.charAt(1) - 'A';
        if(seatMap[row][col] == '.') {
            seatMap[row][col] = 'X';
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Freeing a reserved seat
     * @param seat Seat number to free
     * @return true if seat was successfully freed, false if seat was already available
     * @throws InvalidSeatException If seat number is invalid
     */
    public boolean freeSeat(String seat) throws InvalidSeatException {
        checkSeatNumber(seat);
        int row = seat.charAt(0) - '1';
        int col = seat.charAt(1) - 'A';
        if(seatMap[row][col] == 'X') {
            seatMap[row][col] = '.';
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Saving current seat map to a file
     * @param filename Name of the file to save to
     */
    public void saveMap(String filename) {
        try {
            PrintWriter write = new PrintWriter(new File(filename));
            for (int i=0; i<seatMap.length; i++) {
                for(int j=0; j<seatMap[i].length; j++) {
                    write.print(seatMap[i][j] + " ");
                }
            }
            write.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Cannot write to the file" + filename);
        }
    }

    /**
     * Generating string representation of seat map
     * @return Formatted seat map with row and column headers
     */
    public String toString() {
        String s = "\tA\tB\tC\tD\tE\tF\tG\tH\n";
        for(int i=0; i<seatMap.length; i++) {
            s += (i+1) + "\t";
            for(int j=0; j<seatMap[i].length; j++) {
                s += seatMap[i][j] + "\t";
            }
            s += "\n";
        }
        return s;
    }
}

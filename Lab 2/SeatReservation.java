import java.util.Scanner;

/**
 * Main class for seat reservation system with interactive menu
 */
public class SeatReservation {
    /**
     * Main method to run seat reservation system
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Airplane airplane = new Airplane("seatmap.txt");
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                System.out.println(airplane.toString());
                System.out.println("Select an operation:\n1:Reserve a seat\n2:Free a seat\n3:Quit");
                choice = keyboard.nextInt();
                switch(choice) {
                    case 1:
                        System.out.println("Enter a seat number:");
                        String seat = keyboard.next();
                        if(airplane.reserveSeat(seat)) {
                            System.out.println("Seat reserved successfully");
                        }
                        else {
                            System.out.println("Seat already reserved");
                        }
                        break;
                    case 2:
                        System.out.println("Enter a seat number:");
                        seat = keyboard.next();
                        if(airplane.freeSeat(seat)) {
                            System.out.println("Seat freed successfully");
                        }
                        else {
                            System.out.println("Seat already available");
                        }
                        break;
                    case 3:
                        airplane.saveMap("seatmap.txt");
                        break;
                }
            }
            catch(InvalidSeatException e) {
                System.out.println(e.getMessage());
            }
            
        } while(choice != 3);
    }
}

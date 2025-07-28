/**
 * Program to evaluate postfix expressions and process print requests
 * using a priority queue.
 * @author: Mahir Ashab Enan
 * @version: 1.0
 */
import java.util.Stack;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        // Print heading for the postfix expressions
        System.out.println("Evaluating postfix expressions");
        System.out.println("-------------------------------------------------------------");

        // Stack to evaluate postfix expressions
        Stack<Integer> expressionStack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        boolean continueEvaluation = true;

        // Loop until user chooses not to evaluate another postfix expression
        while (continueEvaluation) {
            System.out.println("Enter a postfix expression:");
            String expression = scanner.nextLine();
            expressionStack.clear();
            String[] tokens = expression.split(" ");

            try {
                for (String token : tokens) {
                    if (token.matches("\\d{1,}")) {
                        int operand = Integer.parseInt(token);
                        expressionStack.push(operand);
                    } else {
                        int operand1 = expressionStack.pop();
                        int operand2 = expressionStack.pop();
                        switch (token) {
                            case "+":
                                expressionStack.push(operand2 + operand1);
                                break;
                            case "-":
                                expressionStack.push(operand2 - operand1);
                                break;
                            case "*":
                                expressionStack.push(operand2 * operand1);
                                break;
                            case "/":
                                expressionStack.push(operand2 / operand1);
                                break;
                            default:
                                System.out.println("Invalid operation");
                        }
                    }
                }
                int result = expressionStack.pop();
                if (expressionStack.isEmpty()) {
                    System.out.println("Result = " + result);
                } else {
                    System.out.println("Malformed expression");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Malformed expression");
            }

            System.out.println("Do you want to evaluate another postfix expression? (yes/no):");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                continueEvaluation = false;
            }
        }

        // Print heading for the summary of printed requests
        System.out.println("\nSummary of the printed requests:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-11s\t%-11s\t%-10s\t%s\n", "User ID", "Group", "Size", "Completion Time");

        // Process print requests using a priority queue
        PriorityQueue<PrintRequest> printQueue = new PriorityQueue<>();
        try {
            Scanner fileScanner = new Scanner(new File("requests.txt"));
            while (fileScanner.hasNext()) {
                int userID = fileScanner.nextInt();
                String userGroup = fileScanner.next();
                long fileSize = fileScanner.nextLong();
                PrintRequest request = new PrintRequest(userID, userGroup, fileSize);
                printQueue.offer(request);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        long printSpeed = 10000; // bytes/sec
        long totalPrintTime = 0;
        while (!printQueue.isEmpty()) {
            PrintRequest request = printQueue.poll();
            long timeToPrint = request.getSize() / printSpeed;
            totalPrintTime += timeToPrint;
            System.out.printf("%s\t%s\n", request, formatTime(timeToPrint));
            System.out.println();
        }

        System.out.println("Total Printing Time: " + formatTime(totalPrintTime));
    }

    public static String formatTime(long time) {
        long days = time / 86400;
        long hours = (time % 86400) / 3600;
        long minutes = (time % 3600) / 60;
        long seconds = time % 60;
        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }
}

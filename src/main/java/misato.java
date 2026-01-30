import java.util.Scanner;



public class misato {

    public static void main(String[] args) {
        //String END_LINE = "____________________________________________________________";
        String name = "Misato Katsuragi";
        printLine();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        printLine();

        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        //Echo loop
        while (true) {
            userInput = scanner.nextLine();  //user will type something

            if (userInput.equalsIgnoreCase("bye bye")){

                break;  //exit loop when user types "bye bye"
            }

            //echo the command back
            echoCommand(userInput);


        }

        printLine();
        System.out.println("Bye! See you again!");
        printLine();

        scanner.close();
    }

    public static void echoCommand(String command) {
        printLine();
        System.out.println(command);
        printLine();
    }

    public static void printLine() {
        String END_LINE = "____________________________________________________________";
        System.out.println(END_LINE);
    }
}

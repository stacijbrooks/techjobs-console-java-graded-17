import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Main loop to allow the user to interact with the program
        while (true) {
            // Prompt the user to choose an action: search or list
            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            // Exit if the user chooses to quit
            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                // If the user chooses to list, prompt for the column choice
                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    // If the user chooses to list all, print all jobs
                    printJobs(JobData.findAll());
                } else {
                    // If the user chooses a specific column, print values for that column
                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of values for the chosen column
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // User chose to search

                // Prompt the user to choose a search field
                String searchField = getUserSelection("Search by:", columnChoices);

                // Prompt the user for the search term
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    // If the user chooses to search all, print jobs matching the search term
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    // If the user chooses a specific column to search, print matching jobs
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // Returns the key of the selected item from the choices dictionary
    public static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        // Loop until the user provides a valid choice
        do {
            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            // Get user input
            //This line checks if the input from the user is an integer (a whole number).
            // If the input is an integer, it means the user made a choice from a menu of options.
            if (in.hasNextInt()) {
                // Reads the user's integer and stores it in the variable choice index.
                choiceIdx = in.nextInt();
                //Reads and discards any extra input.
                in.nextLine();
            } else {
                //if user input is not an integer, it stores the input in a string called "line."
                String userInput = in.nextLine();
                //If user input equals "x", then user quits.
                if (userInput.equals("x")) {
                    return null; // User chose to quit
                }
            }
                /*boolean shouldQuit = line.equals("x");
                if (shouldQuit) {*/


            // Validate user's input
            //If the index is less than 0 or greater than or equal to the length of the available choices,
            // it prints an error message
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

            //while loop continues until valid choice = true.
        } while (!validChoice);
        // Return the key corresponding to the user's choice
        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.isEmpty()) {
            System.out.print("No Results");
        } else {
            for (HashMap<String, String> job : someJobs) {
                String jobTitle = job.get("name");
                String employer = job.get("employer");
                String location = job.get("location");
                String positionType = job.get("position type");
                String coreCompetency = job.get("core competency");

                System.out.println("\n*****");
                System.out.println("position type: " + positionType);
                System.out.println("name: " + jobTitle);
                System.out.println("employer: " + employer);
                System.out.println("location: " + location);
                System.out.println("core competency: " + coreCompetency);
                System.out.println("*****");
            }

        }

    }
}


//            someJobs.forEach(job -> {
//                System.out.println("\n*****");
//                job.forEach((key, value) -> System.out.printf("%s: %s\n", key, value));
//                System.out.println("*****");
//            });
//        }
//    }
//}
//            // Iterate over each job and print all its details
//            for (HashMap<String, String> job : someJobs) {
//                System.out.println("*****");
//
//                // Print each job and its details
//                for (String key : job.keySet()) {
//                    // Print each key-value pair of the job
//                    System.out.println(key + ": " + job.get(key));
//                }
//            }

//            System.out.println("*****");
//        }


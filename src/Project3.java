import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
* COP 3530: Project 3 – Linked lists
* <p>Project3 begins by prompting the user for a file name (States3.csv)
* it takes the fileName and uses readStates(fileName) to parse the file into an array of states object (statesArray[])<p>
* <p>Using this array, it inserts States that full under FAIR, GOOD, and VGOOD into a Stack, and prints the Stack<p>
* <p>After creating a new PriorityQ object, it pops all States in the Stack and inserts them into the PriorityQ, then prints the PriorityQ<p>
* <p>Finally it repeatedly prompts the user for a choice between choices 1-3<p>
* <p>1. Enter a DR interval for deletions: Prompts user for an interval of death rates to delete from the PriorityQ ex: [x,y]<p>
* <p>2. Print priority queue: reprints the PriorityQ<p>
* <p>3. Exit: Closes the program<p>
*
* @author Ricardo Carrasco
* @version 3/7/2021
*/
public class Project3 {
	/**
	* This method takes in a fileName and parses that file into an Array of state objects
	* @param String fileName
	* @return stateArray[]
	*/
	private static State[] readStates(String fileName) {
		Scanner fscnr;
		String templn;
		int throwawayNum;
		int numRecords = 0;
		State[] stateArray = new State[50];
		try {
			fscnr = new Scanner(new File(fileName));
			templn = fscnr.nextLine();
			
			while(fscnr.hasNextLine()){
				templn = fscnr.nextLine();
				Scanner Sscnr = new Scanner(templn).useDelimiter(",");
				stateArray[numRecords] = new State();
				stateArray[numRecords].setName(Sscnr.next());
				stateArray[numRecords].setCapital(Sscnr.next());
				stateArray[numRecords].setRegion(Sscnr.next());
				throwawayNum = Sscnr.nextInt();
				stateArray[numRecords].setPopulation(Sscnr.nextDouble());
				stateArray[numRecords].setNumCases(Sscnr.nextDouble());
				stateArray[numRecords].setNumDeaths(Sscnr.nextDouble());
				stateArray[numRecords].setMedianHouseholdIncome(Sscnr.nextDouble());
				stateArray[numRecords].setCrimeRate(Sscnr.nextDouble());
				numRecords++;
			}
			System.out.println("\nThere were " + numRecords + " records read.");
			fscnr.close();	
		}
		catch(IOException e){
			System.out.println("Could not find file");
		}
		return stateArray;
	}
	/**
	* The main method that uses all other methods and classes in Project 3
	*/
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Scanner drinterval = new Scanner(System.in);
		String fileName = new String();
		State[] statesArray;
		PriorityQ statesPQ = new PriorityQ();
		Stack statesS = new Stack();
		boolean exit = false;
		boolean correctInput = false;
		int tempNum = -1;
		int userChoice;
		int a;
		int b;
		String interval;
		
		System.out.println("COP3530 Project 3");
		System.out.println("Instructor: Xudong Liu\n");
		System.out.println("Linked Lists");
		System.out.print("Enter the file name: ");
		fileName = scnr.next();
		statesArray = readStates(fileName);
			
		for(int i = 0; i < 50; i++) {
			if(statesArray[i].deathRate() < 150) {
				statesS.push(statesArray[i]);
			}
		}
		System.out.println("\nStack contents:");
		statesS.printStack();
		
		
		while((statesS.isEmpty()) != true) {
			statesPQ.insert(statesS.pop());
		}
		System.out.println("\nPriority Queue contents:");
		statesPQ.printPriorityQ();
		
		while(exit == false) {
			System.out.println("\n\n1. Enter a DR interval for deletions");
			System.out.println("2. Print priority queue");
			System.out.println("3. Exit");
			do {
				tempNum++;
				if(tempNum == 0){
					System.out.print("Enter your choice: ");
				}
				else if(tempNum > 0) {
					System.out.print("Invalid choice enter 1-6:");
				}
				while (!scnr.hasNextInt()) {
			        System.out.print("Invalid choice enter 1-6:");
			        scnr.next();
			    }
			    userChoice = scnr.nextInt();
			} while(userChoice < 1 || userChoice > 3);	
			tempNum = -1;
			switch(userChoice) {
				case 1:
					System.out.print("Enter DR interval like [x,y]: ");
					correctInput = false;					
					while(correctInput == false) {
						interval = drinterval.nextLine();
						if(interval.matches("\\[.+\\,.+\\]")) {
							interval = interval.replaceAll("\\[", "");
							interval = interval.replaceAll("\\]", "");
							Scanner intervalscnr = new Scanner(interval).useDelimiter(",");
							if(intervalscnr.hasNextInt()) {
								a = intervalscnr.nextInt();
								if(intervalscnr.hasNextInt()) {
									b = intervalscnr.nextInt();
									if(a < b) {
										if(statesPQ.intervalDelete(a, b)) {
											System.out.printf("\nStates of priority queue with DRs in [%d,%d] are deleted", a, b);
										}
										else {
											System.out.print("\nInterval not found, nothing has been deleted");
										}
										correctInput = true;
									}
									else {
										System.out.print("Invalid interval, first number must be no bigger than the second: ");
									}
								}
								else {
									System.out.print("Invalid interval, enter numbers: ");
								}
							}
							else {
								System.out.print("Invalid interval, enter numbers: ");
							}
						}
						else {
							System.out.print("Incorrect Format, try [X,Y]: ");
						}
					}
					break;
				case 2:
					statesPQ.printPriorityQ();
					break;
				case 3:
					System.out.println("\nHave a good day!");
					exit = true;
					break;
				
			}
		}
	}
}

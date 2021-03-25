import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//change the class name
public class Partners {

	String[] airlineList;
	int[][] matrix;
	boolean found;

	public Partners() {
		found = false;
	}

	public void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int getNumFromAirline(String airline) {
		for (int i = 0; i < airlineList.length; i++) {
			if (airline.equals(airlineList[i])) {
				return i;
			}
		}
		return 0;
	}

	public void run() throws Exception {

		Scanner file = new Scanner(new File("partners.dat"));

		int numberOfAirlines = file.nextInt();
		matrix = new int[numberOfAirlines][numberOfAirlines];

		airlineList = new String[numberOfAirlines];
		for (int i = 0; i < numberOfAirlines; i++) {
			String name = file.next();
			airlineList[i] = name;
		}

		// Add partners to each airline
		numberOfAirlines = file.nextInt();
		file.nextLine();

		for (int i = 0; i < numberOfAirlines; i++) {
			String[] partners = file.nextLine().split(" ");
			int a = getNumFromAirline(partners[0]);
			int b = getNumFromAirline(partners[1]);
			matrix[a][b] = 1;
			matrix[b][a] = 1;
		}

		printMatrix();

		numberOfAirlines = file.nextInt();
		file.nextLine();

		System.out.println(Arrays.toString(airlineList));

		// Is it connected?
		for (int i = 0; i < numberOfAirlines; i++) {
			String[] partners = file.nextLine().split(" ");
			int a = getNumFromAirline(partners[0]);
			int b = getNumFromAirline(partners[1]);
			if (matrix[a][b] == 1) {
				System.out.println(true);
			} else
				System.out.println(recur(a, b, new ArrayList<Integer>()));
		}
	}

	public boolean recur(int current, int target, ArrayList<Integer> doneAirlines) {
		if (matrix[current][target] == 1) {
			return true;
		}
		if (doneAirlines.contains(current)) {
			return false;
		}
		doneAirlines.add(current);
		for (int i : matrix[current]) {
			if (recur(i, target, doneAirlines)) {
				return true;
			}
		}
		return false;

	}

	public static void main(String[] args) throws Exception {
		// change this to whatever your class name is

		Partners p = new Partners();
		p.run();
	}

}

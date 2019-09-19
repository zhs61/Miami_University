import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class hw1 {

	public static Scanner fin = null;

	public static void main(String[] args) {



		fin = new Scanner(System.in);
		System.out.print("Enter the name of the file: ");
		String fileName = fin.nextLine();

		System.out.println();// blank line
		System.out.println("p7.4");
		readData(fileName);

		System.out.println();// blank line
		System.out.println("p7.5");
		getChar(fileName);
		getWords(fileName);
		getLines(fileName);

		System.out.println();// blank line

		System.out.println("p7.6");
		find();

		System.out.println();
		System.out.println("p7.13");
		getSum();
	}
	
	//===================================================== P7.4
	// read Data and get the average of each column. P 7.4
	public static void readData(String fileName) {
		double count = 0, sum1 = 0, sum2 = 0, num1 = 0, num2 = 0, avg1 = 0, avg2 = 0;
		String line = "";
		String[] numLine = new String[2];
		try {
			fin = new Scanner(new File(fileName));
			while (fin.hasNextLine()) {
				count++;
				line = fin.nextLine();
				numLine = line.split("\\s+");
				num1 = Double.parseDouble(numLine[0]);
				num2 = Double.parseDouble(numLine[1]);
				sum1 += num1;
				sum2 += num2;
			}
			avg1 = sum1 / count;
			avg2 = sum2 / count;
			System.out.printf("The average of first column is: %.3f\n", avg1);
			System.out.printf("The average of second column is: %.3f\n", avg2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (fin != null)
				fin.close();

		}
	}

	//================================================== P7.5
	// get the num of lines p7.5
	public static void getLines(String fileName) {
		int countLines = 0, countword = 0, countChar = 0;

		try {
			fin = new Scanner(new File(fileName));

			while (fin.hasNextLine()) {
				String line = fin.nextLine();
				countLines++;
			}
			System.out.println("Lines: " + countLines);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (fin != null)
				fin.close();
		}

	}

	// get the number of words p7.5
	public static void getWords(String fileName) {
		int countWord = 0;

		try {
			fin = new Scanner(new File(fileName));

			while (fin.hasNext()) {
				String word = fin.next();
				countWord++;
			}
			System.out.println("Word: " + countWord);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (fin != null)
				fin.close();
		}

	}

	// get the num of the characters p7.5
	public static void getChar(String fileName) {
		int countChar = 0;

		try {
			fin = new Scanner(new File(fileName));

			while (fin.hasNext()) {
				String word = fin.next();
				countChar = word.length();
			}
			System.out.println("Characters: " + countChar);
		} catch (Exception e) {

		} finally {
			if (fin != null)
				fin.close();
		}

	}

	//======================================================== P7.6
	// Write a program Find that searches all files specified on the command
	// line and prints out
	// all lines containing a specified word. For example,
	public static void find() {
		System.out.print("Enter the word: ");
		ArrayList<String> a1 = new ArrayList<String>();
		fin = new Scanner(System.in);

		String word = fin.next();
		String sentence = "";
		System.out.print("Please enter the file name:(end to quit)");
		sentence = fin.next();
		a1.add(sentence);

		while (!sentence.equals("end")) {
			sentence = fin.nextLine();
			if(!sentence.equalsIgnoreCase("end")){
				a1.add(sentence);
			}
		}

		for(int i = 0; i <a1.size();i++){
			try {
				fin = new Scanner(new File(a1.get(i)));
				while(fin.hasNextLine()){
					String line = fin.nextLine();
					int isThere = line.indexOf(word);
					if(isThere!=-1){
						System.out.println(line);
					}
				}
			}


			catch (Exception e) {

			} finally {
				if(fin!=null)fin.close();
			}
		}
	}
	
	//========================================================= P7.13

	// p7.13 Write a program that asks the user to input a set of floating-point
	// values.
	// When the user enters a value that is not a number, give the user a second
	// chance
	// to enter the value. After two chances, quit reading input. Add all
	// correctly specified
	// values and print the sum when the user is done entering data. Use
	// exception handling to
	// detect improper inputs.
	public static void getSum() {
		double sum = 0.0;
		int chance = 2;
		fin = new Scanner(System.in);
		System.out.print("Enter the number: ");

		while (chance != 0) {
			try {
				sum += fin.nextDouble();
			} catch (Exception e) {
				chance--;
				String wrongInput = fin.next();
			}
		}
		System.out.println("The sum is: " + sum);
		fin.close();
	}
}

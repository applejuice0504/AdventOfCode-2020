import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;

//https://adventofcode.com/2020/day/2
public class Day2 {
	
	public static void main(String[] args) {
		
		ArrayList<pwData> input = readInput("input.txt");
		
		int validCounter = 0;
		int validCounter2 = 0;
		for (pwData p : input) {
			if (p.isValid()) 
				validCounter += 1;
			if (p.checkValidityPart2())
				validCounter2 += 1;
		}
		
		System.out.println("Number of Passwords valid[Part 1]: " + validCounter);
		System.out.println("Number of Passwords valid[Part 2]: " + validCounter2);		
		

	}	
	
	private static ArrayList<pwData> readInput(String p) {
		
		ArrayList<pwData> list = new ArrayList<pwData>();
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(p));
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}
		
		while (fileScanner.hasNextLine()) {		//liest Zeilenweise aus der Datei ein
			pwData pwObj = new pwData();
			
			String s = fileScanner.nextLine().replace('-', ' ').replace(':', ' ');	//Jede Zeile wird als String zwischengespeichert. Trennzeichen aus dem Input werden durch Leerraum ersetzt, damit mit einem zweiten Scanner durch den String durchgegangen werden kann und die Infos ausgelesen werden können
			Scanner strScanner = new Scanner(s);
			
			int minLetterNo = strScanner.nextInt();
			int maxLetterNo = strScanner.nextInt();
			char letterSign = strScanner.next().charAt(0);
			String password = strScanner.next();
			
			pwObj.initPw(minLetterNo, maxLetterNo, letterSign, password);
			list.add(pwObj);	
			strScanner.close();		
		}
		fileScanner.close();
		
		
		return list;
	}
	
}
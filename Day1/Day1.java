import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

//https://adventofcode.com/2020/day/1
public class Day1 {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> input;
		input = readInput("input.txt");
		
		System.out.println();		
		
		System.out.println("Suche 2 Werte die in Summe 2020 ergeben");
		int[] indices2;
		indices2 = findSum2(2020, input);
		System.out.println("Wert1: " + input.get(indices2[0]));
		System.out.println("Wert2: " + input.get(indices2[1]));
		System.out.println("Multiplizieren miteinander: " + input.get(indices2[0]) * input.get(indices2[1]));
		
		System.out.println();
		
		System.out.println("Suche 3 Werte die in Summe 2020 ergeben");
		int[] indices3;
		indices3 = findSum3(2020, input);
		System.out.println("Wert1: " + input.get(indices3[0]));
		System.out.println("Wert2: " + input.get(indices3[1]));
		System.out.println("Wert3: " + input.get(indices3[2]));		
		System.out.println("Multiplizieren miteinander: " + input.get(indices3[0]) * input.get(indices3[1]) * input.get(indices3[2]));
		
			
	}
	
	//findSum3() suche drei Werte aus der Liste die in Summe result ergeben gibt ein Int-Array mit den drei Indizes
	private static int[] findSum3(int result, ArrayList<Integer> input) {
		int[] indices = new int[3];
		int l = input.size();
		
		for (int i = 0; i < l; i++) {
			for (int j = i + 1; j < l; j++) {
				for (int k = j + 1; k < l; k++) {
					if ( input.get(i) + input.get(j) + input.get(k) == result ) {
						indices[0] = i;
						indices[1] = j;
						indices[2] = k;
						System.out.println("Triplet gefunden! Index: " + i + " " + j + " " + k);
					}					
				}

			}
		}
		return indices;
	}
	
	//findSum2() suche zwei Werte aus der Liste die in Summe result ergeben gibt ein Int-Array mit den beiden Indizes
	private static int[] findSum2(int result, ArrayList<Integer> input) {
		int[] indices = new int[2];
		int l = input.size();
		
		for (int i = 0; i < l; i++) {
			for (int j = i + 1; j < l; j++) {
				if ( input.get(i) + input.get(j) == result ) {
					indices[0] = i;
					indices[1] = j;
					System.out.println("Pärchen gefunden! Index: " + i + " " + j);
				}
			}
		}
		return indices;
	}
	
	//readInput() liest die Datei im angegebenen Pfad ein und gibt eine ArrayList<Integer> zurück
	private static ArrayList<Integer> readInput(String p) {
		
		ArrayList<String> listStr = new ArrayList<String>();
		ArrayList<Integer> listInt = new ArrayList<Integer>();
		
		//Einlesen der Datei in eine ArrayList<String>
		Scanner scan = null;
		try {
		    scan = new Scanner(new File(p));
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		
		while (scan.hasNext()) {
			listStr.add(scan.nextLine());
		}
		scan.close();
		
		//Parsen und kopieren in ArrayList<Integer> (könnte auch direkt beim Einlesen gemacht werden...)
		for (String i : listStr) {
			listInt.add(Integer.parseInt(i));	
		}
		
		return listInt;	
	}
	
}
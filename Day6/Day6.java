import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.HashSet;

//https://adventofcode.com/2020/day/6

public class Day6 {
	public static void main(String[] args) {
		
		ArrayList<String> input = readInput("input.txt");
		input.add("");	//hinten leere Zeile anhängen, da Leerstring als Trennung verwendet wird
		
		//-------------Part 1-------------
		int groupCount = 0;
		int totalSumCount = 0;
		HashSet<Character> hash = new HashSet<Character>();
						
		for (String str : input) {
			
			for (int k = 0; k < str.length(); k++) {
				hash.add(str.charAt(k));
			}

			if (str.isEmpty()) {
				groupCount = hash.size();
				hash.clear();
				totalSumCount += groupCount;
			}			
		}	
		System.out.println("Total Sum Count: [Part1] " + totalSumCount);		
		
		
		//-------------Part 2------------- 
		groupCount = 0;
		totalSumCount = 0;
		hash.clear();	
		HashSet<Character> personHash = new HashSet<Character>();
		
		//fill hash with first line of input, so it is not empty in the beginning
		for (char c : input.get(0).toCharArray()) {
			hash.add(c);
		}
		
		//starting finding the intersections for each group		
		for (int i = 1; i<input.size(); i++) {
			
			//if not at an empty line => generate a temp person hash, that represents one line and save the intersection into hash 
			if ( !input.get(i).isEmpty() ) {
				for (char c : input.get(i).toCharArray() ) {
					personHash.add(c);
				}
				hash.retainAll(personHash);
				personHash.clear();				
			} else {	//if a empty line is hit, we have one group finished, so count up the size of the current hash, which represents the intersections of all lines of that group
				groupCount = hash.size();
				totalSumCount += groupCount;
				hash.clear();	//clear the hash, and pre-fill it with the next line, for the next group
				if (i < input.size() - 1) {
					for (char c : input.get(i+1).toCharArray()) {
						hash.add(c);
					}
				}
			}
		}		
		System.out.println("Total Sum Count: [Part2] " + totalSumCount);
	}
	
	private static ArrayList<String> readInput (String p) {
		ArrayList<String> list = new ArrayList<String>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(p));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while ( scanner.hasNextLine() ) {
			list.add(scanner.nextLine());
		}
		scanner.close();
	
		return list;
	}
	
}
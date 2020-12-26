import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;

//https://adventofcode.com/2020/day/4
public class Day4 {
	
	public static void main(String[] args) {
		ArrayList<String> input = readInput("input.txt");
		
		int validCount = 0;
		
		//Check passport validity for Part 1 - 7 Fields existing
		for (String str : input) {
			int fieldCount = 0;
			if (str.contains("byr:")) fieldCount++;
			if (str.contains("iyr:")) fieldCount++;
			if (str.contains("eyr:")) fieldCount++;
			if (str.contains("hgt:")) fieldCount++;
			if (str.contains("hcl:")) fieldCount++;
			if (str.contains("ecl:")) fieldCount++;
			if (str.contains("pid:")) fieldCount++;				
			//If all fields are within the passport infos, the passport is valid, so we can increase the counter for valid passports. Check for cid: (Counry code) is not included cause it's just an optional information
			if(fieldCount == 7) validCount++;				
		} 
		System.out.println("Number of Valid passports [Part 1]: " + validCount + " of " + input.size() + " passports are ok!");
		
		
		//Check passport validity for Part 2 - if the 7 fields have correct entries
		for (String str : input) {
			
		}
		
		
	}
	
	public static ArrayList<String> readInput(String p) {
		
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(p));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		//scan all lines and save into a temp Arraylist
		while (scanner.hasNextLine()) {
			temp.add(scanner.nextLine());
		}
		scanner.close();
				
		//run through the arraylist temp, and combine the strings. as soon an empty line/string is found, the passport infos is complete and will be stored in the return list. a new passport begeins
		String s = "";
		for (String str : temp) {
			if ( ! str.isEmpty() ) 
				s = s + " " + str;
			else {
				list.add(s);
				s = "";
			}
		}
		list.add(s); //as in the last loop the the else is not run through, we need to add the last item manually
				
		return list;
	}
	
}
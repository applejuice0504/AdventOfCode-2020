import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.regex.Pattern;


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
		
				
		int validCount2 = 0;
		//Check passport validity for Part 2 - if the 7 fields have correct entries
		for (String str : input) {
			int fieldCount = 0;			
						
			//Check if byr is between 1920 and 2002
			if ( str.matches(".*byr:19[2-9][0-9]\\W.*") || str.matches(".*byr:200[0-2]\\W.*"))
				fieldCount += 1; 
			
			//Check if iyr is between 2010 and 2020
			if ( str.matches(".*iyr:201[0-9]\\W.*") || str.matches(".*iyr:2020\\W.*"))
				fieldCount += 1; 
			
			//Check if eyr is between 2020 and 2030
			if ( str.matches(".*eyr:202[0-9]\\W.*") || str.matches(".*eyr:2030\\W.*"))
				fieldCount += 1;
			
			//Check if hcl is # + six character 0-9 or a-f
			if ( str.matches(".*hcl:#[0-9a-f]{6}\\W.*") )
				fieldCount += 1; 		
					
			//Check if pid is a nine digit number
			if ( str.matches(".*pid:[0-9]{9}\\W.*") )
				fieldCount += 1; 
			
			//Check ecl is one of amb blu brn gry grn hzl oth
			if ( str.matches(".*ecl:amb\\W.*") || 
				 str.matches(".*ecl:blu\\W.*") ||
				 str.matches(".*ecl:brn\\W.*") ||
			     str.matches(".*ecl:gry\\W.*") ||
			     str.matches(".*ecl:grn\\W.*") ||
				 str.matches(".*ecl:hzl\\W.*") ||
				 str.matches(".*ecl:oth\\W.*") )
					 fieldCount += 1;
			
			//Check if hgt a number followed by cm or in
	        	//If cm, the number must be at least 150 and at most 193.
	        	//If in, the number must be at least 59 and at most 76.
			if ( str.matches(".*hgt:1[5-8][0-9]cm\\W.*") || str.matches(".*hgt:1[9][0-3]cm\\W.*"))
				fieldCount +=1;
			else if ( str.matches(".*hgt:59in\\W.*") || 
					  str.matches(".*hgt:6[0-9]in\\W.*") || 
					  str.matches(".*hgt:7[0-6]in\\W.*") )
						  fieldCount += 1;
			
			if (fieldCount == 7) 
				validCount2 += 1;			
		}
		System.out.println("Number of Valid passports [Part 2]: " + validCount2 + " of " + input.size() + " passports are ok!");
		
		
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
				s = s + " " + str + " ";
			else {
				list.add(s);
				s = "";
			}
		}
		list.add(s); //as in the last loop the the else is not run through, we need to add the last item manually
				
		return list;
	}
	
}
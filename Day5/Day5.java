import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Arrays;

//https://adventofcode.com/2020/day/5

public class Day5 {
	
	public static void main(String[] args) {
		ArrayList<String> input = readInput("input.txt");
		
		String[][] data = new String[input.size()][2];
		
		for (int i = 0; i<input.size(); i++) {
			data[i][0] = input.get(i).substring(0,7);
			data[i][1] = input.get(i).substring(7);
		}
		
		//Part 1: 
		int maxSeatID = 0;
		int rowInt = 0;
		int columnInt = 0;	
		int seatID = 0;
		for (int i = 0; i < data.length; i++) {
			rowInt = binConverter(data[i][0], 0, 127, 'F', 'B');
			columnInt = binConverter(data[i][1], 0, 7, 'L', 'R');
			seatID = getSeatID(rowInt, columnInt);
			if (seatID > maxSeatID) {
				maxSeatID = seatID;
			}				
		}
		System.out.println("Maximum Seat ID: " + maxSeatID);

		//Part 2: Find my Seat, which is missing in the list
		int[] boardingList = new int[data.length];
		for (int i = 0; i<data.length; i++) {
			rowInt = binConverter(data[i][0], 0, 127, 'F', 'B');
			columnInt = binConverter(data[i][1], 0, 7, 'L', 'R');
			boardingList[i] = getSeatID(rowInt, columnInt);
		}
		
		Arrays.sort(boardingList);		
		for (int i=0; i<boardingList.length - 1; i++) {
			if ( boardingList[i+1] - boardingList[i] > 1 ) {
				System.out.print("Your seat is between ");
				System.out.print(boardingList[i] + " and ");
				System.out.println(boardingList[i+1]);
			}					
		}


	}
	
	// Input: String-Code, Int Row-or-Column Limits, Chars that define when to chose lower/left, upper/right rows or colums
	// returns the int value of the String-Code
	// recursive function, recalls itself by always deleting first character of string
	// 2^s.length() needs to be the same as maxLim 
	private static int binConverter (String s, int minLim, int maxLim, char low, char up){
		if (minLim != maxLim) {
			char firstChar = s.charAt(0);
			if ( firstChar == low ) {
				maxLim = (minLim + maxLim) / 2;
			} else if (firstChar == up ) {
				minLim = (minLim + maxLim + 1) / 2;
			}		
			return binConverter(s.substring(1), minLim, maxLim, low, up);
		} else  
			return maxLim;
	}
	
	private static int getSeatID (int row, int column) {
		return 8*row + column;	
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

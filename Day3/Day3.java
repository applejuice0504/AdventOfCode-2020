import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

//https://adventofcode.com/2020/day/3
public class Day3 {
	
	public static void main(String[] args) {
		
		ArrayList<String> input = readInput("input.txt");
		
		int tsk1 = treeCount(input, 1, 1);
		int tsk2 = treeCount(input, 1, 3);
		int tsk3 = treeCount(input, 1, 5);
		int tsk4 = treeCount(input, 1, 7);
		int tsk5 = treeCount(input, 2, 1);
			
		
		System.out.println("Right 1,Down 1: " + tsk1);
		System.out.println("Right 3,Down 1: " + tsk2);				
		System.out.println("Right 5,Down 1: " + tsk3);
		System.out.println("Right 7,Down 1: " + tsk4);
		System.out.println("Right 1,Down 2: " + tsk5);
		
		System.out.println("Task Multipliziert: " + tsk1*tsk2*tsk3*tsk4*tsk5 );
	}
	
	private static int treeCount(ArrayList<String> input, int slopeDown, int slopeRight) {
		int treeCount = 0;
		int l = input.size(); //heigth
		int w = input.get(0).length(); //width
		
		int lCtr = 0; //Schleifenzähler Zeile
		int wCtr = 0; //Schleifenzähler Spalte
		while(lCtr < l) {	
			if ( input.get(lCtr).charAt(wCtr) == '#' )
				treeCount += 1;
			lCtr += slopeDown;		// geht immer 1 Zeile weiter, bzw. wie in der Aufgabe .2 vorgegeben
			wCtr = (wCtr + slopeRight) % w;	// und 3 Schritte nach rechts... %w, da sich der pattern nach rechts immer weiter wiederholt
		}
		
		return treeCount;
	}
		
	//Input einlesen und als ArrayList mit Strings zurückgeben
	private static ArrayList<String> readInput(String p) {
		
		ArrayList<String> listStr = new ArrayList<String>();
		
		//Einlesen der Datei in eine ArrayList<String>
		Scanner scan = null;
		try {
		    scan = new Scanner(new File(p));
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		
		while (scan.hasNext()) {
			
			listStr.add( (scan.nextLine()) );
		}
		scan.close();
		
		return listStr;	
		
	}
	
	
}
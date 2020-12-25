public class pwData {
	
	private char letterSign = ' ';
	private int minLetterNo = 0;
	private int maxLetterNo = 0;
	private String password = null;
	private boolean isValid = false;
	
	public void initPw (int pMinLetterNo, int pMaxLetterNo, char pLetterSign, String pPassword) {
		this.setMinLetterNo(pMinLetterNo);
		this.setMaxLetterNo(pMaxLetterNo);
		this.setLetterSign(pLetterSign);
		this.setPassword(pPassword);
		this.checkValidity();
	}
	
	public void setLetterSign(char inp) {
		this.letterSign = inp;
	}
	
	public void setMinLetterNo(int inp) {
		this.minLetterNo = inp;
	}
	
	public void setMaxLetterNo(int inp) {
		this.maxLetterNo = inp;
	}
	
	public void setPassword(String inp) {
		this.password = inp;
	}
	
	public boolean isValid() {
		this.checkValidity();
		return this.isValid;
	}
	
	/*Each line gives the password policy and then the password. 
	The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. 
	For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.*/
	private void checkValidity() {
		//Check if all information there to validate password...
		if (this.password == null || minLetterNo == 0 || maxLetterNo == 0 || letterSign == ' ') {
			this.isValid = false;
			System.out.println("Not all Information given to check password validity. Either password or password rules unset!");
			return;
		}
		
		//Count how many of required letters are within password
		int occurCount = 0;
		for (int i = 0; i < this.password.length(); i++) {
			if (this.password.charAt(i) == letterSign) 
				occurCount += 1;
		}
		
		//Check if occurence of required letters are within limits
		if (occurCount >= minLetterNo && occurCount <= maxLetterNo) 
			this.isValid = true;
		else 
			this.isValid = false;				
	}
	

    /* Exactly one of these positions must contain the given letter.
	1-3 a: abcde is valid: position 1 contains a and position 3 does not.
    1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
    2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.*/
	public boolean checkValidityPart2() {	// only implemented to solve Part2 of the puzzle quick & dirty
		
		char charAtPos1 = password.charAt(minLetterNo-1);		
		char charAtPos2 = password.charAt(maxLetterNo-1);
		
		if (charAtPos1 == charAtPos2) 	//wenn beide gleich sind, sind die Regeln nicht erfüllt
			return false;
		
		if (charAtPos1 == letterSign || charAtPos2 == letterSign) //dass beide gleich sind, ist schon ausgeschlossen, wenn jetzt also einer dem LetterSign entspricht, sind die Regeln erfüllt
			return true;
		
		return false;	
		//ansonsten stimmt keiner mit LetterSign überein, dann sind die Regeln nicht erfüllt ==> das Ganze könnte auch mit einem XOR (ausschließendem Oder gemacht werden)
		// if (charAtPos1 == letterSign ^ charAtPos2 == letterSign) return true else return false 
	}
	
	@Override
	public String toString() {
		return ( this.minLetterNo + "-" + this.maxLetterNo + " " + this.letterSign + ":" + " " + this.password + "! isValid=" + this.isValid());		
	}	
}
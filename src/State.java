/**
 * set behavior of state
 * @author Napong Dungduangsasitorn
 *
 */
public abstract class State {
	
	/**
	 * handleChar check input char
	 * @param c char that want to check.
	 */
	public abstract void handleChar(char c);
	
	/**
	 * use for syllable count.
	 */
	public void enterState( ) { /* default is to do nothing */ }
	
	/**
	 * isVowel check char is vowel
	 * @param c char that want to check.
	 * @return true if vowel.
	 */
	public boolean isVowel(char c){
		String vowel = "aeiou";
		String temp = c + "";
		return vowel.contains(temp);
	}

	/**
	 * isVowelOrY check is vowel or char y.
	 * @param c char that want to check.
	 * @return true if vowel or char y.
	 */
	public boolean isVowelOrY(char c) {
		if(isVowel(c) || c == 'y')
			return true;
		return false;
	}

}

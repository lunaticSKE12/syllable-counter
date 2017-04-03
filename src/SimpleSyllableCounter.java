/**
 * SimpleSyllableCounter count syllables by simple state.
 * @author Napong Dungduangsasitorn
 *
 */
public class SimpleSyllableCounter {
 
	/**
	 * State is an enum of the states 
	 */
	public enum State{
		START,
		CONSONANT,
		SINGLE_VOWEL,
		MULTIVOWEL,
		HYPHEN,
		NONWORD,
		CHARECTER_E;
	}

	/**
	 * countSyllables count syllables by state.
	 * @param word is word that want to count syllables.
	 * @return syllables sum of syllables by state.
	 */
	public int countSyllables(String word){
		int syllables = 0;
		char c = ' ';
		State state = State.START;  // State is an enum of the states 
		word = word.toLowerCase();
		for(int k=0; k<word.length(); k++) { 
			c = word.charAt(k); 
			if (c == '\'') continue; // ignore apostrophe 
			switch(state) { 
			// process character c using state machine 
			case START: 
				if("e".indexOf(c) >= 0){
					state = State.CHARECTER_E;
				}
				if (isVowelOrY(c)) { 
					state = State.SINGLE_VOWEL; 
					syllables++; 
				} 
				else if (Character.isLetter(c)) 
					state = State.CONSONANT; 
				else if (c == '-') state = State.NONWORD ; 
				else state = State.HYPHEN; 
				break; 

			case CONSONANT: 
				if("e".indexOf(c) >= 0){
					state = State.CHARECTER_E;
				}
				if (isVowelOrY(c)) {
					if (c != 'e' || (k != word.length() - 1) || (syllables == 0)){
					state = State.SINGLE_VOWEL;
					syllables++;
					}
				}
				else if (Character.isLetter(c));
				else if (c == '-')
					state = State.HYPHEN;
				else
					state = State.NONWORD;
				break;

			case SINGLE_VOWEL:
				if (isVowel(c)) {
					state = State.MULTIVOWEL;
				} 
				else if (Character.isLetter(c)) state = State.CONSONANT;
				else if (c == '-') state = State.HYPHEN;
				else
					state = State.NONWORD;
				break;

			case MULTIVOWEL:
				if (isVowel(c)) {} 
				else if (Character.isLetter(c))
					state = State.CONSONANT;
				else if (c == '-')
					state = State.HYPHEN;
				else
					state = State.NONWORD;
				break;

			case HYPHEN:
				if("e".indexOf(c) >= 0){
					state = State.CHARECTER_E;
				}
				if (isVowelOrY(c)) {

					state = State.SINGLE_VOWEL;
					syllables++;

				} else if (Character.isLetter(c))
					state = State.CONSONANT;
				else
					state = State.NONWORD;
				break;
			case NONWORD:
				k = word.length();
				break;

			case CHARECTER_E: 
				if ( isVowel(c) ){ 
					state = state.MULTIVOWEL ; 
				}
				else if(Character.isLetter(c)){ 
					state = state.CONSONANT ; 
					syllables++; 
				}
				else if(c == '-'){ 
					state = state.HYPHEN; 
					syllables++;
				}
				else state = state.NONWORD;
				break;

			default:
				break;
			}
		} 
		return syllables;
	}

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
	private boolean isVowelOrY(char c) {
		if(isVowel(c) || c == 'y')
			return true;
		return false;
	}
}
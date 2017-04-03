
public class OOSyllableCounter {
	private final State START = new StartState();
	private final State CONSONANT = new ConsonantState();
	private final State SINGLE_VOWEL = new SingleVowelState();
	private final State MULTIVOWEL = new MultiVowelState();
	private final State HYPHEN = new HyphenState();
	private final State NONWORD = new NonWordState();
	private State state;
	private int syllableCount = 0;
	int index = 0;
	int wordLength = 0;

	/** change to a new state */ 
	public void setState( State newstate ) { 
		state = newstate; 

	}

	public int countSyllables( String word ){
		syllableCount = 0;
		setState(START);
		char c = ' ';
		word.toLowerCase();
		wordLength = word.length();
		for (int k = 0; k < wordLength; k++) {
			c = word.charAt(k);
			index = k;
			if (c == '\'')
				continue; // ignore apostrophe
			// process character c using state machine
			state.handleChar(c);
		}

		return syllableCount;
	}

	class StartState extends State{

		@Override
		public void handleChar(char c) {
			if (isVowelOrY(c)) { 
				setState(SINGLE_VOWEL);
				enterState(); 
			} 
			else if (Character.isLetter(c)) 
				setState(CONSONANT);
			else if (c == '-') 
				setState(NONWORD);
			else 
				setState(HYPHEN);
		}

		public void enterState() {
			syllableCount++;
		}
	}

	class ConsonantState extends State{

		@Override
		public void handleChar(char c) {
			if (isVowelOrY(c)) {
				if (c != 'e' || (index != wordLength - 1) || (syllableCount == 0)){
					setState(SINGLE_VOWEL);
					enterState();
				}
			}
			else if (Character.isLetter(c));
			else if (c == '-')
				setState(HYPHEN);
			else
				setState(NONWORD);
		}

		public void enterState() {
			syllableCount++;
		}

	}

	class SingleVowelState extends State {

		@Override
		public void handleChar(char c) {
			if (isVowel(c)) {
				setState(MULTIVOWEL);
			} 
			else if (Character.isLetter(c)) 
				setState(CONSONANT);
			else if (c == '-')
				setState(HYPHEN);
			else
				setState(NONWORD);
		}

	}

	class MultiVowelState extends State {

		@Override
		public void handleChar(char c) {
			if (isVowel(c)) {} 
			else if (Character.isLetter(c))
				setState(CONSONANT);
			else if (c == '-')
				setState(HYPHEN);
			else
				setState(NONWORD);
		}

	}

	class HyphenState extends State {

		@Override
		public void handleChar(char c) {
			if (isVowelOrY(c)) {
				setState(SINGLE_VOWEL);
				enterState();
			} else if (Character.isLetter(c))
				setState(CONSONANT);
			else
				setState(NONWORD);

		}

		public void enterState() {
			syllableCount++;
		}

	}

	class NonWordState extends State {

		@Override
		public void handleChar(char c) {
		}

		public void enterState() {
		}

	}

}

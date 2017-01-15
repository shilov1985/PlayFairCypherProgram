public class CipherKey {
	// This class contain a method,which return fixed variant of the CypferKey
	// needed for coding some text
	static StringBuilder keyFiltered;

	public static StringBuilder fixKeyForCoding(char[] arrayLetters) {

		keyFiltered = new StringBuilder();

		// append all array letters to a StringBuilder to be comfortable
		// for manipulating
		for (char x : arrayLetters) {
			keyFiltered.append(x);
		}
		// Here replace all 'J' letters with 'I' in the cipher key
		for (int i = 0; i < keyFiltered.length(); i++) {
			if (keyFiltered.charAt(i) == 'J') {
				keyFiltered.replace(i, i + 1, "I");
			}
		}

		// These nested loops are filtering all
		// letters which repeat.

		for (int x = 0; x < RunProgram.keyArraySymbols.length; x++) {

			for (int i = 0; i < keyFiltered.length(); i++) {

				for (int j = i + 1; j < keyFiltered.length(); j++) {

					if (keyFiltered.charAt(i) == keyFiltered.charAt(j)) {
					
						keyFiltered.deleteCharAt(j);
						// System.out.println(keyFiltered);
					}

				}
			}
		}

		return keyFiltered;

	}
}

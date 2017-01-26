public class RestLettersForMatrix {
	// This class contain a method,which accepts   a cipher key parameter,and
	// return variable of type StringBuilder with the letters ,not
	// included in the cipher key,but without
	// 'J'.
	public static StringBuilder getRestLettersForMatrix(
			StringBuilder keyArrayLetters) {

		StringBuilder restLetters = new StringBuilder();

		int flag = 0;

		char[] alpfabetLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };

		// These nested loops below check for rest letters needed for
		// "playfairMatrix"array ,and append these letters to
		// "restLetters"StringBuilder variable.
		// The code append only elements , not included in the cipher
		// key.
		for (int j = 0; j < alpfabetLetters.length; j++) {

			for (int i = 0; i < keyArrayLetters.length(); i++) {

				if (alpfabetLetters[j] != keyArrayLetters.charAt(i)) {
					flag++;

				}

			}
		
			// If the length of the "flag" is equal to length of
			// "matrixArray",it means that
			// the element on position "alpfabetLetters[j]" is not exist
			// in the cipher key,and the code append this element
			// to"restLetters" variable;

			if (flag == keyArrayLetters.length()) {

				restLetters.append(alpfabetLetters[j]);

			}
			flag = 0;
		}

		return restLetters;
	}
}

public class SentenseForCoding {
	// This class contain method for fixing a sentence which we want to encrypt
	public static String convertSentense(String sentence) {

		StringBuilder formattedString = new StringBuilder();

		// Here remove all spaces and intervals in the sentence,and convert it
		// to upper case and replace 'J' with 'I';
		String trimmedSentence = sentence.toUpperCase().replaceAll("\\s", "")
				.replaceAll("J", "I").trim();

		// Convert "trimmedSentence" variable to variable of type StringBuilder
		// and remove all 'J' letters'.
		for (int i = 0; i < trimmedSentence.length(); i++) {

			formattedString.append(trimmedSentence.charAt(i));

		}
		// Here check "formattedString" by two-letter chunks.If there are
		// repeated
		// letter in the same chunk ,the code below separate them by an 'X'.

		for (int i = 1; i < formattedString.length(); i += 2) {

			if (formattedString.charAt(i - 1) == formattedString.charAt(i)) {
				if (formattedString.charAt(i) == 'X') {
					formattedString.insert(i, "Q");
				} else {
					formattedString.insert(i, "X");
				}
			}

		}
		// The code below append an 'X' in the end of the
		// "formattedString" variable,if the
		// "formattedString" length is not even number.
		if (formattedString.length() % 2 != 0) {
			if (formattedString.charAt(formattedString.length()-1) == 'X') {
				formattedString.append("Q");
			} else {
				formattedString.append("X");
			}

		}

		return String.valueOf(formattedString);
	}

}

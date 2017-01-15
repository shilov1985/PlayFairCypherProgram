import java.util.Scanner;

public class RunProgram {
	// At this class we combine all methods and variables needed to make this
	// program to works.Here is the main program encrypting logic.
	static char[] keyArraySymbols;
	static char[][] playfairMatrix;
	static StringBuilder convertedKey;

	public static void main(String[] args) {

		playfairMatrix = new char[5][5];
		Scanner scan = new Scanner(System.in);
		System.out.println("PlayFair encrypt program by Miroslav Shilov.\n");
		System.out
				.println("Please input a text wich you want to encrypt and press Enter...");

		String sentenseForCoding = null;

		do {
			sentenseForCoding = scan.nextLine().replaceAll("[^a-zA-Z]+", "");
			if (sentenseForCoding.length() == 0) {
				System.out
						.println("Invalid length!\nPlease input a text for encrypting and press Enter...");
			}
		} while (sentenseForCoding.length() == 0);

		System.out
				.println("Please input a key for encrypting of the text and press Enter...");
		System.out
				.println("Note-if you not input key for encrypting,by default\nwill be used"
						+ " the English alpfabet,without 'J'.");

		String key = scan.nextLine().replaceAll("[^a-zA-Z]+", "");
		;
		// Here we remove all spaces and intervals in the cipher key,and convert
		// it
		// to
		// upper case.
		keyArraySymbols = key.toUpperCase().replaceAll("\\s", "").trim()
				.toCharArray();

		// Here we implement "fixKeyForCoding" method to fix the cipher key.
		convertedKey = CipherKey.fixKeyForCoding(keyArraySymbols);

		// Here we implement "fixKeyForCoding" method to get
		// the rest letters for "playfairMatrix".
		StringBuilder restElement = RestLettersForMatrix
				.getRestLettersForMatrix(convertedKey);

		// The code below fill the "playfairMatrix" with the PlayFair-key
		// needed for encrypting
		// and the rest letters from the English alphabet.
		int counterKey = 0;
		int counterRest = 0;

		for (int i = 0; i < playfairMatrix.length; i++) {

			for (int j = 0; j < playfairMatrix[0].length; j++) {
				// Here the code fill "playfairMatrix" with the key.
				if (counterKey < convertedKey.length()) {
					playfairMatrix[i][j] = convertedKey.charAt(counterKey);
					counterKey++;
				} else {
					// Here the code fill "playfairMatrix" with the rest
					// letters.
					playfairMatrix[i][j] = restElement.charAt(counterRest);
					counterRest++;
				}
			}

		}
		// print the "playfairMatrix" in console

		// for (int i = 0; i < playfairMatrix.length; i++) {
		// for (int j = 0; j < playfairMatrix.length; j++) {
		// System.out.print(playfairMatrix[i][j]);

		// }
		// System.out.println();
		// }

		String result = SentenseForCoding.convertSentense(sentenseForCoding);
		// System.out.println("fixed text=" + result);

		// Here breaks "formattedString" into two-letter chunks
		// in two dimensional array to be comfortable use diagrams

		int countSentence = 0;
		char[][] sentenceArr = new char[result.length() / 2][2];
		for (int i = 0; i < sentenceArr.length; i++) {
			for (int j = 0; j < 2; j++) {
				sentenceArr[i][j] = result.charAt(countSentence);
				countSentence++;
			}
		}

		char tempA;
		char tempB;
		StringBuilder codeSentence = new StringBuilder();

		/**
		 * The code below is the main logic in this program. Here we use the
		 * diagrams from "sentenceArr" to encrypt a text.
		 */
		for (int i = 0; i < sentenceArr.length; i++) {
			// First takes the letters from diagrams array "sentenceArr".

			tempA = sentenceArr[i][0];
			tempB = sentenceArr[i][1];

			// Iterate over "playfairMatrix"...
			for (int matrixRows = 0; matrixRows < playfairMatrix.length; matrixRows++) {
				for (int matrixCols = 0; matrixCols < playfairMatrix[0].length; matrixCols++) {

					// Here takes the position of first letter of the chunk
					// In every case the position is founded
					if (tempA == playfairMatrix[matrixRows][matrixCols]) {

						// After have founded the position of first letter of
						// the chunk must find the position of the second
						// letter
						// of the same chunk.
						// Start to search for position of the second letter.
						for (int tempBRows = 0; tempBRows < playfairMatrix[0].length; tempBRows++) {

							// Here check by variable(counter) "x" of the
							// outermost for
							// cycle ,if the second letter is at the same row
							// like the first letter."x"=index of row.
							if (tempB == playfairMatrix[matrixRows][tempBRows]) {

								// And if these two letters are in same row are
								// formed three stories,and in all of the time
								// append the encrypted letters to
								// "codeSentence"
								// variable.

								/**********************************************
								 * First story:if the second letter is in the *
								 * end of the row proceed with code below. *
								 * ********************************************
								 **/
								if (tempBRows == playfairMatrix[0].length - 1) {
									// Here takes the encrypted letter by the
									// position of
									// the first
									// letter from the chunk,shifted with one
									// position
									// on right.
									codeSentence
											.append(playfairMatrix[matrixRows][matrixCols + 1]);
									// Here takes the first letter of the row.
									// This is the encrypted letter.
									codeSentence
											.append(playfairMatrix[matrixRows][0]);

									// System.out.println(playfairMatrix[x][j +
									// 1]);
									// System.out.println(playfairMatrix[x][0]);

									/**********************************************
									 * Second story:if the first letter from
									 * the* chunk is in the end of the row
									 * proceed* with code below.. *
									 ********************************************** 
									 **/
								} else if (matrixCols == playfairMatrix[0].length - 1) {
									// Here takes the first letter of the row.
									// This is the encrypted letter.
									codeSentence
											.append(playfairMatrix[matrixRows][0]);
									// Here takes the encrypted letter by the
									// position of
									// second
									// letter from the chunk,shifted with one
									// position
									// on right.
									codeSentence
											.append(playfairMatrix[matrixRows][tempBRows + 1]);

									// System.out.println(playfairMatrix[x][0]);
									// System.out.println(playfairMatrix[x][z +
									// 1]);

									/**********************************************
									 * Third story:when have no letters from the
									 * chunks equal to the letters in the end of
									 * the matrix rows,proceed with the code
									 * below.
									 ********************************************** 
									 **/
								} else {
									// Here have no special situation,just
									// takes
									// the letters shifted with
									// one position on right.These are the
									// encrypted letters.
									codeSentence
											.append(playfairMatrix[matrixRows][matrixCols + 1]);
									codeSentence
											.append(playfairMatrix[matrixRows][tempBRows + 1]);

									// System.out.println(playfairMatrix[x][j +
									// 1]);
									// System.out.println(playfairMatrix[x][z +
									// 1]);
								}
							}
						}
						// If the two letters in the chunk not forms
						// rows ,maybe forms columns,and code below
						// check is that true.
						// Check if the diagrams forms column
						for (int tempBRows = 0; tempBRows < playfairMatrix.length; tempBRows++) {

							if (tempB == playfairMatrix[tempBRows][matrixCols]) {

								if (tempB == playfairMatrix.length - 1) {
									codeSentence
											.append(playfairMatrix[matrixRows + 1][matrixCols]);
									codeSentence
											.append(playfairMatrix[0][matrixCols]);
									// System.out.println(playfairMatrix[x+1][j]);
									// System.out.println(playfairMatrix[0][j]);

								} else if (matrixRows == playfairMatrix[0].length - 1) {
									codeSentence
											.append(playfairMatrix[0][matrixCols]);
									codeSentence
											.append(playfairMatrix[tempBRows + 1][matrixCols]);
									// System.out.println(playfairMatrix[0][j]);
									// System.out.println(playfairMatrix[k+1][j]);

								} else {
									codeSentence
											.append(playfairMatrix[matrixRows + 1][matrixCols]);
									codeSentence
											.append(playfairMatrix[tempBRows + 1][matrixCols]);
									// System.out.println(playfairMatrix[x+1][j]);
									// System.out.println(playfairMatrix[k+1][j]);

								}
							}
						}
						// check if diagrams forms square
						for (int squareRows = 0; squareRows < playfairMatrix.length; squareRows++) {
							for (int squareCols = 0; squareCols < playfairMatrix[0].length; squareCols++) {

								if (tempB == playfairMatrix[squareRows][squareCols]) {
									// Here check if the letters from
									// "sentenceArr" are
									// on different rows and columns,and start
									// logic
									// for tacking the encrypted letters.
									if ((matrixRows != squareRows)
											&& (squareCols != matrixCols)) {
										codeSentence
												.append(playfairMatrix[matrixRows][squareCols]);
										codeSentence
												.append(playfairMatrix[squareRows][matrixCols]);
									}
								}

							}

						}

					}

				}

			}

		}
		System.out.println("Here is your encrypted text.");
		System.out.println(codeSentence);
	}

}

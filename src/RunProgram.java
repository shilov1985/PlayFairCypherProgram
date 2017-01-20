import java.util.Scanner;

public class RunProgram {
	// At this class combine all methods and variables needed to make this
	// program to works.Here is the main program encrypting logic.
	static char[] keyArraySymbols;
	static char[][] playfairMatrix;
	static StringBuilder convertedKey;

	public static void main(String[] args) {

		playfairMatrix = new char[5][5];
		Scanner scan = new Scanner(System.in);
		System.out
				.println("PlayFair cypher encrypt program by Miroslav Shilov.");
		System.out
				.println("https://github.com/shilov1985/PlayfairCypherProgram\n");

		System.out
				.println("Please input a text which you want to encrypt and press Enter...");

		String sentenseForCoding = null;

		do {
			sentenseForCoding = scan.nextLine().replaceAll("[^a-zA-Z]+", "");
			if (sentenseForCoding.length() == 0) {
				System.out
						.println("Invalid length!\nPlease input a text for encrypting and press Enter...");
			}
		} while (sentenseForCoding.length() == 0);

		// Here implement "SentenseForCoding" method to fix some text which
		// this program encrypt.
		String result = SentenseForCoding.convertSentense(sentenseForCoding);

		System.out
				.println("Here is formatted variant of the text which you want to encrypt:"
						+ result + "\n");

		System.out
				.println("Please input a key for encrypting of the text and press Enter...");
		System.out
				.println("Note-if you not input key for encrypting,by default\nthe matrix will be filled with"
						+ " the English alpfabet,without 'J'.");

		String key = scan.nextLine().replaceAll("[^a-zA-Z]+", "");
		
		
		// Here remove all spaces and intervals in the cipher key,and convert
		//  upper case.
		keyArraySymbols = key.toUpperCase().replaceAll("\\s", "").trim()
				.toCharArray();

		// Here implement "fixKeyForCoding" method to fix the cipher key.
		convertedKey = CipherKey.fixKeyForCoding(keyArraySymbols);
		System.out
				.println("Here is the fixed variant of the key for encrypting:"
						+ convertedKey + "\n");

		if (convertedKey.length() == 0) {
			System.out.println("You not have entered key.\n");

		}
		// Here implement "fixKeyForCoding" method to get
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

		for (int i = 0; i < playfairMatrix.length; i++) {
			for (int j = 0; j < playfairMatrix.length; j++) {
				System.out.print(playfairMatrix[i][j]);

			}
			System.out.println();
		}
		System.out.println();

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
		StringBuilder encryptedSentence = new StringBuilder();

		/*******************************************************************************
		 * The code below is the main logic in this program. Here  use the
		 * diagrams from "sentenceArr" to encrypt a text.
		 *******************************************************************************/
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

						// After have founded the position of the first letter
						// of
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
								// "encryptedSentence"
								// variable.

								/**********************************************
								 * First story:if the second letter is in the *
								 * end of the row proceed with code below. *
								 * ********************************************
								 **/
								if (tempBRows == playfairMatrix[0].length - 1) {

									/*
									 * Here check if the second letter is in the
									 * end of the play fair matrix.This is
									 * special case
									 */
									if (matrixRows == playfairMatrix.length - 1) {

										// Here takes the next letter in the row
										encryptedSentence
												.append(playfairMatrix[matrixRows][matrixCols + 1]);

										// Here takes the first letter of the
										// play fair matrix

										encryptedSentence
												.append(playfairMatrix[0][0]);
									} else {
										// Here takes the encrypted letter by
										// the
										// position of
										// the first
										// letter from the chunk,shifted with
										// one
										// position
										// on right.
										encryptedSentence
												.append(playfairMatrix[matrixRows][matrixCols + 1]);

										// Here takes the first letter of the
										// next
										// row.
										encryptedSentence
												.append(playfairMatrix[matrixRows + 1][0]);
									}
									/**********************************************
									 * Second story:if the first letter from the
									 * chunk is in the end of the row proceed*
									 * with code below.. *
									 ********************************************** 
									 **/
								} else if (matrixCols == playfairMatrix[0].length - 1) {

									/*
									 * Here check if the first letter is in the
									 * end of the play fair matrix. This is
									 * special case!
									 */
									if (matrixRows == playfairMatrix.length - 1) {

										// Here takes the first letter of the
										// play fair matrix.
										encryptedSentence
												.append(playfairMatrix[0][0]);

										// Here takes the encrypted letter by
										// the
										// position of
										// the second
										// letter from the chunk,shifted with
										// one
										// position
										// on right.

										encryptedSentence
												.append(playfairMatrix[matrixRows][tempBRows + 1]);

									} else {

										// Here takes the first letter of the
										// next row.

										encryptedSentence
												.append(playfairMatrix[matrixRows + 1][0]);
										// Here takes the encrypted letter by
										// the
										// position of
										// second
										// letter from the chunk,shifted with
										// one
										// position
										// on right.
										encryptedSentence
												.append(playfairMatrix[matrixRows][tempBRows + 1]);
									}

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
									// one position on right.
									encryptedSentence
											.append(playfairMatrix[matrixRows][matrixCols + 1]);
									encryptedSentence
											.append(playfairMatrix[matrixRows][tempBRows + 1]);

								}
							}
						}
						// If the two letters in the chunk not forms
						// rows ,maybe forms columns,and code below
						// check is that true.

						for (int tempBRows = 0; tempBRows < playfairMatrix.length; tempBRows++) {

							// Check if the diagrams forms column
							// And if the two letters forms column are formed 3
							// stories
							if (tempB == playfairMatrix[tempBRows][matrixCols]) {

								/**********************************************
								 * First story:if the second letter is in the *
								 * end of the column proceed with the code
								 * below. *
								 * ********************************************
								 **/

								if (tempBRows == playfairMatrix.length - 1) {

									/*
									 * Here check if the second letter is in the
									 * end of the play fair matrix. This is
									 * special case!
									 */
									if (matrixCols == playfairMatrix[0].length - 1) {

										// Here takes next letter by the
										// position
										// of first letter shifted with one
										// position below
										encryptedSentence
												.append(playfairMatrix[matrixRows + 1][matrixCols]);

										// Here takes the first element in the
										// matrix.
										encryptedSentence
												.append(playfairMatrix[0][0]);

									} else {

										// Here takes the encrypted letter by
										// the
										// position of
										// first
										// letter from the chunk,shifted with
										// one
										// position
										// below.
										encryptedSentence
												.append(playfairMatrix[matrixRows + 1][matrixCols]);

										// Here takes the first letter of the
										// next column.

										encryptedSentence
												.append(playfairMatrix[0][matrixCols + 1]);
									}

									/**********************************************
									 * Second story:if the first letter from the
									 * chunk is in the end of the row proceed*
									 * with code below.. *
									 ********************************************** 
									 **/
								} else if (matrixRows == playfairMatrix[0].length - 1) {

									// Here check if the first letter is in the
									// end of matrix.This is special case!
									if (matrixCols == playfairMatrix.length - 1) {

										// Here is special case,and must takes
										// the first letter in the matrix.
										encryptedSentence
												.append(playfairMatrix[0][0]);

										// Takes the last letter of the matrix.
										encryptedSentence
												.append(playfairMatrix[tempBRows + 1][matrixCols]);

									} else {

										// Here takes the first letter of the
										// next column.
										encryptedSentence
												.append(playfairMatrix[0][matrixCols + 1]);

										// Here takes the last element of the
										// column.
										encryptedSentence
												.append(playfairMatrix[tempBRows + 1][matrixCols]);
									}

								} else {

									// Here is no special cases.
									// Just takes the letters shifted with one
									// position below.
									encryptedSentence
											.append(playfairMatrix[matrixRows + 1][matrixCols]);
									encryptedSentence
											.append(playfairMatrix[tempBRows + 1][matrixCols]);

								}
							}
						}

						// Check if diagrams forms square!
						for (int squareRows = 0; squareRows < playfairMatrix.length; squareRows++) {
							for (int squareCols = 0; squareCols < playfairMatrix[0].length; squareCols++) {

								if (tempB == playfairMatrix[squareRows][squareCols]) {
									// Here check,if the letters from
									// "sentenceArr" are
									// on different rows and columns,and start
									// logic
									// for encrypting of needed letters.
									if ((matrixRows != squareRows)
											&& (squareCols != matrixCols)) {
										encryptedSentence
												.append(playfairMatrix[matrixRows][squareCols]);
										encryptedSentence
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
		System.out.println(encryptedSentence);

	}
}

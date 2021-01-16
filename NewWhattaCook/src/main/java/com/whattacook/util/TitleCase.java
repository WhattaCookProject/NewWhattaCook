package com.whattacook.util;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Class that contains three static and public methods for converting strings
 * into Title Case, the three methods have the same function, but with different
 * approaches. The first and most basic, simply receives as parameters the
 * string to be converted. The second receives as parameters the string to be
 * converted and an array of exceptions to be ignored in the conversion. And
 * finally, the third method is the same as the second, except that it ignores
 * capitalized words, such as acronyms. Another way to set up global exceptions
 * is to create an instance of the class and thus use its two public methods,
 * which ignore exceptions or capitalized words.
 * 
 * @author FaunoGuazina
 *
 **/
public final class TitleCase {

	// PUBLIC STATIC METHODS FOR USE ----------------------- PUBLIC STATIC METHODS FOR USE //

	/**
	 * Public static method that processes the string to be converted: eliminates
	 * any space characters that may be at the beginning and at the end, then
	 * removes any double spacing, after all proceed with the conversion into Title
	 * Case.
	 * 
	 * @param toConvert : the string sentence that will be converted.
	 * @return The same string sentence converted to a Title Case.
	 */
	public static String all(String toConvert) {

		setExceptions();

		setCapitalWord(false);

		return toTitleSentence(toConvert);
	}

	/**
	 * Public static method that processes the string to be converted: eliminates
	 * any space characters that may be at the beginning and at the end, then
	 * removes any double spacing, if have any exceptions set to be ignored, after
	 * all send everything to conversion.
	 * 
	 * @param toConvert  : the string sentence that will be converted.
	 * @param exceptions : array of strings with all words that will be ignored when
	 *                   converting OR words strings separated by a comma : "word1",
	 *                   "word2", "word3"...
	 * @return the same string sentence converted to a Title Case.
	 */
	public static String withExceptions(String toConvert, String... exceptions) {

		setExceptions(exceptions);

		setCapitalWord(false);

		return toTitleSentence(toConvert);
	}

	/**
	 * Public static method that processes the string to be converted: eliminates
	 * any space characters that may be at the beginning and at the end, then
	 * removes any double spacing, if have any exceptions set to be ignored, after
	 * all send everything to conversion. This method ignores any word that is fully
	 * capitalized, if the whole sentence is not all in upper case, in this case it
	 * is necessary to configure the exceptions that will be ignored.
	 * 
	 * @param toConvert  : the string sentence that will be converted.
	 * @param exceptions : array of all words that will be ignored when converting
	 *                   OR words strings separated by a comma : "word1", "word2",
	 *                   "word3"...
	 * @return the same string sentence converted to a Title Case.
	 */
	public static String withCAPITAL(String toConvert, String... exceptions) {

		setExceptions(exceptions);

		setCapitalWord(!toConvert.matches(UPPER_SENTENCE));

		return toTitleSentence(toConvert);
	}

	// PUBLIC INSTANCE METHODS FOR USE ----------------------- PUBLIC INSTANCE METHODS FOR USE //

	/**
	 * Constructor that allows you to create an instance of TitleCase class and thus
	 * configure the global exceptions that will be used in your methods. This
	 * method has Varargs as a parameter and therefore the way of passing multiple
	 * words is in the form of strings separated by a comma or an array of strings.
	 * 
	 * @param exceptions : array of all words that will be ignored when converting
	 *                   OR strings separated by a comma : "word1", "word2",
	 *                   "word3"...
	 */
	public TitleCase(String... exceptions) {
		setExceptions(exceptions);
	}

	/**
	 * Public instance method that processes the string to be converted: eliminates
	 * any space characters that may be at the beginning and at the end, then
	 * removes any double spacing, use instance global exceptions to set the words
	 * to be ignored, after all send everything to conversion.
	 * 
	 * @param toConvert : the string sentence that will be converted.
	 * @return the same string sentence converted to a Title Case.
	 */
	public String titleCase(String toConvert) {

		setCapitalWord(false);

		return toTitleSentence(toConvert);
	}

	/**
	 * Public instance method that processes the string to be converted: eliminates
	 * any space characters that may be at the beginning and at the end, then
	 * removes any double spacing, use global instance exceptions to set the words
	 * to be ignored, after all send everything to conversion. This method ignores
	 * any word that is fully capitalized, if the whole sentence is not all in upper
	 * case, in this case it is necessary to configure the exceptions that will be
	 * ignored.
	 * 
	 * @param toConvert : the string sentence that will be converted.
	 * @return the same string sentence converted to a Title Case.
	 */
	public String titleCAPITAL(String toConvert) {

		setCapitalWord(!toConvert.matches(UPPER_SENTENCE));

		return toTitleSentence(toConvert);
	}

	// PRIVATE METHODS FOR INTERNAL USE ----------------------- PRIVATE METHODS FOR INTERNAL USE //

	/**
	 * Private method that processes the string sentence to be converted: first
	 * eliminates any space characters that may be at the beginning and at the end,
	 * then removes any double spacing, then split the string from its spaces in an
	 * array of words. For each one of them proceeds with the verification of your
	 * specific case, then if {@code capitalWord} is true will check if every word
	 * matches with a fully capitalized word, if so it does not convert into a Title
	 * Case.
	 * 
	 * @param toConvert : the string sentence that will be converted.
	 * 
	 * @return the same string sentence converted to a Title Case.
	 */
	private static String toTitleSentence(String toConvert) {

		setTheInput(toConvert);

		setArrayOfConvertedWords();

		if (hasExceptions())
			rateFirstWordOfArray();

		return String.join(" ", arrayWords);
	}

	/**
	 * Private auxiliary method that prepares the string that will be converted.
	 * First eliminate spaces that may exist at the beginning and at the end
	 * (TRIM), then replace any multiple space with single spaces.
	 * 
	 * @param input string to be converted
	 */
	private static void setTheInput(String input) {
		TitleCase.theInput = input.trim().replaceAll("( )+", " ");
	}

	/**
	 * Private auxiliary method that break theInput to be converted into an array by
	 * split in spaces, then put that into a stream, then map to convert and collect
	 * a new array. If there are exceptions, the first word may be one and thus
	 * ensures that it is in title case
	 */
	private static void setArrayOfConvertedWords() {
		TitleCase.arrayWords = Arrays.stream(theInput.split(" "))
				.map(TitleCase::rateWords).toArray(String[]::new);
	}

	/**
	 * Private auxiliary method that checks whether the word fits the exception rule
	 * or whether it should be converted to TitleCase, depending on the case invokes
	 * relevant auxiliary methods
	 * 
	 * @param word string each word in array
	 * @return string treated word (converted if is the case)
	 */
	private static String rateWords(String word) {
		return isExceptions(word) ? rateExceptions(word) : toTitleCase(word);
	}

	/**
	 * Private auxiliary method that checks whether the word is an exception or not.
	 * Compare the word with the list of exceptions ignoring the Case or if
	 * capitalWord is true check if the word is capitalized
	 * 
	 * @param word string each word in array
	 * @return boolean false if it doesn't match any type of exception or true if it
	 *         matches
	 */
	private static boolean isExceptions(String word) {
		boolean isException = Arrays.stream(exceptions.split(" "))
				.parallel().anyMatch(w -> w.equalsIgnoreCase(word));
		boolean isCapitalWord = capitalWord && word.matches(CAPITAL_WORD);
		return isException || isCapitalWord;
	}

	/**
	 * Private auxiliary method that checks if the word exists identically among the
	 * exceptions, if negative it returns all lower case.
	 * 
	 * @param word string each word in array
	 * @return returns the same string if it is an exception with Case variation or
	 *         returns all lower case if it is a word that needs to be ignored when
	 *         converting.
	 */
	private static String rateExceptions(String word) {
		boolean isException = Arrays.stream(exceptions.split(" "))
				.parallel().anyMatch(w -> w.equals(word));
		return capitalWord || isException ? word : word.toLowerCase();
	}

	/**
	 * Private auxiliary method that checks if there are exceptions, simply to
	 * improve readability
	 * 
	 * @return boolean true if there are and false if not.
	 */
	private static boolean hasExceptions() {
		return !TitleCase.exceptions.isEmpty();
	}

	/**
	 * Private auxiliary method that converts the first word of the array to title
	 * case if necessary.
	 */
	private static void rateFirstWordOfArray() {
		boolean isFirstWordNonCapital = capitalWord && !arrayWords[0].matches(CAPITAL_WORD);
		if (!capitalWord || isFirstWordNonCapital) {
			arrayWords[0] = toTitleCase(arrayWords[0]);
		}
	}

	/**
	 * Private method that really does the heavy lifting of this utility class, it
	 * checks if the word is made up of letters only or has special characters, for
	 * each case proceeds with the conversion in a different way.
	 * 
	 * @param word the string word itself that will be converted
	 * @return a string word converted to a Title Case
	 */
	private static String toTitleCase(String word) {
		return (word.matches(JUST_LETTERS)) ? titleJustLetters(word) : titleWithPuncts(word);
	}

	/**
	 * Private auxiliary method, if the word is only letters proceed with the
	 * simplest conversion using substring.
	 * 
	 * @param word the string word itself that will be converted
	 * @return a string word converted to a Title Case
	 */
	private static String titleJustLetters(String word) {
		return word.substring(0, 1).toUpperCase().concat(word.substring(1).toLowerCase());
	}

	/**
	 * Private auxiliary method that create a new Builder instance and add each
	 * converted character then return it as a string
	 * 
	 * @param word the string word itself that will be converted
	 * @return a string word converted to a Title Case
	 */
	private static String titleWithPuncts(String word) {
		builder = new StringBuilder();
		Arrays.stream(word.split("")).forEach(TitleCase::append);
		return builder.toString();
	}

	/**
	 * Private auxiliary method which adds to the Builder each character converted
	 * from the word
	 * 
	 * @param letter the character to be append in builder
	 */
	private static void append(String letter) {
		builder.append(lowOrUp(letter));
	}

	/**
	 * Private auxiliary method, which evaluates each letter needs to be capitalized
	 * or not.
	 * 
	 * @param letter the character in question that is being analyzed
	 * @return the same character in upper or lower case
	 */
	private static String lowOrUp(String letter) {
		return conditionToUpper() ? letter.toUpperCase() : letter.toLowerCase();
	}

	/**
	 * Private auxiliary method, verify if is the first letter of the word, if there
	 * was a space before or if there were any of these chars before:
	 * !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
	 * 
	 * @return boolean true if it is the first letter of the word or if it has a
	 *         special char, false if not.
	 */
	private static boolean conditionToUpper() {
		if (builder.length() == 0)
			return true;
		String lastCharOnBuilder = String.valueOf(builder.charAt(builder.length() - 1));
		return lastCharOnBuilder.matches("\\p{Punct}|\\s");
	}

	/**
	 * method that configures whether the conversion will ignore capitalized words
	 * or convert them too
	 * 
	 * @param capitalWord boolean true to ignore words capitalized on conversion and
	 *                    false to convert them too
	 */
	private static void setCapitalWord(boolean capitalWord) {
		TitleCase.capitalWord = capitalWord;
	}

	/**
	 * Method that configures exceptions, that is, the words that will be ignored
	 * when converting to TitleCase. This method has Varargs as a parameter and
	 * therefore the way of passing multiple words is in the form of strings
	 * separated by a comma or an array of strings.
	 * 
	 * @param words : Varargs whith de words to be ignored
	 * @return If set previously string with exceptions separated by space, is
	 *         equivalent to: "word1 word2 word3 ", otherwise an empty string "". It
	 *         also adds versions with these !,./\:;? grammatical points after each
	 *         word and versions in between ()[]{}"'`.
	 */
	private static void setExceptions(String... words) {

		TitleCase.exceptions = Arrays.stream(words).parallel()
				.map(TitleCase::concatAllVariations)
				.collect(Collectors.joining(" "));
	}

	// Concat all variations possibles whith symbol library
	private static String concatAllVariations(String word) {
		return word.concat(whithSymbols(word)).concat(whithWraps(word));
	}
	
	private static String whithSymbols(String word) {
		return Arrays.stream(SYMBOLS.split("")).parallel()
				.map(s -> String.format(" %s%s %s%s%s %s%s", s, word, s, word, s, word, s))
				.collect(Collectors.joining());
	}
	
	private static String whithWraps(String word) {
		builder = new StringBuilder();
		for (int i = 0; i < RIGHT_WRAPS.length; i++) {
			builder.append(String.format(" %s%s%s", RIGHT_WRAPS[i], word, LEFT_WRAPS[i]));
		}
		return builder.toString();
	}
	
	// set of static variables used by this utility class
	private static String exceptions;
	private static boolean capitalWord;
	private static String theInput;
	private static String [] arrayWords;
	private static StringBuilder builder;
	private static final String SYMBOLS = "¡!¿?⸘‽“”‘’‛‟.,‚„'\"′″´˝^°¸˛¨`˙˚ªº…:;&_¯§#¶†‡@%‰‱¦|/\\ˉˆ˘ˇ~*‼︎⁇⁈⁉︎❛❜❝❞❢❣❡"
			+ "$€¥¢£₽₨₩฿₺₮₱₭₴₦৲৳૱௹﷼₹₲₪₡₫៛₵₢₸₤₳₥₠₣₰₧₯₶₷®©℗™℠№ªº℔℥ℨℬℊµℹ︎ℌ℞ℳ℃℉℀℁℅℆+×=∞ℇ∀∁∃∄∅∆∇<>≤≥∑⨋√∛∜∬-";
	private static final String [] RIGHT_WRAPS = "‹«<⟨⟪❨(❮{[〔【〖❪❴❲❬⎧⎡⎨⎜⎢⎪⎣⎝⎩¡¿⸘“‘".split("");
	private static final String [] LEFT_WRAPS = "›»>⟩⟫❩)❯}]〕】〗❫❵❳❭⎫⎤⎬⎟⎥⎪⎦⎠⎭!?‽”’".split("");
	private static final String JUST_LETTERS = "^[\\p{L}]+$";
	private static final String CAPITAL_WORD = "^[\\p{Lu}\\p{Punct}\\p{N}]*$";
	private static final String UPPER_SENTENCE = "^\\s*[\\p{Lu}\\p{Punct}\\p{N}]+\\s*$";

}


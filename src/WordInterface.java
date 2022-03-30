
/**
 *
 * this is an interface that holds all methods needed to implement the Word class
 *
 * @author Saber Elsayed
 * @version Jan 2022
 */
public interface WordInterface {

    /**
     * sets a word object
     *
     * @param word a word object
     */
    public void setWord(String word);

    /**
     * sets and returns the word
     *
     * @return a word object
     */
    public String getWord();

    /**
     * converts a letter to its phone digit equivalent (see the picture provided
     * on page 1). This method works only on a digit or alphanumeric letter,
     * i.e., “\” is not a correct letter to get its digit. You could choose to
     * do this just with code, or supported by a suitable helper table.
     *
     * @param letter is of type char
     * @return phone digit
     */
    public char getDigit(char letter);

    /**
     * sets the phone number that corresponds to the word. You will need to use
     * the getDigit()method you created above. Remember, this method should
     * recognize numbers that correspond to words and digits, e.g., PIZZA2GO
     * (74992246).
     *
     * @param word a word object
     */
    public void setWordToNumber(String word);

    /**
     * gets the corresponding number (as a String) of a given word object
     *
     * @return the corresponding number (as a String) of a given word object
     */
    public String getWordToNumber();

    /**
     * gets a string representation of a word object
     *
     * @return a string representation of a word object
     */
    @Override
    public String toString();

}

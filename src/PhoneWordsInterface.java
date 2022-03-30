import java.util.List;

/**
 * this is an interface that holds the basic methods needed to implement the
 * PhoneWords class
 *
 * @author Saber Elsayed
 * @version Jan 2022
 */
public interface PhoneWordsInterface {

    /**
     * Add another word to the list if does not exist
     *
     * @param w word to add
     */
    public void addWord(String w);

    /**
     * check if word is known or not
     *
     * @param word word to check
     * @return true or false if word is known
     */
    public boolean isKnown(Word word);

    /**
     * returns how many words in the collection
     *
     * @return number of words in the collection
     */
    public int getNumWords();

    /**
     * true if we want the number to be sorted, false otherwise
     *
     * @param numSortedStatus true or false
     */
    public void setNumSorted(boolean numSortedStatus);

    /**
     *
     * @return true if we want the number to be sorted, false otherwise
     */
    public boolean isNumSorted();

    /**
     * lookup words matching supplied number; you may need to comment this
     * method to run task 2
     *
     * @param num telephone number want word for
     * @return a list of words matching supplied number or null if none
     */
    public List<String> listWords(String num) throws InvalidNumberException;

    /**
     * sorts a number passed as a string.
     *
     * @param num to be sorted
     * @return number sorted as a string
     */
    public String sortNum(String num);
}


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * Given a collection of words, you can convert any word to its equivalent phone
 * number, or phone number into a list of possible valid words. It accepts
 * either reading words from words.txt and/or adding new words
 *
 * @author Saber Elsayed
 * @version Jan 2022
 * @see Word
 */
public class PhoneWords implements PhoneWordsInterface {

    /** if yes, sort the phone number, before finding its equivalent word(s)*/
    private boolean numSorted;
    /** if yes, sort the phone number and remove redundant digits, before finding
     * its equivalent word(s) */
    private boolean numUniqueSorted;
    private List<Word> PhoneWordList;


    /** PhoneWords
     * construct a new phoneWord object
     * @param isWordsFileProvided yes, if we need to read words from words.txt,
     * otherwise no
     */
    public PhoneWords(boolean isWordsFileProvided) {

        numSorted = false;
        numUniqueSorted=false;
        this.PhoneWordList = new ArrayList<Word>();
        if (isWordsFileProvided) {
            loadDict("C:\\Users\\sammy\\IdeaProjects\\Lab1DSR\\src\\words.txt", this); //remember to change to words.txt
        }
    }

    /**  addWord
     *Adds word to PhoneWordList if it is not contained
     *
     * @param w word
     * @return void
     */

    public void addWord(String w){      //method responsible for adding word to PhoneWordList if not already
        Word NWord = new Word(w);  // creates new word as a word object

        if(isKnown(NWord)== false){    //if the word is not contained in the phone word list, it will be added
            PhoneWordList.add(NWord); //adding of new word (NWord)
        }
    }

    /**  isKnown
     *Checks if word is contained in PhoneWordList
     *
     * @param word word
     * @return Boolean
     */

    public boolean isKnown(Word word){          //checks if current word is already contained in PhoneWordList

        Boolean wordKnown = false;              //wordKnown is set too false to assume the word is not known
        for (Word w: PhoneWordList){
            if (word.getWord().equals(w.getWord())) {       //if the word is exists in the PhoneWordList, wordKnown is set to true.
                wordKnown = true;
                break;
            }
        }

        return wordKnown; //the result is returned to be used in other methods.
        }

    /** getNumWords
     *returns the number of elements in PhoneWordList
     *
     * @return int number of words in PhoneWordList
     */

    public int getNumWords(){           //returns the number of elements contained in the PhoneWordList
        return PhoneWordList.size();    //uses .size() to return the size of PhoneWordList
    }

    /**  setNumSorted
     * sets the value of NumsSorted
     *
     * @param boolean numSortedStatus
     * @return void
     */
    public void setNumSorted(boolean numSortedStatus) {     //sets numSorted to numSortedStatus
        this.numSorted = numSortedStatus;

    }

    /**  isNumSorted
     * returns NumsSorted
     *
     * @return numSorted
     */
 
    public boolean isNumSorted() {      //Returns the boolean numSorted
        return numSorted;
    }

    /**  Invalidnum
     * checks if num is of a valid format to be sorted and used
     *
     *@param String num
     * @return boolean correctform
     */

    public Boolean InvalidNum(String num){      //This checks the validity of the given num
        int LeftBrac = 0;       //Counts the number of left brackets
        int RightBrac = 0;      //Counts the number of right brackets
        int count = 0;      //is a position holder to determine left bracket validity
        int Plus = 0;           //counts the number of plus's in the number
        Boolean correctform = true;      //boolean variable that determines if the num is in correct form
        for (char ch: num.toCharArray()){           //for loop iterates through the num as an array

            if (ch == '(') LeftBrac++;
            if (LeftBrac>1) {correctform = false; break;}       // if there is more than one leftbrac, correctform is false
            if (ch == ')') RightBrac++;
            if (RightBrac>1) {correctform = false; break;}          // if there is more than one rightbrac, correct form is false
            if ((LeftBrac==1)&&(count!=0)) {correctform = false; break;}        // if the left bracket is not in the first position in num but is contained later,
                                                                                // then correct form is false
            if (LeftBrac == 0) {count = count++;}       //used to ensure left bracket, if contained in num, is in first position
            if (ch == '+') Plus++;
            if (Plus>1) {correctform = false; break;}   //if more than one "+" is contained, correct form is false

        }
        if (LeftBrac != RightBrac) {correctform = false; }      //if one form of bracket is contained and not a complimentary one, correct form is false
        return correctform;         // returns corresponding boolean correct form value to be used

    }

    /**  checkNum
     * checks if num is valid then converts it integer only format
     *
     *@param String num
     * @return string num
     */

    public String checkNum(String num) throws InvalidNumberException {      //checks if num is in a usable form to sort
        num.replaceAll("\\s",""); // removes all whitespaces and replaces with nothing

        if (InvalidNum(num)) {          // if number is in correct form, the if loop will replace plus's and brackets
            if (num.matches("^\\(\\+\\)[0-9]*$|")) {
                num.replaceAll("\\+", "00");            //replaces plus's with 00 (correct phone number form)
            }
            if (num.matches("^\\(\\d\\d\\)[0-9]*$\"")) {
                num.replaceAll("[^()]", "");        //Replace brackets with nothing to ensure can be sorted
            }
            return num;         //returns adjusted num to be used
        }
        else throw new InvalidNumberException (num);      //throws exception for num is not correct form

    }

    /**  ListWords
     * checks if num is valid then checks if it matches any elements contained in PhoneWordList
     *
     *@param String num
     * @return List BothList
     */

    public List<String> listWords(String num) throws InvalidNumberException {
        sortNum(checkNum(num));  // checks the num is valid then sorts num
        if (this.numSorted){
            List BothList= new ArrayList();     //creates a new arrayList
            for (Word word: PhoneWordList){
                if (num.equals(word.getWordToNumber())){        //if num is contained in the PhoneWordList, it is added to the BothList
                    BothList.add(word.getWord());
                }
            }
            return BothList;                // returns the list containing crossover num
        } else throw new InvalidNumberException (num);          //If not sorted correctly throw exception

    }

    /**  sortNum
     * sorts elements in num in ascending order
     *
     *@param String num
     * @return string sortedNumStringBuilder
     */

    public String sortNum(String num) {         //This method sorts num in ascending order
        String [] stringArray = num.split("");      //converts num to a string array
        int size = stringArray.length;      //calculates length of string array
        int [] intArray = new int [size];       //create int array of the correct size
        for(int i=0; i<size; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);         //iterates through the string array and places the value as an int in the int array
        }
        Arrays.sort(intArray);          // sorts the int array in ascending order
        StringBuilder sortedNumStringBuilder = new StringBuilder();  // iterates through intList and return the sorted num String
        for(int i: intArray){
            sortedNumStringBuilder.append(i);       //iterates through the int array and appends them to the sortednumstringbuilder
        }
        numSorted = true;           //sets numsorted to true so in other methods it is identified as sorted
        return sortedNumStringBuilder.toString();       //returns the sorted num
    }

    /**  printAll
     * prints the words and equivalent numbers from PhoneWordList
     *
     * @return void
     */

    public void printAll(){         // a test method responsible for printing the phonewordlist
        for (Word w: PhoneWordList){                //for every word in the PhoneWordList, the word and its corrisponding number is printed
            System.out.println(w.getWord());
            System.out.println(w.getWordToNumber());
        }
    }


    /**
     * read and process all the words in the words file and add them to the
     * PhoneWords object
     *
     * @param name of the file being indexed
     * @param pw PhoneWords object to add words to
     */
    private static void loadDict(String name, PhoneWords pw) {
        Scanner dictIn;                      // words file scanner
        // open named words file, then read & add words to pw
        try {
            dictIn = new Scanner(new File(name));
            while (dictIn.hasNextLine()) {
                String word = dictIn.nextLine();
                // add next word from words file, catching any errors
                try {
                    pw.addWord(word);
                } catch (IllegalArgumentException iae) {
                    System.out.println("addWord failed: " + iae.getMessage());
                }
            }
        } // handle file I/O exceptions
        catch (FileNotFoundException fnfe) {
            System.err.println("Error opening words file: " + fnfe);
            System.exit(100);
        } catch (IOException e) {
            System.err.println("Error reading words file: " + e);
            System.exit(100);
        }
    }

}


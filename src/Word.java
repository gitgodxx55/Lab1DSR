import java.util.*;


public class Word implements WordInterface{

    private String word;
    private String wordToNumber;





    /** Constructor for objects of class Word */
    public Word(String word)
    {
        StringBuilder builder = new StringBuilder();            // iterates through char array of word and adds digits and letters to stringbuilder then sets this.word to the builder.toString

        for (char ch: word.toCharArray()){
            if(Character.isDigit(ch) || Character.isLetter(ch)) builder.append(ch);
        }
        this.word = builder.toString().toLowerCase();       //ensures that the to.string is in lower case to fit required format
        //this.word = (word.replaceAll("\\s+|[^a-zA-Z0-9]","").toLowerCase()); // removes whitespaces and all non alphanumeric characters and converts to lower case
        setWordToNumber(this.word);         //sets current word to number using setWordToNumber method
    }

    /**  setWord
     *setWord is used to set the word
     * @param String word
     * @return   void
     */
    public void setWord(String word) //responsible for setting the word and removing whitespace/converting to lowercase
    {
        StringBuilder builder = new StringBuilder();            // iterates through char array of word and adds digits and letters to stringbuilder then sets this.word to the builder.toString
        for (char ch: word.toCharArray()){
            if(Character.isDigit(ch) || Character.isLetter(ch)) builder.append(ch);
        }
        this.word = builder.toString().toLowerCase();        //ensures that the to.string is in lower case to fit required format

        setWordToNumber(this.word); //sets current word to number using setWordToNumber method

    }

    /**  getWord
     * getWord is used to get the word
     * @param void
     * @return   String word
     */
    public String getWord()
    {
        return word;
    }   //This returns the given word as set in setWord method

    /**  getDigit
     *converts a letter to its phone digit equivalent
     * @param char letter
     * @return char digit
     */

    public char getDigit(char letter)       //This converts the given character to its relative number
    {

        char result_num;  //This is used to hold the converted value
        switch(letter){  // This switch is used to convert the letter in the words.txt to the corresponding number
            //as given by the original phone image

            case 'a': case 'b':case 'c':result_num = '2'; break;
            case 'd': case 'e':case 'f':result_num = '3'; break;
            case 'g': case 'h':case 'i':result_num = '4'; break;
            case 'j': case 'k':case 'l':result_num = '5'; break;
            case 'm': case 'n':case 'o':result_num = '6'; break;
            case 'p': case 'q':case 'r':case 's': result_num = '7'; break;
            case 't': case 'u':case 'v':result_num = '8'; break;
            case 'w': case 'x':case 'y':case 'z': result_num = '9'; break;
            default:result_num = letter;   // This sets the resulting number to the original letter character
        }
        return result_num; //returns the resulting number to be used
    }
    /**  setWordToNumber
     *converts a word to its phone digit equivalent
     * @param String word
     * @return void
     */
    public void setWordToNumber(String newWord){

        StringBuilder builder1 = new StringBuilder();            // iterates through char array of word and adds digits and letters to stringbuilder then sets this.word to the builder.toString
        for (char ch: word.toCharArray()){
            if(Character.isDigit(ch) || Character.isLetter(ch)) builder1.append(ch);
        }
        this.word = builder1.toString().toLowerCase();        //ensures that the to.string is in lower case to fit required format

        char[] temp = word.toCharArray();               //uses temp to act as variable for current word
        new ArrayList<>(Arrays.asList(temp));       //creates new list using the temporary variable(temp)
        StringBuilder builder = new StringBuilder(word.length());
        for (char ch: temp){
            builder.append(getDigit(ch));       // iterates through temp and appends each character to the builder
        }
        this.wordToNumber = builder.toString();         //creates string representation of the given wordToNumber
    }


    /**  getWordToNumber
     *retrieves word to number to be used
     * @param String word
     * @return void
     */
    public String getWordToNumber(){
        return wordToNumber;
    } //returns wordToNumber


    /**  toString
     * returns a string displaying the word and equivalent number
     * @param void
     * @return String
     */
    public String toString(){
        return ("Word: " + word + "Num: " + wordToNumber); //displays visually the word and its equivalent number
    }
}

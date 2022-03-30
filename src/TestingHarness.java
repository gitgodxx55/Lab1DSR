import java.util.*;

public class TestingHarness{
    public void testWord(Word testWord,String ExpectedWord, String ExpectedWordToNum){
        if((testWord.getWord().equals(ExpectedWord)) && (testWord.getWordToNumber().equals(ExpectedWordToNum))){
            System.out.println("TEST PASSED");
        }
        else System.out.println("TEST FAILED");
        System.out.println(testWord.getWord());
    }


    public void testPhoneWordsIsKnown(PhoneWords testPhoneWords, Word testIsKnown, boolean expectedIsKnown){
        if(testPhoneWords.isKnown(testIsKnown) == expectedIsKnown){
            System.out.println("TEST PASSED");
        }
        else System.out.println("TEST FAILED");
    }
    public void testPhoneWordsGetNumWords(PhoneWords testPhoneWords, int expectedGetNumWords){
        if (testPhoneWords.getNumWords() == expectedGetNumWords){
            System.out.println("TEST PASSED");
        }
        else System.out.println("TEST FAILED");
    }



    public static void main(String[] args){
        //Word Tests
        TestingHarness test = new TestingHarness();
        System.out.println("Starting Word Tests...");

        Word Word1 = new Word("abcdefghijklmnopqrstuvwxyz");    // all lower case
        Word Word2 = new Word("ABCDEFGHIJKLMNOPQRSTUVWXYZ");    //all upper case
        Word Word3 = new Word("A BCD EFGH IJKL MN OPQ RST UVWX YZ");    //spaces
        Word Word4 = new Word("");    //empty strin
        Word Word5 = new Word("t35t1ng!");
        System.out.println("TEST 1 abcdefghijklmnopqrstuvwxyz");
        test.testWord(Word1, "abcdefghijklmnopqrstuvwxyz", "22233344455566677778889999");
        System.out.println("TEST 2 ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        test.testWord(Word2, "abcdefghijklmnopqrstuvwxyz", "22233344455566677778889999");
        System.out.println("TEST 3 A BCD EFGH IJKL MN OPQ RST UVWX YZ");
        test.testWord(Word3, "abcdefghijklmnopqrstuvwxyz", "22233344455566677778889999");
        System.out.println("TEST 4 Empty String");
        test.testWord(Word4, "", "");
        System.out.println("TEST 5 T35T1NG");
        test.testWord(Word5, "t35t1ng", "8358164");

        System.out.println("Word PASSED");

        System.out.println("Starting PhoneWords Tests...");

        long startTime = System.nanoTime();
        PhoneWords wordListTest = new PhoneWords(true);
        long endTime = System.nanoTime();
        System.out.println("Time taken to add words.txt " + (endTime - startTime)/1000000);    // given in ms

        PhoneWords case1 = new PhoneWords(false); // localCase
        System.out.println("Case1 isKnown");
        test.testPhoneWordsIsKnown(case1,Word1,false);
        System.out.println("Case1 getNumWords");
        test.testPhoneWordsGetNumWords(case1, 0);


        case1.addWord(""); //new empty string
        case1.addWord("abcdefghijklmnopqrstuvwxyz"); // alphabet
        case1.addWord("ABCDEFGHIJKLMNOPQRSTUVWXYZ"); // capital alphabet should be picked up by isKnown
        case1.addWord("A BCD EFGH IJKL MN OPQ RST UVWX YZ"); // spaces should be picked up by isKnown
        case1.addWord("T35T1NG"); //letters and numbers

        System.out.println(case1.getNumWords());
        case1.printAll();

        //expected list length of 3 consisting of <"",abcdefghijklmnopqrstuvwxyz,t35t1ng>

        test.testPhoneWordsIsKnown(case1,Word1,true);
        test.testPhoneWordsIsKnown(case1,Word2,true);
        test.testPhoneWordsIsKnown(case1,Word5,true);
        try{
            case1.listWords("+(00)231)");
        }
        catch (InvalidNumberException e){
            System.out.println("caught");
        }

        System.out.println(case1.sortNum("9237236"));




        System.out.println("Phone Words PASSED");


    }



}
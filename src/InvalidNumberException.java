public class InvalidNumberException  extends Exception{
    private String invalidNum;

    public InvalidNumberException(String invalidNum){
        this.invalidNum = invalidNum;
    }
    public String getMalPhoneNum(){
        return invalidNum;
    }

}

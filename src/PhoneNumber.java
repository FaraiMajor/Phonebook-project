
import java.io.File;
import java.util.Scanner;

public class PhoneNumber {
    private String number;

    public PhoneNumber(String number){
        this.number = number;
    }
    public String getAreaCode(){
        String areaCode = this.number.substring(1,4);
        return areaCode;
    }
    public String getExchange(){
        String exchange = number.substring(5,8);
                return exchange;
    }
    public String getLineNumber(){
        String lineNumber = this.number.substring(9);
        return lineNumber;
    }
    public boolean isTollFree(){
        if(this.number.charAt(1)== '8')
            return true;
        else
            return false;
    }
    public String toString(){
        return number;
    }
    public boolean equals(PhoneNumber other){
        return number.equals(other.number);
    }

    public static PhoneNumber read(Scanner scanner) {
        if (!scanner.hasNext())
            return null;
        String number = scanner.next();
        return new PhoneNumber(number);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("numbers.text"));
        int count = 0;

        PhoneNumber number = read(scanner);
        PhoneNumber prevNumber = null;

        while(number!=null){
            if(prevNumber!=null && number!=null && prevNumber.equals(number)){
                System.out.println("Duplicate phone number \"" + number + "\" discovered");
            }else{
                System.out.println("phone number: " + number);
                System.out.println("area code: " + number.getAreaCode());
                System.out.println("exchange: " + number.getExchange());
                System.out.println("line number: " + number.getLineNumber());
                System.out.println("is toll free: " + number.isTollFree());
                System.out.println();
            }
            count++;
            prevNumber = number;
            number = read(scanner);

        }
        System.out.println("---");
        System.out.println(count + " phone numbers processed.");
    }
}

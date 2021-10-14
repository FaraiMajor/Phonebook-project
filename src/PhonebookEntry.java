
import java.io.File;
import java.util.Scanner;

public class PhonebookEntry{

    private Name name;
    private PhoneNumber phoneNumber;

    public PhonebookEntry(Name name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    public void call(){
        if(this.phoneNumber.isTollFree()){
            System.out.println("Dialing (toll-free) " + name + ": " + phoneNumber);
        }else{
            System.out.println("Dialing " + name + ": " + phoneNumber);
        }
    }
    public String toString() {
        return name + ": " + phoneNumber;
    }
//    public boolean equals(PhonebookEntry other){
//        return name.equals(other.name) && phoneNumber.equals(other.phoneNumber);
//    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhonebookEntry))
            return false;
        PhonebookEntry other = (PhonebookEntry) o;
        return name.equals(other.name) && phoneNumber.equals(other.phoneNumber);

    }
    public static PhonebookEntry read(Scanner scanner){
        if (!scanner.hasNext()) {
            return null;
        }
        return new PhonebookEntry(Name.read(scanner), PhoneNumber.read(scanner));
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("phonebook.text"));

        int count = 0;
        PhonebookEntry pbookEntry = read(scanner);
        PhonebookEntry pbPrevEntry = null;

        while(pbookEntry!=null){
            if(pbPrevEntry!=null && pbookEntry.equals(pbPrevEntry)) {
                System.out.println("Duplicate phone book entry \"" + pbookEntry + "\" discovered");
                pbookEntry = read(scanner);
                count++;
            }else
                if(pbPrevEntry!=null && pbookEntry.getName().equals(pbPrevEntry.getName())) {
                System.out.println("Warning duplicate name encountered \"" + pbookEntry.getName() + ": " + pbookEntry.getPhoneNumber() + "\" discovered");
            }else {
                    pbPrevEntry = pbookEntry;
                }
            System.out.println("phone book entry: " + pbookEntry);
            pbookEntry.call();
            System.out.println();
            count++;
            pbookEntry = read(scanner);
        }
        System.out.println("---");
        System.out.println(count + " phonebook entries processed.");
    }
}

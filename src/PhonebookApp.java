import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PhonebookApp {
    public static void main(String[] args) {
        try {

            Map<Name, PhonebookEntry> map = new TreeMap<Name, PhonebookEntry>();

            final String filename = "phonebook.text";

            Scanner keyboard = new Scanner(System.in);
            int numElts = read(filename, map);

            String lookup, prompt = "lookup, quit (l/q)? ";
            if (args.length!=1){
                System.out.print(prompt);}

            lookup = keyboard.next();

            boolean quit = lookup.equalsIgnoreCase("q"); // boolean declared to use q as a "quit " command

            /*while loop checks the lookup input*/
            while (!(quit)) {
                if (lookup.equalsIgnoreCase("l")) {
                    lastNameLookup(numElts, map, keyboard, lookup);
                }
                System.out.print(prompt);
                lookup = keyboard.next();
                quit = lookup.equalsIgnoreCase("q");
            }
            keyboard.close();
        } catch (FileNotFoundException e) { // Exception is File isn't found
            System.out.print("*** IOException *** phonebook.text(No such file or directory)");

        }
        catch (NoSuchElementException e){
            System.out.println("Usage: PhonebookApp 'phonebook-filename'");
        }
        catch (Exception e) {
            System.out.print("*** Exception *** Phonebook capacity exceeded - increase size of underlying array");
        }
    }

    public static void lastNameLookup(int numElts, Map<Name, PhonebookEntry> map, Scanner keyboard, String lookup) {

        System.out.print("last name? ");
        String lastName = keyboard.next();
        System.out.print("first name? ");
        String firstName = keyboard.next();
        String number = lookup(map, lastName, firstName, numElts);
        if (number.equals("")) {// if number is an empty String, no name is found
            System.out.println("-- Name not found");
        } else {
            System.out.println(firstName + " " + lastName + "'s phone numbers: " + number);
        }
        System.out.println();

    }

    public static String lookup(Map<Name, PhonebookEntry> map, String lastName, String firstName, int size) {
        Name temporary = new Name(lastName, firstName);
        PhonebookEntry temp= map.get(temporary);

        for (Map.Entry<Name, PhonebookEntry> entry : map.entrySet()) {
            if (temporary.equals(entry.getKey())) {
                return "["+temp.toString()+"]";
            }

        }
        return "";
    }

    public static int read(String filename, Map<Name, PhonebookEntry> map) throws Exception {

        Scanner inputFile = new Scanner(new File(filename));
        int size = 0;
        while (inputFile.hasNext()) {

            String lastName = inputFile.next(), firstName = inputFile.next();
            Name name = new Name(lastName, firstName);

            PhonebookEntry pbEntry = new PhonebookEntry(inputFile);
            map.put(name, pbEntry);
            size++;
        }
        inputFile.close();
        return size;
    }

}
class PhonebookEntry {

    private Name name;
    private PhoneNumber phoneNumber;
    private ArrayList<ExtendedPhoneNumber> extendedPN = new ArrayList();

    /*Constructor*/
    public PhonebookEntry(Scanner sc) {
        int count = sc.nextInt();
        for (int i= 0;i < count;i++){
            String description = sc.next(), number = sc.next();
            ExtendedPhoneNumber output = new ExtendedPhoneNumber(description, number);
            extendedPN.add(output);
        }
    }

    /*getName method which returns name variable*/
    public Name getName() {
        return this.name;
    }


    /*getPhoneNumber method which returns phoneNumber variable*/
    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }


    /*toString method*/
    public String toString() {
        String phonebook = "";
        for (int i= 0; i < extendedPN.size() ;i++){
            phonebook += extendedPN.get(i)+", ";

        }
        String newString = phonebook.substring(0, phonebook.length()-2);
        return newString;
    }

}

class Name implements Comparable<Name> {
    private String firstName, lastName;

    // Constructor
    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean equals(Name other) {
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override
    public int compareTo(Name name){

        return name.toString().compareTo(toString());
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    // Read first and last name from the file
    static public Name read(Scanner scanner) {
        // Check if file has next entry
        if(!scanner.hasNext())
            return null;

        String lastName = scanner.next();
        String firstName = scanner.next();
        Name name = new Name(firstName, lastName);

        return name;
    }
}

class PhoneNumber {
    private String phoneNumber;

    // Constructor
    PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        PhoneNumber pNum = (PhoneNumber)obj;
        return this.phoneNumber.equals(pNum.phoneNumber);
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }

}
class ExtendedPhoneNumber extends PhoneNumber {
    private String description;

    // Constructor
    public ExtendedPhoneNumber(String description, String phoneNumber) {
        super(phoneNumber);
        this.description = description;
    }

    // Read extended phone number along with description
    public static ExtendedPhoneNumber read(Scanner scanner) {
        // Check if file has next entry
        if (scanner.hasNext() == false)
            return null;
        String phoneNumber = scanner.next();
        String description = scanner.next();
        return new ExtendedPhoneNumber(description, phoneNumber);
    }


        public String toString() {
            return description + ": " + super.toString();
    }
}


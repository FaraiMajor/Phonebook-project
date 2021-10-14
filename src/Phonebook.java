import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {

        int lookupCount = 0;
        int revLookupCount = 0;
        int phoneEntryCount = 0;
        String input = "";

        try {
            Scanner scanner = new Scanner(new File("phonebook.text"));
            PhonebookEntry phonebookEntry[] = new PhonebookEntry[100];

           while(scanner.hasNext()) {
               phonebookEntry[phoneEntryCount++] = PhonebookEntry.read(scanner);
               if (phoneEntryCount >= 100) {
                   throw new Exception("Phonebook capacity exceeded - increase size of underlying array");
               }
           }
            Scanner inputReader = new Scanner(System.in);

           while (!input.equals("q")) {

                System.out.print("lookup, reverse-lookup, quit (l/r/q)? ");
                input = inputReader.next();
                if (input.equalsIgnoreCase("l")) {
                    lookupCount++;
                    System.out.print("last name? ");
                    String lastName = inputReader.next();
                    System.out.print("first name? ");
                    String firstName = inputReader.next();
                    Name name = new Name(lastName, firstName);

                    boolean x = false;
                    for (int i = 0; i < phoneEntryCount; i++) {
                        PhonebookEntry inputPhoneBook = phonebookEntry[i];
                         if(inputPhoneBook.lookup(name) != null) {
                            System.out.println(inputPhoneBook.getName() + "'s phone number is " + inputPhoneBook.getPhoneNumber());
                             x =true;
                        }
                    }
                    if(!x)
                        System.out.println("-- Name not found");
                    System.out.println();

                } else if (input.equalsIgnoreCase("r")) {
                    revLookupCount++;
                    System.out.print("phone number (nnn-nnn-nnnn)? ");
                    String phone = inputReader.next();
                    PhoneNumber phoneNumber = new PhoneNumber(phone);
                    boolean y = false;
                    for (int i = 0; i < phoneEntryCount; i++) {
                        PhonebookEntry inputPhoneBook = phonebookEntry[i];
                        if (inputPhoneBook.reverseLookup(phoneNumber) != null) {
                            System.out.println(inputPhoneBook.getPhoneNumber() + " belongs to " + inputPhoneBook.getName());
                            y = true;
                        }
                    }
                    if(!y)
                    System.out.println("-- Phone number not found");
                    System.out.println();
                }
            }
            System.out.println();
            System.out.print(lookupCount + " lookups performed\n");
            System.out.print(revLookupCount + " reverse lookups performed\n");
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("*** IOException *** phonebook.text (No such file or directory)");
        } catch (Exception e) {
            System.out.println("*** Exception *** " + e.getMessage());
        }
    }
}
class PhonebookEntry {

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

    public String toString() {
        return name + ": " + phoneNumber;
    }

    public static PhonebookEntry read(Scanner scanner) {
        if (!scanner.hasNext()) {
            return null;
        }
        return new PhonebookEntry(Name.read(scanner), PhoneNumber.read(scanner));
    }
    String lookup(Name name1){
        while(name.equals(name1))
            return phoneNumber.toString();
        return null;

    }
    String reverseLookup(PhoneNumber phoneNumber1){
        while(phoneNumber.equals(phoneNumber1))
            return name.toString();
        return null;
    }
}

class Name {

    private String first, last;
    private String firstName;

    public Name(String last, String first) {
        this.last = last;
        this.first = first;
    }
    public boolean equals(Name other) {
        return first.equals(other.first) && last.equals(other.last);
    }

    public String toString() {
        return first + " " + last;
    }

    public static Name read(Scanner scanner) {
        if (!scanner.hasNext())
            return null;
        String last = scanner.next();
        String first = scanner.next();
        return new Name(last, first);
    }
}

class PhoneNumber {
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return number;
    }

    public boolean equals(PhoneNumber other) {
        return number.equals(other.number);
    }

    public static PhoneNumber read(Scanner scanner) {
        if (!scanner.hasNext())
            return null;
        String number = scanner.next();
        return new PhoneNumber(number);
    }
}





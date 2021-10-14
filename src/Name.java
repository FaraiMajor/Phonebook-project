import java.io.File;
import java.util.Scanner;

class Name {
    public Name(String last, String first) {
        this.last = last;
        this.first = first;

    }
    public Name(String first) {
        this("", first);
    }

    public String getFormal() {return first + " " + last;}
    public String getOfficial() {return last + ", " + first;}

    public boolean equals(Name other) {
        return first.equals(other.first) && last .equals(other.last);
    }

    public String toString() {
        return first + " " + last;
    }

    public String getInitials(){
        String initials = Character.toUpperCase(this.first.charAt(0)) +"."+ Character.toUpperCase(this.last.charAt(0)) + ".";
        return initials;
    }

    public static Name read(Scanner scanner) {
        if (!scanner.hasNext())
            return null;
        String last = scanner.next();
        String first = scanner.next();
        return new Name(last, first);
    }

    private String first, last;

    public static void main(String [] args) throws Exception {
        Scanner scanner = new Scanner(new File("names.text"));

        int count = 0;

        Name name = read(scanner);
        Name nameRead = null;
        while (name != null) {
            if(nameRead!=null && name.equals(nameRead)){
                System.out.println("Duplicate name \"" +name+ "\" discovered");
            }else {
                System.out.println("name: " + name.toString());
                System.out.println("formal: " + name.getFormal());
                System.out.println("official: " + name.getOfficial());
                System.out.println("initials: " + name.getInitials());
                System.out.println();
            }
            nameRead = name;
            count++;
            name = read(scanner);

        }
        System.out.println("---");
        System.out.println(count + " names processed.");
    }
}
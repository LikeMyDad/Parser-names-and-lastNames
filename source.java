import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    Method "password" generate password with random symbols.
    Standard value of length password = 8. If you want to long or short password,
    u might change lengthOfPassword below...
    */

    public static String password() {
        String pattern = "[a-zA-Z_0-9]";
        String pass = "";
        int lengthOfPassword = 0;
        while (true) {
            int randomChar = (int) (Math.random() * 122);
            char rand = (char) randomChar;
            if (lengthOfPassword == 8) { // change here!
                break;
            }
            if (String.valueOf(rand).matches(pattern)) {
                pass += String.valueOf(rand);
                lengthOfPassword++;
            }
        }
        if (pass.contains("_")) {
            pass = pass.replace("_", "a");
        }
        return pass;
    }


    public static String date() {
        int day = 1 + (int) (Math.random() * 30);
        int month = 1 + (int) (Math.random() * 11);
        int year = 1 + (int) ((Math.random() * (2000 - 1989)) + 1989); // from 1989 to 2000
        String date = day + ":" + month + ":" + year; // example: 29:01:2000
        return date;
    }



    public static void main(String[] args) throws IOException {
        ArrayList<String> listOfNames = new ArrayList<>(); // create empty array of names
        ArrayList<String> listOfLastNames = new ArrayList<>(); // create empty array of last names;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Please, write file direction of names, example: \"D:\\\\filename.txt\"");
        String catalogNames = scanner.nextLine();
        System.out.println("Please, write file direction of last names, example: \"D:\\\\filename2.txt\"");
        String catalogFam = scanner.nextLine();
        System.out.println("Please, write how many lines you need: ");
        int n = scanner.nextInt(); // n - value of lines

        String token = ""; // Temp value. Need to save name or last name while scanner read all lines of text file. Dont change it here and below!
        String text = ""; // empty line

        FileWriter write = new FileWriter("result.txt", true);

        try  {
            File namesFile = new File(catalogNames);
            Scanner scannerFileOfNames = new Scanner(namesFile);
            while(scannerFileOfNames.hasNext()) {
                token = scannerFileOfNames.next(); // save name for add at list of names
                listOfNames.add(token); // add
            }
            File famFile = new File(catalogFam);
            Scanner scannerFileOfFam = new Scanner(famFile);
            while (scannerFileOfFam.hasNext()) {
                token = scannerFileOfFam.nextLine(); // save last name for add at list of last names
                listOfLastNames.add(token); // add
            }
            for (int i = 0; i < n; i++) {
                text = listOfNames.get((int) (Math.random() * listOfNames.size())) + " " + listOfLastNames.get((int) (Math.random() * listOfNames.size())) + "::" +
                        password() + ":" + date() + "\n"; // format: Name LastName::password:date;
                /*
                Code get name/last name from array of names/last name use method .get ( variable.get(index) here index is random value * size of list)
                 */
                write.write(text);
            }

        } catch (IOException e) {
            System.out.println("No file in directory");
        } finally {
            write.close();
        }
    }
}

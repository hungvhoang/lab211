package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {
    private final static Scanner sc = new Scanner(System.in);
    public static String readString(String message){
        System.out.print(message+": ");
        String str;
        str = sc.nextLine().trim();
        return str;
    }
    public static String readByCondition(String message,String pattern){
        String str;
        boolean valid = false;
        do {
            str = readString(message);
            valid = str.matches(pattern);
            if(!valid){
                System.out.println("Wrong format. Enter again");
            }
        }while (!valid);
        return str;
    }
    public static Date readDate(String message, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(1900,1,1);
        boolean valid = false;
        do{
            String str = readString(message);
            try {
                date = dateFormat.parse(str);
                valid = true;
            } catch (ParseException e) {
                System.out.println("Error when parsing. Enter again.");
            }
        }while (!valid);
        return date;
    }

    public static boolean readBoolean(String message){
        String str = readString(message).trim().toUpperCase();
        return str.charAt(0) == 'Y' || str.charAt(0) == 'T' || str.charAt(0) == '1';
    }

    public static int readMenuChoice(String[] options){
        for(int i=0;i<options.length;i++){
            System.out.println((i+1)+". "+options[i]+".");
        }
        int choice = Integer.parseInt(readByCondition("Enter your choice",Validation.INTEGER));
        return choice;
    }
}

package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools{
    private static Scanner sc = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Read an positive integer from user. 
     * @param message
     * @return integer
     */
    public static int inputInt(String message){
        boolean condition =false;
        int num = -1;
        do{
            try{
                System.out.print(message+": ");
                num = Integer.parseInt(sc.nextLine());
                if(num < 0) throw new Exception();
                condition = true;
            }catch(Exception e){
                System.out.println("Invalid! Must be an integer.");
                condition = false;
            }
        }while(!condition);
        return num;
    }

    /**
     * read a postive double from user.
     * @param message
     * @return
     */
    public static double inputDouble(String message){
        boolean condition =false;
        double num = -1;
        do{
            try{
                System.out.print(message+": ");
                num = Double.parseDouble(sc.nextLine());
                if(num < 0) throw new Exception();
                condition = true;
            }catch(Exception e){
                System.out.println("Invalid! The number must greater than 0.");
                condition = false;
            }
        }while(!condition);
        return num;
    }


    public static boolean inputBoolean(String message,char matches){
        System.out.print(message+": ");
        String temp = sc.nextLine();
        char x = temp.trim().toLowerCase().charAt(0);
        return (x == matches);
    }

    /**
     * read a string from user
     * @param message
     * @return
     */
    public static String inputString(String message){
        System.out.print(message+": ");
        String temp=sc.nextLine();
        return temp;
    }


    /**
     * Read a non-blank string from user.
     * @param message
     * @return
     */
    public static String inputNonBlankString(String message){
        String temp = "";
        do {
            System.out.print(message+": ");
            temp = sc.nextLine();
            if(temp.isBlank()) 
                System.out.println("Cannot be empty! Enter again");
        } while (temp.isBlank());
        return temp;
    }

    /**
     * read a string from user by format.
     * @param message
     * @param format
     * @return
     */
    public static String inputFormat(String message, String format){
        String temp = "";
        do {
            System.out.print(message+": ");
            temp = sc.nextLine();
            if(!temp.matches(format))
                System.out.println("Wrong format. Enter again");
        } while (!temp.matches(format));
        return temp;
    }

    /**
     * read date from user using formay dd/mm/yyyy
     * @param message
     * @return
     */
    public static Date inputDate(String message){
        Date date = new Date(1900,1,1);
        boolean condition = false;
        do {
            try {
                System.out.print(message+"(format:dd/mm/yyyy): ");
                String temp = sc.nextLine();
                //if(!temp.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) throw new Exception();
                date = dateFormat.parse(temp);
                condition =true;
            } catch (Exception e) {
                System.out.println("Invalid. Enter again");
                condition = false;
            }
        } while (!condition);
        return date;
    }

    /**
     * auto add 0 before the number
     * Example: prefix: P, lenght: 7, number:7 -> return: P0000007 
     * @param prefix
     * @param lenght
     * @param curNumber
     * @return
     */
    public static String generateCode(String prefix , int lenght,int curNumber){
        String formatStr="%0"+lenght+"d"; //->%07d
        return prefix+String.format(formatStr, curNumber);
    }
    
    public static int drawMenu(String[] options){
        boolean condition =false;
        int choice = 0;
        do{
            try{
                for(int i=0;i<options.length;i++){
                    System.out.println("\t"+(i+1)+". "+options[i]+".");
                }
                System.out.print("Enter your choice: ");
                choice=Integer.parseInt(sc.nextLine());
                if(choice<1| choice >options.length) throw new Exception();
                condition=true;
            }catch(Exception e){
                System.out.println("Invalid! Enter again.");
                condition=false;
            }
        }while(!condition);
        return choice;
    }


}
/*
    작성자 : 박진현
    문제 : Cracking the coding interview,
    문자열 str2가 문자열 str1의 rotation인가?

    ex) "watterbottle" is a rotation of "erbottlewat"
 */
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static boolean isSubstring(String str1, String str2) {
        if(str1.contains(str2))
            return true;
        else
            return false;
    }

    public static boolean isRotation(String str1, String str2) {
        if(isSubstring(str1+str1, str2))
            return true;
        else
            return false;
    }

    public static void main(String args[]) throws IOException{
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        if(isRotation(str1, str2))
            System.out.println("true");
        else
            System.out.println("false");
    }
}

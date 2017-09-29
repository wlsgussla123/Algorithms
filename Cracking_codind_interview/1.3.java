import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static Scanner sc = new Scanner(System.in);

    public static String remove(char[] charArray) {
        String result = "";

        for(int i=0; i<charArray.length; i++) {
            for(int j=0; j<charArray.length; j++) {
                if(charArray[i] == charArray[j] && i != j) {
                    charArray[j] = 0;
                }
            }
        }

        for(int i=0; i<charArray.length; i++) {
            if(charArray[i] != 0)
               result += charArray[i];
        }

        return result;
    }

    public static void main(String args[]) throws IOException{
        String str = sc.nextLine();

        System.out.println(remove(str.toCharArray()));
    }
}

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static String replace(String str) {
        return str.replaceAll(" ", "%20");
    }
    public static void main(String args[]) {
        String str = sc.nextLine();

        System.out.println(replace(str));
    }
}


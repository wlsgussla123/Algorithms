/**
 * Created by 박진현 on 2017-09-22.
 * input으로 들어온 괄호의 쌍이 맞는가?
 */
import java.util.*;

public class Main {
    static Stack<Character> stack = new Stack<Character>();
    static boolean[] check;

    public static boolean checkExpression(String exp) {
        while(!stack.empty()) stack.pop();

        for(int i=0; i<exp.length(); i++) {
            switch(exp.charAt(i)) {
                case '(':
                    stack.push(exp.charAt(i));
                    break;
                case '[':
                    stack.push(exp.charAt(i));
                    break;
                case '{':
                    stack.push(exp.charAt(i));
                    break;

                case ')':
                    char op = stack.pop();
                    if(op != '(') {
                        return false;
                    }
                    break;
                case ']':
                    op = stack.pop();
                    if(op != '[') {
                        return false;
                    }
                    break;
                case '}':
                    op = stack.pop();
                    if(op != '{') {
                        return false;
                    }
                    break;
            }
        }

        if(stack.empty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        check = new boolean[T];

        for(int i=0; i<T; i++) {
            String exp = sc.next();
            check[i] = checkExpression(exp);
        }

        for(int i=0; i<T; i++) {
            if(check[i]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

/*
()
({[})
([)}
(a{b|}[...hello])
()
(())
({}[]([{}]))
(((
 */

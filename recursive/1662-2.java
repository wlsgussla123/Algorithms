package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private String str;
	private char[] input;
	private char[] stack;
	private int top = 0;
	
//	private Stack<Character> s1 = new Stack<Character>();
	private int parentheses = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private final Scanner sc = new Scanner(System.in);
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void stackInit() {
		stack = new char[input.length];
		for(int i=0; i<input.length; i++) {
			stack[top++] = input[i];
		}
	}
	
	// 입력
	private void input() throws IOException {
//		st = getStringTokenizer();
//		str = st.nextToken();
		str = sc.next();
		input = new char[str.length()];
		input = str.toCharArray();
		
		stackInit();
	}
	
	private long cancelCompression(long length, char number) {
		return length * (number - '0');
//		StringBuilder temp = new StringBuilder(sb);
//		for(int i=1; i<n; i++) {
//			sb.insert(0, temp);
//		}
	}
	
	private void process() {
		boolean isMultiply = false;
//		StringBuilder sb = new StringBuilder();
		long totalLength = 0;
		long subLength = 0;
		
		while(top>0) {
			char c = stack[--top];
			switch(c) {
			case '(':
				isMultiply = true;
				parentheses--;
				break;
			case ')':
				parentheses++;
				break;
			default:
				if(isMultiply) {
					subLength = cancelCompression(subLength, c); 
					if(parentheses == 0) {
						totalLength += subLength;
						subLength = 0;
					}
					isMultiply = false;
				} else {
					if(parentheses != 0) {
						subLength++;
					} else {
						totalLength++;
					}
				}
				break;
			}
		}
		System.out.println(totalLength);
	}
	
	public void run() throws IOException {
		input();
		process();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

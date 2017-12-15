
package algo;

import java.util.Arrays;
import java.util.Scanner;

class Task {
	private final Scanner sc = new Scanner(System.in);
	private String[] strs;
	private int N;
	private boolean[] answer = new boolean[26];
	private int res;
	
	private void input() {
		N = sc.nextInt();
		strs = new String[N];
		res = N;
		
		for(int i=0; i<N; i++) strs[i] = sc.next();
		setAnswer();
	}
	
	private void process() {		
		for(int i=0; i<N; i++) {
			char[] temp = strs[i].toCharArray();
			char ch = temp[0];
			answer[(int)ch-97] = true;
			
			for(int j=1; j<temp.length; j++) {
				if(temp[j] == ch) {
					answer[(int)ch-97] = true;
				} else {
					if(answer[(int)temp[j]-97]) {
						res--;
						break;
					} else {
						answer[temp[j]-97] = true;
						ch = temp[j];
					}
				}
			}
			setAnswer();
		}
		
		System.out.println(res);
	}
	
	private void setAnswer() {
		for(int i=0; i<26; i++) answer[i] = false;
	}
	
	public void run() {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

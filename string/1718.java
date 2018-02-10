package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private char[] plainText;
	private char[] secret;
	private char[] answer;
	private int answerIdx = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private void input() throws IOException {
		plainText = br.readLine().toCharArray();
		secret = br.readLine().toCharArray();
		answer = new char[plainText.length];
	}
	
	private void makeSecret(char p, char s) {
		if(p ==' ') {
			answer[answerIdx++] = ' ';
			return;
		}
		int num = (p-s);
		if(num<0) {
			answer[answerIdx++] = (char)(num+'z');
		} else {
			if(num+'a'-1 == 96) answer[answerIdx++] = 'z';
			else answer[answerIdx++] = (char)(num+'a'-1);
		}
	}
	
	private void process() {
		int j=0;
		for(int i=0; i<plainText.length; i++) {
			j = j%secret.length;
			makeSecret(plainText[i], secret[j++]);
		}
		
		System.out.println(String.valueOf(answer));
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

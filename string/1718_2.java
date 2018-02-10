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
		int num = s-96; // 97은 a이므로.. a이면 1칸 앞, b면 2칸 앞 ....
		answer[answerIdx++] = (char)(p-num<97 ? (p-num+26) : p-num);
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

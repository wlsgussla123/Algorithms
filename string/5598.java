package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private char[] ciphertext;
	private char[] plaintext;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		ciphertext = st.nextToken().toCharArray();
		plaintext = new char[ciphertext.length];
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<ciphertext.length; i++) {
			int num = ciphertext[i]-3;
			plaintext[i] = num >= 65 ? (char)num : (char)(num+26); 
		}
		System.out.println(String.valueOf(plaintext));
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

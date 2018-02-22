package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private ArrayList<String> sentence = new ArrayList<String >();
	private int[] alphabet = new int[26];
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Scanner sc = new Scanner(System.in);
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), ",");
	}

	private void input() throws IOException {
		while(sc.hasNext()) {
			sentence.add(sc.next());
		}
	}
	
	private int findMax(int[] alphabet) {
		int max = 0;
		for(int i=0; i<26; i++) {
			if(alphabet[i] > max) max  = alphabet[i];
		}
		return max;
	}

	private void solution() {
		for(int i=0; i<sentence.size(); i++) {
			for(int j=0; j<sentence.get(i).length(); j++) {
				if(sentence.get(i).charAt(j) >= 'a' && sentence.get(i).charAt(j) <= 'z') {
					int idx = sentence.get(i).charAt(j) - 'a';
					alphabet[idx]++;
				}
			}
		}
		int max = findMax(alphabet);
		for(int i=0; i<26; i++) {
			if(max == alphabet[i]) {
				char c = (char)(i+97);
				System.out.print(c);
			}
		}
	}

	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

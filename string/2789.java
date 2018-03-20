package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private ArrayList<Character> list = new ArrayList<>();
	private int[] alphabet = new int[26];
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		String str = "CAMBRIDGE";
		int len = str.length();
		for(int i=0; i<len; i++) {
			alphabet[str.charAt(i)-'A'] = 1;
		}
		
		st = getStringTokenizer();
		String input = st.nextToken();
		len = input.length();
		for(int i=0; i<len; i++) {
			if(alphabet[input.charAt(i) - 'A'] == 1) continue;
			list.add(input.charAt(i));
		}
	}
	
	private void solution() {
		for(Character c : list) {
			System.out.print(c);
		}
		System.out.println();
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

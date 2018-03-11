package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private String str1;
	private String str2; 
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		str1 = st.nextToken();
		str2 = st.nextToken();
	}
	
	private boolean oneWayReplace(String str1, String str2) {
		boolean oneChance = false;
		for(int i=0; i<str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				if(oneChance) return false;
				oneChance = true;
			}
		}
		return true;
	}
	
	private boolean oneWayInsert(String str1, String str2) {
		boolean oneChance = false;
		int idx1 = 0;
		int idx2 = 0;
		while(idx1 < str1.length() && idx2 < str2.length()) {
			if(str1.charAt(idx1) != str2.charAt(idx2)) {
				if(oneChance) return false;
				idx1++;
				oneChance = true;
			} else {
				idx1++;
				idx2++;
			}
		}
		
		return true;
	}
	
	public void run() throws IOException {
		input();
		if(str1.length() == str2.length()) {
			System.out.println(oneWayReplace(str1, str2));
		} else if(str1.length()-1 == str2.length()) {
			System.out.println(oneWayInsert(str1, str2));
		} else if(str1.length()+1 == str2.length()) {
			System.out.println(oneWayInsert(str2, str1));
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private boolean solution() throws IOException {
		st = getStringTokenizer();
		String str = st.nextToken();
		int[] count = new int[26];
		int len = 0;
		str = str.toLowerCase();
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != ' ') {
				count[str.charAt(i) - 'a']++;
				len++;
			}
		}
		
		boolean isEven = len % 2 == 0 ? true : false;
		boolean center = false;
		for(int i=0; i<26; i++) {
			if(count[i] == 0) continue;
			if(count[i]%2 != 0) {
				if(isEven) return false;
				if(center) {
					return false;
				}
				center = true;
			}
		}
		return true;
	}

	public void run() throws IOException {
		System.out.println(solution());
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

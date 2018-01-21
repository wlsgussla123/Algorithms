package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int L,C;
	private char[] arr;
	private boolean[] visited;
	private boolean[] isVowel;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		arr = new char[C+1];
		visited = new boolean[C+1];
		isVowel = new boolean[C+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		init();
		
		st = getStringTokenizer();
		for(int i=1; i<=C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
	}
	
	private void sort() {
		Arrays.sort(arr);
		for(int i=1; i<=C; i++) {
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				isVowel[i] = true;
			}
		}
	}
	
	private boolean counting() {
		int vowelCnt=0, consonantCnt=0;
		for(int i=1; i<=C; i++) {
			if(visited[i]) {
				if(isVowel[i]) vowelCnt++;
				else consonantCnt++;
			}
		}
		
		if(vowelCnt>=1 && consonantCnt>=2) return true;
		else return false;
	}
	
	private void backtracking(int index, int cnt) {
		if(cnt == L) {
			if(counting()) {
				for(int i=1; i<=C; i++) {
					if(visited[i]) System.out.print(arr[i]);
				}
				System.out.println();				
			}
		} else {
			for(int i=index+1; i<=C; i++) {
				visited[i] = true;
				backtracking(i, cnt+1);
				visited[i] = false;
			}
		}
	}
	
	public void run() throws IOException {
		input();
		sort();
		for(int i=1; i<=C; i++) {
			visited[i] = true;
			backtracking(i, 1);
			visited[i] = false;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

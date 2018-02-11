package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private String[] words;
	private String[] strs;
	private boolean[] alphabet;
	private int answer;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		words = new String[N];
		strs = new String[N];
		alphabet = new boolean[26];
		answer = 0;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			words[i] = st.nextToken();
		}
	}
	
	private String removeOverlap(char[] word) {
		HashSet<Character> set = new HashSet<Character>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<word.length; i++) {
			if(set.contains(word[i])) continue;
			set.add(word[i]);
			sb.append(word[i]);
		}
		char[] temp = sb.toString().toCharArray();
		Arrays.sort(temp);
		return String.valueOf(temp);
	}
	
	private void readWord() {
		int cnt = N;
		for(int i=0; i<N; i++) {
			char[] temp = strs[i].toCharArray();
			for(int j=0; j<temp.length; j++) {
				if(!alphabet[temp[j]-'a']) {
					cnt--;
					break;
				}
			}
		}
		if(cnt > answer) answer = cnt;
	}
	
	private void backtracking(int index, int cnt) {
		if(cnt == K) {
			readWord();
		} else {
			for(int i=index+1; i<26; i++) {
				alphabet[i] = true;
				backtracking(i, cnt+1);
				alphabet[i] = false;
			}
		}
	}
	
	private void process() throws IOException {
		// 일단은 중복을 제거한 형태를 반환
		for(int i=0; i<N; i++) {
			strs[i] = removeOverlap(words[i].toCharArray());
		}
		
		for(int i=0; i<26; i++) {
			alphabet[i] = true;
			backtracking(i, 1);
			alphabet[i] = false;
		}
		System.out.println(answer);
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

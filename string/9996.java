package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.print.attribute.standard.PDLOverrideSupported;

class Task {
	private int N;
	private char[] pattern;
	private String[] strs;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		pattern = st.nextToken().toCharArray();
		strs = new String[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			strs[i] = st.nextToken();
		}
	}
	
	private boolean firstMatch(String str) {
		char[] temp = str.toCharArray();
		int idx = 0;
		for(int i=temp.length-1; i>=0; i--) {
			if(temp[i] == pattern[1]) {
				idx = i;
				break;
			}
		}
		
		for(int i=1; i<pattern.length; i++) {
			if(idx == temp.length) return false;
			if(pattern[i] != temp[idx++]) return false;
		}
		if(temp.length != idx) return false;
		
		return true;
	}
	
	private boolean lastMatch(String str) {
		char[] temp = str.toCharArray();
		for(int i=0; i<pattern.length-1; i++) {
			if(i==temp.length) return false;
			if(temp[i] != pattern[i]) return false;
		}
		return true;
	}
	
	private int findStar() {
		for(int i=0; i<pattern.length; i++) {
			if(pattern[i] == '*') return i;
		}
		return -1;
	}
	
	/*
	 * 반례 : abcd*cdef
	 * 		abcdef (같은 cd를 반복해서 센다.)
	 */
	private boolean middleMatch(String str) {
		int starIdx = findStar();
		char[] temp = str.toCharArray();
		int i=0;
		for(i=0; i<starIdx; i++) {
			if(temp.length == i) return false; // 패턴하기도 전에 문자열이 끝날 경우
			if(temp[i] != pattern[i]) return false;
		}
		
		int idx = temp.length-1;
		int j=0;
		for(j=pattern.length-1; j>starIdx; j--) {
			if(idx<i) break;
			if(temp[idx--] != pattern[j]) return false;
		}
		
		if(j>starIdx) return false;
		
		return true;
	}
	
	private void process() {
		for(int i=0; i<N; i++) {
			if(pattern[0] == '*') {
				if(firstMatch(strs[i])) System.out.println("DA");
				else System.out.println("NE");
			} else if(pattern[pattern.length-1] == '*') {
				if(lastMatch(strs[i])) System.out.println("DA");
				else System.out.println("NE");
			} else {
				if(middleMatch(strs[i])) System.out.println("DA");
				else System.out.println("NE");
			}
		}
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

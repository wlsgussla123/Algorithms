package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private String str;
	private char[] words;
	private int[] alphabet;
	private char[] answer;
	private int whichAlphabet = -1; // 어떤 알파벳이 중간에 들어갈 것인가 저장
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 문자열을 입력받고, 그 문자열을 char형 배열로 바꾼다. 
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
		words = new char[str.length()];
		words = str.toCharArray();
		alphabet = new int[26];
		answer = new char[str.length()];
	}
	
	// 입력받은 문자열에 어떤 단어가 몇 번 나왔는지 카운팅 한다.
	private void countingAlphabet() {
		int len = words.length;
		int index = 0;
		for(int i=0; i<len; i++) {
			index = words[i] - 65; // 0 = A, 1 = B ..... 25 = Z
			alphabet[index]++;
		}		
	}
	
	// 홀수인 알파벳은 1개까지만 허용된다. (펠린드롬의 규칙 !)
	private boolean checkOdd() {
		int cnt = 0;
		for(int i=0; i<26; i++) {
			if(alphabet[i] % 2 != 0) {
				cnt++;
				whichAlphabet = i; // 어차피 2개 이상은 종료니까 상관없다.
				alphabet[i]--; // 해당 알파벳을 중앙에 배치시킬 것이기 때문에 alphabet에서 하나 차감
			}
		}
		if(cnt >= 2) return true;
		else return false;
	}

	private void process() {
		int startIndex = 0;
		int endIndex = str.length()-1;
		
		for(int i=0; i<26; i++) {
			int count = alphabet[i]; // 알파벳 수만큼 앞뒤로 하나씩 붙이자
			for(int j=0; j<count; j++) {
				if(j%2 == 0) {
					answer[startIndex++] = (char) ('A' + i);
				} else {
					answer[endIndex--] = (char) ('A' + i);
				}
			}				
		}
		
		// -1이 아니면 중간에 넣어야 할 것이 있습니다.
		if(whichAlphabet != -1) {
			int centerIndex = (str.length()/2);
			answer[centerIndex] = (char) ('A' + whichAlphabet); 
		}
	}
	
	private void answer() {
		int len = answer.length;
		for(int i=0; i<len; i++) {
			System.out.print(answer[i]);
		}
		System.out.println();
	}
	
	public void run() throws IOException {
		input();
		countingAlphabet();
		if(checkOdd()) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		process();
		answer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

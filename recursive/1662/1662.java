package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private String str;
	private char[] charArray;
	private Stack<Character> s1 = new Stack<>(); 
	private Stack<Character> s2 = new Stack<>(); 
	private int answerCnt = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		charArray = new char[str.length()];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
		init();
		charArray = str.toCharArray();
	}
	
	private void compression() {
		for(int i=0; i<charArray.length; i++) {
			s1.push(charArray[i]);
		}
	}
	
	private void release() {
		boolean isCompression = false;
		StringBuilder tempSb = new StringBuilder();
		
		while(!s1.isEmpty()) {
			char c = s1.pop();
			
			switch(c) {
			case ')':
				s2.push(c);
				isCompression = false;
				break;
			case '(':
				isCompression = true; // 다음에 나올 숫자만큼 반복 시킬거고
				s2.pop(); // s2도 하나 빼주고
				break;
			default:
				if(isCompression) {
					int index = c - '0'; // 이만큼 반복
					// 1부터 시작하는 이유는 1일 경우 이미 tempSb에 있기 때문에.
					String str = tempSb.toString();
					for(int i=1; i<index; i++) {
						tempSb.insert(0, str);
					}
					
					if(s2.isEmpty()) {
						answerCnt += tempSb.length();
						tempSb.delete(0, tempSb.length());
					}
					
				} else {
					if(!s2.isEmpty()) {
						tempSb.insert(0, c);
					} else {
						answerCnt++;
					}	
				}
				
				isCompression = false;
			}
		}
		
		System.out.println(answerCnt + tempSb.length());
	}

	public void run() throws IOException {
		input();
		compression();
		release();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private String str;
	private char[] charArray;
	private Stack<Character> s1 = new Stack<>(); 
	private Stack<Character> s2 = new Stack<>(); 
	private int answerCnt = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		charArray = new char[str.length()];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
		init();
		charArray = str.toCharArray();
	}
	
	private void compression() {
		for(int i=0; i<charArray.length; i++) {
			s1.push(charArray[i]);
		}
	}
	
	private void release() {
		boolean isCompression = false;
		StringBuilder tempSb = new StringBuilder();
		
		while(!s1.isEmpty()) {
			char c = s1.pop();
			
			switch(c) {
			case ')':
				s2.push(c);
				isCompression = false;
				break;
			case '(':
				isCompression = true; // 다음에 나올 숫자만큼 반복 시킬거고
				s2.pop(); // s2도 하나 빼주고
				break;
			default:
				if(isCompression) {
					int index = c - '0'; // 이만큼 반복
					// 1부터 시작하는 이유는 1일 경우 이미 tempSb에 있기 때문에.
					String str = tempSb.toString();
					for(int i=1; i<index; i++) {
						tempSb.insert(0, str);
					}
					
					if(s2.isEmpty()) {
						answerCnt += tempSb.length();
						tempSb.delete(0, tempSb.length());
					}
					
				} else {
					if(!s2.isEmpty()) {
						tempSb.insert(0, c);
					} else {
						answerCnt++;
					}	
				}
				
				isCompression = false;
			}
		}
		
		System.out.println(answerCnt + tempSb.length());
	}

	public void run() throws IOException {
		input();
		compression();
		release();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

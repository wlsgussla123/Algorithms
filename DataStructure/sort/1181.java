package algo;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Words {
	List<String> words;
	private int wordsSize;
	
	public Words() {
		this.words = new LinkedList<>();
	}
	
	public void addWord(String word) {
		if(!words.contains(word)) {
			this.words.add(word);
		}
	}
	
	public void setWordSize() {
		this.wordsSize = this.words.size();
	}
	
	public void sort() {
		Collections.sort(words, new Comparator<String>() {
			Collator coll = Collator.getInstance();
			
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() < o2.length()) return -1;
				else if(o1.length() > o2.length()) return 1;
				else {
					return coll.compare(o1, o2);
				}
			}
		});
	}
	
	public void print() {
		for(int i=0; i<wordsSize; i++) {
			System.out.println(words.get(i));
		}
	}
}

class Task {
	private int N;
	private Words words;
	private final Scanner sc = new Scanner(System.in);
	
	private void init() {
		this.words = new Words();
	}
	private void input() {
		N = sc.nextInt();
		
		String word;
		for(int i=0; i<N; i++) {
			word = sc.next();
			this.words.addWord(word);
		}
		this.words.setWordSize(); // 다 입력하고 나서 크기 잡아줘야함 (중복되는 것은 받지 않기 때문에 N으로 하면 print 시에 에러)
	}
	
	private void sort() {
		this.words.sort();
	}
	
	private void print() {
		this.words.print();
	}
	
	public void run() {
		init();
		input();
		sort();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

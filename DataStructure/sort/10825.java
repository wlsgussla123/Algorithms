package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class Student {
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public Student() {}
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}

class Task {
	private ArrayList<Student> students;
	private int N;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private void init() {
		students = new ArrayList<Student>();
	}
	private void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			students.add(new Student(
					st.nextToken(), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())));
		}
		
		br.close();
	}
	
	private void sort() {
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getKor() > o2.getKor()) {
					return -1;
				} else if(o1.getKor() == o2.getKor()) {
					if(o1.getEng() > o2.getEng()) {
						return 1;
					} else if(o1.getEng() == o2.getEng()) {
						if(o1.getMath() > o2.getMath()) {
							return -1;
						} else if(o1.getMath() == o2.getMath()) {
							return o1.getName().compareTo(o2.getName());
						} else {
							return 1;
						}
					} else {
						return -1;
					}
				} else {
					return 1;
				}
			}
		});
	}
	
	private void print() {
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<N; i++) {
			sb.append(students.get(i).getName()+"\n");
		}
		
		System.out.println(sb);
	}
	
	public void run() throws IOException {
		init();
		input();
		sort();
		print();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

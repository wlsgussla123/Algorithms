/*
 * 작성자 : 박진현
 * 문제 : Cracking the coding interview 3.2, 최소값을 반환하는 stack
 * About Stack
 */

import java.util.Iterator;
import java.util.Stack;

public class Main {
	private static Stack<Integer> s1 = new Stack<Integer>(); // 값들을 저장할 stack
	private static Stack<Integer> s2 = new Stack<Integer>(); // 임시 버퍼용 stack
	
	public static void main(String[] args) {
		push(2);
		push(3);
		push(1);
		push(4);
		
		while(!s1.isEmpty()) {
			System.out.println(s1.pop());
		}
	}
	
	// s1에 정렬된 값들이 들어있고, data가 들어왔을 때 제 위치를 찾기 위해 s2를 임시버퍼로 이용하여 정렬을 한다.
	public static void push(int data) {
		if(s1.isEmpty()) {
			s1.push(data);
			return;
		}
	
		int tmp = s1.peek();
		while(tmp < data) {
			s2.push(s1.pop());
			if(!s1.isEmpty()) {
				tmp = s1.peek();				
			} else {
				break;
			}
		}
		
		s1.push(data);
		
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
	}
	
	public static Integer min() {
		int data = s1.peek();
		
		return data;
	}
}

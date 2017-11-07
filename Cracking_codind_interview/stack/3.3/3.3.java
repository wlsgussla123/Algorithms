/*
 * 작성자 : 박진현
 * 문제 : Cracking the coding interview 3.3, 접시무더기
 * About Stack
 */

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class SetOfStacks {
	public LinkedList<Stack<Integer>> list;
	private int stackSize;
	
	public SetOfStacks(int size) {
		this.list = new LinkedList<Stack<Integer>>();
		this.stackSize = size;
	}
	
	public void push(int data) throws Exception {
		if(this.list.size() == 0) {
			Stack<Integer> s = new Stack<Integer>();
			this.list.add(s);
			s.push(data);
		} else {
			if(list.isEmpty()) {
				throw new Exception();
			}
			
			Stack<Integer> s = list.getLast();
			if(s.isEmpty()) {
				throw new Exception();
			}
			
			if(s.size() < stackSize) {
				s.push(data);				
			} else {
				Stack<Integer> newStack = new Stack<Integer>();
				newStack.push(data);
				
				list.add(newStack);
			}
		}
	}
	
	public int pop() throws Exception {
		if(list.isEmpty()) {
			throw new Exception();
		}
		
		Stack<Integer> s = list.getLast();
		int data;
		
		if(s.isEmpty()) {
			throw new Exception();
		}
		
		data = s.pop();
		if(s.size() == 0) {
			list.removeLast();
		}
		
		return data;
	}
}

public class Main {
	public static void main(String[] args) {
		SetOfStacks sos = new SetOfStacks(3);
		
		try {
			sos.push(1);
			sos.push(2);
			sos.push(3);
			sos.push(4);
			sos.push(5);
			sos.push(6);			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("push error");
		}

		
		try {
			System.out.println(sos.pop());
			System.out.println(sos.pop());
			System.out.println(sos.pop());
			System.out.println(sos.pop());
			System.out.println(sos.pop());
			System.out.println(sos.pop());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("pop error");
		}
	}
}

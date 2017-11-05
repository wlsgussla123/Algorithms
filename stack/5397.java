package com.jinhyeon.demo;

import java.util.EmptyStackException;
import java.util.Scanner;

class Stack {
	private char[] data;
	private int top;
	
	public Stack() {
		this.data = new char[1000000];
		this.top = -1;
	}
	
	public void push(char data) throws StackOverflowError {
		if(isFull())
			new StackOverflowError();
		
		this.top++;
		this.data[top] = data;
	}
	
	public char pop() throws EmptyStackException {
		if(isEmpty()) new EmptyStackException();
		
		char data = this.data[this.top];
		this.top--;
		return data;
	}
	
	public boolean isEmpty() {
		return this.top == -1;
	}
	
	public boolean isFull() {
		return this.top == 1000000;
	}
}

public class Main {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		int T;
		
		T = sc.nextInt();
		while(T > 0) {
			String input = sc.next();
			char[] str = input.toCharArray(); 
		
			int i=0;
			int len = str.length;		
			while(i < len) {
				char c = str[i];
				if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
					s1.push(c);
				} else if(c == '<' && !s1.isEmpty() && !s2.isFull()) {
					s2.push(s1.pop());
				} else if(c == '>' && !s2.isEmpty() && !s1.isFull()) {
					s1.push(s2.pop());
				} else if(c == '-' && !s1.isEmpty()) {
					s1.pop();
				}
				
				i++;
			} 
			
			while(!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			
			while(!s2.isEmpty()) {
				System.out.print(s2.pop());
			}
			System.out.println();
			
			T--;
			while(!s1.isEmpty()) s1.pop();
			while(!s2.isEmpty()) s2.pop();			
		}
	}
}

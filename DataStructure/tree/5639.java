package test;

import java.util.Scanner;

/*
 * 작성자 : 박진현 
 * 문제 : 백준, 5639번 이진검색트리
 * 자료구조를 사용하기 위한 문제로 선택
 */

class BST {
	private class BSTNode {
		private int data;
		private BSTNode left;
		private BSTNode right;
		
		public BSTNode() {
			this.left = null;
			this.right = null;
		}
		
		public BSTNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	private BSTNode root = null;
	public BST() {}
	
	private BSTNode insertKey(BSTNode root, int key) {
		BSTNode newNode = new BSTNode(key);
		BSTNode cur = root;
		
		if(cur == null) {
			return newNode;
		}

		if(cur.data > key) {
			cur.left = insertKey(cur.left, key);
		} else {
			cur.right = insertKey(cur.right, key);
		}

		return cur;
	}
	public void insertNode(int key) {
		root = insertKey(root, key);
	}
	
	public void preorder(BSTNode root) {
		System.out.println(root.data);
		if(root.left != null) preorder(root.left);
		if(root.right != null) preorder(root.right);
	}
	
	public void inorder(BSTNode root) {
		if(root.left != null) inorder(root.left);
		System.out.println(root.data);
		if(root.right != null) inorder(root.right);
	}
	
	public void postorder(BSTNode root) {
		if(root.left != null) postorder(root.left);
		if(root.right != null) postorder(root.right);
		System.out.println(root.data);
	}
	
	public void printBST() {
		postorder(root);
	}
}


public class Main {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		BST root = new BST();
		String input;
		int num;

		// EOF 처리에 대한 숙지를 하고있자.
		while(sc.hasNextLine()) {
			input = sc.nextLine();
			if(input.length() <= 0) {
				break;
			}
			num = Integer.parseInt(input);
			root.insertNode(num);
		}
				
		root.printBST();
	}
}

/*
	nextLine() 함수는 더 이상 입력받을 줄이 없을 경우 에러를 발생시킴.
	-> 몇 줄이 주어지는지 알 수 없는 경우 hasNextLine() 함수를 이용해 입력받기 전에 남은 줄이 있는지 확인
	
*/

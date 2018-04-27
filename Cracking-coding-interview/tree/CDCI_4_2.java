package coding_interview;

class BinaryTree {
	class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		public TreeNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	private TreeNode root = null;
	public BinaryTree() {
		this.root = null;
	}
	
	public void insert(int[] arr) {
		root = insertNode(arr, 0, arr.length-1); 
	}
	
	public TreeNode insertNode(int[] arr, int start, int end) {
		if(start > end) {
			return null;
		}
		
		int mid = (start + end)/2;
		TreeNode newNode = new TreeNode(mid);
		newNode.left = insertNode(arr, start, mid-1);
		newNode.right = insertNode(arr, mid+1, end);
		
		return newNode;
	}
	
	public int getLeftDepth(int cnt, TreeNode node) {
		TreeNode cur = node;
		if(cur.left != null) {
			return getLeftDepth(cnt+1, cur.left);
		} else {
			return cnt;
		}
	}
	
	public int getRightDepth(int cnt, TreeNode node) {
		TreeNode cur = node;
		if(cur.right != null) {
			return getRightDepth(cnt+1, cur.right);
		} else {
			return cnt;
		}
	}
	
	public TreeNode getRoot() {
		return root;
	}
}

public class CDCI_4_2 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8};
		BinaryTree bt = new BinaryTree();
		bt.insert(arr);
		System.out.println(bt.getLeftDepth(0, bt.getRoot()));
		System.out.println(bt.getRightDepth(0, bt.getRoot()));
	}
}

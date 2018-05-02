package datastructure;

class Trie {
	private Trie[] next;
	private boolean finish;
	private final int SIZE = 26;
	
	public Trie() {
		next = new Trie[SIZE];
	}
	
	public void insert(String key, int idx) {
		if(key.length() == idx) {
			finish = true;
			return;
		}
		
		int curr = key.charAt(idx) - 'A';
		if(next[curr] == null) {
			next[curr] = new Trie();
		}
		
		next[curr].insert(key, idx+1);
	}
	
	public boolean find(String key, int idx) {
		if(key.length() == idx) {
			if(finish) return true;
			else return false;
		}
		
		int curr = key.charAt(idx) - 'A';
		if(next[curr] == null) {
			return false;
		}
		return next[curr].find(key, idx+1);
	}
}

public class TrieMain {
	public static void main(String[] args) {
		Trie root = new Trie();
		String[] strs = {"BA", "BAG", "ABC", "ABCD"};
		int len = strs.length;
		for(int i=0; i<len; i++) {
			root.insert(strs[i], 0);
		}
		
		for(int i=0; i<len; i++) {
			if(root.find(strs[i], 0)) {
				System.out.println("found");
			}
		}
		
		if(root.find("A", 0)) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}
	}
}

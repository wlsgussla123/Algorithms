import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

class Set {
	public BufferedWriter bw;
	private HashSet<Integer> hashSet;
	public Set() throws IOException {
		hashSet = new HashSet<Integer>();
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public void add(int x) {
		if(hashSet.contains(x)) return;
		hashSet.add(x);
	}
	
	public void remove(int x) {
		if(!hashSet.contains(x)) return;
		hashSet.remove(x);
	}
	
	public void check(int x) throws IOException {
		if(hashSet.contains(x)) bw.write(String.valueOf("1"));
		else bw.write(String.valueOf("0"));
		bw.newLine();
	}
	
	public void toggle(int x) {
		if(hashSet.contains(x)) {
			remove(x);
		} else {
			add(x);
		}
	}
	
	public void all() {
		empty();
		for(int i=1; i<=20; i++) {
			hashSet.add(i);
		}
	}
	
	public void empty() {
		hashSet = new HashSet<Integer>();
	}
}

class Task {
	private Set set;
	private int N;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		set = new Set();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			String op = st.nextToken();
			if(op.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				set.add(x);
			} else if(op.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				set.check(x);
			} else if(op.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				set.remove(x);
			} else if(op.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				set.toggle(x);
			} else if(op.equals("all")) {
				set.all();
			} else if(op.equals("empty")) {
				set.empty();
			}
		}
		set.bw.close();
	}
	
	public void run() throws IOException {
		input();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

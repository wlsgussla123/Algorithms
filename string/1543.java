import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private String document;
	private String word;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), ",");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		document = st.nextToken();
		st = getStringTokenizer();
		word = st.nextToken();
	}

	private void solution() {
		int answer = 0;
		while(document.indexOf(word) != -1) {
			document = document.substring(document.indexOf(word) + word.length(), document.length());
			answer++;
		}
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class Main {
	private static int[][] customer = { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 3, 0 }, { 1, 1 }, { 2, 0 }, { 4, 0 }, { 2, 1 } };
	// private static int[][] customer = {{1, 1}, {2, 1}, {3, 1}, {2, 0}, {2, 1}};
	// private static int[][] customer = {{2, 1}, {1, 1}, {3, 1}, {1, 0}, {1, 1},
	// {2, 0}, {2, 1}};

	private static int[] solution(int[][] customer, int K) {
		List<Pair<Integer, Integer>> pairs = new ArrayList();

		Map<Integer, Pair<Integer, Integer>> buckets = new LinkedHashMap<>();

		for (int i = 0; i < customer.length; i++) {
			Pair<Integer, Integer> pair = new Pair<>(customer[i][0], customer[i][1]);
			pairs.add(pair);
		}

		for (int i = 0; i < pairs.size(); i++) {
			Pair<Integer, Integer> pair = pairs.get(i);
			if (pair.getValue() == 1) {
				buckets.put(pair.getKey(), pair);
			} else {
				if (buckets.get(pair.getKey()) != null) {
					buckets.remove(pair.getKey());
				}
			}
		}

		Collection<Pair<Integer, Integer>> values = buckets.values();
		List<Pair<Integer, Integer>> valueList = new ArrayList<>(values);
		if (valueList.size() > K) {
			valueList = valueList.subList(0, K);
		}

		int[] results = valueList.stream().map(Pair::getKey).mapToInt(Integer::intValue).toArray();
		Arrays.sort(results);

		return results;
	}

	public static void main(String... args) {
		int k = 3;
		int[] result = solution(customer, k);
		for (int a : result) {
			System.out.println(a);
		}
		// for (int i = 0; i < result.length; i++) {
		// if (result[i] == 0) continue;
		// System.out.println(result[i]);
		// }
	}
}

package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;

// 1번문제
class Main {
	public static int[] solution(int day, int k) {
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(new Pair<>(0, 0));
		pairs.add(new Pair<>(1, 0));
		pairs.add(new Pair<>(2, 0));
		pairs.add(new Pair<>(3, 0));
		pairs.add(new Pair<>(4, 0));
		pairs.add(new Pair<>(5, 1));
		pairs.add(new Pair<>(6, 1));

		final List<Integer> monthOfDays = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		final List<List<Integer>> valueOfDays = new ArrayList<>();

		for (int j = 0, i = day; j < monthOfDays.size(); j++) {
			List<Integer> newMonth = new ArrayList<>();
			for (int localCounter = 0; localCounter < monthOfDays.get(j); i++, localCounter++) {
				newMonth.add(i % 7);
			}
			valueOfDays.add(newMonth);
		}

		return valueOfDays.stream().map(integers -> pairs.get(integers.get(k - 1)).getValue()).mapToInt(i -> i)
				.toArray();
	}

	public static void main(String... args) {
		int[] values = solution(6, 25);
		System.out.println("===");
		for (int value : values) {
			System.out.println(value);
		}
	}
}

/*
	작성자 : 박진현

	0/1 knapsack problem
	: 뽑거나, 안 뽑거나. (아이템 하나 뽑으면 끝, 중복해서 뽑을 수 없다.) 
*/
#include <iostream>

using namespace std;

int K[5][21] = { 0, };

int max(int a, int b) {
	return a > b ? a : b;
}

int knapsack(int n, int W, int price[], int weight[]) {
	for (int i = 0; i <= n; i++) {
		K[i][0] = 0; // kanpsack 무게가 0이면 값은 0이야.
	}

	for (int w = 0; w <= W; w++) {
		K[0][w] = 0; // 첫 행 시작할 때 다 0이지.
	}

	/*
		각 행은 사용하는 아이템을 의미
		ex) i=1 일 때, 첫 번째에 있는 아이템 하나만 사용하여 채우기.
			i=2 일 때, 첫 번째 아이템, 두 번째 아이템으로만 ... 이런 식으로 진행

		0/1 knapsack은 중복이 없다. 
		즉, i번째 행에서 j무게의 아이템을 구할 때, i번째 아이템이 포함된다고 가정하면 
		i번째 아이템의 value를 뽑고, i번째 아이템을 뺀 나머지 만큼의 무게에서 구할 수 있는 최대 값을 더해주면 된다. 
	*/
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= W; j++) {
			if (weight[i] > j) {
				K[i][j] = K[i - 1][j];
			}
			else {
				int selected_value = K[i-1][j - weight[i]] + price[i]; // 지금 뽑는 아이템 값 + 이 아이템 없이 최대값, i-1 (이 아이템 포함 x) [j-weight[i]] j : 현재 남은 무게 - weight[i] 지금 넣는 물건의 무게
				int unselected_value = K[i - 1][j];

				K[i][j] = max(selected_value, unselected_value);
			}
		}
	}

	return K[n][W];
}

int main(void) {
	int price[5] = { 0, 1, 4, 5, 7 };
	int weight[5] = { 0, 1, 3, 4, 5 };
	int Weight = 7; // 배낭의 한계
	int answer = knapsack(3, Weight, price, weight);

	cout << answer << endl;

	return 0;
}

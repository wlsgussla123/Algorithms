
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

vector<int> E, C;
int N;
int gas = 0;

int isEnableDrive() {
	int index = 0;
	int result = -1;
	bool end = false;
	int check = 0;

	// 일단은 다 돌아보자
	while (index < N) {
		int check = index; // check랑 같으면 답

		int i = index; // 인덱스는 크게 돌 것, i는 이번 회차에서 사용할 것
		gas = 0; // 가스 초기화s
		gas = gas + E[i]; // 먼저 가스 충전

		while (gas >= C[i]) {
			gas = gas - C[i]; // 가스 소모.
			
			if (i == N-1) {
				i = (i % (N-1));
			}
			else {
				i++;
			}

			// 답 나옴
			if (i == check) {
				result = index;
				end = true;
				break;
			}
			gas = gas + E[i]; // 가스 충전
		}
		if (end) break;
		index++; // 반복문을 나오면 가스가 부족하다는 뜻이므로 못 도달한다.
	}


	return result;
}

int main(int argc, const char *argv[]) {
	int T = 0;

	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> N;

		for (int i = 0; i < N; i++) {
			int energy = 0;
			cin >> energy;
			E.push_back(energy);
		}

		for (int i = 0; i < N; i++) {
			int cost = 0;
			cin >> cost;
			C.push_back(cost);
		}

		cout << isEnableDrive() << endl;
		gas = 0;
		E.clear();
		C.clear();
	}
}

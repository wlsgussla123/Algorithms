#include <stdio.h>
#include <utility>
#include <vector>

using namespace std;

int N, M;
int map[50][50] = { 0, };
int answer = 1; // 답이 될 변수
vector<pair<int, int>> v;
int temp = 0;

void findRow(int row, int col, int value);
void findCol(int row, int col, int value);

void findRow(int row, int col, int value) {
	bool check = false;

	for (int i = M - 1; i >= col; i--) {
		if (i != col && value == map[row][i]) {
			v.push_back(make_pair(row, i)); // 0 4
			check = true;
			break;
		}
	}

	if (check) {
		printf("TEST");
		findCol(row, col, value);
	}
	else {
		return;
	}
}

void findCol(int row, int col, int value) {
	bool check = false;

	for (int i = N - 1; i >= row; i--) {
		if (i != row && value == map[i][col]) {
			v.push_back(make_pair(i, col)); // 2 3
			check = true;
			break;
		}
	}

	if (check) {
		printf("TEST");
		findRow(v.back().first, v.back().second, value);
	}
	else {
		return;
	}
}

// 좌표 넓이 구하기
int calculator() {
	int cnt = 0;

	for (int i = v[3].first; i <= v[1].first; i++) {
		for (int j = v[3].second; j <= v[0].second; j++) {
			cnt++;
		}
	}
	
	return cnt;
}

int main(void) {
	int value = 0;

	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			value = map[i][j];
			
			findRow(i, j, value);
			
			if (v.size() != 3) {
				v.clear();
				continue;
			}

			v.push_back(make_pair(i,j));
			temp = calculator();

			if (temp > answer) {
				answer = temp;
			}

			v.clear();
		}		
	}

	printf("%d\n", answer);

	return 0;
}

/*
3 5 
4 2 1 0 1
2 2 1 0 0
2 2 1 0 1

2 3
1 1 
2 2 
2 2 
*/

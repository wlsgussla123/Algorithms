/*
	작성자 : 박진현
	문제 : 백준, 11724 연결요소의 개수

	설명 : 그래프 방문일 경우는 row만으로 판별 가능..
*/

#include <iostream>

using namespace std;

int map[1001][1001] = { 0, };
int visit[1001] = { 0, };

int N, M;

void DFS(int row) {
	visit[row] = 1;

	for (int i = 1; i <= N; i++) {
		if (map[row][i] && !visit[i]) {
			DFS(i);
		}
	}
}

int main(void) {
	scanf("%d %d", &N, &M);
	int row, col;
	for (int i = 0; i < M; i++) {
		scanf("%d %d", &row, &col);
		map[row][col] = map[col][row] = 1;
	}

	int answer = 0;
	for (int i = 1; i <= N; i++) {
		if (!visit[i]) {
			answer++;
			DFS(i);
		}
	}

	cout << answer << endl;

	return 0;
}

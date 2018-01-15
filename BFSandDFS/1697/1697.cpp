/*
	작성자 : 박진현
	문제 : 숨바꼭질 문제

	설명 : 최소 경로를 구할 때는 BFS쓰자.
*/

#include <iostream>
#include <queue>

using namespace std;

int N, K;
int map[100001] = { 0, };
int visit[100001] = { 0, };

void bfs() {
	queue<pair<int, int>> q;

	q.push({ N, 0 });
	while (!q.empty()) {
		int num = q.front().first;
		int answer = q.front().second;
		q.pop();
		visit[num] = 1;

		if (num == K) {
			cout << answer << endl;
			break;
		}

		if (num - 1 >= 0 && num - 1 <= 100000 && !visit[num - 1]) {
			q.push({ num - 1, answer + 1 });
		}

		if (num + 1 >= 0 && num + 1 <= 100000 && !visit[num + 1]) {
			q.push({ num + 1, answer + 1 });
		}

		if (num * 2 >= 0 && num * 2 <= 100000 && !visit[num * 2]) {
			q.push({ num * 2, answer + 1 });
		}
	}
}

int main(void) {
	scanf("%d %d", &N, &K);
	map[N] = map[K] = 1;
	bfs();

	return 0;
}

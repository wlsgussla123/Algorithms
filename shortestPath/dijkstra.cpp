/*
	작성자 : 박진현
	다익스트라 알고리즘. (not use 우선순위 큐)
*/

#include <iostream>

#define MAX_VERTEX 20001
#define INF 987654321

using namespace std;

int map[MAX_VERTEX][MAX_VERTEX] = { 0, };
int answer[MAX_VERTEX];
int visit[MAX_VERTEX] = { 0, };
int V, E, K; // vertex, edge, 시작점

// 다익스트라
void dijkstra() {
	int min = INF;
	int minIndex = K-1;

	for (int i = 0; i < V; i++) {
		min = INF;

		for (int j = 0; j < V; j++) {
			if (!visit[j] && min > answer[j]) {
				min = answer[j];
				minIndex = j;
			}
		}
		visit[minIndex] = 1;

		for (int k = 0; k < V; k++) {
			if (answer[k] > answer[minIndex] + map[minIndex][k]) {
				answer[k] = answer[minIndex] + map[minIndex][k];
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &V, &E);
	scanf("%d", &K);

	// 경로 초기화
	for (int i = 0; i < V; i++) for (int j = 0; j < V; j++) map[i][j] = INF;
	for (int i = 0; i < V; i++) answer[i] = INF;
	answer[K - 1] = 0; // 시작점 거리 0
	visit[K - 1] = 1; // 시작점은 방문한 것으로 

	// 경로 간의 거리 셋팅
	for (int i = 0; i < E; i++) {
		int start, end, value;
		scanf("%d %d %d", &start, &end, &value);
		map[start - 1][end - 1] = value;
	}

	dijkstra();
	for (int i = 0; i < V; i++) {
		if (answer[i] == INF) cout << "INF" << endl;
		else cout << answer[i] << endl;
	}

	return 0;
}

/*
6 9
1
1 2 10
1 3 30		
1 4 15
2 5 20
3 6 5
4 3 5
4 6 20
5 6 20
6 4 20
*/

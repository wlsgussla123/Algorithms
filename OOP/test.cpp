#include <iostream>
#include <stdlib.h>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

class Task {
private:
	int **map;
	int **visit;
	int M, N, cnt;

	int dir[4][2] = { { 0,1 },{ 0, -1 },{ 1,0 },{ -1,0 } };
public:
	void run();
	void input();
	void drawMap();
	void printMap();

	void bfs(int row, int col);
	bool checkCondition(int row, int col);
	bool checkRange(int row, int col);

	int answer();

	Task() {}
};

int Task::answer() {
	int result = 0;

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			result = max(result, map[i][j]);
		}
	}

	return result - 1; // 시작이 1로 하니까
}

void Task::bfs(int row, int col) {
	queue <pair<pair<int, int>, int>> q;
	q.push(make_pair(make_pair(row, col), cnt));

	while (!q.empty()) {
		int x = q.front().first.first;
		int y = q.front().first.second;
		int cnt = q.front().second;

		visit[x][y] = 1;
		map[x][y] = cnt;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (checkRange(nx, ny)) {
				if (!visit[nx][ny]) q.push(make_pair(make_pair(nx, ny), cnt + 1));
			}
		}
	}
}

void Task::run() {
	cnt = 1;
	input();
	drawMap();

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (checkCondition(i, j)) {
				bfs(i, j);
				//cout << "entry : " << i << " " << j << endl;
			}
		}
	}
}

bool Task::checkRange(int nx, int ny) {
	if (nx >= 0 && nx < M && ny >= 0 && ny < N) return true;
	else return false;
}

bool Task::checkCondition(int row, int col) {
	if (!visit[row][col] && map[row][col]) return true;
	else return false;
}

void Task::input() {
	cin >> N;
	cin >> M;
}

void Task::drawMap() {
	map = (int **)malloc(sizeof(int *) * M);
	visit = (int **)malloc(sizeof(int *) * M);
	for (int i = 0; i < M; i++) map[i] = (int *)malloc(sizeof(int) * N);
	for (int i = 0; i < M; i++)	visit[i] = (int *)malloc(sizeof(int) * N);

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			visit[i][j] = 0;
		}
	}
}

void Task::printMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cout << map[i][j] << " ";
		}
		cout << endl;
	}
}

int main(void) {
	Task task = Task();
	task.run();
	cout << task.answer() << endl;

	return 0;
}

/*
 *	작성자 : 박진현
 *
 */
#include <iostream>
#include <stdlib.h>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int M,N;
int **map;
int **visit;
queue<pair<pair<int, int>, int>> q;

char answerChar = 'T';
int answer = 0;
// queue<pair<int, int>> q;

// 방향 (8가지의 경우의 수)
int dir[8][2] = {{1,2}, {-1,2}, {-1, -2}, {1, -2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}};

void input() {
	cin >> M;
	cin >> N;
	
	if(M < 2 || N < 2) {
		cout << "-1" << endl;
		exit(1);
	}
}

void drawMap() {
	map = (int **)malloc(sizeof(int *) * M);
	visit = (int **)malloc(sizeof(int *) * M);
	
	for(int i=0; i<M; i++) {
		map[i] = (int *)malloc(sizeof(int) * N);
		visit[i] = (int *)malloc(sizeof(int) * N);
	}
}

void initMap() {
	for(int i=0; i<M; i++) {
		for(int j=0; j<N; j++) {
			map[i][j] = 0;
			visit[i][j] = 0;
		}
	}	
}

// 초기에 진행되는 것들
void init() {
	input();
	drawMap();
	initMap();
	// printMap();
}

void bfs(int row, int col, int cnt) {
	q.push(make_pair(make_pair(row, col), cnt));
	
	while(!q.empty()) {
		int x = q.front().first.first;
		int y = q.front().first.second;
		int num = q.front().second;

		map[x][y] = num;
		visit[x][y] = 1;

		q.pop();
		
		
		for(int i=0; i<8; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
				if(!visit[nx][ny] && !map[nx][ny]) {
					q.push(make_pair(make_pair(nx, ny), num+1));
				}
			}
		}
	}	
}

// void dfs(int row, int col, int cnt) {
// 	map[row][col] = cnt;
// 	visit[row][col] = 1;
	
// 	for(int i=0; i<8; i++) {
// 		int nx = row + dir[i][0];
// 		int ny = col + dir[i][1];

// 		if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
// 			if(!visit[nx][ny] && map[nx][ny] != 0) {
// 				dfs(nx,ny,cnt++);
// 			}
// 		}		
// 	}
// }

void checkNumber() {
	for(int i=0; i<M; i++) {
		for(int j=0; j<N; j++) {
			answer = max(answer, map[i][j]);
		}
	}
}

void checkChar() {
	for(int i=0; i<M; i++) {
		for(int j=0; j<N; j++) {
			if(!map[i][j]) {
				answerChar = 'F';
				return;
			}
		}
	}
}

void printMap() {
	for(int i=0; i<M; i++) {
		for(int j=0; j<N; j++) {
			cout << map[i][j] << " ";
		}
		cout << endl;
	}
}

int main(int argc, char* argv[]) {
	init();
	
	bfs(0,0,1);	// 1을 보냈기 때문에 답에서는 -1을 해주자
	// printMap();
	
	checkNumber();
	checkChar();
	cout << answerChar << answer-1 <<endl;
	
	return 0;
}

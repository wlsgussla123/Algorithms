/*
	작성자 : 박진현
	문제 : 백준 1032, 명령 프롬프트 문제
*/

#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

int main(void) {
	int N;
	vector<string> v;
	char answer[50];
	int cnt = 0;

	// 입력문
	scanf("%d", &N);
	string temp;
	for (int i = 0; i < N; i++) {
		cin >> temp;
		v.push_back(temp);
	}
	
	int len = v.front().size();
	if (N == 1) {
		for (int i = 0; i < v.front().size(); i++) {
			answer[i] = v[0][i];
		}
	}
	else {
		// 문자가 다르면 ?, 같으면 해당하는 문자  삽입.

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < len; j++) {
				if (answer[j] == '?') continue;

				if (v[i][j] == v[i + 1][j]) {
					answer[j] = v[i][j];
				}
				else {
					answer[j] = '?';
				}
			}
		}
	}
	answer[len] = '\0';

	printf("%s\n", answer);
	return 0;
}

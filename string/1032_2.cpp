/*
	작성자: 박진현
	문제 : 백준 1032번, 명령프롬프트
	설명 : 스터디를 통하여 코드 리팩토링
*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(void) {
	int N = 0;
	string result;
	string strs[50];

	scanf("%d", &N);
	getchar(); // 개행 먹기
	for (int i = 0; i < N; i++) {
		getline(cin, strs[i]);
	}

	result.assign(strs[0]);
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < strs[i].size(); j++) {
			if (result[j] != strs[i][j]) {
				result.replace(j, 1, "?");
			}
		}
	}

	cout << result << endl;

	return 0;
}

/*
	작성자 : 박진현
	문제 : 백준 1120, 문자열 문제
*/

#include <stdio.h>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main(void) {
	string str1;
	string str2;

	int diff = 0;
	int diffCnt1 = 0;
	int diffCnt2 = 0;
	int answer = 0;

	cin >> str1;
	cin >> str2;

	// str1 < str2이면 str1 > str2로
	string temp;
	if (str2.size() > str1.size()) {
		temp = str1;
		str1 = str2;
		str2 = temp;
	}

	diff = str1.size() - str2.size(); // 길이의 차를 구하고
	temp.clear();

	// 뒤에 문자를 추가한 임의의 문자열 생성
	for (int i = 0; i < str2.size() + diff; i++) {
		if (i < str2.size()) {
			temp.push_back(str2[i]);
		}
		else {
			temp.push_back(str1[i]);
		}
	}

	// 생성한 문자열로 비교
	for (int i = 0; i < str1.size(); i++) {
		if (str1[i] != temp[i]) {
			diffCnt1++;
		}
	}

	cout << temp << endl;
	temp.clear(); // temp를 다시 비우고
	//// 앞에 문자를 추가한 임의의 문자열 생성
	for (int i = 0; i < str2.size() + diff; i++) {
		if (i < diff) {
			temp.push_back(str1[i]);
		}
		else {
			temp.push_back(str2[i - diff]);
		}
	}

	// 생성한 문자열로 비교
	for (int i = 0; i < str1.size(); i++) {
		if (str1[i] != temp[i]) {
			diffCnt2++;
		}
	}

	cout << temp << endl;

	answer = min(diffCnt1, diffCnt2);
	printf("%d\n", answer);
	
	return 0;
}

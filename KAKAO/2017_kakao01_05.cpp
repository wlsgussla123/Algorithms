/*
	작성자 : 박진현
	문제 : 카카오 1차 채용 테스트 문제 5번
	작성일 : 17.10.06
	설명 : 의외로 숫자 형변환에서 시간 좀 걸렸음. 
*/

#include <stdio.h>
#include <string>
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(void) {
	string str1 = "";
	string str2 = "";
	string temp = "";

	vector<string> v1;
	vector<string> v2;

	double answer = 0.0;

	getline(cin, str1);
	getline(cin, str2);

	// 대문자로 변형
	transform(str1.begin(), str1.end(), str1.begin(), toupper);
	transform(str2.begin(), str2.end(), str2.begin(), toupper);

	// 문자열 set 
	for (int i = 0; i < str1.size(); i++) {
		if (str1[i] >= 'A' && str1[i] <= 'Z' && i+1 != str1.size() && str1[i+1] >= 'A' && str1[i+1] <= 'Z') {
			temp = temp + str1[i] + str1[i + 1];
			v1.push_back(temp);
			temp = "";
		}
	}

	// 문자열 set 
	for (int i = 0; i < str2.size(); i++) {
		if (str2[i] >= 'A' && str2[i] <= 'Z' && i + 1 != str2.size() && str2[i + 1] >= 'A' && str2[i + 1] <= 'Z') {
			temp = temp + str2[i] + str2[i + 1];
			v2.push_back(temp);
			temp = "";
		}
	}

	int intersectionCnt = 0;
	int unionCnt = v1.size() + v2.size();

	// 교집합 구하기
	for (int i = 0; i < v1.size(); i++) {
		for (int j = 0; j < v2.size(); j++) {
			if (!v1[i].compare(v2[j])) {
				intersectionCnt++;
				break;
			}
		}
	}

	unionCnt -= intersectionCnt;

	// 합집합과 교집합의 차가 0이라는 것은 자카드 유사도가 1이라는 것
	if (unionCnt == 0) {
		printf("65536\n");
		return 0;
	}

	answer = (1.0 * intersectionCnt / unionCnt) * 65536;
	printf("%d\n", (int)answer);

	return 0;
}

/*
	작성자 : 박진현
	문제 : 백준 10610번, 30
	설명 : 정수론을 알아야 풀 수 있는 문제다. 3의 배수는 각 자릿수의 합이 3의 배수, 10의 배수는 끝자리가 0이어야 하기 때문에 30에 대한 정수론은 이를 만족하는 것.
*/
#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	string num;
	bool zeroCheck = false;
	int sum = 0;

	cin >> num;

	for (int i = 0; i < num.length(); i++) {
		if (num[i] == '0')
			zeroCheck = true;

		sum += num[i] - '0';
	}

	// string 오름차순 해놓고
	if (sum % 3 == 0 && zeroCheck) {
		sort(num.begin(), num.end());
		// string을 뒤집으면 그것이 string의 내림차순이다.
		reverse(num.begin(), num.end());
	}
	else {
		num = "-1";
	}

	cout << num;

	return 0;
}

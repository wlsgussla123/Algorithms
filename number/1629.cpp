/*
	작성자 : 박진현
	문제 : 백준 1629번
*/

#include <iostream>

using namespace std;

long long result(long long A, long long B, long long C) {
	long long answer = 1LL;
	
	// A * B % M = (A % M) * (B % M) % M
	// A^8 => 8이 2^3 이므로 3번 제곱을 하는 것으로 대체하면, 3번의 곱셈 
	while (B > 1) {
		if (B % 2 == 1) {
			answer = (answer % C) * (A % C) % C;
		}
		A = (A % C) * (A % C) % C;
		B = B / 2;
	}
	
	return (answer % C) * (A % C) % C;
}

int main(void) {
	long long A, B, C;

	cin >> A;
	cin >> B;
	cin >> C;

	cout << result(A, B, C) << endl;

	return 0;
}

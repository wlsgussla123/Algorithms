/*
	작성자 : 박진현
	문제 : 카카오 1차 채용 3번 문제

	설명 : 의외로 문자열을 입력받고 구분하는 부분이 오래 걸렸다. 왜 C++은 문자열 구분자를 지원하지 않는가...
*/

#include <stdio.h>
#include <sstream>
#include <vector>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

vector<string> caches;
vector<string> v_cities;

int main(void) {
	int N;
	int cnt = 0;
	int answer = 0; // 실행시간

	bool flag = false;

	string cities;

	scanf("%d", &N); // 캐시 사이즈 입력
	getc(stdin); // 개행 처리
	getline(cin, cities); // 도시입력, getline으로 해야 space bar 포함
	transform(cities.begin(), cities.end(), cities.begin(), toupper); // 대문자로 변경

	/*
		stringstream은 공백을 기준으로 읽어드림.
	*/
	stringstream stream(cities);
	string buf;
	string test = "seoul";
	
	// 버퍼에 임시로 넣고 (공백을 기준으로 stream이 읽기 때문에 공백 단위로 단어를 받는다.)
	while (stream >> buf) {
		v_cities.push_back(buf); // 단어를 vector에 넣어주세여
	}

	// LRU 알고리즘
	if (N != 0) {
		for (int i = 0; i < v_cities.size(); i++) {
			// cache에 있는지 확인
			for (int j = 0; j < caches.size(); j++) {
				if (!v_cities[i].compare(caches[j])) {
					caches.erase(caches.begin() + j);
					caches.push_back(v_cities[i]);
					answer += 1;
					flag = true;

					break;
				}
			}

			// 위에서 변경했으면 넘어가도 됨.
			if (flag) {
				flag = false;
				continue;
			}

			// 캐시가 비었으면 그냥 넣자.
			if (cnt != N) {
				caches.push_back(v_cities[i]);
				cnt++;
				answer += 5;
			} // 캐시가 꽉 찼으면 
			else if (cnt == N) {
				caches.erase(caches.begin());
				caches.push_back(v_cities[i]);
				answer += 5;
			}
		}
	}
	else {
		// 캐시가 없다면 
		for (int i = 0; i < v_cities.size(); i++) {
			answer += 5;
		}
	}


	printf("%d\n", answer);
	return 0;
}

/*
jeju pangyo seoul newyork la jeju pangyo seoul newyork la 
Jeju Pangyo Seoul Jeju Pangyo Seoul Jeju Pangyo Seoul 
Jeju Pangyo newyork newyork - 16
jeju pangyo seoul newyork la sanfrancisco seoul rome paris jeju newyork rome
*/

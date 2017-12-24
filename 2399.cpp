#include <stdio.h>

using namespace std;

int N;
int x[10000];
long long answer = 0;

int abs(int x) {
    if(x >= 0) return x;
    else return -x;
}

int main(void) {
    scanf("%d", &N);
    for(int i=0; i<N; i++) {
        scanf("%d", &x[i]);
    }
    
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            answer += abs(x[i] - x[j]);
        }
    }
    
    printf("%lld\n", answer);
    return 0;
}

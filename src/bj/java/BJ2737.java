package bj.java;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ2737 {

	// 매우 수학적인 문제이다.
	// 홀수의 개수 - 1 이 정답이다.
	// N = a+(a+1)+(a+2)+⋯+(a+k−1)
	// 2N=k(2a+k−1) 을 가지고 왜 그런지 고민해보자.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			long N = Long.parseLong(br.readLine());
			int count = countOddDivisors(N);

			// 자기 자신 하나로 이루어진 경우 제외
			sb.append(count - 1).append('\n');
		}

		System.out.print(sb.toString());
	}

	// N의 홀수 약수 개수 계산
	private static int countOddDivisors(long n) {
		int cnt = 0;

		// 2의 배수 제거
		while (n % 2 == 0) {
			n /= 2;
		}

		// 홀수 약수만 검사
		for (long i = 1; i * i <= n; i += 2) {
			if (n % i == 0) {
				cnt++; // i
				if (i != n / i) {
					cnt++; // n / i
				}
			}
		}

		return cnt;
	}
}

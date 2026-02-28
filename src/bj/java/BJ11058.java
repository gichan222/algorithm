package bj.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ11058 {

	/*
	하나의 값에서 뻗어 나갈 수 있는 행위
	1. 그냥 뒤에 A 추가
	2. 버퍼가 있었다면 C-V하고 추가
	3. C-A C-C C-V하고 3단계 뒤를 고려
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[] dp = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			// 1) A 누르기
			dp[i] = dp[i - 1] + 1;

			// 2) j에서 Ctrl-A, Ctrl-C 하고 (i-j-2)번 Ctrl-V
			// 최소 1번 붙여넣기 필요 => j <= i-3
			for (int j = 1; j <= i - 3; j++) {
				int k = i - j - 2;             // Ctrl-V 횟수
				dp[i] = Math.max(dp[i], dp[j] * (k + 1)); // (k+1)배!
				// == dp[j] * (i - j - 1)
			}
		}

		System.out.println(dp[N]);
	}
}

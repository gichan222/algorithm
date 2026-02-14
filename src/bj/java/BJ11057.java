package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11057 {

	static final int MOD = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N + 1][10];
		int[] sum = new int[N + 1];

		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		sum[1] = 10;

		for (int i = 2; i <= N; i++) {

			dp[i][0] = sum[i - 1] % MOD;

			int temp = dp[i][0];
			int minus = dp[i - 1][0];

			for (int j = 1; j < 10; j++) {

				dp[i][j] = (sum[i - 1] - minus) % MOD;
				if (dp[i][j] < 0) dp[i][j] += MOD;

				minus = (minus + dp[i - 1][j]) % MOD;
				temp = (temp + dp[i][j]) % MOD;
			}

			sum[i] = temp;
		}

		System.out.println(sum[N]);
	}
}

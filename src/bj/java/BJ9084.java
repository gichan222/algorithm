package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		while(T-- > 0){
			int N = Integer.parseInt(br.readLine()); // 동전 종류 개수
			st = new StringTokenizer(br.readLine());
			int[] coins = new int[N+1];
			for(int i = 1; i <= N; i++){
				coins[i] = Integer.parseInt(st.nextToken()); // 동전 종류
			}
			int M = Integer.parseInt(br.readLine()); // 만들어야 할 금액
			int[] dp = new int[M+1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (j - coins[i] > 0) {
						dp[j] = dp[j] + dp[j-coins[i]];
					} else if (j - coins[i] == 0) {
						dp[j]++;
					}
				}
			}
			System.out.println(dp[M]);
		}
	}
}

package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2616 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] train = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			train[i] = Integer.parseInt(st.nextToken());
		}

		int len = Integer.parseInt(br.readLine());
		int[] sum = new int[n+1];
		sum[0] = 0;
		// 누적합
		for(int i=0; i<n; i++) {
			sum[i+1] = sum[i] + train[i];
		}

		// dp 배열
		int[][] dp = new int[4][n+1];
		int res = 0;
		for(int i=1; i<4; i++){
			for(int j=1; j<n+1; j++) {
				dp[i][j] = 0;
				if(j >= len) {
					// dp 점화식
					// 이전 기차를 통해 계산
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-len] + (sum[j] - sum[j-len]));
				}
				res = Math.max(dp[i][j], res);
			}
		}
		System.out.println(res);
	}
}

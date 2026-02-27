package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2502 {

	/*
	1 : x
	2 : y
	3 : x + y
	4 : x + 2y
	5 : 2x + 3y
	6 : 3x + 5y
	7 : 5x + 8y
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[D+1][2];
		dp[1][0] = 1;
		dp[2][1] = 1;
		for(int i = 3; i <= D; i++){
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}

		for(int i = 1; i <= K; i++){
			int rest = K - dp[D][0] * i;
			if(rest % dp[D][1] == 0){
				System.out.println(i + "\n" + rest / dp[D][1]);
				return;
			}
		}

	}
}

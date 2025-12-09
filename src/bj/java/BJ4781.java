package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class BJ4781 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		BigDecimal m = new BigDecimal(st.nextToken());

		while (n != 0) {
			// BigDecimal로 받지 않으면 부동소수점에 의한 오류 발생 가능
			int size = m.multiply(new BigDecimal("100")).intValue(); // 오차 없이 센트로 변환
			int[] dp = new int[size + 1];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());

				// 가격 p도 BigDecimal로 받기
				BigDecimal p = new BigDecimal(st.nextToken());
				int resizeP = p.multiply(new BigDecimal("100")).intValue(); // 안전하게 변환

				for (int j = resizeP; j <= size; j++) {
					dp[j] = Math.max(dp[j], dp[j - resizeP] + c);
				}
			}

			sb.append(dp[size]).append("\n");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			if (n == 0) break;

			m = new BigDecimal(st.nextToken());
		}

		System.out.println(sb);
	}
}

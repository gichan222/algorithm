package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1451 {

	static int N, M;
	static long[][] sum;

	// (x1, y1) ~ (x2, y2) 직사각형 합
	static long getSum(int x1, int y1, int x2, int y2) {
		long res = sum[x2][y2];
		if (x1 > 0) res -= sum[x1 - 1][y2];
		if (y1 > 0) res -= sum[x2][y1 - 1];
		if (x1 > 0 && y1 > 0) res += sum[x1 - 1][y1 - 1];
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sum = new long[N][M];

		// 입력 + 누적합 계산
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				int val = line.charAt(j) - '0';
				sum[i][j] = val;
				if (i > 0) sum[i][j] += sum[i - 1][j];
				if (j > 0) sum[i][j] += sum[i][j - 1];
				if (i > 0 && j > 0) sum[i][j] -= sum[i - 1][j - 1];
			}
		}

		long max = 0;

		// 가로 3등분
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				long a = getSum(0, 0, i, M - 1);
				long b = getSum(i + 1, 0, j, M - 1);
				long c = getSum(j + 1, 0, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		// 세로 3등분
		for (int i = 0; i < M - 2; i++) {
			for (int j = i + 1; j < M - 1; j++) {
				long a = getSum(0, 0, N - 1, i);
				long b = getSum(0, i + 1, N - 1, j);
				long c = getSum(0, j + 1, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		// 위 1 + 아래 2 (아래 세로 분할)
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				long a = getSum(0, 0, i, M - 1);
				long b = getSum(i + 1, 0, N - 1, j);
				long c = getSum(i + 1, j + 1, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		// 아래 1 + 위 2 (위 세로 분할)
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				long a = getSum(0, 0, i, j);
				long b = getSum(0, j + 1, i, M - 1);
				long c = getSum(i + 1, 0, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		// 왼 1 + 오른 2 (오른 가로 분할)
		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				long a = getSum(0, 0, N - 1, i);
				long b = getSum(0, i + 1, j, M - 1);
				long c = getSum(j + 1, i + 1, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		// 오른 1 + 왼 2 (왼 가로 분할)
		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				long a = getSum(0, 0, j, i);
				long b = getSum(j + 1, 0, N - 1, i);
				long c = getSum(0, i + 1, N - 1, M - 1);
				max = Math.max(max, a * b * c);
			}
		}

		System.out.println(max);
	}
}

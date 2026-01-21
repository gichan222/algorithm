package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ1722 {

	static int N;
	static BigInteger[] fact;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int command = Integer.parseInt(st.nextToken());

		// 팩토리얼 미리 계산
		fact = new BigInteger[N + 1];
		fact[0] = BigInteger.ONE;
		for (int i = 1; i <= N; i++) {
			fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
		}

		used = new boolean[N + 1];

		// 명령 1: k번째 순열 출력
		if (command == 1) {
			BigInteger k = new BigInteger(st.nextToken());
			int[] result = new int[N];

			for (int i = 0; i < N; i++) {
				BigInteger block = fact[N - i - 1];

				for (int num = 1; num <= N; num++) {
					if (used[num]) continue;

					if (k.compareTo(block) > 0) {
						k = k.subtract(block);
					} else {
						result[i] = num;
						used[num] = true;
						break;
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(result[i]).append(" ");
			}
			System.out.println(sb);

		}
		// 명령 2: 순열의 순서 출력
		else {
			int[] perm = new int[N];
			for (int i = 0; i < N; i++) {
				perm[i] = Integer.parseInt(st.nextToken());
			}

			BigInteger answer = BigInteger.ONE;

			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int num = 1; num < perm[i]; num++) {
					if (!used[num]) cnt++;
				}

				answer = answer.add(
					fact[N - i - 1].multiply(BigInteger.valueOf(cnt))
				);

				used[perm[i]] = true;
			}

			System.out.println(answer);
		}
	}
}

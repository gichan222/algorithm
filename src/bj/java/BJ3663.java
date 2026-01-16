package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3663 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());

		while (T-- > 0) {
			String name;
			while (true) {
				name = br.readLine();
				if (name != null && !name.trim().isEmpty()) {
					name = name.trim();   // 개행/공백 제거 (중요)
					break;
				}
			}

			int n = name.length();
			int answer = 0;

			// 상하 이동 계산
			for (int i = 0; i < n; i++) {
				char c = name.charAt(i);
				answer += Math.min(c - 'A', 'Z' - c + 1);
			}

			// 좌우 이동 최소화
			int move = n - 1; // 기본: 오른쪽으로만 이동

			for (int i = 0; i < n; i++) {
				int next = i + 1;
				while (next < n && name.charAt(next) == 'A') {
					next++;
				}

				// 오른쪽 → 왼쪽
				move = Math.min(move, i * 2 + n - next);
				// 왼쪽 → 오른쪽
				move = Math.min(move, i + 2 * (n - next));
			}

			answer += move;
			System.out.println(answer);
		}
	}
}

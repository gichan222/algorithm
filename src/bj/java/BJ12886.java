package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ12886 {

	static class Stone {
		int a, b, c;

		Stone(int x, int y, int z) {
			int[] arr = {x, y, z};
			Arrays.sort(arr);
			this.a = arr[0];
			this.b = arr[1];
			this.c = arr[2];
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Stone)) return false;
			Stone s = (Stone) o;
			return a == s.a && b == s.b && c == s.c;
		}

		@Override
		public int hashCode() {
			return Objects.hash(a, b, c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int sum = A + B + C;

		// 🔥 필수 조건
		if (sum % 3 != 0) {
			System.out.println(0);
			return;
		}

		Queue<Stone> q = new ArrayDeque<>();
		Set<Stone> visited = new HashSet<>();

		Stone start = new Stone(A, B, C);
		q.add(start);
		visited.add(start);

		while (!q.isEmpty()) {
			Stone cur = q.poll();

			if (cur.a == cur.b && cur.b == cur.c) {
				System.out.println(1);
				return;
			}

			int[] arr = {cur.a, cur.b, cur.c};

			// 모든 쌍 시도
			for (int i = 0; i < 3; i++) {
				for (int j = i + 1; j < 3; j++) {
					if (arr[i] == arr[j]) continue;

					int x = arr[i];
					int y = arr[j];

					int small = Math.min(x, y);
					int big = Math.max(x, y);

					int nx = small + small;
					int ny = big - small;

					int[] next = arr.clone();
					next[i] = nx;
					next[j] = ny;

					Stone nextStone = new Stone(next[0], next[1], next[2]);

					if (!visited.contains(nextStone)) {
						visited.add(nextStone);
						q.add(nextStone);
					}
				}
			}
		}

		System.out.println(0);
	}
}

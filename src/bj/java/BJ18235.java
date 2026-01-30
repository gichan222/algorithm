package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18235 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;

		Queue<Integer> fiveDuck = new ArrayDeque<>();
		Queue<Integer> sixDuck = new ArrayDeque<>();

		fiveDuck.add(A);
		sixDuck.add(B);

		int day = 0;

		while (!fiveDuck.isEmpty() && !sixDuck.isEmpty()) {
			day++;
			int dist = 1 << (day - 1);

			boolean[] visited = new boolean[N];

			int size = fiveDuck.size();
			for (int i = 0; i < size; i++) {
				int cur = fiveDuck.poll();

				if (cur - dist >= 0 && !visited[cur - dist]) {
					visited[cur - dist] = true;
					fiveDuck.add(cur - dist);
				}
				if (cur + dist < N && !visited[cur + dist]) {
					visited[cur + dist] = true;
					fiveDuck.add(cur + dist);
				}
			}

			size = sixDuck.size();
			for (int i = 0; i < size; i++) {
				int cur = sixDuck.poll();

				if (cur - dist >= 0) {
					if (visited[cur - dist]) {
						System.out.println(day);
						return;
					}
					sixDuck.add(cur - dist);
				}
				if (cur + dist < N) {
					if (visited[cur + dist]) {
						System.out.println(day);
						return;
					}
					sixDuck.add(cur + dist);
				}
			}
		}

		System.out.println(-1);
	}
}

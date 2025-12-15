package bj.java;

import java.io.*;
import java.util.*;

public class BJ1525 {

	// 정답 문자열
	static final String ANSWER = "123456780";

	// 방향
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder start = new StringBuilder();

		// 시작 문자열
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				start.append(st.nextToken());
			}
		}

		System.out.println(bfs(start.toString()));
	}

	// 배열이 아닌 문자열으로 처리하기
	static int bfs(String start) {
		// bfs용 큐
		Queue<String> q = new LinkedList<>();

		// 방문 처리를 위한 문자열 집합
		// 문자열이 동일하다면 체크한 것으로 처리
		Set<String> visited = new HashSet<>();

		q.offer(start);
		visited.add(start);

		int depth = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				String cur = q.poll();

				if (cur.equals(ANSWER)) {
					return depth;
				}

				int zeroIdx = cur.indexOf('0');
				// 인덱스를 문자열 -> 배열처럼 사용하기 위함
				int x = zeroIdx / 3;
				int y = zeroIdx % 3;

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;

					int swapIdx = nx * 3 + ny;

					String next = swap(cur, zeroIdx, swapIdx);

					if (!visited.contains(next)) {
						visited.add(next);
						q.offer(next);
					}
				}
			}
			depth++;
		}

		return -1;
	}

	static String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return new String(arr);
	}
}

package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7511 {

	static int[] parent;
	static final String SCENARIO = "Scenario ";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		int cnt = 1;

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine()); // 유저 수
			parent = new int[n];

			// 부모 배열 초기화
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}

			int k = Integer.parseInt(br.readLine()); // 친구 관계 수
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			sb.append(SCENARIO).append(cnt++).append(":\n");

			int m = Integer.parseInt(br.readLine()); // 질의 수
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 같은 집합이면 1, 아니면 0
				sb.append(find(a) == find(b) ? 1 : 0).append("\n");
			}
			sb.append("\n"); // 시나리오 간 빈 줄
		}

		System.out.print(sb);
	}

	// 두 노드를 같은 집합으로 합침
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			if (rootA > rootB) {
				parent[rootA] = rootB;
			} else {
				parent[rootB] = rootA;
			}
		}
	}

	// 루트 노드 찾기 (경로 압축)
	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
}

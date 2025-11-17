package bj.java;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1765 {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// N: 사람 수, M: 관계 수
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		// enemies[i] : i의 적 목록
		// friends[i] : i의 친구 목록
		ArrayList<Integer>[] enemies = new ArrayList[N+1];
		ArrayList<Integer>[] friends = new ArrayList[N+1];

		parent = new int[N+1]; // 유니온파인드

		for (int i = 1; i <= N; i++) {
			friends[i] = new ArrayList<>();
			enemies[i] = new ArrayList<>();
			parent[i] = i; // 초기 부모는 자기 자신
		}

		// 입력 관계 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (type.equals("E")) { // 적 관계
				enemies[a].add(b);
				enemies[b].add(a);
			} else { // 친구 관계
				friends[a].add(b);
				friends[b].add(a);
			}
		}

		// "적의 적 = 친구" 규칙 적용
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < enemies[i].size(); j++) {
				int enemy = enemies[i].get(j);

				// enemy의 적들은 모두 i의 친구
				for (int k = 0; k < enemies[enemy].size(); k++) {
					int enemyOfEnemy = enemies[enemy].get(k);

					if (i == enemyOfEnemy) continue; // 자기 자신은 무시

					// 친구 목록에 추가 (양방향)
					friends[i].add(enemyOfEnemy);
					friends[enemyOfEnemy].add(i);
				}
			}
		}

		// 모든 친구들을 유니온파인드로 묶기
		for (int i = 1; i <= N; i++) {
			for (int f : friends[i]) {
				union(i, f);
			}
		}

		// 유니온파인드의 대표자 개수 = 그룹 수
		HashSet<Integer> hs = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			hs.add(find(i)); // parent[i]가 아닌 find(i)를 넣어야 정확함
		}

		System.out.println(hs.size());
	}

	// 유니온파인드 find
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

	// 유니온파인드 union
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) parent[b] = a;
	}
}

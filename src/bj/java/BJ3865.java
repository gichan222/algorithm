package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ3865 {

	// 부모 자식 관계를 통해 리스트를 모두 수집함
	static Map<String, List<String>> graph;
	static Set<String> result;
	static Set<String> visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;

			graph = new HashMap<>();
			result = new HashSet<>();
			visited = new HashSet<>();

			String target = null;

			for (int i = 0; i < n; i++) {
				String line = br.readLine().replace(".", "");
				String[] parts = line.split(":");

				String key = parts[0];
				if (i == 0) target = key;

				graph.putIfAbsent(key, new ArrayList<>());

				String[] values = parts[1].split(",");
				for (String v : values) {
					graph.get(key).add(v);
				}
			}

			dfs(target);
			System.out.println(result.size());
		}
	}

	// 깊이 탐색을 통해 자식 모두 수집
	static void dfs(String key) {
		if (visited.contains(key)) return;
		visited.add(key);

		for (String next : graph.get(key)) {
			if (graph.containsKey(next)) {
				dfs(next);
			} else {
				result.add(next);
			}
		}
	}
}

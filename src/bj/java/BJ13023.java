package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ13023 {

	static int N, M;
	static Map<Integer, List<Integer>> edges = new HashMap<>();
	static boolean[] visited;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		for(int i = 0; i < N; i++){
			edges.put(i, new ArrayList<>());
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			edges.get(b).add(a);
		}

		for(int i = 0; i < N; i++){
			dfs(i, 1);
		}
		if(flag){
			return;
		}
		System.out.println(0);
	}

	static void dfs(int cur, int depth){
		visited[cur] = true;
		if(flag){
			return;
		}
		if(depth == 5){
			System.out.println(1);
			flag = true;
			return;

		}
		for(int next : edges.get(cur)){
			if(visited[next]){
				continue;
			}
			dfs(next, depth+1);
		}
		visited[cur] = false;
	}
}

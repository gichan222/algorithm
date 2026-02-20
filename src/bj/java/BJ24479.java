package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ24479 {

	static int N, M, R;
	static int[] order;
	static int cnt = 1;
	static Map<Integer, List<Integer>> edges = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		order = new int[N+1];

		for(int i = 0; i <= N; i++){
			edges.put(i, new ArrayList<>());
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			edges.get(f).add(s);
			edges.get(s).add(f);
		}

		for(int i = 0; i <= N; i++){
			Collections.sort(edges.get(i));
		}
		dfs(R);

		for(int i = 1; i <= N; i++){
			System.out.println(order[i]);
		}
	}

	static void dfs(int now){
		order[now] = cnt++;

		for(int next : edges.get(now)){
			if(order[next] != 0){
				continue;
			}
			dfs(next);
		}
	}
}

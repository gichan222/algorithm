package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ14699 {

	static int N, M;
	static List<Set<Integer>> edges = new ArrayList<>();
	static int[] height;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		height = new int[N+1];
		dp = new int[N+1];
		for(int i = 0; i <= N; i++){
			edges.add(new HashSet<>());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++){
			height[i] = Integer.parseInt(st.nextToken()); // 높이
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			edges.get(b).add(a);
		}

		for(int i = 1; i <= N; i++){
			if(dp[i] != 0){
				continue;
			}
			dfs(i,1);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++){
			sb.append(dp[i]).append("\n");
		}
		System.out.println(sb);
	}

	static int dfs(int start, int depth){
		for(int next : edges.get(start)){
			if(height[start] >= height[next]){ // 높이가 더 낮은 곳으론 이동 x
				continue;
			}
			if(dp[next] >= depth + 1){ // 이미 경로가 더 많은 경우 x
				dp[start] = Math.max(dp[start], dp[next] + 1);
				continue;
			}
			dp[start] = Math.max(dp[start], dfs(next, depth+1) + 1);
		}
		if(dp[start] == 0){
			dp[start] = 1;
		}
		return dp[start];
	}
}

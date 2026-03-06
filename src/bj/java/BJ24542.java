package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ24542 {

	static final int MOD = 1_000_000_007;
	static int N, M;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N];

		for(int i = 0; i < N; i++){
			parent[i] = i; // 자기 자신을 부모로 설정
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			union(first, second);
		}

		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < N; i++){
			if(map.containsKey(find(i))){
				map.put(find(i), map.get(find(i)) + 1);
			}else{
				map.put(find(i), 1);
			}
		}
		long start = 1;
		for(int key : map.keySet()){
			int size = map.get(key);
			start *= size;
			start %= MOD;
		}
		System.out.println(start);
	}

	static int find(int x){
		if(parent[x] == x){
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b){
		int rootA = find(a);
		int rootB = find(b);

		if(rootA != rootB){
			if(rootA >= rootB){
				parent[rootA] = rootB;
				return;
			}
			parent[rootB] = rootA;
		}
	}
}

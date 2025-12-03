package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14938 {

	// 플로이드 와샬 알고리즘 사용을 위한 배열
	static int[][] floyd;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		floyd = new int[n][n];

		int[] value = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			value[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(floyd[i], 1_000_000_000);
			floyd[i][i] = 0;
		}


		for(int i = 0; i < r; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			floyd[a][b] = l;
			floyd[b][a] = l;
		}

		// k가 경유점
		for(int k = 0; k < n; k++){
			// i가 출발점
			for(int i = 0; i < n; i++){
				// j가 도착점
				for(int j = 0; j < n; j++){
					// 만약 경유하는게 더 빠르다면
					if(floyd[i][j] > floyd[i][k] + floyd[k][j]){
						// 업데이트
						floyd[i][j] = floyd[i][k] + floyd[k][j];
					}
				}
			}
		}

		int max = 0;

		for(int i = 0; i < n; i++){
			int temp = 0;
			for(int j = 0; j < n; j++){
				// m이하의 비용으로 갈 수 있다면
				if(floyd[i][j] <= m){
					// temp에 추가
					temp += value[j];
				}
			}
			// max 업데이트
			max = Math.max(temp, max);
		}

		System.out.println(max);

	}

}

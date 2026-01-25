package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ9470 {

	/*
	자기 자신으로 들어오는 값들에 대해 먼저 파악하자.
	1. 만약 자기 자신에게 들어오는 노드가 없다 -> 순서 1이 된다.
	2. 자기 자신에게 들어오는 노드가 있다
	2-1. 그 중에 순서가 정해지지 않은 것이 있다 -> 패스
	2-2. 순서가 정해져있다 노드 값 정하기
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 테스트 케이스 번호
			int M = Integer.parseInt(st.nextToken()); // 노드의 수
			int P = Integer.parseInt(st.nextToken()); // 간선의 수

			int[] strahler = new int[M+1];
			Map<Integer, List<Integer>> map = new HashMap<>(); // end <- start 연결 정보
			for(int i = 1; i <= M; i++){
				map.put(i, new ArrayList<>());
			}
			for(int i = 0; i < P; i++){
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				map.get(end).add(start);
			}

			int cnt = M;
			for(int key : map.keySet()){ // 자기 자신에게 들어오는 노드가 없다면 1
				if(map.get(key).size() == 0){
					strahler[key] = 1;
					cnt--;
				}
			}

			while(cnt > 0){
				for(int i = 1; i <= M; i++){
					// 이미 체크된 값은 패스
					if(strahler[i] != 0){
						continue;
					}
					boolean plusFlag = false; // 연결된 최대 노드가 2개 이상인지
					boolean clearFlag = false; // 아직 연결 안 된 값이 있는지
					int max = 0; // 연결된 최대 노드 값
					for(int start : map.get(i)){
						// 아직 체크되지 않은 노드가 있다면 패스
						if(strahler[start] == 0){
							clearFlag = true;
							break;
						}
						// 최댓값 찾기
						if(strahler[start] > max){
							max = strahler[start];
							plusFlag = false;
						}else if(max == strahler[start]){
							plusFlag = true;
						}
					}
					if(clearFlag){
						continue;
					}
					cnt--;
					strahler[i] = plusFlag ? max + 1 : max;
				}
			}
			sb.append(K).append(" ").append(strahler[M]).append("\n");
		}
		System.out.println(sb);
	}
}

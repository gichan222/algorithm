package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1680 {

	// 가까운 곳부터 방문해야하고
	// 꼭 "그 지점"까지 가야만 못 실을지 알 수 있다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); // 쓰레기차 용량
			int N = Integer.parseInt(st.nextToken()); // 쓰레기 지점 수

			int cur = 0;      // 현재 적재량
			long ans = 0;     // 왕복 거리 누적
			int x = 0, w = 0; // 마지막 지점 저장용

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()); // 거리
				w = Integer.parseInt(st.nextToken()); // 쓰레기 양

				cur += w;

				if (cur > W) {
					ans += x;   // 초과 → 해당 지점까지 왕복
					cur = w;    // 새로 시작
				}

				if (cur == W) {
					ans += x;   // 꽉 참 → 해당 지점까지 왕복
					cur = 0;
				}
			}

			if (cur > 0) {
				ans += x; // 남은 쓰레기 처리
			}

			System.out.println(ans * 2); // 왕복
		}
	}
}

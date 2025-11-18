package bj.java;

import java.io.*;
import java.util.*;

public class BJ10800 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Ball> balls = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			balls.add(new Ball(color, size, i));
		}

		// size 기준 정렬
		balls.sort(Comparator.comparingInt(o -> o.size));

		long[] answer = new long[N];
		long[] colorSum = new long[200001]; // 색은 최대 200000
		long totalSum = 0;

		int pointer = 0;

		// 같은 size 끼리는 한 번에 처리해야 함
		for (int i = 0; i < N; i++) {
			Ball cur = balls.get(i);

			// pointer가 현재보다 사이즈 작은 애들까지 누적
			while (balls.get(pointer).size < cur.size) {
				Ball small = balls.get(pointer);

				totalSum += small.size;
				colorSum[small.color] += small.size;

				pointer++;
			}

			// 현재 공의 정답 = 전체 작은 공 합 - 같은 color에서 작은 공 합
			answer[cur.idx] = totalSum - colorSum[cur.color];
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (long v : answer) {
			sb.append(v).append("\n");
		}
		System.out.print(sb);
	}

	static class Ball {
		int color;
		int size;
		int idx;

		Ball(int color, int size, int idx) {
			this.color = color;
			this.size = size;
			this.idx = idx;
		}
	}
}

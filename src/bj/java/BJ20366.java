package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ20366 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 모든 가능한 눈사람 조합(쌍) 생성
		List<Point> lists = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				lists.add(new Point(i, j, arr[i] + arr[j]));
			}
		}

		// 키 합 순으로 정렬
		Collections.sort(lists);

		int min = Integer.MAX_VALUE;

		// 인접한 값들끼리 비교 (투 포인터와 유사한 방식)
		// i를 기준으로 왼쪽/오른쪽을 탐색
		for (int i = 0; i < lists.size(); i++) {
			Point cur = lists.get(i);

			// 왼쪽 탐색
			for (int j = i - 1; j >= 0; j--) {
				Point left = lists.get(j);

				// 구성하는 눈덩이 인덱스가 겹치면 패스
				if (isOverlap(cur, left)) {
					continue;
				}

				// 겹치지 않는 값을 찾으면 그게 최소 차이
				// 더 왼쪽으로 갈수록 차이는 커지므로 바로 break
				min = Math.min(min, cur.value - left.value);
				break;
			}

			// 오른쪽 탐색
			for (int j = i + 1; j < lists.size(); j++) {
				Point right = lists.get(j);

				if (isOverlap(cur, right)) {
					continue;
				}

				min = Math.min(min, right.value - cur.value);
				break;
			}
		}

		System.out.println(min);
	}

	// 인덱스 겹침 확인
	private static boolean isOverlap(Point p1, Point p2) {
		return p1.sIdx == p2.sIdx || p1.sIdx == p2.eIdx ||
			p1.eIdx == p2.sIdx || p1.eIdx == p2.eIdx;
	}

	static class Point implements Comparable<Point> {
		int sIdx;
		int eIdx;
		int value;

		public Point(int sIdx, int eIdx, int value) {
			this.sIdx = sIdx;
			this.eIdx = eIdx;
			this.value = value;
		}

		@Override
		public int compareTo(Point point) {
			return this.value - point.value;
		}
	}
}
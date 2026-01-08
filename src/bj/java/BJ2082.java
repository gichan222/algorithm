package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2082 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 세그먼트 위치별로 가능한 숫자
		Map<Integer, List<Integer>> map = new HashMap<>();
		map.put(0, List.of(0,2,3,4,5,6,7,8,9));
		map.put(1, List.of(0,2,3,5,6,7,8,9));
		map.put(2, List.of(0,1,2,3,4,5,6,7,8,9));
		map.put(3, List.of(0,4,5,6,8,9));
		map.put(4, List.of());
		map.put(5, List.of(0,1,2,3,4,7,8,9));
		map.put(6, List.of(0,2,3,4,5,6,8,9));
		map.put(7, List.of(2,3,4,5,6,8,9));
		map.put(8, List.of(0,1,2,3,4,5,6,7,8,9));
		map.put(9, List.of(0,2,6,8));
		map.put(10, List.of());
		map.put(11, List.of(0,1,3,4,5,6,7,8,9));
		map.put(12, List.of(0,2,3,5,6,8,9));
		map.put(13, List.of(0,2,3,5,6,8,9));
		map.put(14, List.of(0,1,2,3,4,5,6,7,8,9));

		// 각 자리(4자리 숫자)에 대해 가능한 숫자 집합
		Map<Integer, Set<Integer>> nums = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			nums.put(i, new TreeSet<>(List.of(0,1,2,3,4,5,6,7,8,9)));
		}

		// 입력 처리
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				String temp = st.nextToken();
				for (int k = 0; k < 3; k++) {
					if (temp.charAt(k) == '#') {
						// 교집합
						nums.get(j).retainAll(map.get(3 * i + k));
					}
				}
			}
		}

		// 가능한 시간 중 가장 이른 시간 찾기
		int answerH = -1, answerM = -1;

		for (int h1 : nums.get(0)) {
			for (int h2 : nums.get(1)) {
				int hour = h1 * 10 + h2;
				if (hour > 23) continue;

				for (int m1 : nums.get(2)) {
					for (int m2 : nums.get(3)) {
						int min = m1 * 10 + m2;
						if (min > 59) continue;

						answerH = hour;
						answerM = min;
						System.out.printf("%02d:%02d\n", answerH, answerM);
						return;
					}
				}
			}
		}

		// 가능한 시간이 없을 경우
		System.out.println("impossible");
	}
}

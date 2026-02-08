package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Assignment> assignments = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			assignments.add(new Assignment(deadline, value));
		}

		// 마감일 오름차순 정렬
		Collections.sort(assignments);

		// 가치 기준 최소 힙
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (Assignment a : assignments) {
			pq.offer(a.value);

			// 마감일 초과하면 가장 작은 가치 제거
			if (pq.size() > a.deadline) {
				pq.poll();
			}
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}

		System.out.println(answer);
	}

	static class Assignment implements Comparable<Assignment> {
		int deadline;
		int value;

		Assignment(int deadline, int value) {
			this.deadline = deadline;
			this.value = value;
		}

		@Override
		public int compareTo(Assignment o) {
			return this.deadline - o.deadline; // 마감일 오름차순
		}
	}
}

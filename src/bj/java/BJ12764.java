package bj.java;

import java.io.*;
import java.util.*;

public class BJ12764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		List<Time> times = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			times.add(new Time(s, e));
		}

		// 1) 시작 시간 기준 정렬
		Collections.sort(times);

		// 사용 가능한 컴퓨터 번호 (가장 작은 번호 우선)
		PriorityQueue<Integer> available = new PriorityQueue<>();
		available.add(1);

		// 컴퓨터 사용 종료 시각 (가장 빨리 끝나는 순)
		PriorityQueue<Finish> finishes = new PriorityQueue<>();

		// 자리별 사용 횟수
		Map<Integer, Integer> count = new TreeMap<>();
		count.put(1, 0);

		int maxSeat = 1;

		for (Time cur : times) {

			// 현재 시간보다 먼저 끝나는 컴퓨터는 반납
			while (!finishes.isEmpty() && finishes.peek().end <= cur.start) {
				Finish done = finishes.poll();
				available.add(done.seat);
			}

			int seat;

			// 빈 컴퓨터가 있다면 사용
			if (!available.isEmpty()) {
				seat = available.poll();
			} else {
				// 새 컴퓨터 개설
				seat = ++maxSeat;
				count.put(seat, 0);
			}

			// 사용 횟수 증가
			count.put(seat, count.get(seat) + 1);

			// 끝나는 시간 PQ에 추가
			finishes.add(new Finish(cur.end, seat));
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(maxSeat).append("\n");
		for (int seat : count.keySet()) {
			sb.append(count.get(seat)).append(" ");
		}

		System.out.println(sb);
	}

	static class Time implements Comparable<Time> {
		int start;
		int end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time other) {
			return this.start - other.start; // 시작 시간 오름차순
		}
	}

	static class Finish implements Comparable<Finish> {
		int end;
		int seat;

		public Finish(int end, int seat) {
			this.end = end;
			this.seat = seat;
		}

		@Override
		public int compareTo(Finish o) {
			return this.end - o.end; // 가장 빨리 끝나는 순
		}
	}
}

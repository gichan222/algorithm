package bj.java;

import java.io.*;
import java.util.*;

public class BJ9370 {

	// n: 교차로 수, m: 도로 수, t: 목적지 후보 수
	static int n, m, t;

	// start: 출발점, g-h: 반드시 지나야 하는 간선의 양 끝점
	static int start, g, h;

	// 목적지 후보 목록
	static List<Integer> endList = new ArrayList<>();

	// 인접 리스트 (정점 -> 연결된 간선들)
	static Map<Integer, List<Node>> edges = new HashMap<>();

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		while (T-- > 0) {
			endList.clear();
			edges.clear();

			// n, m, t 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			// 시작점, g, h 입력
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			// 간선 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				/*
				 * 모든 간선 가중치는 2배 (짝수)
				 * 단, g-h 간선만 -1 해서 홀수로 만든다
				 * → 최단 경로에 g-h가 포함되면 최종 거리는 홀수
				 */
				int weight = d * 2;
				if ((a == g && b == h) || (a == h && b == g)) {
					weight -= 1;
				}

				// 양방향 그래프
				edges.computeIfAbsent(a, k -> new ArrayList<>())
					.add(new Node(b, weight));
				edges.computeIfAbsent(b, k -> new ArrayList<>())
					.add(new Node(a, weight));
			}

			// 목적지 후보 입력
			for (int i = 0; i < t; i++) {
				endList.add(Integer.parseInt(br.readLine()));
			}

			// start에서 모든 정점까지 최단 거리 계산 (딱 1번)
			int[] dist = dijkstra(start);

			Collections.sort(endList);
			StringBuilder sb = new StringBuilder();

			/*
			 * 최단 거리(dist[end])가 홀수라면
			 * → g-h 간선을 포함한 최단 경로가 존재
			 */
			for (int end : endList) {
				if (dist[end] != INF && dist[end] % 2 == 1) {
					sb.append(end).append(" ");
				}
			}

			System.out.println(sb);
		}
	}

	// 다익스트라: start에서 모든 정점까지 최단 거리
	static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 이미 더 짧은 경로가 있으면 스킵
			if (cur.weight > dist[cur.end]) continue;

			// 현재 정점에서 갈 수 있는 모든 간선 확인
			for (Node next : edges.getOrDefault(cur.end, Collections.emptyList())) {
				int newDist = dist[cur.end] + next.weight;

				if (dist[next.end] > newDist) {
					dist[next.end] = newDist;
					pq.add(new Node(next.end, newDist));
				}
			}
		}

		return dist;
	}

	// 다익스트라용 노드 (도착 정점, 누적 거리)
	static class Node implements Comparable<Node> {
		int end, weight;

		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}

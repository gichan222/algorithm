package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ22865 {

	static Map<Integer, List<Point>> edges = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++){
			edges.put(i, new ArrayList<>()); // 연결 도로
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			edges.get(a).add(new Point(b, value));
			edges.get(b).add(new Point(a, value));
		}

		int[] lengthA = new int[N];
		int[] lengthB = new int[N];
		int[] lengthC = new int[N];
		Arrays.fill(lengthA, -1);
		Arrays.fill(lengthB, -1);
		Arrays.fill(lengthC, -1);

		// (A <-> 각 지점) 의 거리
		lengthA[A-1] = 0;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(Point p : edges.get(A-1)){
			pq.add(p);
		}
		while(!pq.isEmpty()){
			Point cur = pq.poll();
			if(lengthA[cur.point] != -1){
				continue;
			}
			lengthA[cur.point] = cur.value;
			for(Point next : edges.get(cur.point)){
				if(lengthA[next.point] != -1){
					continue;
				}
				pq.add(new Point(next.point, next.value+ cur.value));
			}
		}

		// (B <-> 각 지점) 의 거리
		lengthB[B-1] = 0;
		pq.clear();
		for(Point p : edges.get(B-1)){
			pq.add(p);
		}
		while(!pq.isEmpty()){
			Point cur = pq.poll();
			if(lengthB[cur.point] != -1){
				continue;
			}
			lengthB[cur.point] = cur.value;
			for(Point next : edges.get(cur.point)){
				if(lengthB[next.point] != -1){
					continue;
				}
				pq.add(new Point(next.point, next.value+ cur.value));
			}
		}

		// (C <-> 각 지점) 의 거리
		lengthC[C-1] = 0;
		pq.clear();
		for(Point p : edges.get(C-1)){
			pq.add(p);
		}
		while(!pq.isEmpty()){
			Point cur = pq.poll();
			if(lengthC[cur.point] != -1){
				continue;
			}
			lengthC[cur.point] = cur.value;
			for(Point next : edges.get(cur.point)){
				if(lengthC[next.point] != -1){
					continue;
				}
				pq.add(new Point(next.point, next.value+ cur.value));
			}
		}

		// 최대 거리 계산
		int max = 0;
		int idx = 0;
		for(int i = 0; i < N; i++){
			int min = lengthA[i];
			min = Math.min(min, lengthB[i]);
			min = Math.min(min, lengthC[i]);
			if(max < min){
				max = min;
				idx = i+1;
			}
		}

		System.out.println(idx);
	}

	static class Point implements Comparable<Point>{
		int point;
		int value;

		public Point(int point, int value){
			this.point = point;
			this.value = value;
		}

		@Override
		public int compareTo(Point p){
			return this.value - p.value;
		}
	}
}

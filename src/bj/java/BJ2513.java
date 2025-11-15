package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2513 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 아파트 단지 수
		int K = Integer.parseInt(st.nextToken()); // 통학버스 정원
		int S = Integer.parseInt(st.nextToken()); // 학교 위치

		PriorityQueue<Node> leftQueue = new PriorityQueue<>(((o1, o2) -> {
			return o1.point - o2.point; // point 기준 오름차순 정렬
		}));

		PriorityQueue<Node> rightQueue = new PriorityQueue<>(((o1, o2) -> {
			return o2.point - o1.point; // point 기준 내림차순 정렬
		}));

		while(N-- > 0){
			st = new StringTokenizer(br.readLine());
			int point = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			if(point < S){
				leftQueue.add(new Node(point, cnt)); // 학교보다 왼쪽에 있다면
			}else{
				rightQueue.add(new Node(point, cnt)); // 학교보다 오른쪽에 있다면
			}
		}

		long length = 0;
		long total;
		while(!leftQueue.isEmpty()){ // 왼쪽 큐부터 검사
			Node cur = leftQueue.poll(); // 제일 끝 노드 꺼내기
			total = K; // 버스 정원 수 설정
			length += Math.abs(S - cur.point) * 2; // 제일 끝 노드는 무조건 방문해야함
			if(cur.cnt > total){ // 만약 버스에 태울 수 있는 인원보다 크다면
				cur.cnt -= total; // 남은 학생수를 계산
				leftQueue.add(cur); // 다시 큐에 넣음
				continue;
			}else{
				total -= cur.cnt; // 모두 버스에 태울 수 있다면, 버스 남은 좌석 수 계산
			}
			while(!leftQueue.isEmpty()){
				Node next = leftQueue.poll(); // 그 다음 끝 노드 꺼내기
				if(next.cnt > total){ // 만약 버스에 태울 수 있는 인원보다 크다면
					next.cnt -= total; // 남은 학생 술르 계산
					leftQueue.add(next); // 다시 큐에 넣음
					break;
				}else{
					total -= next.cnt; // 모두 버스에 태울 수 있다면, 버스 남은 좌석 수 계산
				}
			}
		}

		while (!rightQueue.isEmpty()) { // 오른쪽 큐 검사 시작
			Node cur = rightQueue.poll(); // 제일 끝 노드(가장 먼 아파트) 꺼내기
			length += Math.abs(S - cur.point) * 2; // 제일 끝 노드는 무조건 방문해야 함
			total = K; // 버스 정원 수 설정

			if (cur.cnt > total) { // 만약 버스에 태울 수 있는 인원보다 크다면
				cur.cnt -= total; // 남은 학생 수 계산
				rightQueue.add(cur); // 다시 큐에 넣음
				continue;
			} else {
				total -= cur.cnt; // 모두 버스에 태울 수 있다면 버스 남은 좌석 수 계산
			}

			while (!rightQueue.isEmpty()) {
				Node next = rightQueue.poll(); // 그 다음 끝 노드 꺼내기
				if (next.cnt > total) { // 만약 버스에 태울 수 있는 인원보다 크다면
					next.cnt -= total; // 남은 학생 수 계산
					rightQueue.add(next); // 다시 큐에 넣음
					break;
				} else {
					total -= next.cnt; // 모두 버스에 태울 수 있다면 버스 남은 좌석 수 계산
				}
			}
		}


		System.out.println(length);
	}

	static class Node{
		int point;
		int cnt;

		public Node(int point, int cnt){
			this.point = point;
			this.cnt = cnt;
		}
	}
}

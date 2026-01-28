package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ22254 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] gifts = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			gifts[i] = Integer.parseInt(st.nextToken());
		}

		int s = 1;
		int e = N;
		int mid = (s + e) / 2;
		PriorityQueue<Integer> pq = new PriorityQueue();
		while(s <= e){
			pq.clear();
			mid = (s + e) / 2;
			for (int i = 0; i < mid; i++) pq.add(0);

			for (int i = 0; i < N; i++) {
				int time = pq.poll();
				pq.add(time + gifts[i]);
			}

			while(pq.size() > 1){
				pq.poll();
			}
			int max = pq.poll();
			if(max <= X){
				e = mid - 1;
			}else{
				s = mid + 1;
			}
		}
		System.out.println(s);
	}

}

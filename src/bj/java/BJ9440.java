package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ9440 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		while(N != 0){
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			int cnt = 0; // 0개수
			for(int i = 0; i < N; i++){
				int num = Integer.parseInt(st.nextToken());
				// 0은 추후 추가할 예정
				if(num == 0){
					cnt++;
					continue;
				}
				pq.add(num);
			}

			// 첫번째 자리 수 선정
			int first = pq.poll();
			int second = pq.poll();
			for(int i = 0; i < cnt; i++){
				pq.add(0);
			}

			// 다음 숫자를 어디에 추가할지
			// 여기서 첫번째가 아닌 두번째 숫자부터 추가하면 틀림
			// ex) 1 2 7 8 9 숫자에서 1 2가 먼저 뽑힌 상황에 다음 숫자인 7이 second에 들어가면 최종적으로 18 279 라는 모순된 숫자가 뽑힘
			boolean isNextFirst = true;
			while(!pq.isEmpty()){
				if(!isNextFirst){
					second *= 10;
					second += pq.poll();
					isNextFirst = true;
					continue;
				}
				first *= 10;
				first += pq.poll();
				isNextFirst = false;
			}
			System.out.println(first + second);
			// 다음 입력 받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
		}
	}
}

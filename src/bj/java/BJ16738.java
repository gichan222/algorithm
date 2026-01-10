package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16738 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		List<Room> rooms = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		boolean[] isChecked = new boolean[N+1];
		loop1:
		while(Q-- > 0){
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			// 명령어가 new일 때
			if(command.equals("new")){
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				loop2:
				for(int i = 1; i <= N - (Y-1); i++){
					for(int j = i; j <= i + (Y-1); j++){
						// 1부터 Y 사이즈 방이 있나 모두 체크
						if(isChecked[j]){
							continue loop2;
						}
					}
					// 방을 만들 수 있다면 체크해둠
					for(int j = i; j <= i + (Y-1); j++){
						isChecked[j] = true;
					}
					// 방 만들기
					rooms.add(new Room(X, i, i + Y - 1));
					sb.append(i).append(" ").append(i+Y-1).append("\n");
					// 다음 명령어로
					continue loop1;
				}
				// 방 만들기 실패
				sb.append("REJECTED").append("\n");
			}
			// 명령어가 in이라면
			else if(command.equals("in")){
				// list 인덱스 조정
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken());
				rooms.get(A).cnt += B;
			}
			// 명령어가 out이라면
			else{
				// list 인덱스 조정
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken());
				rooms.get(A).cnt -= B;
				// 만약 방이 비었다면
				if(rooms.get(A).cnt == 0){
					for(int i = rooms.get(A).L; i <= rooms.get(A).R; i++){
						isChecked[i] = false;
					}
					sb.append("CLEAN ").append(rooms.get(A).L).append(" ").append(rooms.get(A).R).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	static class Room{
		int cnt;
		int L;
		int R;

		public Room(int cnt, int L, int R){
			this.cnt = cnt;
			this.L = L;
			this.R = R;
		}
	}
}

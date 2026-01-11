package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ8896 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			int N = Integer.parseInt(br.readLine());
			String[] line = new String[N];
			List<Integer> next = new ArrayList<>();
			for(int i = 0; i < N; i++){
				line[i] = br.readLine();
				next.add(i);
			}
			List<Integer> rock = new ArrayList<>();
			List<Integer> scissors = new ArrayList<>();
			List<Integer> paper = new ArrayList<>();

			for(int i = 0; i < line[0].length(); i++){
				rock.clear();
				scissors.clear();
				paper.clear();
				for(int j : next){
					char alphabet = line[j].charAt(i);
					if(alphabet == 'R'){
						rock.add(j);
					}else if(alphabet == 'S'){
						scissors.add(j);
					}else{
						paper.add(j);
					}
				}
				List<Integer> winners = winner(rock, scissors, paper);
				if(winners == null){
					continue;
				}
				next.clear();
				// 참조 주소가 아닌 값만을 넘기기 위함
				next.addAll(winners);
			}
			if(next.size() == 1){
				System.out.println(next.get(0)+1);
			}else {
				System.out.println(0);
			}
		}
	}

	static List<Integer> winner(List<Integer> rock, List<Integer> scissors, List<Integer> paper){
		if(rock.size() != 0){ // 주먹이 있을 때
			// 둘 다 없다면
			if(paper.size() == 0 && scissors.size() == 0){
				return rock;
			}
			// 보는 없고, 가위는 있다면
			if(paper.size() == 0 && scissors.size() != 0){
				return rock;
			}
			// 보는 있는데, 가위가 없다면
			if(paper.size() != 0 && scissors.size() == 0){
				return paper;
			}
			// 둘 다 있다면
			return null;
		}
		if(scissors.size() != 0){ // 가위가 있을 때
			// 둘 다 없다면
			if(paper.size() == 0 && rock.size() == 0){
				return scissors;
			}
			// 보는 없고, 주먹이 있다면
			if(paper.size() == 0 && rock.size() != 0){
				return rock;
			}
			// 보는 있는데, 주먹이 없다면
			if(paper.size() != 0 && rock.size() == 0){
				return scissors;
			}
			// 둘 다 있다면
			return null;
		}
		// 보가 있을 때
		// 둘 다 없다면
		if(rock.size() == 0 && scissors.size() == 0){
			return paper;
		}
		// 주먹은 없고, 가위는 있다면
		if(rock.size() == 0 && scissors.size() != 0){
			return scissors;
		}
		// 주먹은 있는데, 가위가 없다면
		if(rock.size() != 0 && scissors.size() == 0){
			return paper;
		}
		// 둘 다 있다면
		return null;
	}
}

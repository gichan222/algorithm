package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ2580 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] nums = new int[9][9];
		int cnt = 0;
		for(int i = 0; i < 9; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++){
				nums[i][j] = Integer.parseInt(st.nextToken());
				if(nums[i][j] == 0){
					cnt++;
				}
			}
		}

		Set<Integer>[] possibles = new HashSet[9];
		for (int i = 0; i < 9; i++) {
			possibles[i] = new HashSet<>();
			possibles[i].addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
		}

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(nums[i][j] == 0){
					continue;
				}
				possibles[(i / 3) * 3 + j % 3].remove(nums[i][j]);
			}
		}

		while(cnt > 0){
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					Set<Integer> row = new HashSet<>();
					row.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
					for(int k = 0; k < 9; k++){
						if(nums[i][k] == 0){
							continue;
						}
						row.remove(nums[i][k]);
					}
					Set<Integer> col = new HashSet<>();
					col.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
					for(int k = 0; k < 9; k++){
						if(nums[i][k] == 0){
							continue;
						}
						col.remove(nums[i][k]);
					}
					row.retainAll(col);
					row.retainAll(possibles[(i / 3) * 3 + j % 3]);
					if(row.size() == 1){
						nums[i][j] = row.iterator().next();
						possibles[(i / 3) * 3 + j % 3].remove(nums[i][j]);
						cnt--;
					}
				}
			}
		}

		for(int i = 0 ; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}


	}


}

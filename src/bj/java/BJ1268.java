package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1268 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] student = new int[n][5];
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <5 ; j++){
				student[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//구현
		int max = 0;
		int leader = 0;
		for(int i = 0; i<n; i++){
			Set<Integer> set = new HashSet<>();
			for(int j = 0; j<5; j++){
				for(int k = 0; k<n; k++){
					if(student[i][j] == student[k][j]
						&& i!=k){
						set.add(k);
					}
				}
			}
			if(set.size()>max) {
				leader = i;
				max = set.size();
			}
		}
		System.out.println(leader + 1);
	}
}

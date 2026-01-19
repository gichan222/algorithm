package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ5107 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int answer = 1;
		while(N != 0){

			Map<String, String> map = new HashMap<>();
			for(int i = 0; i  < N; i++){
				st = new StringTokenizer(br.readLine());
				map.put(st.nextToken(), st.nextToken());
			}

			int cnt = 0;
			Set<String> checked = new HashSet<>();
			for(String key : map.keySet()){
				if(checked.contains(key)){
					continue;
				}
				cnt++;
				checked.add(key);
				String nextKey = map.get(key);
				while(true){
					if(checked.contains(nextKey)){
						break;
					}
					checked.add(nextKey);
					nextKey = map.get(nextKey);
				}
			}

			System.out.println(answer++ + " " + cnt);

			N = Integer.parseInt(br.readLine());
		}
	}
}

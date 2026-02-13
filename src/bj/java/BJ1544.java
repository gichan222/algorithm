package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ1544 {

	static int N;
	static List<String>[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N];
		int result = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<>();
			String line = br.readLine();
			for(int j=0; j<line.length(); j++){
				arr[i].add(line.substring(j)+ line.substring(0,j));
			}

			boolean flag = true;
			for (int j = 0; flag && j <= i-1; j++) {
				for (int k = 0; flag && k < arr[j].size(); k++) {
					if (line.equals(arr[j].get(k))) flag = false;
				}
			}
			if (flag)
				result++;
		}

		System.out.println(result);
	}

}

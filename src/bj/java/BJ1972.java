package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BJ1972 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		StringBuilder sb = new StringBuilder();
		loop1:
		while(!(line = br.readLine()).equals("*")){
			Set<String> strings = new HashSet<>();
			for(int i = 1; i < line.length(); i++){
				for(int j = 0; j <= line.length() - i - 1; j++){
					String temp = line.substring(j,j+1) + line.substring(j+i,j+i+1);
					if(strings.contains(temp)){
						sb.append(line).append(" is NOT surprising.").append("\n");
						continue loop1;
					}
					strings.add(temp);
				}
				strings.clear();
			}
			sb.append(line).append(" is surprising.").append("\n");
		}
		System.out.println(sb);
	}
}

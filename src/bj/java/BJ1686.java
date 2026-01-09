package bj.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1686 {

	public static void main(String[] args) throws IOException {
		// BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		double v = Double.parseDouble(st.nextToken()) * 60;
		double m = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double startX = Double.parseDouble(st.nextToken());
		double startY = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double endX = Double.parseDouble(st.nextToken());
		double endY = Double.parseDouble(st.nextToken());
		List<Point> nodes = new ArrayList<>();
		nodes.add(new Point(startX, startY,0));
		String line = null;
		while((line = br.readLine()) != null){
			st = new StringTokenizer(line);
			nodes.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()), 0));
		}
		nodes.add(new Point(endX, endY, 0));
		int[] visited = new int[nodes.size()];
		Arrays.fill(visited, -1);
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(startX, startY, 0));
		visited[0] = 0;
		while(!q.isEmpty()){
			Point cur = q.poll();
			for(int i = 0; i < visited.length; i++){
				if(visited[i] != -1){
					continue;
				}
				Point next = nodes.get(i);
				double length = Math.sqrt(Math.pow(cur.x - next.x, 2) + Math.pow(cur.y - next.y, 2)) / v;
				if(length >= m){
					continue;
				}
				visited[i] = cur.value + 1;
				q.add(new Point(next.x, next.y, cur.value + 1));
			}
		}
		// for(int i = 0; i < visited.length; i++){
		// 	System.out.println("i : " + i + " value : " + visited[i]);
		// }
		if(visited[visited.length-1] == -1){
			System.out.println("No.");
			return;
		}

		System.out.printf("Yes, visiting %d other holes.", visited[visited.length-1]-1);
	}

	static class Point{
		double x, y;
		int value;

		public Point(double x, double y, int value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}

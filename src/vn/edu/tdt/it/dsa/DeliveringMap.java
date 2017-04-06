package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DeliveringMap {

	private int[][] undirectedGraph = new int[100][100];
	private int[][] directedGraph = new int[100][100];

	public DeliveringMap(File file) throws IOException{
		//sinh vien viet ma o day
		Scanner sc = new Scanner(file);
		String content = "";
		while (sc.hasNext()) {
			content += sc.next();
		}
		List<String> list = new ArrayList<>(Arrays.asList(content.split("\\s+")));
		for (int i=0; i < list.size(); i ++) {
			int weight = Integer.parseInt(list.get(i).substring(2,5));
			int src = Integer.parseInt(list.get(i).substring(0,2));
			int dest = Integer.parseInt(list.get(i).substring(5,7));

			undirectedGraph[src][dest] = weight;
			undirectedGraph[dest][src] = weight;
			directedGraph[src][dest] = weight;
		}
	}

	
	public int calculate(int level, boolean rushHour){
		int res = 0;
		//sinh vien viet cma o day 
		return res;
	}
	
	public static void main (String[] args){
		try{
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
			System.out.println(map.calculate(3, false));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

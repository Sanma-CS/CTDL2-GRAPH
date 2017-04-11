package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class DeliveringMap {
	public static final int MAX_EDGE = 100;


	private int[][] undirectedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int[][] directedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int sumEdgesVertices = 0;

	public DeliveringMap(File file) throws IOException{
		//sinh vien viet ma o day
		Scanner sc = new Scanner(file);
//		String content = "";
		List<String> list = new ArrayList<>();
		Set<Integer> vertices = new HashSet<>();
		while (sc.hasNext()) {
//			content += sc.next() + " ";
			list.add(sc.next());
		}
		for (int i=0; i < list.size(); i ++) {
			int weight = Integer.parseInt(list.get(i).substring(2,5));
			int src = Integer.parseInt(list.get(i).substring(0,2));
			int dest = Integer.parseInt(list.get(i).substring(5,7));

			undirectedGraph[src][dest] = weight;
			undirectedGraph[dest][src] = weight;
			directedGraph[src][dest] = weight;

			vertices.add(src);
			vertices.add(dest);
			sumEdgesVertices += weight;
		}
		sumEdgesVertices += vertices.size();
	}

	
	public int calculate(int level, boolean rushHour){
		int res = 0;
		//sinh vien viet cma o day 
		return res;
	}

	public int[][] getUndirectedGraph() {
        return undirectedGraph;
    }

    public int[][] getDirectedGraph() {
        return directedGraph;
    }

	public void printGraph(int[][] graph) {
        for (int i = 0; i < MAX_EDGE; i++) {
            for (int j = 0; j < MAX_EDGE; j++) {
                System.out.print(graph[i][j] + ",");
            }
            System.out.println();
        }
    }

	public void printAllPath(int src, int dest) {
        boolean[] visited = new boolean[MAX_EDGE];
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        printAllPathUtil(directedGraph, visited, src, dest, path, paths);
        for (ArrayList<Integer> item : paths) {
            System.out.println(item.toString());
        }
    }

    private void printAllPathUtil(int[][] graph, boolean[] visited, int src,
                                  int dest, ArrayList<Integer> path,
                                  ArrayList<ArrayList<Integer>> paths) {
        visited[src] = true;
        path.add(src);

        if (src == dest) {
            paths.add(new ArrayList<>(path));
        } else {
            for (int i = 0; i < MAX_EDGE; i++) {
                if (directedGraph[src][i] > 0) {
                    if (!visited[i]) {
                        printAllPathUtil(graph, visited, i, dest, path, paths);
                    }
                }
            }
        }
        path.remove(path.size() - 1);
        visited[src] = false;
    }

	private int case_1 (int level) {
		return sumEdgesVertices + level;
	}
	
	public static void main (String[] args){
		try{
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
//			System.out.println(map.calculate(3, false));		//default do not touch
//			System.out.println(map.case_1(1));	//debug
//            map.printGraph(map.getDirectedGraph());
            map.printAllPath(1,7);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

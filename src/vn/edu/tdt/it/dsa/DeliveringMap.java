package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class DeliveringMap {
	public static final int MAX_EDGE = 100;
	public static final int COFFEE = 1;
	public static final int BLOCK = -1;
	public static final int NOTHING = 0;

	private int[][] undirectedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int[][] directedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int sumEdgesVertices = 0;
	private ArrayList<Integer> path = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

	public DeliveringMap(File file) throws IOException{
		//sinh vien viet ma o day
		Scanner sc = new Scanner(file);
		List<String> list = new ArrayList<>();
		Set<Integer> vertices = new HashSet<>();
		for (int i = 0; i < MAX_EDGE; i++) {
			Arrays.fill(undirectedGraph[i], Integer.MAX_VALUE);
			Arrays.fill(directedGraph[i], Integer.MAX_VALUE);
		}

		while (sc.hasNext()) {
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
		//sinh vien viet ma o day
		return res;
	}

	private int case_1 (int level) {
		return sumEdgesVertices + level;
	}

	private void DFSUtil (int src, int dest, int graph[][], boolean visited[]) {
		visited[src] = true;
		path.add(src);
		if (src == dest) {
			paths.add(new ArrayList<>(path));
		}
		else {
			//System.out.println(src + " ");	//debug
			for (int i = 0; i < MAX_EDGE; i++) {
				if (graph[src][i] != Integer.MAX_VALUE) {
					if (!visited[i]) {
						DFSUtil(i, dest, graph, visited);
					}
				}
			}

		}
		path.remove(path.size()-1);
		visited[src] = false;
	}

	private void DFS (int src, int dest, int graph[][]) {
		boolean visited[] = new boolean[MAX_EDGE];
		path.clear();
		paths.clear();
		DFSUtil(src, dest, graph, visited);
	}

    private int minKey(int key[], boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < MAX_EDGE; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    private ArrayList<Integer> printMST(int parent[], int graph[][])
    {
        //System.out.println("Edge   Weight");
        ArrayList<Integer> listWeight = new ArrayList<>();
        for (int i = 0; i < MAX_EDGE; i++) {
            if (parent[i] < 0 || graph[i][parent[i]] == Integer.MAX_VALUE) continue;
          //  System.out.println(parent[i] + " - " + i + "    " +
          //          graph[i][parent[i]]);
            listWeight.add(graph[i][parent[i]]);
        }
        return listWeight;
    }

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    private int primMST(int graph[][])
    {
    	// Array to store constructed MST
        int parent[] = new int[MAX_EDGE];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int [MAX_EDGE];

        // To represent set of vertices not yet included in MST
        boolean mstSet[] = new boolean[MAX_EDGE];

        // Initialize all keys as INFINITE
        for (int i = 0; i < MAX_EDGE; i++)
        {
            if (isContainsEdge(graph, i)) {
                key[i] = Integer.MAX_VALUE;
            } else {
                key[i] = 0;
            }
        }

        // Always include first 1st vertex in MST.
        key[findFirstNode(graph)] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[findFirstNode(graph)] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < MAX_EDGE-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            if (u < 0) continue;
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < MAX_EDGE; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v]!=Integer.MAX_VALUE && mstSet[v] == false &&
                        graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        ArrayList<Integer> sumWeight  = printMST(parent, graph);
        //System.out.println(printMST(parent, graph));	//debug
		return getSumlist(sumWeight);
    }

    private int findFirstNode(int[][] graph) {
        for (int i = 0; i < MAX_EDGE; i++) {
            for (int j = 0; j < MAX_EDGE; j++) {
                if (graph[i][j] > 0 && graph[i][j] != Integer.MAX_VALUE)
                    return i;
            }
        }
        return -1;
    }

    private boolean isContainsEdge(int[][] graph, int src) {
        for (int i = 0; i < MAX_EDGE; i++) {
            if (graph[src][i] == Integer.MAX_VALUE || graph[i][src] ==
                    Integer.MAX_VALUE) continue;
            if (graph[src][i] > 0 || graph[i][src] > 0) return true;
        }
        return false;
    }

    private int getSumlist (ArrayList<Integer> list) {
		int s = 0;
		for (int i=0; i < list.size(); i++) {
			s += list.get(i);
		}
		return  s;
	}

	private int getSumWeightpath (ArrayList<Integer> way, int[][] graph) {
		int s = 0;
		for (int i = 0; i < way.size() - 1; i++) {
			s += graph[way.get(i)][way.get(i+1)];
		}
		return s;
	}

	//To track down anything in the path
	private int getSpecialPathType  (ArrayList<Integer> way, int[][] graph) {
		for (int i = 0; i < way.size() - 1; i++) {
			if(graph[way.get(i)][way.get(i+1)] == 99)
				return BLOCK;
			else if(graph[way.get(i)][way.get(i+1)] == 0)
				return COFFEE;
		}
		return NOTHING;
	}

	//To track down anything in the map
	private int haveSpecialPath (int[][] graph) {
		for (int i = 0; i < paths.size(); i++) {
			if(getSpecialPathType(paths.get(i), graph) == BLOCK )
				return BLOCK;
			else if(getSpecialPathType(paths.get(i), graph) == COFFEE)
				return COFFEE;
		}
		return NOTHING;
	}


	private int getMaxweight(int[][] graph) {
		List<Integer> w = new ArrayList<>();
		for (int i = 0; i < paths.size(); i++) {
			w.add(getSumWeightpath(paths.get(i), graph));
		}
		return Collections.max(w);
	}

	private int getMinweight(int[][] graph) {
		List<Integer> w = new ArrayList<>();
		for (int i = 0; i < paths.size(); i++) {
			w.add(getSumWeightpath(paths.get(i), graph));
		}
		return Collections.min(w);
	}

	private int calcPath(int[][] graph, boolean isMin) {

		// Remove all paths contain BLOCK
		ArrayList<ArrayList<Integer>> removePath = new ArrayList<>();
		if (haveSpecialPath(graph) == BLOCK) {
			for (ArrayList<Integer> path : paths) {
				if (getSpecialPathType(path, graph) == BLOCK) {
					removePath.add(path);
				}
			}
		}

		// Remove all paths not contain COFFEE
		if (haveSpecialPath(graph) == COFFEE) {
			for (ArrayList<Integer> path : paths) {
				if (getSpecialPathType(path, graph) != COFFEE) {
					removePath.add(path);
				}
			}
		}

		for (ArrayList<Integer> path : removePath) {
			paths.remove(path);
		}

		if (isMin) {
			return getMinweight(graph);
		}
		return getMaxweight(graph);
	}

	public static void main (String[] args){
		try{
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
			System.out.println(map.primMST(map.undirectedGraph));
			map.DFS(0,2,map.undirectedGraph);
			for (ArrayList<Integer> path : map.paths) {
				System.out.println(path.toString() + " " + map.getSumWeightpath											(path,	map.undirectedGraph));
			}
			System.out.println(map.calcPath(map.undirectedGraph, true));
			System.out.println(map.calcPath(map.undirectedGraph, false));

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}


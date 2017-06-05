/* MWST.java
   CSC 225 - Spring 2012
   Assignment 5 - Template for a Minimum Weight Spanning Tree algorithm
   
   The assignment is to implement the mwst() method below, using any of the algorithms
   studied in the course (Kruskal, Prim-Jarnik or Baruvka). The mwst() method computes
   a minimum weight spanning tree of the provided graph and returns the total weight
   of the tree. To receive full marks, the implementation must run in O(mlog(n)) time
   on a graph with n vertices and m edges.

   This template includes some testing code to help verify the implementation.
   Input graphs can be provided with standard input or read from a file.
   
   To provide test inputs with standard input, run the program with
	java MWST
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. graphs.txt), run the program with
    java MWST graphs.txt
	
   The input format for both methods is the same. Input consists
   of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   For example, a path on 3 vertices where one edge has weight 1 and the other
   edge has weight 2 would be represented by the following
   
    3
	0 1 0
	1 0 2
	0 2 0
	
   An input file can contain an unlimited number of graphs; each will be processed separately.
   
   B. Bird - 03/11/2012
*/



import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.lang.*;
import java.io.*;

public class MWST{

	public static int totalWeight = 0;
	public static int numVerts = 0;
	
	static int smallest_value(int min_wieght[], Boolean to_determine[]){
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < numVerts; v++){
            if (to_determine[v] == false && min_wieght[v] < min){
                min = min_wieght[v];
                min_index = v;
            }
		}
        return min_index;
    }
	
	
	
	static int mwst(int[][] G){
		numVerts = G.length;

        int MST[] = new int[numVerts];
        int min_wieght[] = new int [numVerts];
        Boolean to_determine[] = new Boolean[numVerts];
 
        for (int i = 0; i < numVerts; i++){
            min_wieght[i] = Integer.MAX_VALUE;
            to_determine[i] = false;
        }
        min_wieght[0] = 0;   
        MST[0] = -1;

        for (int count = 0; count < numVerts-1; count++){
            int x = smallest_value(min_wieght, to_determine);
             to_determine[x] = true;
			 
            for (int j = 0; j < numVerts; j++){
                if (G[x][j]!=0 && to_determine[j] == false && G[x][j] <  min_wieght[j]){
                    MST[j]  = x;
                    min_wieght[j] = G[x][j];
                }
			}
        }
 
		for (int i = 1; i < numVerts; i++){
			totalWeight +=  G[i][MST[i]];
		}
		
		return totalWeight;
		
	}

	

	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		int graphNum = 0;
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(!s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				G[i] = new int[n];
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			if (!isConnected(G)){
				System.out.printf("Graph %d is not connected (no spanning trees exist...)\n",graphNum);
				continue;
			}
			int totalWeight = mwst(G);
			System.out.printf("Graph %d: Total weight is %d\n",graphNum,totalWeight);
				
		}
	}

	/* isConnectedDFS(G, covered, numVerts)
	   Used by the isConnected function below.
	   You may modify this, but nothing in this function will be marked.
	*/
	static void isConnectedDFS(int[][] G, boolean[] covered, int numVerts){
		covered[numVerts] = true;
		for (int i = 0; i < G.length; i++)
			if (G[numVerts][i] > 0 && !covered[i])
				isConnectedDFS(G,covered,i);
	}
	   
	/* isConnected(G)
	   Test whether G is connected.
	   You may modify this, but nothing in this function will be marked.
	*/
	static boolean isConnected(int[][] G){
		boolean[] covered = new boolean[G.length];
		for (int i = 0; i < covered.length; i++)
			covered[i] = false;
		isConnectedDFS(G,covered,0);
		for (int i = 0; i < covered.length; i++)
			if (!covered[i])
				return false;
		return true;
	}

}
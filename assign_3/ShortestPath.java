
//package shorteshpath;
/* ShortestPath.java
   CSC 226 - Spring 2017
      
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java ShortestPath
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java ShortestPath file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry A[i][j] of the adjacency matrix gives the weight of the edge from 
   vertex i to vertex j (if A[i][j] is 0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that A[i][j]
   is always equal to A[j][i].
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   B. Bird - 08/02/2014
*/


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Object;


//Do not change the name of the ShortestPath class
public class ShortestPath{

    //TODO: Your code here   
        public static int numVerts;

	/* ShortestPath(G)
		Given an adjacency matrix for graph G, calculates and stores the shortest paths to all the
                vertces from the source vertex.
		
		If G[i][j] == 0, there is no edge between vertex i and vertex j
		If G[i][j] > 0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	
	
	
	    static int minDistance(int dist[], Boolean sptSet[]){
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < numVerts; v++){
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
		}
		//System.out.println("the min index is = "+ min_index + " The min distance is " + min);
		//System.out.print(min_index);
        return min_index;
    }
	
	
	
	public static String reverseIt(String source) {
		int i, len = source.length();
		StringBuilder dest = new StringBuilder(len);
		for (i = (len - 1); i >= 0; i--){
			dest.append(source.charAt(i));
		}
		return dest.toString();
	}



	static void ShortestPath(int[][] G, int source){
		numVerts = G.length;
        int dist[] = new int[numVerts];
		int path_finder[] = new int[numVerts];

        Boolean sptSet[] = new Boolean[numVerts];
 
        for (int i = 0; i < numVerts; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }     
		dist[source] = 0;
        for (int count = 0; count < numVerts-1; count++){

            int u = minDistance(dist, sptSet);
             sptSet[u] = true;
            for (int v = 0; v < numVerts; v++){
                if (!sptSet[v] && G[u][v]!=0 &&dist[u] != Integer.MAX_VALUE &&dist[u]+G[u][v] < dist[v]){
                    dist[v] = dist[u] + G[u][v];
					path_finder[v]= u;
					
			//		System.out.println("the count is = "+ count + " The vertex is  " + v);

				}
			//	System.out.println("the distance total = "+ dist[v] + " of index " + v);

			}
//		System.out.printf("and the total distance is: "+ dist[count] );

        }
	//	System.out.printf("\nThe path from 0 to "+ (numVerts-1)+" is: " );
//		System.out.printf("and the total distance is: "+ dist[numVerts-1] );

 		//System.out.println("");
		int checker;
		int k=0;
		for(int i = 0; i<numVerts; i++){
			checker=1;
			k=i;
			System.out.printf("The nodes of path from 0 to "+ k+ " is:");
			String s2 = new String();
			String s3= new String();
			s2=(""+k+ " ");
			while(checker==1){
				String s1 = new String();
				if(k==0){
					checker =2;
					s1=(" 0 --> ");
					s2=s1.concat(s2);  
					break;
				}else{
					if (path_finder[k]!=0){
					s1=(path_finder[k]+" --> ");
					}
					k=path_finder[k];
					
				}
				s2=s1.concat(s2);  
			}
			System.out.printf(s2);
		System.out.println("and the total distance is: "+ dist[i] );
			
			 
		}
	}

        static void PrintPaths(int source){
           //TODO: Your code here   
        }
        
		
	/* main()
	   Contains code to test the ShortestPath function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			
			ShortestPath(G, 0);
                        PrintPaths(0);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			//System.out.printf("Graph %d: Minimum weight of a 0-1 path is %d\n",graphNum,totalWeight);
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}




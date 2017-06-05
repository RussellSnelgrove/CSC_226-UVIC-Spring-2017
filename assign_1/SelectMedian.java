

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class SelectMedian {

       
    public static int LinearSelect(int[] A, int k){
		int median =14;
		median = finder(A, 0, A.length-1, k);		
        return median;
    }    
	
	public static int finder(int A[], int l, int r, int k){
        if (k > 0 && k <= r){
            int postion = randomPartition(A, l, r);
			//equal
            if (postion-l == k-1){
                return A[postion];
			//greater
            }if (postion-l > k-1){
                return finder(A, l, postion-1, k);
			}else{
			//less then
             return finder(A, postion+1, r, k-postion+l-1);
			}
        }else{
			return -1;
		}
		
    }
	
    public static int partition(int A[], int l, int r){
        int x = A[r], i = l;
        for (int j = l; j <= r - 1; j++){
            if (A[j] <= x){
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, r);
        return i;
    }
 
    public static int randomPartition(int A[], int l, int r){
        int n = r;
        int pivot = (int)(Math.random()) % n;
        swap(A, pivot, r);
        return partition(A, l, r);
    }
	
	
	public static void swap(int A[], int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
	
    
    public static void main(String[] args) {
		SelectMedian Median = new SelectMedian();
		
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
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] A = new int[inputVector.size()];

		for (int i = 0; i < A.length; i++)
			A[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",A.length);

		
		
		
		long startTime = System.currentTimeMillis();
/*
        int[] A = {50, 54, 49, 49, 48, 49, 56, 52, 51, 52, 50, 59};
		
		
		*/
		int x;
		if(A.length%2 == 1){
			x = (A.length/2)+1;
		}else{
			x =A.length/2;
		}
        System.out.println("The median weight is " + Median.LinearSelect(A, (A.length/2)));




		long endTime = System.currentTimeMillis();
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
    }
    
}



/*






*/
/*
We are given two sorted arrays. We need to merge these two arrays such that the initial numbers (after complete sorting) are in the first array and the remaining 
numbers are in the second array. Extra space allowed in O(1).

Example: 

Input: ar1[] = {10};
       ar2[] = {2, 3};
Output: ar1[] = {2}
        ar2[] = {3, 10}  

Input: ar1[] = {1, 5, 9, 10, 15, 20};
       ar2[] = {2, 3, 8, 13};
Output: ar1[] = {1, 2, 3, 5, 8, 9}
        ar2[] = {10, 13, 15, 20}


METHOD 1:
	APPROACH:
		Basically we can create and array of size N+M and then plug all elements into that. After that we sort that array and at last plug N and M no. of elements 
		into the array1 and array2.

TIME: O(NlogN) for sorting + O(N) + O(N).


METHOD 2:

TIME: O(N*M).

SPACE: O(1).
*/

class Solution
{
    //Function to merge the arrays.
    public static void merge(long arr1[], long arr2[], int n, int m) 
    {
        // ignore the element if it is
        // already in the correct order; otherwise, swap it with the next smaller
        // element, which happens to be the first element of arr2
        for(int i = 0; i < n; i++){
            
            // if greter then only
            if(arr1[i] > arr2[0]){
                
                // compare the current element of arr1 with the first element of arr2
                long temp = arr1[i];
                arr1[i] = arr2[0];
                arr2[0] = temp;
                
                long ele = arr2[0];
                
                // now we have move arr2[0] to its correct position to 
                // maintain the sorted order
                int k;
                for(k = 1; k < m && arr2[k] < ele; k++){
                    arr2[k-1] = arr2[k];  // shifting elements
                }
                
                arr2[k-1] = ele;
            }
        }
    }
}

/*
METHOD 3:(USING GAP METHOD)

TIME: O((N+M)*log(N+M)), log(N+M) denotes the no. of times we perform the gap operation and for each gap operation we traverse (N + M) at worst case assume.

SPACE: O(1).
/*

class Solution
{
    private static int calGap(int gp){
        if(gp <= 1) return 0;
        
        // take the ceil value
        return (gp / 2) + (gp % 2);
    }
    
    public static void merge(long arr1[], long arr2[], int n, int m) 
    {
        int gap = n + m;
        
        // using gap method
        for(gap = calGap(gap); gap >= 1; gap = calGap(gap)){
            
            // first check for the array1
            int i;
            for(i = 0; i + gap < n; i++){
                if(arr1[i] > arr1[i+gap]){
                    long temp = arr1[i];
                    arr1[i] = arr1[i+gap];
                    arr1[i+gap] = temp;
                }
            }
            
            // now checking elements simultaneous in the arr1 and arr2
            // comparing elments in bothe arr1 and arr2
            int j;
            for(j = gap > n ? gap-n : 0; j < m && i < n; j++, i++){
                if(arr1[i] > arr2[j]){
                    long temp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = temp;
                }
            }
            
            // now comparing elements in the arr2 only
            // bcz we have to maintain the sorted order
            if(j < m){
                for(j = 0; j + gap < m; j++){
                    if(arr2[j] > arr2[j+gap]){
                        long temp = arr2[j];
                        arr2[j] = arr2[j+gap];
                        arr2[j+gap] = temp;
                    }
                }
            }
        }
    }
}
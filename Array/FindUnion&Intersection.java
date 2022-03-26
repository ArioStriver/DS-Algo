/*
Given two unsorted arrays that represent two sets (elements in every array are distinct), find the union and intersection of two arrays.

For example, if the input arrays are: 
arr1[] = {7, 1, 5, 2, 3, 6} 
arr2[] = {3, 8, 6, 20, 7} 

Then your program should print Union as {1, 2, 3, 5, 6, 7, 8, 20} and Intersection as {3, 6, 7}. Note that the elements of union and intersection can be printed in 
any order.


METHOD 1:(USING SET)

TIME: O(mlog(m) + nlog(n))
*/

class Solution{
    public static int doUnion(int a[], int n, int b[], int m) 
    {
        Set<Integer> set = new HashSet();
        
        for(int i = 0; i < n; i++){
            set.add(a[i]);
        }
        
        for(int i = 0; i < m; i++){
                set.add(b[i]);
        }
        
        return set.size();
    }
}

/*
METHOD 2:
	APPROACH:		
		We can improve performance of getUnion method by iterating over both the arrays for index from 0 to min(n, m)-1 adding all the elements in both the 
		arrays to the set, and then iterate over the other array with the remaining elements and add them to the set.

TIME: O(max(m, n)).
*/

class Solution{
    public static int doUnion(int a[], int n, int b[], int m) 
    {
        int min = (n < m) ? n : m;
        
        Set<Integer> set = new HashSet();
        
        // add the elements form both the array from i = 0 to min
        for(int i = 0; i < min; i++){
            set.add(a[i]);
            set.add(b[i]);
        }
        
        // now we are going to add the remaining elements
        if(n > m){
            for(int i = m; i < n; i++){
                set.add(a[i]);
            }
        }
        else if(n < m){
            for(int i = n; i < m; i++){
                set.add(b[i]);
            }
        }
        
        return set.size();
    }
}
	
/*
METHOD 3:(USING SORTING AND SEARCHING)

TIME: O(min(mLogm + nLogm, mLogn + nLogn)) == O(min(m+n)Logm, (m+n)Logn)).
*/


class Solution {
    
    public static void printUnion(int[] a, int[] b, int n, int m){
        
        // s1: if the first array is larger in length then make it smaller
        if(n > m){
            int[] tempA = a;
            a = b;
            b = tempA;
            
            // changing their sizes
            int temp = n;
            n = m;
            m = temp;
        }
        
        // s2: Now sort the smaller array
        Arrays.sort(a);

        for(int i = 0; i < n; i++){
            System.out.print(a[i]+" ");
        }
        
        // s3: for every element X in the larger array, we try to find those elements 
	// which are not present in array a, then we put that into array a;
        for(int i = 0; i < m; i++){
            
            // not present
            if(binarySearch(a, 0, n-1, b[i]) == -1){
                System.out.print(b[i]+" ");
            }
        }
    }
    
    public static void printIntersection(int[] a, int[] b, int n, int m){
        
        // s1: if the first array is larger in length then make it smaller
        if(n > m){
            int[] tempA = a;
            a = b;
            b = tempA;
            
            // changing their sizes
            int temp = n;
            n = m;
            m = temp;
        }
        
        // s2: Now sort the smaller array
        Arrays.sort(a);
        
        // s3: for every element X in the larger array, we try to find those elements 
	// which are not present in array a, then we put that into array a;
        for(int i = 0; i < m; i++){
            
            // not present
            if(binarySearch(a, 0, n-1, b[i]) != -1){
                System.out.print(b[i]+" ");
            }
        }
    }
}
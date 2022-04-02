/*
Given an array arr[] and an integer K where K is smaller than size of array, the task is to find the Kth smallest element in the given array. It is given that all 
array elements are distinct.

Example 1:

Input:
N = 6
arr[] = 7 10 4 3 20 15
K = 3

Output : 7

Explanation :
3rd smallest element in the given array is 7.

Example 2:

Input:
N = 5
arr[] = 7 10 4 20 15
K = 4

Output : 15

Explanation :
4th smallest element in the given array is 15.

METHOD 1:(USING SORTING) 
	Just sort the array and return the (K - 1)th element. TIME: O(NlogN).

METHOD 2:(USING HEAP)

TIME: O(NlogK), logK to build the heap.
*/

class Solution{
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < k; i++){
            maxHeap.offer(arr[i]);
        }
        
        for(int i = k; i < arr.length; i++){
            if(arr[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        
        return maxHeap.peek();
    } 
}

/*
METHOD 3:(USING K-SELECT ALGORITHM)

TIME: O(N), on average.But at worst case it may be go upto O(N^2).
*/

class Solution{
    
    public static int partition(int[] a, int left, int right, int pivotIndex){
        
        int pivotValue = a[pivotIndex];
        int i = left;
        
        /*
        Move the item at the 'pivotIndex' OUT OF THE WAY. We are about to bulldoze
        through the partitioning space and we don't want it in the way
        */
        swap(a, pivotIndex, right);
        
        for(int j = left; j < right; j++){
            if(a[j] < pivotValue){
                swap(a, j, i);
                i++;
            }
        }
        /*
        * Ok...partitioning is done. Swap the pivot item BACK into the space we just
        * partitioned at the 'lesserItemsTailIndex'...that's where the pivot item
        * belongs In the middle of the "sandwich".
        */
        swap(a, right, i);
        
        return i;
    }
    
    public static void swap(int[] a, int i, int j){
        
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
       int n = arr.length;
       int left = l;
       int right = r;
       
       Random rand = new Random(0);
       
       while(left <= right){
           
           int pivotIndex = rand.nextInt(right - left + 1) + left;
           
           // performing the partition operation
           int finalIndexOfChoosenPivot = partition(arr, left, right, pivotIndex);
           
           if(finalIndexOfChoosenPivot == k - 1){
               
               // we found our k the smallest element
               return arr[finalIndexOfChoosenPivot];
           }
           else if(finalIndexOfChoosenPivot < k - 1){
               /*
                * finalIndexOfChoosenPivot < k - 1
                * k'th smallest must be in the right partition. We "undershot" and need to go
                * right (and we do this by narrowing the left bound)
                */
               left = finalIndexOfChoosenPivot + 1;
           }
           else{
               /*
                * k'th smallest must be in the left partition. We "overshot" and need to go left
                * (and we do this by narrowing the right bound)
                */
               right = finalIndexOfChoosenPivot - 1;
           }
       }
       
       return -1;
    } 
}
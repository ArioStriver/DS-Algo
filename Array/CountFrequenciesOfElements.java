/*
Given an array A[] of N positive integers which can contain integers from 1 to P where elements can be repeated or can be absent from the array. Your task is to count the frequency of all 
elements from 1 to N.

Note: The elements greater than N in the array can be ignored for counting and please read your task section of the problem carefully to understand how to output the array.

Example 1:

Input:
N = 5

arr[] = {2, 3, 2, 3, 5}
P = 5

Output:
0 2 2 0 1
Explanation: 
Counting frequencies of each array element
We have:
1 occurring 0 times.
2 occurring 2 times.
3 occurring 2 times.
4 occurring 0 times.
5 occurring 1 time.

Example 2:

Input:
N = 4

arr[] = {3,3,3,3}
P = 3

Output:
0 0 4 0
Explanation: 
Counting frequencies of each array element
We have:
1 occurring 0 times.
2 occurring 0 times.
3 occurring 4 times.
4 occurring 0 times.


METHOD 1:(USING HASHING)

TIME: O(N).

SPACE: O(N).

METHOD 2:
	APPROACH:
		All the array elements are from 1 to n. Reduce every element by 1. The array elements now are in range 0 to n-1 so it can be said that for every index i, floor(array[i]/n) = 0. 
		So as previously said that the idea is to traverse the given array, use elements as an index and store their counts at the index. Consider the basic approach, a Hashmap of size 
		n is needed and the array is also of size n. So the array can be used as a hashmap but the difference is that instead of replacing elements add n to the array[i]th index. 
		So after updating the (array[i] % n) will give the ith element and (array[i] / n) will give the frequency of (i+1). 

TIME: O(N).

SPACE: O(1).
*/

class Solution{
    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P)
    {
        // 1. FIrst decrease the the each array element by one bcz accd. to the question the elements 
        // are in the range from (1 - n) & inedx(0 - (n-1)), so if there is a element with value n, then
        // there is no index for that element
        for(int i = 0; i < N; i++) {
            arr[i]--;
        }
        
        // 2. Use every element arr[i] as index and add 'N' to that element present at (arr[i] % n) 
        // to keep track of count of occurrences of arr[i]
        // why we are adding 'N' ? bcz as we know that the values in the current array are in the range 
        // from (0 - (N-1)), it means that all elements should be less than N
        for(int i = 0; i < N; i++) {
            if(arr[i] % P > N-1) {
                continue;
            }
            
            arr[arr[i] % P] = P + arr[arr[i] % P];
        }
        
        // 3. print counts, simply print the number of times n was added at index corresponding to every element
        for (int i = 0; i < N; i++) {
            arr[i] = (arr[i] / P);
        }
    }
}
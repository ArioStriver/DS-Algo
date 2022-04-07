/*
Given three arrays sorted in increasing order. Find the elements that are common in all three arrays.
Note: can you take care of the duplicates without using any additional Data Structure?

Example 1:

Input:
n1 = 6; A = {1, 5, 10, 20, 40, 80}
n2 = 5; B = {6, 7, 20, 80, 100}
n3 = 8; C = {3, 4, 15, 20, 30, 70, 80, 120}

Output: 20 80

Explanation: 20 and 80 are the only
common elements in A, B and C.


METHOD 1:

TIME: O(N1 + N2 + N3), where N1, N2, N3 are the length of Arrays A, B, C.

SPACE: O(1).
*/

class Solution
{
    ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) 
    {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        int i = 0, j = 0, k =0;
        
        while(i < n1 && j < n2 && k < n3){
            
            // handling duplicates
            while(i+1 < n1 && A[i] == A[i+1]) i++;
            
            while(j+1 < n2 && B[j] == B[j+1]) j++;
            
            while(k+1 < n3 && C[k] == C[k+1]) k++;
            
            // three of then are equal
            if(A[i] == B[j] && B[j] == C[k]){
                result.add(A[i]);
                i++;
                j++;
                k++;
            }
            // i < j
            else if(A[i] < B[j]){
                i++;
            }
            // j < k
            else if(B[j] < C[k]){
                j++;
            }
            // j > k
            else{
                k++;
            }
        }
        
        return result;
    }
}
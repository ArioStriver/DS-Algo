/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4

Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

METHOD 1:(BRUTE FORCE)
	APPROACH:
		Check the whether the consecutive elements exists or not for each current element. 

TIME: O(N^3).

SPACE: O(1).
*/

class Solution {
    
    private boolean consecutiveContains(int[]a, int val){
        
        for(int i = 0; i < a.length; i++) {
            if(a[i] == val) {
                return true;
            }
        }
        return false;
    }
    
    public int longestConsecutive(int[] arr) {
        
        int max_subsequence_length = 0;
        
        for(int a : arr){
            
            int currNum = a;
            int count = 1;
            
            // checking for the consecutive elements onr by one
            while(consecutiveContains(arr, currNum+1)) {
                currNum += 1;
                count++;
            }
            
            max_subsequence_length = Math.max(max_subsequence_length, count);
        }
        
        return max_subsequence_length;
    }
}

/*
METHOD 2:(SORTING)
	APPROACH:
		If the difference between the current element and the next element is 1, it is a consecutive sequence.

TIME: O(NlogN).

SPACE: O(1).
*/

class Solution {
    public int longestConsecutive(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }

        Arrays.sort(arr);

        int max_subsequence_length = 1;
        int count= 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i-1]) {
                if (arr[i] == arr[i-1]+1) {
                    count++;
                }
                else {
                    max_subsequence_length = Math.max(max_subsequence_length, count);
                    count = 1;
                }
            }
        }

        return Math.max(max_subsequence_length, count);
    }
}

/*
METHOD 3:(USING HASHSET)

TIME: O(N) + O(N) + O(N), first O(N) for adding elements into the set, second O(N) for traversing the array and last O(N) for finding consecutive the element at worst case.

SPACE: O(N).
*/

class Solution {
    
    public int longestConsecutive(int[] arr) {
        
        int max_subsequence_length = 0;
        
        Set<Integer> nums_set = new HashSet<>();
        
        // adding the elements into the set
        for(int num : arr){
            nums_set.add(num);
        }
        
        // now we gonna find the longest subsequence length
        for(int num : arr) {
            
            /* always try to find the smallest number of the longest sequence present or not
               if it is present then it's not the smallest number in the sequence, move to the 
               next element if not prsent then look for the other consecutive elements. This will 
               help us to avoid checking each and every element in the array, which will gives us 
               better time complexity */
            if(!nums_set.contains(num-1)) {
                
                int curNum = num;
                int count = 1;
                
                // check for cosecutive element
                while(nums_set.contains(curNum + 1)) {
                    curNum++;
                    count++;
                }
                
                max_subsequence_length = Math.max(max_subsequence_length, count);
            }
        }
        
        return max_subsequence_length;
    }
}
/*
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:

Input: ratings = [1,0,2]
Output: 5

Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:

Input: ratings = [1,2,2]
Output: 4

Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Start with distributing one candy to each child and then check if any child who has more rating than his one of the neighbours should have more candy than its neighbour. So, 
		distribute one more candy to that child who does not satisfy the above condition. Do this until every child is satisfied.

TIME: O(N^2).

SPACE: O(N).
*/

class Solution {
    public int candy(int[] ratings) {
        
        int[] candies = new int[ratings.length];
        boolean flag = true;
        
        // first distribute atleast one candy to each student
        Arrays.fill(candies, 1);
        
        // keep traversing the ratings array untill we fulfill the given condition in the question
        // means keep incrementing the candy number untill 
        // Children with a higher rating get more candies than their neighbors
        while(flag == true) {
            
            flag = false;
            
            for(int i = 0; i < ratings.length; i++) {
                
                // checking for left neighbour
                if(i > 0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) {
                    candies[i] = candies[i-1] + 1;
                    flag = true;
                }
                
                // checking for right neighbour
                if(i < ratings.length-1 && ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
                    candies[i] = candies[i+1] + 1;
                    flag = true;
                }
            }
        }
        
        // finding the total candies needed
        int sum = 0;
        for(int num : candies) {
            sum += num;
        }
        
        return sum;
    }
}

/*
METHOD 2: (GREEDY METHOD)(UISNG TWO ARRAYS)
	APPROACH:
		Dividing the problem into two parts.
			1. Find the minimum number of candies given to the child[i] who have a higher rating than child[i-1].
			2. Find the minimum number of candies given to the child[i] who have a higher rating than child[i+1].
 
TIME: O(3N) == O(N).

SPACE: O(2N) == O(N).
*/

class Solution {
    public int candy(int[] ratings) {
      
        int n = ratings.length;
        int[] left2right = new int[n];
        int[] right2left = new int[n];
        
        // intialization
        left2right[0] = 1; right2left[n-1] = 1;
        
        // first will traverse from left to right
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) {
                left2right[i] = left2right[i-1] + 1;
            }
            else {
                left2right[i] = 1;
            }
        }
        
        // then will traverse from right to left
        for(int i = n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {
                right2left[i] = right2left[i+1] + 1;
            }
            else {
                right2left[i] = 1;
            }
        }
        
        // finally find the maximum for ecah index from both the array
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        
        return sum;
    }
}

/*
METHOD 3:(USING ONE ARRAY)

TIME: O(3N) == O(N).

SPACE: O(N).
*/

class Solution {
    public int candy(int[] ratings) {
      
        int n = ratings.length;
        int[] candy = new int[n];
        
        // intialization
        candy[0] = 1;
        candy[n-1] = 1;
        
        // first will traverse from left to right
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
            else {
                candy[i] = 1;
            }
        }
        
        // then will traverse from right to left
        for(int i = n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1] && candy[i] <= candy[i+1]) {
                candy[i] = candy[i+1] + 1;
            }
        }
        
        // finally find the maximum for ecah index from both the array
        int sum = 0;
        for(int num : candy) {
            sum += num;
        }
        
        return sum;
    }
}
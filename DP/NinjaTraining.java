/*
NINJA TRAINING

Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). 
Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find 
out the maximum merit points Ninja can earn?
You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points 
that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.

Sample Input 1:
2
3
1 2 5 
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
Sample Output 1:
11
210

METHOD 1:(RECURSION)

TIME: O(2^N). N this approach, in the worst case, we will run the 'maxScoreEarned' function for all possible combinations. Each day has two choices. So the total number of 
	 combinations is 2^N. Hence the overall time complexity is O(2^N).
/*

public class Solution {
    
    private static int maxScoreEarned(int day, int lastTaskPerformed, int[][] points){
    	// base case
        // if we are at the 0 th day
        if(day == 0){
            // then we have to check what task we have performed on previous day
            int max = 0;
            
            for(int task = 0; task < 3; task++){
                // if the current task is not performed take it
                if(task != lastTaskPerformed){
                    max = Math.max(max, points[0][task]);
                }
            }
            return max;
        }
        
        // do the same thing for  the rest of the days
        int maxScore = 0;
        
        for(int task = 0; task < 3; task++){
            // if the current task is not performed 
            if(task != lastTaskPerformed){
                
                // add the points of the current task we performing + the max form the previous day
                int point = points[day][task] + maxScoreEarned(day - 1, task, points);
                maxScore = Math.max(maxScore, point);
            }
        }
        
        return maxScore;
    }
    
    public static int ninjaTraining(int n, int points[][]) {
		
        //int n = points.length;
        // day, last task we performed, points array
        return maxScoreEarned(n - 1, 3, points);
    }
}


// METHOD 2:(USING MEMOIZATION)

// TIME: O(N).

// SPACE: O(4 * N)+ O(N). In this approach, we are using the ‘DP’ table of size ‘N’ * 4’, and in the worst case, we are making N * 3 calls of the 'maxScoreEarned'function. 
//	  Hence the overall space complexity is O(N).     


public class Solution {
    
    private static int maxScoreEarned(int day, int lastTaskPerformed, int[][] points, int[][] memo){
    	// base case
        // if we are at the 0 th day
        if(day == 0){
            // then we have to check what task we have performed on previous day
            int max = 0;
            
            for(int task = 0; task < 3; task++){
                // if the current task is not performed take it
                if(task != lastTaskPerformed){
                    max = Math.max(max, points[0][task]);
                }
            }
            return max;
        }
        
        // if previously solved
        if(memo[day][lastTaskPerformed] != -1) return memo[day][lastTaskPerformed];
        
        // do the same thing for  the rest of the days
        int maxScore = 0;
        
        for(int task = 0; task < 3; task++){
            // if the current task is not performed 
            if(task != lastTaskPerformed){
                
                // add the points of the current task we performing + the max form the previous day
                int point = points[day][task] + maxScoreEarned(day - 1, task, points, memo);
                maxScore = Math.max(maxScore, point);
            }
        }
        return memo[day][lastTaskPerformed] = maxScore;
    }
    
    public static int ninjaTraining(int n, int points[][]) {
		
        // why 4 ? bcz 0, 1, 2 task and 3 means we not performed any task
        int[][] memo = new int[n][4];
        
        for (int i = 0; i < n; i++) {
		for (int j = 0; j < 4; j++) {
			memo[i][j] = -1;
		}
	}
        
        return maxScoreEarned(n - 1, 3, points, memo);
    }
}


// METHOD 3:(UISNG DYNAMIC PROGRAMMING)

// TIME: O(N * 4 * 3) == O(N).

// SPACE: O(N * 4)


public class Solution {

    public static int ninjaTraining(int n, int points[][]) {
		
        //int n = points.length;
        int[][] dp = new int[n][4];
        
        // base case
        // for 0th day
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        for(int day = 1; day < n; day++){
            for(int last = 0; last < 4; last++){
                
                // there are three tasks
                for(int task = 0; task < 3; task++){
                    // if the current task is not equal to the previous task
                    if(task != last){
                        int point = points[day][task] + dp[day-1][task];

                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }
        
        return dp[n-1][3];
    }
}

/*
 METHOD 4:(SPACE OPTIMIZED)
	APPROACH:
		If we closely look the relation,

			dp[day][last] =  max(dp[day][last],points[day][task] + dp[day-1][task])

		Here the task can be anything from 0 to 3 and day-1 is the previous stage of recursion. So in order to compute any dp array value, we only require the 
		last row to calculate it.
		
		1. So rather than storing the entire 2D Array of size N*4, we can just store values of size 4(say prev). 
		2. We can then take a dummy array, again of size 4 (say temp) and calculate the next row’s value using the array we stored in step 1.
		3. After that whenever we move to the next day, the temp array becomes our prev for the next step.
		4. At last prev[3] will give us the answer.

TIME: O(N*4*3). There are three nested loops.

SPACE: O(4). We are using an external array of size ‘4’ to store only one row.
*/

public class Solution {

    public static int ninjaTraining(int n, int points[][]) {
		
        //int n = points.length;
        int[] prev = new int[4];
        
        // base case
        // for 0th day
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        for(int day = 1; day < n; day++){
           
            // to store  the current result
            int[] temp = new int[4];
            
            for(int last = 0; last < 4; last++){
                
                // there are three tasks
                for(int task = 0; task < 3; task++){
                    // if the current task is not equal to the previous task
                    if(task != last){
                        int point = points[day][task] + prev[task];
                        temp[last] = Math.max(temp[last], point);
                    }
                }
            }
            prev = temp;
        }
        
        return prev[3];
    }
}
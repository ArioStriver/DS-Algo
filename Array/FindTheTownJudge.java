/*
In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2

Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3

Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1


METHOD 1:

TIME: O(N + N).

SPACE: O(N).
*/

class Solution {
    public int findJudge(int N, int[][] trust) {
        
        int[][] count = new int[N+1][2];
        
        for(int i = 0; i < trust.length; i++) {
            count[trust[i][0]][0]++;  // keep track of how many others you are trusting
            count[trust[i][1]][1]++;  // keep track of how many others trust this person
        }

        for(int i = 1; i <= N; i++) {
            // if the current person doesn't trust anyone 
            // and the (N-1) no of person trust the current person
            // then the current person is the town judge
            if(count[i][0] == 0 && count[i][1] == (N-1)) {
                return i;
            }
        }

        return -1;
    }
}


/*
METHOD 2:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int findJudge(int N, int[][] trust) {
        
        int[] count = new int[N+1];

        // when the current person trusting someone increment the count for the that person
        // and as the current person trusting someone so we have to decrement current persons count
        for(int i = 0; i < trust.length; i++) {
            count[trust[i][1]]++;  
            count[trust[i][0]]--;  
        }

        for(int i = 1; i <= N; i++) {
            // if the (N-1) no of person trust the current person
            // then the current person is the town judge
            if(count[i] == (N-1)) {
                return i;
            }
        }

        return -1;
    }
}

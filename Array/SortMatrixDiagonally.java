/*
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. 
For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

Example 1:

Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

Example 2:

Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 
Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100


METHOD 1:
	APPROACH:
		First take out all the diagonal elements and store them in a data structure and sort them and put them back in the matrix.

TIME: O((M + N) * KlogK), where K = min(M, N), why? bcz at most that many numbers are there in the diagonal.

SPACE: O(K).
*/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        
        // for column = 0
        for(int row = m-1; row >= 0; row--) {
            sort(mat, row, 0, m, n);
        }
        
        // for row = 0
        for(int col = 1; col < n; col++) {
            sort(mat, 0, col, m, n);
        }
        
        return mat;
    }
    
    private void sort(int[][] mat, int row, int col, int m, int n) {
        
        // taking a data structure to store the diagonal elements
        List<Integer> nums = new ArrayList<>();
        
        int i = row, j = col;
        
        while(i < m && j < n) {
            nums.add(mat[i][j]);
            i++;
            j++;
        }
        
        // sort the list
        Collections.sort(nums);
        
        // now put the sorted elements back into the matrix
        i = row; 
        j = col;
        int idx = 0;
      
        while(i < m && j < n) {
            mat[i][j] = nums.get(idx++);
            i++;
            j++;
        }
    }
}

/*
METHOD 2:(COUNT SORT)
	APPROACH:
		As the number rage is small till 100, so Instead of using in build sort functon to sort the array, we an use count sort to sort the diagonals.

TIME: O((M + N) * K)

SPACE: O(1).
*/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        
        // for column = 0
        for(int row = m-1; row >= 0; row--) {
            sort(mat, row, 0, m, n);
        }
        
        // for row = 0
        for(int col = 1; col < n; col++) {
            sort(mat, 0, col, m, n);
        }
        
        return mat;
    }
    
    private void sort(int[][] mat, int row, int col, int m, int n) {
        
        // taking a data structure to store the diagonal elements
        int[] count = new int[101];
        
        int r = row, c = col;
        
        while(r < m && c < n) {
            count[mat[r][c]]++;
            r++;
            c++;
        }
        
        // put the sorted elements back into the matrix
        r = row; 
        c = col;
        
        for(int i = 0; i < 101; i++) {
            
            if(count[i] > 0) {
                while(count[i] > 0) {
                   mat[r][c] = i;
                    r++;
                    c++;
                    count[i]--;
                }
            }
        }
    }
}

/*
METHOD 3:(USING HASHING & PRIORITYQUEUE)
	APPROACH:
		We know that all cells in the same diagonal (i,j) have the same difference b/w i & j, so we can get the diagonal of a cell using the difference i-j. So depending on the difference
 		we are storing the diagonal elements having same difference in a priorityqueu. Why PriorityQueue? bcz PriorityQueue by default use min heap, so if we store the elements in a 
		PriorityQueue then we always get the small element from the list, we don't need to sort it. This is the idea behind using PriorityQueue to avoid sorting.

TIME: O((M * N) * logK), where K = min(M, N).

SPACE: O(M * N), that many elements we are storing in a map.
*/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        
        // Taking a  map to store the diagonal elements
        // Taking priority, so that we don't ahve to sort the diagonal elements as priorityqueue by
        // default uses min heap, so we will always get the minimum element first
        Map<Integer, PriorityQueue<Integer>> nums = new HashMap<>();
        
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // here we storing the diagonal elements depending on the (i-j)
                // bcz all cells in the same diagonal (i,j) have the same difference b/w i & j
                int diagonalKey = i - j;
                
                // intializing a new priority queue if there is no pq for the current id
                PriorityQueue<Integer> pq = nums.getOrDefault(diagonalKey, new PriorityQueue<>());
                pq.offer(mat[i][j]);
                nums.put(diagonalKey, pq);
            }
        }
        
        // now as the priority queue will always first give us the minimum value, so we will get the data
        // in ascending order, so no need to sort the diagonal elements
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                int diagonalKey = i - j;
                PriorityQueue<Integer> pq = nums.get(diagonalKey);
                mat[i][j] = pq.poll();
            }
        }
        
        return mat;
    }
}
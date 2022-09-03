/*
Given a matrix mat[][] of size N x M, where every row and column is sorted in increasing order, and a number X is given. The task is to find whether element X is present in the matrix or 
not.

Example 1:

Input:
N = 3, M = 3
mat[][] = 3 30 38 
         44 52 54 
         57 60 69
X = 62

Output:
0
Explanation: 62 is not present in the matrix, so output is 0

Example 2:

Input:
N = 1, M = 6
mat[][] = 18 21 27 38 55 67

X = 55

Output:
1
Explanation: 55 is present in the matrix at 5th cell.


METHOD:

TIME: O(M* N).

SPACE: O(1).
*/

class Sol
{
    public static int matSearch(int mat[][], int n, int m, int x)
    {
        int i = 0, j = m-1; //set indexes for top right element
        while ( i < n && j >= 0 )
        {
            if( mat[i][j] == x )
            {
                return 1;
            }
            if(mat[i][j] > x)
                j--;
            else            //  if mat[i][j] < x
                i++;
        }
 
       return 0;
    }
}

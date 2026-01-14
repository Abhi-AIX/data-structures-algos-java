package graphs.implementations;

public class GraphMatrixImpl {

    int n,m = 3;
    int[][] adj = new int[n+1][n+1];

    public void storeGraphInMatrix(){
        //edge 1 --- 2
        adj[1][2] = 1;
        adj[2][1] = 1;

        //edge 2 --- 3
        adj[2][3] = 1;
        adj[3][2] = 1;

        //edge 3 --- 2
        adj[3][2] = 1;
        adj[2][3] = 1;

        //O(n) * O(n) = O(n^2)
    }

}

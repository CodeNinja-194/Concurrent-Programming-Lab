import java.util.concurrent.*;

public class MatrixMultiplication 
{
public static void main(String[] args) 
{
        int n = 4; // number of threads
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] b = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] result = new int[a.length][b[0].length];
        // Thread pool with n threads
        ExecutorService executor = Executors.newFixedThreadPool(n);
        // Create n tasks and add them to the thread pool
        for (int i = 0; i < a.length; i++) 
        {
            for (int j = 0; j < b[0].length; j++) 
            {
                executor.execute(new MatrixThread(a, b, result, i, j));
            }
        }
        // Wait for all tasks to complete
        executor.shutdown();
        try 
        {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        // Print the result
        for (int i = 0; i < result.length; i++) 
        {
            for (int j = 0; j < result[0].length; j++)
            {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// Thread class to perform matrix multiplication
class MatrixThread implements Runnable 
{
    private int[][] a, b, result;
    private int row, col;
    public MatrixThread(int[][] a, int[][] b, int[][] result, int row, int col) 
    {
        this.a = a;
        this.b = b;
        this.result = result;
        this.row = row;
        this.col = col;
    }

    @Override
    public void run() 
    {
        for (int i = 0; i < b.length; i++) 
        {
            result[row][col] += a[row][i] * b[i][col];
        }
    }
}

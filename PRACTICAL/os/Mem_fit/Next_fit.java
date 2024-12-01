package Mem_fit;
import java.util.Scanner;

public class Next_fit{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        int[] p = new int[n];

        System.out.println("Enter number of memory blocks:");
        int m = sc.nextInt();
        int[] b = new int[m];
        int[] f = new int[m];  
        boolean[] allocated = new boolean[n]; 

        for (int i = 0; i < n; i++) {
            System.out.println("Enter size of process number " + i + ":");
            p[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            System.out.println("Enter size of Block " + i + ":");
            b[i] = sc.nextInt();
            f[i] = 0;
        }
        int[][] allocation = new int[n][2];
        int lastAllocatedBlock = 0;

        for (int i = 0; i < n; i++) {
            boolean allocatedProcess = false;
            for (int j = 0; j < m; j++) {
                int index = (lastAllocatedBlock + j) % m;  
                if (p[i] <= b[index] && f[index] == 0) {
                    allocation[i][0] = i;
                    allocation[i][1] = index;
                    f[index] = 1;
                    allocated[i] = true;
                    lastAllocatedBlock = index;
                    allocatedProcess = true;
                    break;
                }
            }

            if (!allocatedProcess) {
                allocated[i] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!allocated[i]) {
                System.out.println("Process no " + i + " is not allocated to any block.");
            } else {
                System.out.println("Process no " + allocation[i][0] + " is running in block " + allocation[i][1]);
            }
        }
    }
}

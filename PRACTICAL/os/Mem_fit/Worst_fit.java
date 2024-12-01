package Mem_fit;
import java.util.Arrays;
import java.util.Scanner;

public class Worst_fit{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        int[] p = new int[n];
        
        System.out.println("Enter number of memory blocks:");
        int m = sc.nextInt();
        int[][] b = new int[m][2];
        int[] f = new int[m];
        boolean[] allocated = new boolean[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter size of process number " + i + ":");
            p[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            System.out.println("Enter size of Block " + i + ":");
            b[i][0] = sc.nextInt();
            b[i][1] = i;
            f[i] = 0;
        }
        Arrays.sort(b, (a, b1) -> b1[0] - a[0]);
        int[][] allocation = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (p[i] <= b[j][0] && f[j] == 0) {
                    allocation[i][0] = i;
                    allocation[i][1] = b[j][1];
                    f[j] = 1;
                    allocated[i] = true;
                    break;
                }
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

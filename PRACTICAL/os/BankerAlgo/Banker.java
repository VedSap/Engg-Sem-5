package BankerAlgo;
import java.util.Scanner;

class Banker {
    static int P;
    static int R;

    static void calculateNeed(int need[][], int maxm[][], int allot[][]) {
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                need[i][j] = maxm[i][j] - allot[i][j];
            }
        }
    }

    static boolean isSafe(int processes[], int avail[], int maxm[][], int allot[][]) {
        int[][] need = new int[P][R];
        calculateNeed(need, maxm, allot);

        boolean[] finish = new boolean[P];
        int[] safeSeq = new int[P];
        int[] work = new int[R];

        for (int i = 0; i < R; i++) {
            work[i] = avail[i];
        }

        int count = 0;
        while (count < P) {
            boolean found = false;
            for (int p = 0; p < P; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < R; j++) {
                        if (need[p][j] > work[j]) {
                            break;
                        }
                    }
                    if (j == R) {
                        for (int k = 0; k < R; k++) {
                            work[k] += allot[p][k];
                        }
                        safeSeq[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }

        System.out.println("System is in a safe state.");
        System.out.print("Safe sequence is: ");
        for (int i = 0; i < P; i++) {
            System.out.print(safeSeq[i] + " ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        P = sc.nextInt();

        System.out.print("Enter number of resources: ");
        R = sc.nextInt();

        int[] processes = new int[P];
        for (int i = 0; i < P; i++) {
            processes[i] = i;
        }

        int[] avail = new int[R];
        System.out.println("Enter available resources: ");
        for (int i = 0; i < R; i++) {
            avail[i] = sc.nextInt();
        }

        int[][] maxm = new int[P][R];
        System.out.println("Enter maximum resource matrix: ");
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                maxm[i][j] = sc.nextInt();
            }
        }

        int[][] allot = new int[P][R];
        System.out.println("Enter allocation matrix: ");
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                allot[i][j] = sc.nextInt();
            }
        }

        isSafe(processes, avail, maxm, allot);

        sc.close();
    }
}


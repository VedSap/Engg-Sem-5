package scheduling;
import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        int n, i, j, c, st = 0, tot = 0;
        float totaltat = 0, totalwt = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int f[] = new int[n];  // To mark finished processes

        // Input process details
        for (int m = 0; m < n; m++) {
            System.out.println("Enter the Process ID: ");
            pid[m] = sc.nextInt();
            System.out.println("Enter the Arrival Time: ");
            at[m] = sc.nextInt();
            System.out.println("Enter the Burst Time: ");
            bt[m] = sc.nextInt();
            f[m] = 0; // Mark all processes as unfinished
        }

        // Sort processes by Arrival Time (FCFS requires ordering by arrival time)
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    int temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                }
            }
        }

        // FCFS scheduling
        while (true) {
            c = n;  // No process selected initially

            // Find the next process that has arrived and is not finished
            for (i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0) {
                    c = i;
                    break;
                }
            }

            // If all processes are completed, exit the loop
            if (tot == n) {
                break;
            }

            if (c == n) {
                // No process is ready; increment start time
                st++;
            } else {
                // Schedule the selected process
                ct[c] = st + bt[c];             // Completion time
                tat[c] = ct[c] - at[c];         // Turnaround time
                wt[c] = tat[c] - bt[c];         // Waiting time
                st = ct[c];                     // Update start time to the current completion time
                f[c] = 1;                       // Mark process as finished
                tot++;                          // Increment completed process count
            }
        }

        // Calculate total TAT and WT
        for (i = 0; i < n; i++) {
            totaltat += tat[i];
            totalwt += wt[i];
        }

        // Calculate averages
        float atat = totaltat / n;
        float awt = totalwt / n;

        // Display results
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("Average Turnaround Time: " + atat);
        System.out.println("Average Waiting Time: " + awt);

        sc.close();
    }
}

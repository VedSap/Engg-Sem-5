package scheduling;
import java.util.Scanner;

public class PriorityP {
    public static void main(String[] args) {
        int n, i, j, c, st = 0, tot = 0;
        float totaltat = 0, totalwt = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int remaining_bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int priority[] = new int[n];
        int f[] = new int[n];  // To mark finished processes

        // Input process details
        for (int m = 0; m < n; m++) {
            System.out.println("Enter the Process ID: ");
            pid[m] = sc.nextInt();
            System.out.println("Enter the Arrival Time: ");
            at[m] = sc.nextInt();
            System.out.println("Enter the Burst Time: ");
            bt[m] = sc.nextInt();
            System.out.println("Enter the Priority (lower number = higher priority): ");
            priority[m] = sc.nextInt();
            remaining_bt[m] = bt[m]; // Initialize remaining burst time with burst time
            f[m] = 0;                // Mark all processes as unfinished
        }

        // Priority Preemptive scheduling
        while (true) {
            c = n;  // Initialize to n to indicate no process selected
            int min_priority = 99;

            // Find the process with the highest priority (lowest priority number) that has arrived
            for (i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0 && priority[i] < min_priority) {
                    min_priority = priority[i];
                    c = i;
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
                // Execute one unit of the selected process
                remaining_bt[c]--;    // Decrement the remaining burst time by 1
                st++;                 // Increment the start time by one unit

                // If the process is finished, calculate completion, TAT, WT
                if (remaining_bt[c] == 0) {
                    ct[c] = st;                 // Completion time is the current time
                    tat[c] = ct[c] - at[c];     // Turnaround time = Completion time - Arrival time
                    wt[c] = tat[c] - bt[c];     // Waiting time = Turnaround time - Original burst time
                    f[c] = 1;                   // Mark the process as finished
                    tot++;                      // Increment the count of completed processes
                }
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
        System.out.println("PID\tAT\tBT\tPriority\tCT\tTAT\tWT");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + priority[i] + "\t\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("Average Turnaround Time: " + atat);
        System.out.println("Average Waiting Time: " + awt);

        sc.close();
    }
}
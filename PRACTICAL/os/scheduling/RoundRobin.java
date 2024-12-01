package scheduling;
import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {
		 int n, i, j, k;
	        float atat = 0, awt = 0;

	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter the number of processes: ");
	        n = sc.nextInt();
	        int pid[] = new int[n];
	        int at[] = new int[n];
	        int bt[] = new int[n];
	        int ct[] = new int[n];
	        int tat[] = new int[n];
	        int wt[] = new int[n];
	        int b[]= new int[n];
	        int flag[] = new int[n];
	        

	        for (int m = 0; m < n; m++) {
	            System.out.println("Enter the Process id");
	            pid[m] = sc.nextInt();
	            System.out.println("Enter the Arrival time");
	            at[m] = sc.nextInt();
	            System.out.println("Enter the Burst time");
	            bt[m] = sc.nextInt();
	            flag[m] = 0;
	            b[m]= bt[m];
	        }
	        System.out.println("enter the time quantum");
	        int tq = sc.nextInt();
	        int tot = 0,st = 0;
	        while(true) {
	        	if(tot == n) {
	        		break;
	        	}
	        	for( i = 0; i<n;i++) {
	        		if (at[i]<= st && flag[i] == 0 ) {
	        			if (tq >= b[i]) {
	        				ct[i] = st + b[i];
	        				st = ct[i];
	        				b[i] = 0;
	        				tot++;
	        				flag[i] = 1;
	        			}
	        			else {
	        				b[i] = b[i] - tq;
	        				st = st + tq;
	        			}
	        		}
	        	}
	        }
	        	for (int t = 0; t < n; t++) {
	                tat[t] = ct[t] - at[t];
	                wt[t] = tat[t] - bt[t];
	                atat += tat[t];
	                awt += wt[t];
	            }

	            System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
	            for (k = 0; k < n; k++) {
	                System.out.println(pid[k] + "\t" + at[k] + "\t" + bt[k] + "\t" + ct[k] + "\t" + tat[k] + "\t" + wt[k]);
	            }

	            atat /= n;
	            awt /= n;
	            System.out.println("Average Turnaround time: " + atat);
	            System.out.println("Average Waiting time: " + awt);

	            sc.close(); 	        

	}

}

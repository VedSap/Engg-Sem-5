package scheduling;
import java.util.Scanner;

public class SJF {

	public static void main(String[] args) {
		int n,i,k;
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
        int flag[] = new int[n];
       

        for (int m = 0; m < n; m++) {
            System.out.println("Enter the Process id");
            pid[m] = sc.nextInt();
            System.out.println("Enter the Arrival time");
            at[m] = sc.nextInt();
            System.out.println("Enter the Burst time");
            bt[m] = sc.nextInt();
            flag[m] = 0;
        }
        int st = 0;
    	int tot = 0;
        while(true) {
        	
        	int min = 99;
         	int c = n;
        
        	
        	if(tot == n) {
        		break;
        	}
        	for(i = 0; i < n; i++ ) {
        		if(at[i]<=st && flag[i]==0 && bt[i]<min) {
        			c=i;
        			min = bt[i];
        		}
        	}
        	if(c==n) {
        		st++;
        	}
        	else {
        		ct[c] = st + bt[c];
        		flag[c] = 1;
        		st = ct[c];
        		tot++;
        		
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
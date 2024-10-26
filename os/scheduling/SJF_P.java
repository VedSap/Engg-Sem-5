package scheduling;
import java.util.Scanner;

public class SJF_P{
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of processes: ");
		int n = sc.nextInt();
		
		int[] Pid = new int[n];
		int[] AT = new int[n];
		int[] BT = new int[n];
		int[] CT = new int[n];
		int[] TAT = new int[n];
		int[] WT = new int[n];
		int[] remainingBT = new int[n];
		boolean[] completed = new boolean[n];
		
		
		for (int i = 0; i < n; i++) {
		Pid[i] = i + 1;
		System.out.println("Enter the Arrival Time of process " + (i+1)); 
		AT[i] = sc.nextInt();
		}
		
		
		
		for (int i = 0; i < n; i++) {
		System.out.println("Enter the Burst Time of process " + i); 
		BT[i] = sc.nextInt();
		
		remainingBT[i] = BT[i];
		}
		
		
		int st = 0 ;
		int completedProcesses = 0;
		int atat = 0,awt = 0;
		
		
		while (completedProcesses < n) { 
			int minBT = Integer.MAX_VALUE;
			int c = -1;
			for (int i = 0; i < n; i++) {
				if (AT[i] <= st && !completed[i] && remainingBT[i] < minBT) { 
					minBT = remainingBT[i];
					c = i;
					}
			}
		
			
			if (c == -1) { 
				st++;
			} 
			else { 
				remainingBT[c]--; 
				st++;
		
				if (remainingBT[c] == 0) { 
					CT[c] = st;
					TAT[c] = CT[c] - AT[c];
					WT[c] = TAT[c] - BT[c];
					atat = TAT[c];
		            awt += WT[c];
					completed[c] = true;
					completedProcesses++;
				 
				}
			}
		}
		System.out.println("\nPid\tAT\tBT\tCT\tTAT\tWT"); 
		for (int i = 0; i < n; i++) {
		System.out.println(Pid[i] + "\t" + AT[i] + "\t" + BT[i] + "\t" + CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
        }
		System.out.println("Average Turnaround time: " + atat);
        System.out.println("Average Waiting time: " + awt);
		
		sc.close();
	}
}

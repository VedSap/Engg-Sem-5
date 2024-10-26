package scheduling;
import java.util.Scanner;

public class PriorityP {
	static int n = 5;
	static int[] at = new int[n];
	static int[] bt = new int[n];
	static int[] Pbt = new int[n]; 
	static int[] Pid = new int[n]; 
	static int[] p = new int[n]; 
	static int[] ct = new int[n]; 
	static int[] tat = new int[n]; 
	static int[] wt = new int[n];
	static float atat = 0, awt = 0;
	
	static void inp() {
		Scanner sc = new Scanner(System.in);
		System.out.print("number of processes is 5 ");
		 
		for (int i = 0; i < n; i++) {
			System.out.println("\nEnter process id:"); 
			Pid[i] = sc.nextInt(); 
			System.out.println("Enter arrival time:"); 
			at[i] = sc.nextInt(); 
			System.out.println("Enter burst time:"); 
			bt[i] = sc.nextInt();
			System.out.println("Enter priority (lower number indicates higher priority):"); 
			p[i] = sc.nextInt();
		}
		sc.close();
	}
	static void out() {
		System.out.println("pid\tat\tbt\tp\tct\ttat\twt"); 
		for (int i = 0; i < n; i++) {
		System.out.println(Pid[i] + "\t" + at[i] + "\t" + Pbt[i] + "\t" + p[i] + "\t" + ct[i] + "\t"+ tat[i] + "\t" + wt[i]);
		}
		System.out.println("Average turnaround time: " + (atat / n)); 
		System.out.println("Average waiting time: " + (awt / n));
		
	}
	public static void main(String[] args) { 
		inp();
		for(int i=0;i<n;i++){ 
			Pbt[i]=bt[i];
		}
		int min;
		int ft[]=new int[n]; 
		int total=0;
		int st=0; 
		int c; 
		
		while(true){
			min=99; c=n; 
			if(total==n) {
			break;
			}
			for(int i=0;i<n;i++){
				if(at[i]<=st && ft[i]== 0 && min>p[i]){ 
				min=p[i]; c=i;
				}
			}
			if(c==n){ 
				st++;
			}
			else{
				bt[c]=bt[c]-1; st=st+1; 
				if(bt[c]==0){ 
					ct[c]=st;
					ft[c]=1;
					total++;
			}	
			}
		}
		
		for(int i = 0; i < n; i++) { 
		tat[i]=ct[i]-at[i];
		wt[i]=tat[i]-Pbt[i];
		 atat += tat[i]; 
		awt += wt[i];
		
		}
		out();		
		
		
	}
}
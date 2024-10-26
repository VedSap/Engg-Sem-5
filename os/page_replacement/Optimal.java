package page_replacement;

import java.util.*;

public class Optimal {
	
    static int predict(int[] pages, ArrayList<Integer> frames, int index) {
        int res = -1, farthest = index;
        for (int i = 0; i < frames.size(); i++) {
            int j;
            for (j = index; j < pages.length; j++) {
                if (frames.get(i) == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == pages.length)
                return i;
        }
        return (res == -1) ? 0 : res;
    }

    
    static void optimalPageReplacement(int[] pages, int capacity) {
        ArrayList<Integer> frames = new ArrayList<>(capacity);
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
           
            if (frames.size() < capacity) {
                if (!frames.contains(pages[i])) {
                    frames.add(pages[i]);
                    pageFaults++;
                }
            } else {
          
                if (!frames.contains(pages[i])) {
                   
                    int j = predict(pages, frames, i + 1);
                    frames.set(j, pages[i]);
                    pageFaults++;
                }
            }

           
            System.out.println("Frames: " + frames);
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter number of Frames: ");
        int frameCount = sc.nextInt();
    	System.out.println("Enter number of pages");
    	int N = sc.nextInt();
        int[] pages = new int[N];
        for(int i = 0; i < N; i++) {
        	System.out.println("Enter the page"+ i);
        	pages[i] = sc.nextInt();
        }
        optimalPageReplacement(pages, frameCount);
        sc.close();
    }
}

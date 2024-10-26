package page_replacement;
import java.util.*;

public class LRU{

    
    static void lruPageReplacement(int[] pages, int capacity) {
        LinkedHashSet<Integer> cache = new LinkedHashSet<>(capacity);
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!cache.contains(page)) {
                
                if (cache.size() == capacity) {
                    int firstPage = cache.iterator().next();
                    cache.remove(firstPage);
                }
                
                cache.add(page);
                pageFaults++;
            } else {
                
                cache.remove(page);
                cache.add(page);
            }

            
            System.out.println(cache);
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
        lruPageReplacement(pages, frameCount);
        sc.close();
    }
}

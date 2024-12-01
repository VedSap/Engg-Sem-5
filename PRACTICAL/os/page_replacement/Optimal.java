package page_replacement;
import java.util.Arrays;
import java.util.Scanner;

public class Optimal{
	  // Function to find the next use of a page
    public static int findNextUse(int page, int[] pages, int start) {
        for (int i = start; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return Integer.MAX_VALUE; // Return a large number if the page is not used again
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the number of pages and frames
        System.out.print("Enter number of pages: ");
        int p = scanner.nextInt();
        System.out.print("Enter number of frames: ");
        int f = scanner.nextInt();

        int[] pages = new int[p];   // array to store pages
        int[] frames = new int[f];  // array to store frames

        // Initialize frames
        Arrays.fill(frames, -1);   // -1 indicates an empty frame

        int pagefault = 0;   // track page faults
        int pagehit = 0;     // track page hits
        int pointer = 0;     // pointer for frame insertion

        // Input pages
        System.out.println("Enter the pages:");
        for (int i = 0; i < p; i++) {
            pages[i] = scanner.nextInt();
        }

        // Process each page
        for (int i = 0; i < p; i++) {
            int page = pages[i];
            boolean hit = false;

            // Check if the page is already in frames (Page Hit)
            for (int j = 0; j < f; j++) {
                if (frames[j] == page) {
                    pagehit++;
                    hit = true;
                    break;
                }
            }

            // If it's a miss (Page Fault)
            if (!hit) {
                pagefault++;

                // If there is an empty frame, insert the page there
                if (pointer < f) {
                    frames[pointer] = page;
                    pointer++;
                } else {
                    // Find the frame to replace based on future use
                    int farthest = -1;
                    int replaceIndex = -1;

                    for (int j = 0; j < f; j++) {
                        int nextUse = findNextUse(frames[j], pages, i + 1);
                        
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            replaceIndex = j;
                        }
                    }

                    // Replace the frame at replaceIndex with the new page
                    frames[replaceIndex] = page;
                }
            }

            // Print the current state of frames
            System.out.print("Frames: ");
            for (int j = 0; j < f; j++) {
                System.out.print(frames[j] + " ");
            }
            System.out.println();
        }

        System.out.println("Total Page Hits: " + pagehit);
        System.out.println("Total Page Faults: " + pagefault);

        scanner.close();
    }

 
}

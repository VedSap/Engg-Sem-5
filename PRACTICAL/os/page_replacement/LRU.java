package page_replacement;
import java.util.Scanner;

public class LRU{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int frames = scanner.nextInt();
        System.out.print("Enter the number of page references: ");
        int pages = scanner.nextInt();
        int[] pageReferences = new int[pages];
        System.out.println("Enter the page reference sequence:");
        for (int i = 0; i < pages; i++) {
            pageReferences[i] = scanner.nextInt();
        }
        int[] pageFrame = new int[frames];
        int[] recentUse = new int[frames];
        int pageFaults = 0;
        for (int i = 0; i < frames; i++) {
            pageFrame[i] = -1;
            recentUse[i] = 0;
        }
        for (int i = 0; i < pages; i++) {
            int page = pageReferences[i];
            boolean pageHit = false;
            for (int j = 0; j < frames; j++) {
                if (pageFrame[j] == page) {
                    pageHit = true;
                    recentUse[j] = i;
                    break;
                }
            }

            if (!pageHit) {
                pageFaults++;

                int lruIndex = 0;
                for (int j = 1; j < frames; j++) {
                    if (pageFrame[j] == -1) {
                        lruIndex = j;
                        break;
                    }
                    if (recentUse[j] < recentUse[lruIndex]) {
                        lruIndex = j;
                    }
                }

                pageFrame[lruIndex] = page;
                recentUse[lruIndex] = i;  
            }
            System.out.print("Page " + page + ": ");
            for (int j = 0; j < frames; j++) {
                if (pageFrame[j] != -1) {
                    System.out.print(pageFrame[j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        System.out.println("Total page faults: " + pageFaults);
        scanner.close();
    }
}

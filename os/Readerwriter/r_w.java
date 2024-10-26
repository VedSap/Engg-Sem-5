package Readerwriter;
import java.util.concurrent.Semaphore;

class r_w {
    static Semaphore Mutex = new Semaphore(1);
    static Semaphore write = new Semaphore(1);
    static int readCount = 0;

    static class SharedResource {
        private String data = "Hello";

        public void readData(int readerId) {
            System.out.println("Reader " + readerId + " reads: " + data);
        }

        public void writeData(int writerId, String newData) {
            data = newData;
            System.out.println("Writer " + writerId + " writes: " + data);
            System.out.println("Writer "+ writerId +" has exited");
        }
    }
    static class Reader implements Runnable {
        private int readerId;
        private SharedResource resource;

        public Reader(int readerId, SharedResource resource) {
            this.readerId = readerId;
            this.resource = resource;
        }
        public void run() {
            try {
            	Mutex.acquire();
                readCount++;
                if (readCount == 1) {
                    write.acquire();
                }
                Mutex.release();
                resource.readData(readerId);
                Mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    write.release();
                    System.out.println("readers have exited");
                }
                Mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Writer implements Runnable {
        private int writerId;
        private SharedResource resource;
        private String newData;

        public Writer(int writerId, SharedResource resource, String newData) {
            this.writerId = writerId;
            this.resource = resource;
            this.newData = newData;
        }
        public void run() {
            try {
                write.acquire();
                resource.writeData(writerId, newData);
                write.release();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        System.out.println("the shared resource is:"+ resource.data);
        Thread reader1 = new Thread(new Reader(1, resource));
        Thread reader2 = new Thread(new Reader(2, resource));
        Thread reader3 = new Thread(new Reader(3, resource));
        Thread writer1 = new Thread(new Writer(1, resource, "hi"));
        Thread writer2 = new Thread(new Writer(2, resource, "wadakam"));
        Thread writer3 = new Thread(new Writer(3, resource, "namaskar"));
        reader1.start();
        reader2.start();
        reader3.start();
        writer1.start();
        writer2.start();
        writer3.start();
    }
}




public class AppendOrHashThread implements Runnable{

    private String ID;
    private AppendOrHashThread nextThread;
    private static Object myLock = new Object();
    static int threadAllowedToRun = 1;
    static int numThread = 1;
    int myThreadID;




    public AppendOrHashThread(String id) {

        this.ID = id;
        this.nextThread = nextThread;
        this.myThreadID = numThread++;

  }



    @Override
    public void run() {
        synchronized (myLock) {
            while (myThreadID != threadAllowedToRun) {
                try {










                    myLock.wait();
                } catch (InterruptedException e) {

                } catch (Exception e) {}
            }
            try {
                Thread.sleep(2000);
                int iterations = 0;
                while(Data.numOfIterations != iterations){

                    if(Data.currentIterations == 3){
                        Data.currentIterations = 0;
                    }


                    if(Data.currentIterations == 0){
                        Data.word = Data.word + this.ID;

                        System.out.println(Data.word);
                    }

                    if(Data.currentIterations == 1){
                        Data.word = Data.word + this.ID;

                        System.out.println(Data.word);
                    }

                    if(Data.currentIterations== 2){
                        Data.word = Hasher.hash(Data.word);
                        System.out.println(Data.word);
                    }
                    iterations++;
                    Data.currentIterations++;
                }
                System.out.println(iterations);
            } catch (InterruptedException e) {
            }






            System.out.println("myThreadID is running: " + myThreadID);
            myLock.notifyAll();
            threadAllowedToRun++;
        }

    }
    }

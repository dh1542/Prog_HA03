public class AppendOrHashThread extends Thread {
    public static Object lock = new Object();

    public String id;
    public boolean hashThread;
    public AppendOrHashThread nextThread;
    public static int numberOfThreads = 0;
    public boolean working;


    public AppendOrHashThread(String id) {
        this.id = id;
        numberOfThreads += 1;
        if (numberOfThreads == 3){
            hashThread = true;
        }
        else {
            hashThread = false;
        }

        if (numberOfThreads == 1){
            working = true;
        }
        else {
            working = false;
        }

    }


    @Override

    public void run() {
        synchronized (this) {
            while (Data.currentIterations <= Data.numOfIterations) {


                    if (this.working){
                        if (hashThread){
                            Data.word = Hasher.hash(Data.word);
                            Data.currentIterations++;
                            if (Data.currentIterations == Data.numOfIterations){
                                System.out.println(Data.word);
                                this.nextThread.working = true;
                                working = false;

                                break;
                            }
                        }
                        else {
                            Data.word += id;
                        }


                        working = false;
                        this.nextThread.working = true;


                    }

                    if (this.nextThread.isAlive()) { // wir prüfen, ob der andere Thread bereits gestartet ist
                        this.nextThread.interrupt(); // wenn ja, wecken wir ihn
                    } else {
                        this.nextThread.start(); // wenn nicht, starten wir ihn
                    }



                    if (Data.currentIterations == Data.numOfIterations){
                        break;
                    }

                }










            }
        }

}

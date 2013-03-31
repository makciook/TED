package codilime.ted.example

import java.util.List;

class CountToN implements Runnable {
    private static final int WAIT_TIME = 2000; // in milis
    private static final int N = 20;

    @Override
    public void run() {
        for(int i = 0; i < N; ++i) {
            wait(WAIT_TIME);
            System.out.println(i);
        }
    }

    private void wait(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch(InterruptedException e) {
            throw new RuntimeException("Thread interrupted.",e);
        }
    }
}

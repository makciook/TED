package codilime.ted.example

class Itemize implements Runnable {
    private static int WAIT_TIME = 2000; // in milis
    private static List<String> LANGUAGES = Arrays.asList("CLOJURE", "Groovy", "Java", "JRuby", "Scala");

    @Override
    public void run() {
        for(String language : LANGUAGES) {
            wait(WAIT_TIME);
            System.out.println(String.format("JVM language: %s", language));
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

package codilime.ted.example

/**
 * Class provided for testing computationally demanding threaded actions
 * fib(40) takes from 1 to 2 minutes.
 */

class Fib {
    def old=1,fib=1,current=1

    def fib(n) { return (n<2 ? 1 : fib(n-1)+fib(n-2)) }

    def next() {
        def newFib=fib+old
        old=fib
        fib=newFib
        current++
    }

    def fib2(n) {
        while(current < n) next()
        return fib
    }

    @Override
    public void run() {
        System.out.println(fib(40));
    }
}
package client;

import compute.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Primes implements Task<List<Integer>>, Serializable {
    private int min;
    private int max;

    public Primes(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public List<Integer> execute() {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i/2; j++) {

                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primes.add(i);
            }
        }

        return primes;
    }
}

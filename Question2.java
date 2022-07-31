class SubsetFinder {

    int realArrLen;
    int[] a;

    SubsetFinder(int realArrLen, int[] a) {

        this.realArrLen = realArrLen;
        // this.a = a;
        int len = 0;
        int[] passer = new int[a.length];

        // First unique elements are separated
        // where repeated numbers are removed

        // Comparing every element to every other element
        // if they are same or not,and push if there's no same
        // element remaining

        for (int i = 0; i < a.length; i++) {
            boolean push = false;
            for (int j = 0; j < a.length; j++) {
                if (i >= j) {
                    // Since last remaining element will always be
                    // left to be unique, so, elements gets pushed
                    if (i == a.length - 1) {
                        push = true;
                        break;
                    }
                    continue;
                }

                if (a[i] == a[j]) {
                    push = false;
                    break;
                } else if (a[i] != a[j]) {
                    push = true;
                }
            }
            if (push) {
                passer[len] = a[i];
                push = false;
                len++;
            }
        }

        // creating array with length of unique elements
        // counted from above

        int[] originalPasser = new int[len];

        // passing elements to the newly made array

        for (int i = 0; i < len; i++) {
            originalPasser[i] = passer[i];
            // System.out.println(passer[i]);
        }

        // substituting to the global variable

        this.a = originalPasser;

    }

    void resultFinder() {

        // Compilation of whole required functions or steps

        // finding prime numbers and 1 from the unique array
        int[][] outputValues = findPrime(a);
        // 0 index contains primenumers including 1 if exists
        int[] primeValues = outputValues[0];
        // index 0 of 1 contains the number of unique elements found
        int primeItterator = outputValues[1][0];
        // System.out.println(primeItterator);
        // Checking if the required number is already satisfied
        // or not, if yes, print the whole subset elements
        if (primeItterator == realArrLen) {

            for (int i = 0; i < primeItterator; i++) {
                System.out.println(primeValues[i]);
            }
        } else {

            // search for any extra element that would suffice the unique
            // subset

            int[] extra = extrasSearch(primeValues, primeItterator);
            // System.out.println(extra[0]);

            // printing out the subsets
            for (int i = 0; i < realArrLen; i++) {
                if (i >= primeItterator) {
                    int num = i - primeItterator;
                    System.out.println(extra[num]);
                } else {
                    System.out.println(primeValues[i]);
                }

            }
        }

    }

    int[] extrasSearch(int[] primes, int primeItterator) {

        int[] extras = new int[realArrLen];
        int extrasin = 0;
        boolean skip = false;
        for (int i = 0; i < a.length; i++) {
            for (int ext = 0; ext < primes.length; ext++) {
                if (a[i] == primes[ext]) {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                skip = false;
                continue;
            }

            boolean push = true;

            for (int j = 0; j < primeItterator; j++) {

                if (primes[j] == 1) {
                    continue;
                } else {
                    // check if the division is perfect or not, if no, push in the subset array
                    // System.out.println(a[i]);
                    float checkval = (float) a[i] / (float) primes[j];
                    if (checkval == Math.floorDiv(a[i], primes[j])) {
                        push = false;
                    }
                }
            }
            // when push is true, extra element is pushed
            // into the array of subsets
            if (push) {
                extras[extrasin] = a[i];
                extrasin++;
            }

        }

        return extras;

    }

    int[][] findPrime(int[] arr) {
        // Findout Prime numbers among element of given array arr

        int[] ourPrimeElements = new int[realArrLen];
        int primeItterator = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {
                continue;
            } else if (arr[i] == 1) {
                ourPrimeElements[primeItterator] = 1;
                primeItterator++;

            } else if (arr[i] == 2) {
                ourPrimeElements[primeItterator] = 2;
                primeItterator++;
            } else if (arr[i] == 3) {
                ourPrimeElements[primeItterator] = 3;
                primeItterator++;
            }

            else {

                boolean prime = true;
                // dividing each number by 2, by which
                // process of finding prime becomes more efficient
                // int element = Math.floorDiv(arr[i], 2);
                int divideLimit = Math.floorDiv(arr[i], 2);
                for (int j = 2; j <= divideLimit; j++) {

                    float check = (float) a[i] / j;
                    // System.out.println(a[i]);
                    // System.out.println(j);
                    // System.out.println(check);
                    // If division is perfect,with no modulus
                    // left, element is not prime
                    if (check == Math.floorDiv(a[i], j)) {

                        prime = false;

                    }
                }

                if (prime == true) {

                    ourPrimeElements[primeItterator] = a[i];
                    primeItterator++;
                }

            }

        }

        int[][] returner = { ourPrimeElements, { primeItterator } };

        return returner;

    }

    // int[] filter(int[] primes) {
    // // Filter among elements of arr and search for elements
    // // with whole-number division from more than one number

    // int wholeNumSuccess = 0;

    // for(int i=0; i<a.length;i++){
    // for(int j=0; )
    // }

    // }

    public static void main(String[] args) {

        // Providing the input

        int[] input = { 10, 10, 5, 0, 2, 1, 2, 5 };

        SubsetFinder sub = new SubsetFinder(3, input);
        sub.resultFinder();
    }

}

class PathFinder {

    String[] input;
    int rows;
    int columns;

    PathFinder(String[] input) {
        // Setting up global variables
        this.input = input;
        this.rows = input.length;
        this.columns = input[0].length();
    }

    int processor() {

        int keys = keysNumberFinder();// total number of keys

        int totalTravelled = 0;
        int keysFound = 0;
        boolean run = true;
        int x = 0;
        int travelledRow = 0;
        int adder = 1;// adder is the variable that determines direction of path, 1 or -1
        char[] foundK = new char[keys];// it forms key storing
        // array with size of keys

        while (run) {
            // checks in the left-side, if the space is not a wall
            if (input[travelledRow].charAt(x + adder) != '#') {
                // if founds open space, goes forward
                if (input[travelledRow].charAt(x + adder) == '*') {
                    x += adder;// travels forward, if it's just a open space
                    totalTravelled++;// counting total travel
                    continue;
                } else {
                    // checks if, the is letter is small,if yes key found
                    if (input[travelledRow].charAt(x + adder) != input[travelledRow].toUpperCase().charAt(x + adder)) {
                        totalTravelled++;
                        foundK[keysFound] = input[travelledRow].charAt(x + adder);
                        x += adder;
                        keysFound++;
                        // checks if all keys are found or not
                        if (keysFound == keys) {
                            run = false;
                            break;
                        } else {
                            continue;
                        }

                    } else {

                        // this, step is to see,when
                        // we travel through a lock; do we
                        // have the corresponding key to travel or not
                        boolean yes = false;

                        for (int l = 0; l < foundK.length; l++) {
                            if (foundK[l] == input[travelledRow].toLowerCase().charAt(x + adder)) {

                                yes = true;
                            }
                        }

                        if (yes) {// if yes, we travel forward
                            // keysFound++;
                            totalTravelled++;
                            x += adder;
                            continue;
                        } else {// if not, we change the direction altogether with -1 adder
                            adder *= -1;
                            continue;
                        }

                    }
                }

            }

            // The process goes same, but it's for up and down movement
            if (input[travelledRow + 1].charAt(x) != '#') {

                if (input[travelledRow + 1].charAt(x) == '*') {

                    travelledRow += 1;
                    totalTravelled++;
                    continue;
                } else {
                    if (input[travelledRow + 1].charAt(x) != input[travelledRow + 1].toUpperCase().charAt(x)) {
                        totalTravelled++;
                        foundK[keysFound] = input[travelledRow + 1].charAt(x);
                        travelledRow += 1;
                        keysFound++;

                        if (keysFound == keys) {
                            run = false;
                            break;
                        } else {
                            continue;
                        }

                    } else {

                        boolean yes = false;

                        for (int l = 0; l < foundK.length; l++) {
                            if (foundK[l] == input[travelledRow + 1].toLowerCase().charAt(x)) {

                                yes = true;
                            }
                        }

                        if (yes) {
                            // keysFound++;
                            totalTravelled++;
                            travelledRow++;
                            continue;
                        }

                    }
                }

            }
        }

        return totalTravelled;// returning total travelled blocks
    }

    // Function returns number of keys available in the whole
    // path
    int keysNumberFinder() {

        int alphabets = 0;

        // lopping through the input, and checking if the
        // input letters are alphabets by filtering out the
        // different given types of contraints as wall,space etc
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (input[i].charAt(j) == '@' || input[i].charAt(j) == '#'
                        || input[i].charAt(j) == '*') {
                    continue;
                } else {
                    alphabets++;
                }
            }
        }

        int keys = alphabets / 2;// divided by two, cause
        // the number is addition of keys and locks,
        // since locks and keys are of same number
        // for keys, divided whole number by 2

        return keys;
    }

    public static void main(String[] args) {

        // setting up the path
        String[] value = { "@*a*#", "###*#", "b*A*B" };

        PathFinder pth = new PathFinder(value);
        System.out.println(pth.processor());// using the processor function to get
        // required answer
    }

}

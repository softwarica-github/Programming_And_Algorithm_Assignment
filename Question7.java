class SaveTheDeer {

    int[][] inputPathAlignment;
    int rows;
    int columns;

    SaveTheDeer(int[][] inputPathAlignment) {

        // Setting the global variables
        this.inputPathAlignment = inputPathAlignment;
        this.rows = inputPathAlignment.length;
        this.columns = inputPathAlignment[0].length;
    }

    int processor() {

        boolean done = false;
        int notFound = 0;
        int x = 0;
        int rowsTravelled = 0;
        int times = 0;

        // Used two loop to use of break, and re-initiate the
        // internal loop with different sets of parameters
        while (!done) {

            while (!done) {

                // just checking for columns limit, for
                // preventing from the situation of getting
                // out of bounds
                if (x < columns - 1) {

                    // checking for space in the column travel as left travel

                    if (inputPathAlignment[rowsTravelled][x + 1] == 0) {

                        if (x == 0 && rowsTravelled == 0) {

                            x++;
                            continue;
                        }

                        // after travelling, change the state of path itself
                        // to one so that for next whole process,
                        // the program would know, as where not to go
                        inputPathAlignment[rowsTravelled][x] = 1;
                        x++;

                        if (x == (inputPathAlignment[0].length - 1) && rowsTravelled == rows - 1) {
                            // x++;
                            // having a checkpoint if the process reaches to
                            // most left bottom side
                            times++;
                            // checking for completed condition
                            if (times == 2 || (times + notFound == 2)) {
                                done = true;
                                break;
                            }
                            break;
                        }

                        continue;

                    }

                }
                // else {

                // notFound++;
                // }

                if (inputPathAlignment[rowsTravelled + 1][x] == 0) {

                    if (x == 0 && rowsTravelled == 0) {

                        rowsTravelled++;
                        continue;
                    }

                    inputPathAlignment[rowsTravelled][x] = 1;
                    rowsTravelled++;

                    if (x == (inputPathAlignment[0].length - 1) && rowsTravelled == rows - 1) {
                        // rowsTravelled++;
                        times++;
                        // similar processes as above,and checking for completed condition
                        if (times == 2 || (times + notFound == 2)) {
                            done = true;
                            break;
                        }

                        // reseting all the parameters once single
                        // process is completed, and let the starting
                        // point be the initial stage
                        x = 0;
                        rowsTravelled = 0;
                        break;
                    }

                    continue;

                }

                // Checking for completed condition
                notFound++;
                if (times + notFound == 2) {
                    done = true;
                }

            }

        }
        return times;
    }

    public static void main(String[] args) {

        // providing input for the path, that is supposed to be travelled
        int[][] val = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

        // Running the processor function to give the final output
        System.out.println(new SaveTheDeer(val).processor());

    }

}

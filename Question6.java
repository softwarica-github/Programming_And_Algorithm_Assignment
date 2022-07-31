import java.util.HashMap;

class RandomUniqueEqualizer {

    // array of words
    String[] leftSide;
    // Single righ side word
    String rightSide;
    // Hashmap to store numerical values of different letters
    HashMap<String, String> mapper = new HashMap<String, String>();

    RandomUniqueEqualizer(String[] leftSide, String rightSide) {

        // assigning to the global variable
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    boolean processor() {

        // converting array of words into a single string
        String checkval1 = String.join("", leftSide);
        String checkvalFinal = checkval1 + rightSide;
        // System.out.println(checkval);
        // passing parameters into function to get unique letters and have them
        // assigned different unique numbers
        String uniqueCheck = uniqueFinder(checkvalFinal, checkval1.charAt(checkval1.length() - 1));

        // Function that returns sum of left side words
        int leftSum = leftItterSum();

        String rightSum = "";

        // Finding right-side sum
        for (int i = 0; i < rightSide.length(); i++) {
            rightSum += mapper.get("" + rightSide.charAt(i));
        }

        // System.out.println(rightSum);
        // System.out.println(leftSum);

        // Check if both are same or not
        if (leftSum == Integer.parseInt(rightSum)) {
            return true;
        }

        return false;

    }

    int leftItterSum() {

        // int lastCheckIter = 0;
        int unitsItter = 0;
        String[] units = new String[leftSide.length];
        int leftTotal = 0;

        // if(leftSide[lastCheckIter].charAt(i) ==
        // leftSide[lastCheckIter].charAt(leftSide[lastCheckIter].length())){
        // unitsItter++;
        // }
        // itterate over left-side words letters and findout sum of all
        for (int i = 0; i < leftSide.length; i++) {

            units[unitsItter] = "";

            for (int j = 0; j < leftSide[i].length(); j++) {

                // System.out.println(mapper.get("H"));
                units[unitsItter] += mapper.get("" + leftSide[i].charAt(j));

            }
            leftTotal += Integer.parseInt(units[unitsItter]);
            unitsItter++;

        }

        // System.out.println(units[0]);
        // System.out.println(units[1]);
        // System.out.println(units[2]);

        // System.out.println(leftTotal);

        return leftTotal;// returning lef-side sum
    }

    String uniqueFinder(String a, char leftend) {

        int len = 0;
        char[] passer = new char[a.length()];

        // Here, the letters are itterated over other letters and if found
        // same, the loop is skipped, if not found, it is considered unique

        for (int i = 0; i < a.length(); i++) {
            boolean push = false;
            for (int j = 0; j < a.length(); j++) {
                if (i >= j) {
                    // By following this process,
                    // at last, the last letter is always unique
                    if (i == a.length() - 1) {
                        push = true;
                        break;
                    }
                    continue;
                }

                if (a.charAt(i) == a.charAt(j)) {
                    push = false;
                    break;
                } else if (a.charAt(i) != a.charAt(j)) {
                    push = true;
                }
            }
            if (push) {
                passer[len] = a.charAt(i);
                push = false;
                len++;
            }
        }

        String combined = "";
        // int itter = 0;

        // String[] leftRightSum = new String[2];

        // passing elements in its tightly fitting
        // array, where size is same as number of elements
        for (int i = 0; i < len; i++) {
            combined += passer[i];
            mapper.put("" + passer[i], "" + i);
            // if (passer[i] == leftend) {
            // itter++;
            // }
            // leftRightSum[itter] += mapper.get("" + passer[i]);

        }

        // System.out.println(combined);

        return combined;
        // return leftRightSum;

    }

    public static void main(String[] args) {

        // Giving the left-side words
        String[] leftInput = { "HE", "HA", "NO" };

        // calling processor function to compare leftsum and rightsum
        System.out.println(new RandomUniqueEqualizer(leftInput, "JR").processor());
    }

}

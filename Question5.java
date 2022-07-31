
class WordSearcher {

    String[] strg;
    String target;

    WordSearcher(String[] str, String target) {
        // Substituting to the global variables
        this.strg = str;
        this.target = target;
    }

    // Function to scan letters in the array
    int scanner() {

        int travelled = 0;

        int foundSubstring = 0;
        boolean allDone = false;

        // Altogether, there are three loops, first in the target
        // word, then in the array, in each element and last
        // lopping through each letter of each element

        for (int i = 0; i < target.length(); i++) {

            boolean found = false;

            if (allDone) {
                break;
            }

            for (int j = 0; j < strg.length; j++) {

                // if any letter is found, found turns into
                // true and select next target letter for
                // linear search

                if (found) {
                    found = false;
                    break;
                } else {
                    // Current array from aray of words
                    String current = strg[j];
                    // String aim = (String) target[i];

                    for (int k = 0; k < current.length(); k++) {
                        // Comparing each letter in elements in array
                        // to each element in target letters
                        if (current.charAt(k) == target.charAt(i)) {
                            found = true;
                            foundSubstring++;// number of found letters
                            if (foundSubstring == target.length()) {
                                allDone = true;
                            }
                            break;

                        }

                    }
                    if (travelled < j + 1) {

                        travelled++;// counting number of words in
                        // array, does the search had to go
                    }
                }

            }

        }

        return travelled;
    }

    public static void main(String[] args) {

        // Input, array of words
        String[] str = { "Programming", "for", "developers" };

        // calling the class, and setting up default behaviour
        WordSearcher wdSearch = new WordSearcher(str, "frog");
        System.out.println(wdSearch.scanner());

    }

}
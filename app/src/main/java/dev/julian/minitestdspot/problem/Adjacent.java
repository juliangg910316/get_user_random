package dev.julian.minitestdspot.problem;

public class Adjacent {

    public static int countLetterAdjacent(String input){
        int deletions = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.substring(i, i+1).equals(input.substring(i+1, i+2)))
                deletions++;
        }
        return deletions;
    }
}

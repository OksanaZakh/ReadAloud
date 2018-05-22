package com.example.administrator.readaloud.api.ReadHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsCounter {
    private List<String> readingText;
    private List<String> speechText;
    public static final String REGEX = "([\\s]+)|(\\.+)|(\\,+)|(\\-+)|(\\:+)|(\\;+)|(\\!+)|(\\?+)";

    public WordsCounter(String readingText, String speechText) {
        this.readingText = new ArrayList<>(Arrays.asList(readingText.split(REGEX)));
        this.speechText = new ArrayList<>(Arrays.asList(speechText.split(REGEX)));
    }

    public int getResult() {
        int speechTextSize = speechText.size();
        int num = 5;
        String one, two, three;
        if (speechTextSize > 7) {
            for (int i = 0; i <= num; i++) {
                one = speechText.get(speechTextSize - 1 - i);
                two = speechText.get(speechTextSize - 2 - i);
                three = speechText.get(speechTextSize - 3 - i);
                for (int k = 2; k < readingText.size(); k++) {
                    if (one.equalsIgnoreCase(readingText.get(k)) && two.equalsIgnoreCase(readingText.get(k - 1))
                            && three.equalsIgnoreCase(readingText.get(k - 2))) {
                        return k+1;
                    }
                }
            }
        } else {
            return speechTextSize;
        }
        return 0;
    }
}

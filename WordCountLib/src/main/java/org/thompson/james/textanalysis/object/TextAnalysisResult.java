package org.thompson.james.textanalysis.object;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;
import org.thompson.james.string.IFormattedString;

public class TextAnalysisResult implements Serializable, IFormattedString {
    private transient String sentence;
    private transient String[] splitSentence;
    
    private Integer wordAmount;
    private Double averageWordLength;
    private Map<Integer, Integer> numberOfWordsOfLength;
    private Map<Integer, Integer> highestOccurringWordLength;

    public TextAnalysisResult(String sentence) {
        this.sentence = sentence;
    }

    public TextAnalysisResult(String sentence, String[] splitSentence,
                              Integer wordAmount, Double averageWordLength,
                              Map<Integer, Integer> numberOfWordsOfLength,
                              Map<Integer, Integer> highestOccurringWordLength) {
        this.sentence = sentence;
        this.splitSentence = splitSentence;
        this.wordAmount = wordAmount;
        this.averageWordLength = averageWordLength;
        this.numberOfWordsOfLength = numberOfWordsOfLength;
        this.highestOccurringWordLength = highestOccurringWordLength;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String[] getSplitSentence() {
        return splitSentence;
    }

    public void setSplitSentence(String[] splitSentence) {
        this.splitSentence = splitSentence;
    }

    public Integer getWordAmount() {
        return wordAmount;
    }

    public void setWordCount(Integer wordAmount) {
        this.wordAmount = wordAmount;
    }

    public Double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(Double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public Map<Integer, Integer> getNumberOfWordsOfLength() {
        return numberOfWordsOfLength;
    }

    public void setNumberOfWordsOfLength(Map<Integer, Integer> numberOfWordsOfLength) {
        this.numberOfWordsOfLength = numberOfWordsOfLength;
    }

    public Map<Integer, Integer> getHighestOccurringWordLength() {
        return highestOccurringWordLength;
    }

    public void setHighestOccurringWordLength(Map<Integer, Integer> highestOccurringWordLength) {
        this.highestOccurringWordLength = highestOccurringWordLength;
    }
    
    @Override
    public String toFormattedString() {
        StringBuilder returnString = new StringBuilder();
        
        returnString.append(String.format("Word count = %s \n", this.getWordAmount()));
        returnString.append(String.format("Average Word Length = %s \n", this.getAverageWordLength()));
        this.getNumberOfWordsOfLength().forEach((key, value) -> {
            returnString.append(String.format("Number of words of length %s is %s \n", key, value));
        });
        returnString.append(
                String.format("The most frequently occurring word length is %s, for word lengths of %s \n", 
                this.getHighestOccurringWordLength().values().toArray()[0],
                this.getHighestOccurringWordLength().keySet().stream().map(String::valueOf).collect(Collectors.joining(", "))));
        
        return returnString.toString();
    }
}

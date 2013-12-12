package com.silanis.esl.sdk;

import static java.lang.String.format;

public class Challenge {

    private final String question;
    private final String answer;

    public Challenge(String question, String answer) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("question parameter cannot be null or empty");
        }

//        if (answer == null || answer.trim().isEmpty()) {
//            throw new IllegalArgumentException("answer parameter cannot be null or empty");
//        }

        this.question = question;
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = question.hashCode();

        hash = hash * 31 + answer.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Challenge)) {
            return false;
        }

        Challenge challenge = (Challenge)other;

        return challenge.question.equals(question) && challenge.answer.equals(answer);
    }

    @Override
    public String toString() {
        return format("%s, %s", question, answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
package day2;

import java.security.InvalidParameterException;

public enum AnswerKey {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    private String elfAnswerKey;

    private String playerAnswerKey;

    private int point;

    private AnswerKey(String elfAnswerKey,
            String playerAnswerKey, int point) {
        this.elfAnswerKey = elfAnswerKey;
        this.playerAnswerKey = playerAnswerKey;
        this.point = point;
    }

    public int getPoint() {
        return this.point;
    }

    public int competeWith(AnswerKey answerKey) {
        if (this.equals(answerKey)) {
            return 3;
        }
        switch (this) {
            case ROCK -> {
                if (SCISSORS.equals(answerKey)) {
                    return 6;
                }
            }
            case PAPER -> {
                if (ROCK.equals(answerKey)) {
                    return 6;
                }
            }
            case SCISSORS -> {
                if (PAPER.equals(answerKey)) {
                    return 6;
                }
            }
        }

        return 0;
    }

    public static AnswerKey getAnswerKeyFromChar(String charOption) {
        AnswerKey[] answerKeys = AnswerKey.values();
        for (int i = 0; i < answerKeys.length; i++) {
            AnswerKey answerKey = answerKeys[i];
            if (charOption.equals(answerKey.elfAnswerKey) || answerKey.playerAnswerKey.equals(charOption)) {
                return answerKey;
            }
        }
        System.out.println(charOption);
        throw new InvalidParameterException("No Answer Key is found");
    }
}
package com.project.legendsofleague.db.player.domain;

public enum Position {
    TOP("Top"),
    JUG("Jungle"),
    MID("Mid"),
    ADC("Bot"),
    SUP("Support"),
    COACH("Coach");
    private final String value;

    Position(String value) {
        this.value = value;
    }

    public static Position fromString(String text) {
        for (Position position : Position.values()) {
            if (position.value.equalsIgnoreCase(text)) {
                return position;
            }
        }
        return null;
    }
}

package edu.ada.ClassBoard.model;

public enum SubjectName {
    MATH("Mathematics"),
    SCIENCE("Science"),
    PHYSICS("Physics"),
    CHEMISTRY("Chemistry"),
    HISTORY("History"),
    GEOGRAPHY("Geography"),
    ENGLISH("English"),
    COMPUTER_SCIENCE("Computer Science");

    private final String displayName;

    SubjectName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


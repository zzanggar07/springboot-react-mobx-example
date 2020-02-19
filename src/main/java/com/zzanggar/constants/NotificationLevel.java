package com.zzanggar.constants;

public enum NotificationLevel {
    INFO(Values.INFO),
    WARNING(Values.WARNING),
    DANGER(Values.DANGER);

    private String type;

    NotificationLevel(String type) {
            this.type = type;
    }

    public String getType() {
            return type;
    }

    public static class Values {
        public static final String INFO    = "INFO";
        public static final String WARNING = "WARNING";
        public static final String DANGER  = "DANGER";
    }
}

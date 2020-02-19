package com.zzanggar.constants;

public enum NotificationType {
    REGISTER(Values.REGISTER),
    REGISTERED(Values.REGISTERED),
    ENCODING_PROGRESS(Values.ENCODING_PROGRESS);

    private String type;

    NotificationType(String type) {
            this.type = type;
    }

    public String getType() {
            return type;
    }

    public static class Values {
        public static final String REGISTER          = "REGISTER";
        public static final String REGISTERED        = "REGISTERED";
        public static final String ENCODING_PROGRESS = "ENCODING_PROGRESS";
    }
}

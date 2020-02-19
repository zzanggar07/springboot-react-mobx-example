package com.zzanggar.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
    public static String getBaseURL(HttpServletRequest request, String hostAddress) {
        return String.format("%s://%s%s", request.getScheme(), hostAddress, request.getContextPath());
    }
}

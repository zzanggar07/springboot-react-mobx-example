package com.zzanggar.constants;

import java.util.HashMap;
import java.util.Map;

public enum ServerError {
    // User
    ALREADY_REGISTERED_USER        (1001, "이미 등록된 사용자"),
    NOT_EXIST_USER                 (1002, "존재하지 않는 사용자"),
    NOT_EQUALS_PASSWORD            (1003, "패스워드가 일치하지 않음"),

    // Folder
    NOT_EXIST_FOLDER               (2001, "존재하지 않는 폴더"),

    // Meta
    NOT_EXIST_META                 (3001, "존재하지 않는 메타"),

    // Role
    NOT_EXIST_ROLE                 (4001, "존재하지 않는 권한"),

    // File
    FILE_SAVE_STORAGE              (5001, "파일 저장에러"),
    FILE_LOAD                      (5002, "파일 로드 에러"),
    NOT_EXIST_RESOURCE             (5003, "존재하지 않는 리소스"),
    RESIZE                         (5004, "리사이즈 에러"),

    // Password
    NOT_EXIST_PASSWORD_RESET_TOKEN (6001, "존재하지 않는 패스워드 리셋 토큰"),
    EXPIRED_PASSWORD_RESET_TOKEN   (6002, "패스워드 리셋 토큰 만료"),

    // Mail
    FAILED_CONNECTING_MAIL_SERVER  (7001, "메일 서버 에러"),
    FAILED_SEND_MAIL               (7002, "메일 발송 실패"),
    FAILED_SEND_MAIL_PASSWORD_RESET(7003, "패스워드 변경 메일 발송 실패"),

    // Board
    NOT_EXIST_BOARD                (8001, "존재하지 않는 게시글"),
    NOT_EXIST_COMMENT              (8002, "존재하지 않는 댓글"),
    NOT_EXIST_TYPE                 (8003, "존재하지 게시판 타입"),
    NOT_WRITER                     (8004, "본인글이 아님"),


    // Common
    FIELD_PARAMS                   (9001, "필드 파라미터 에러"),
    HTTP_ERROR                     (9002, "HTTP 에러"),

    // STT
    STT_SERVER                     (10001, "STT 서버 에러"),

    // Product
    ALREADY_REGISTERED_COMPANY_PRODUCT_NAME (11001, "이미 등록된 업체명 & 제품명"),
    NOT_EXIST_PRODUCT              (11002, "존재하지 않는 제품"),

    // Customer
    NOT_EXIST_CUSTOMER              (12001, "존재하지 않는 고객"),
    ALREADY_REGISTERED_CUSTOMER     (12002, "이미 등록된 고객"),
    ALREADY_REGISTERED_EMAIL_CUSTOMER  (12003, "이미 존재하는 이메일"),

    // Counsel
    NOT_EXIST_COUNSEL              (13001, "존재하지 않는 상담"),
    ALREADY_REGISTERED_COUNSEL     (13002, "이미 등록된 상담"),

    // excel
    EXCEL_DOWNLOAD                 (14001, "엑셀다운로드"),

    // Analysis
    ANALYSIS_SERVER_ERROR          (15001, "분석 서버 에러");

    private static final Map<Integer, ServerError> typesByValue = new HashMap<>();

    static {
        for (ServerError type : ServerError.values()) {
            typesByValue.put(type.code, type);
        }
    }

    private final int    code;
    private final String desc;

    ServerError(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ServerError forCode(int code) {
        return typesByValue.get(code);
    }

    public static ServerError forDesc(String desc) {
        return typesByValue.get(desc);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

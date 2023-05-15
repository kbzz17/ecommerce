package jw.project.common;

public enum ErrorCode {
    DUPLICATED_EMAIL("400", "이미 존재하는 이메일입니다.");


    private final String code;
    private final String status;

    ErrorCode(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}

package jw.project.common;

public enum ErrorCode {
    DUPLICATED_EMAIL("400", "이미 존재하는 이메일입니다."),
    EMAIL_NOT_EXIST("400", "존재하지 않는 이메일입니다"),
    PASSWORD_NOT_MATCH("400", "패스워드가 일치하지 않습니다."),

    //Shop
    NOT_EXISTS_SHOP("400","해당 Id 상점이 없습니다."),
    SHOP_OWNER_NOT_MATCH("400","상점 주인 정보가 일치하지 않습니다."),
    SHOP_ALREADY_OPEN("400","이미 상점이 열려있습니다"),
    SHOP_ALREADY_CLOSED("400","이미 상점이 닫혀있습니다"),
    INVALID_INPUT("400", "잘못된 입력입니다.");


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

package jw.project.common;

public record ApiResponse<T>(
        boolean success,
        T data,
        Error error
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode code) {
        return new ApiResponse<>(false, null, new Error(code.getCode(), code.getStatus()));
    }

    public static <T> ApiResponse<T> fail(ErrorCode code, String msg) {
        return new ApiResponse<>(false, null, new Error(code.getCode(), msg));
    }

    record Error(String code, String msg) {
    }
}

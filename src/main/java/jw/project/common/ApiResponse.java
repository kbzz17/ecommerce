package jw.project.common;

public record ApiResponse<T>(
        boolean success,
        T data,
        Error error
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode code, String message) {
        return new ApiResponse<>(false, null, new Error(code, message));
    }

    record Error(ErrorCode code, String msg) {
    }
}

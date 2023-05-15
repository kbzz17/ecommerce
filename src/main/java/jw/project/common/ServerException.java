package jw.project.common;

public class ServerException extends RuntimeException {
    private final ErrorCode error;

    public ServerException(ErrorCode error) {
        super(error.getStatus());
        this.error = error;
    }

    public ErrorCode getError() {
        return error;
    }
}

package it.pkg.common.exception;

/**
 * api异常
 *
 * @author luobo.cs@raycloud.com
 * @since 2021/05/20
 */
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

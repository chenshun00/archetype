package it.pkg.front.model;

/**
 * 坏响应
 *
 * @author luobo.cs@raycloud.com
 * @since 2021/05/20
 */
public class BadResponse extends AbstractResponse {

    public BadResponse() {
        this.setSuccess(Boolean.FALSE);
    }

    public BadResponse(String error) {
        this.setCode(200);
        this.setSuccess(Boolean.FALSE);
    }

}

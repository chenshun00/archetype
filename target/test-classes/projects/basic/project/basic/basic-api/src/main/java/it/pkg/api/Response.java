package it.pkg.api;

public class Response {
    private Integer code;
    private String message;
    private Object data;

    public Response() {
    }

    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class WebResponseBuilder {
        private Integer code;
        private String message;
        private Object data;

        public WebResponseBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public WebResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public WebResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this.code, this.message, this.data);
        }

    }

    public static WebResponseBuilder builder() {
        return new WebResponseBuilder();
    }


    public static Response fail(String message) {
        return fail(200, message);
    }

    public static Response fail(Integer code) {
        return fail(code, "failure");
    }

    public static Response fail(Integer code, String message) {
        return builder().code(code).message(message).build();
    }

    public static Response success() {
        return success(100);
    }

    public static Response success(Integer code) {
        return success(code, "success");
    }

    public static Response success(Object data) {
        return success(100, data);
    }

    public static Response success(Integer code, String message) {
        return success(code, message, "");
    }

    public static Response success(Integer code, Object data) {
        return success(code, "SUCCESS", data);
    }

    public static Response success(Integer code, String message, Object object) {
        return builder().code(code).message(message).data(object).build();
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

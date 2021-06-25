package it.pkg.service.config.exception;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author luobo.cs@raycloud.com
 * @since 2021/2/3 11:08 上午
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public AbstractResponse argument(RuntimeException run) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage(run.getMessage());
        return badResponse;
    }

    @ExceptionHandler(SQLException.class)
    public AbstractResponse sql(SQLException s) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage("程序发生SQL异常");
        logger.error("[程序发生SQL异常][{}]", s.getMessage(), s);
        return badResponse;
    }

    @ExceptionHandler(ApiException.class)
    public AbstractResponse api(ApiException s) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage(s.getMessage());
        logger.error("[程序发生API异常][{}]", s.getMessage(), s);
        return badResponse;
    }

    @ExceptionHandler(TooManyResultsException.class)
    public AbstractResponse ibatis(TooManyResultsException tooManyResultsException) {
        BadResponse baseResponse = new BadResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("程序发生SQL异常");
        logger.error("[{}]", tooManyResultsException.getMessage(), tooManyResultsException);
        return baseResponse;
    }

    @ExceptionHandler(NullPointerException.class)
    public AbstractResponse npe(NullPointerException s) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage("程序发生空指针异常");
        logger.error("[程序发生空指针异常]", s);
        return badResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public AbstractResponse mx(MissingServletRequestParameterException mx) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage("请求参数不存在:" + mx.getMessage());
        return badResponse;
    }

    @ExceptionHandler(Exception.class)
    public AbstractResponse common(Exception s) {
        BadResponse badResponse = new BadResponse();
        badResponse.setCode(200);
        badResponse.setMessage(s.getMessage());
        logger.error("[程序发生通用异常][{}]", s.getMessage(), s);
        return badResponse;
    }
}

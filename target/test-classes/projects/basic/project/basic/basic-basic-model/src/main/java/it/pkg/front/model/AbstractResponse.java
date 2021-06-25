package it.pkg.front.model;

import it.pkg.yapi.api.Mock;
import it.pkg.yapi.api.Required;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/5/20 10:20 上午
 */
@Getter
@Setter
@ToString
public class AbstractResponse {

    /**
     * 响应码
     * 100成功,非100失败
     */
    @Required
    @Mock("100")
    private Integer code;

    /**
     * 消息
     */
    @Required
    @Mock("success")
    private String message;

    @Required
    @Mock("true")
    private Boolean success = Boolean.TRUE;

}

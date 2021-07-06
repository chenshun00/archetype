#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import java.io.Serializable;

/**
 * @author chenshun00@gmail.com
 * @since 2020/6/2 11:58 上午
 */
public abstract class BaseEntity implements Serializable {
    protected static final long serialVersionUID = 1083405461556625430L;
}

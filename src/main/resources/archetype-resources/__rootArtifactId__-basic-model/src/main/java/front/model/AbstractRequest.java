#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.front.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author chenshun00@gmail.com
 * @since 2021/5/20 10:20 上午
 */
@Getter
@Setter
@ToString
public class AbstractRequest {

    /**
     * 检查请求字段
     *
     * @throws IllegalAccessException 反射出现异常
     */
    public void check() throws IllegalAccessException {
        Class<?> aClass = this.getClass();
        do {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                final NotBlank notBlank = declaredField.getAnnotation(NotBlank.class);
                final NotNull notNull = declaredField.getAnnotation(NotNull.class);
                if (Objects.nonNull(notBlank) || Objects.nonNull(notNull)) {
                    declaredField.setAccessible(true);
                    final Object o = declaredField.get(this);
                    String message;
                    if (Objects.nonNull(notBlank)) {
                        message = notBlank.message();
                    } else {
                        message = notNull.message();
                    }
                    if (message.contains("javax.validation.constraints")) {
                        message = String.format("%s 不能为空", declaredField.getName());
                    }

                    if (Objects.isNull(o)) {
                        throw new IllegalArgumentException(message);
                    }

                    if (o instanceof String) {
                        String fieldResult = (String) o;
                        if (fieldResult.trim().length() == 0) {
                            throw new IllegalArgumentException(message);
                        }
                    }

                }
            }
            aClass = aClass.getSuperclass();
        } while (!aClass.equals(Object.class));
    }

}

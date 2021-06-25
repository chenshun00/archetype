package it.pkg.front.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author luobo.cs@raycloud.com
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
    protected void check() throws IllegalAccessException {
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            final NotBlank notBlank = declaredField.getAnnotation(NotBlank.class);
            final NotNull notNull = declaredField.getAnnotation(NotNull.class);
            if (Objects.nonNull(notBlank) || Objects.nonNull(notNull)) {
                declaredField.setAccessible(true);
                final Object o = declaredField.get(this);
                if (Objects.isNull(o)) {
                    String message;
                    if (Objects.nonNull(notBlank)) {
                        message = notBlank.message();
                    } else {
                        message = notNull.message();
                    }
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

}

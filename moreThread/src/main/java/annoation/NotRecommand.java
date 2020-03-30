package annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 不提倡使用的写法
 * @author 徐文产
 *
 */
@Target(ElementType.TYPE)//注解作用的目标
@Retention(RetentionPolicy.SOURCE)//注解作用范围
public @interface NotRecommand {

	String value() default "";
}

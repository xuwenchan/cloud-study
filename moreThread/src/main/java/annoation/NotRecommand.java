package annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * ���ᳫʹ�õ�д��
 * @author ���Ĳ�
 *
 */
@Target(ElementType.TYPE)//ע�����õ�Ŀ��
@Retention(RetentionPolicy.SOURCE)//ע�����÷�Χ
public @interface NotRecommand {

	String value() default "";
}

package annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * �̲߳���ȫ
 * @author ���Ĳ�
 *
 */
@Target(ElementType.TYPE)//ע�����õ�Ŀ��
@Retention(RetentionPolicy.SOURCE)//ע�����÷�Χ
public @interface NotThreadSafe {

	String value() default "";
}

package chapter17;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) 					// 타겟할 타입
@Retention(RetentionPolicy.RUNTIME) 			// 범위 , 컴파일동안도 가능하고 런타임동안도 가능함
public @interface MyAnnotation {

}

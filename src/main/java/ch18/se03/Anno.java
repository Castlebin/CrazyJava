package ch18.se03;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//定义可重复注解（Java 8新增）
@Repeatable(Annos.class)
@interface Anno {}

@Retention(RetentionPolicy.RUNTIME)
@interface Annos {
	Anno[] value();
}

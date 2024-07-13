package itstep.learning.oop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // включається у build
@Target(ElementType.TYPE)  // діє на класи/інтерфейси
public @interface Periodic {
}
/*
Анотації (аналог атрибутів у С#)
різновид інтерфейсів-маркерів, які можуть додавати
метадані не лише до класів, а й до їх складових частин
(поля, методи, тощо)
 */
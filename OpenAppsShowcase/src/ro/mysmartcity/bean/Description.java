package ro.mysmartcity.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * just fields annotated with QueryPAram will be considered for queries
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.FIELD })
public @interface Description {

	String description() default "N/A";
}

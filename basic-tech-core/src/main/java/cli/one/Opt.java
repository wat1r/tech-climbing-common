package cli.one;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Opt {
	String opt();
	String longOpt() default "";
	String description();
	String argName() default "";
	boolean required() default false;
}

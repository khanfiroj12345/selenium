package pom.customFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//Lifetime of a WebElement found
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
//defining custom annotation structure
public @interface SearchWith
{
    String locatorsFile() default "";
    String name() default "";
    String id() default "";
    String xpath() default "";
}

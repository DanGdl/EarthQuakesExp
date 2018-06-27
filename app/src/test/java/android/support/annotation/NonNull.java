package android.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by Owner
 * on 19/03/2018.
 */

@Documented
@Retention(CLASS)
@Target({METHOD, PARAMETER, FIELD, ANNOTATION_TYPE, PACKAGE})
public @interface NonNull {
}

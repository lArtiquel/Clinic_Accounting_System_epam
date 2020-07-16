package com.cas.interfaces;

import java.lang.annotation.*;

/**
 * This annotation indicates that class implements Command interface.
 * Params:
 *      - path: path to controller.
 *      - description: description what controller is doing.
 *
 * @author  Arti
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {

    String path() default "";

    String description() default "";

}

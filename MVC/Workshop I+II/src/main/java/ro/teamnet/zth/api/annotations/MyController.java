package ro.teamnet.zth.api.annotations;

import jdk.nashorn.internal.ir.annotations.Reference;

import java.lang.annotation.*;

/**
 * Created by Ionutz on 06.05.2015.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String urlPath();
}

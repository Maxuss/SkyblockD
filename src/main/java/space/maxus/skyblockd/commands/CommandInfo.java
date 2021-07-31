package space.maxus.skyblockd.commands;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    @NotNull String name();
    @NotNull String permission() default "";
    boolean playerOnly();
    @NotNull String configReq() default "";
}

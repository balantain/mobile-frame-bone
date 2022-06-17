package spring.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target (TYPE)
@Retention (RetentionPolicy.RUNTIME)
@Component
@Inherited
@Scope ("thread")
public @interface PageObject{}

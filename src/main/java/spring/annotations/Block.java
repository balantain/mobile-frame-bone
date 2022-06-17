package spring.annotations;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target (TYPE)
@Retention (RUNTIME)
@org.springframework.stereotype.Service
@Scope ("thread")
public @interface Block{}

package com.bobocode.annotation;

import com.bobocode.StringTrimmingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(StringTrimmingConfiguration.class)
public @interface EnableStringTrimming {
}
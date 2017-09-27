package com.tarrega.study.junit5.userguide.three.one;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
// 3.1.1. Meta-Annotations and Composed Annotations
// @Fast can be used as a drop-in replacement for @Tag("fast").
public @interface Fast {
}
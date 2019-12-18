package me.piggypiglet.randomspawn.data;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME) @Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Data {
}

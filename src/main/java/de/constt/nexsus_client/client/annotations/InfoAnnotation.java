package de.constt.nexsus_client.client.annotations;

import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAnnotation {
    String name();
    String description();
    CategoryImplementation.Categories category();
}

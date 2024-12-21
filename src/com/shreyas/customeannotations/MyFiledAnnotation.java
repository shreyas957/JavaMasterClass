package com.shreyas.customeannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This custom annotation is used to mark the fields of a class.
 * The {@code @Retention} annotation is used to specify how the marked annotation is stored.
 * <ul>
 *     <li>{@code RetentionPolicy.SOURCE} - The marked annotation is retained only in the source level and is ignored by the compiler.</li>
 *     <li>{@code RetentionPolicy.CLASS} - The marked annotation is retained by the compiler at compile time, but is ignored by the JVM.</li>
 *     <li>{@code RetentionPolicy.RUNTIME} - The marked annotation is retained by the JVM so it can be used by the runtime environment.</li>
 * </ul>
 * <p>What does actual RetentionPolicy.RUNTIME mean? <br>
 *  It means that the annotation will be available to the JVM through runtime reflection.
 *  This means that you can write code that can examine the annotations at runtime.
 *  This is done using reflection API. This is a powerful feature that allows you to write code that can adapt to the presence or absence of certain annotations.</p>
 * The {@code @Target} annotation is used to specify where the marked annotation can be applied.
 * <ul>
 *     <li>{@code ElementType.ANNOTATION_TYPE} - The marked annotation can be applied to an annotation type.</li>
 *     <li>{@code ElementType.CONSTRUCTOR} - The marked annotation can be applied to a constructor.</li>
 *     <li>{@code ElementType.FIELD} - The marked annotation can be applied to a field or property.</li>
 *     <li>{@code ElementType.LOCAL_VARIABLE} - The marked annotation can be applied to a local variable.</li>
 *     <li>{@code ElementType.METHOD} - The marked annotation can be applied to a method-level annotation.</li>
 *     <li>{@code ElementType.PACKAGE} - The marked annotation can be applied to a package declaration.</li>
 *     <li>{@code ElementType.PARAMETER} - The marked annotation can be applied to the parameters of a method.</li>
 *     <li>{@code ElementType.TYPE} - The marked annotation can be applied to a class, interface, enum, or annotation type.</li>
 *     <li>{@code ElementType.TYPE_PARAMETER} - The marked annotation can be applied to a type parameter.</li>
 *     <li>{@code ElementType.TYPE_USE} - The marked annotation can be applied to any type use.</li>
 *     <li>{@code ElementType.MODULE} - The marked annotation can be applied to a module declaration.</li>
 *     <li>{@code ElementType.RECORD_COMPONENT} - The marked annotation can be applied to a record component.</li>
 * </ul>
 *
 * @author shreyas shevale
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyFiledAnnotation {
    /**
     * this is a field in the annotation which can be used to store some value
     * and passed to the annotation using {@code MyAnnotation(value = x)} after the annotation name.
     *
     * @return the value of the field. Default value is 0.
     */
    int value() default 0;
}

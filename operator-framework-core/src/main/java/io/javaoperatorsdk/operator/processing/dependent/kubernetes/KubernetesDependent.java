package io.javaoperatorsdk.operator.processing.dependent.kubernetes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.javaoperatorsdk.operator.api.reconciler.Constants;
import io.javaoperatorsdk.operator.processing.event.source.filter.*;

import static io.javaoperatorsdk.operator.api.reconciler.Constants.NO_VALUE_SET;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface KubernetesDependent {

  String[] DEFAULT_NAMESPACES = {Constants.SAME_AS_CONTROLLER};

  /**
   * Specified which namespaces this Controller monitors for custom resources events. If no
   * namespace is specified then the controller will monitor the namespaces configured for the
   * controller.
   *
   * @return the list of namespaces this controller monitors
   */
  String[] namespaces() default {Constants.SAME_AS_CONTROLLER};

  /**
   * Optional label selector used to identify the set of custom resources the controller will acc
   * upon. The label selector can be made of multiple comma separated requirements that acts as a
   * logical AND operator.
   *
   * @return the label selector
   */
  String labelSelector() default NO_VALUE_SET;

  Class<? extends OnAddFilter<? extends HasMetadata>> onAddFilter() default VoidOnAddFilter.class;

  Class<? extends OnUpdateFilter<? extends HasMetadata>> onUpdateFilter() default VoidOnUpdateFilter.class;

  Class<? extends OnDeleteFilter<? extends HasMetadata>> onDeleteFilter() default VoidOnDeleteFilter.class;

  Class<? extends GenericFilter<? extends HasMetadata>> genericFilter() default VoidGenericFilter.class;
}

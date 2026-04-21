package com.zmr.common.security.annotation;

import java.lang.annotation.*;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-07-28
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreEncry {

}

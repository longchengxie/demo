/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by IntelliJ IDEA.<p/>
 * User: troy-kou<p/>
 * Date: 15/4/30<p/>
 * Time: 下午4:10<p/>
 * Email:kouhongyu@163.com<p/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface ReadOnlyDataSource {
}

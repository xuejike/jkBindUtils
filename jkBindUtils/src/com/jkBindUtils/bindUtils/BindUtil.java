package com.jkBindUtils.bindUtils;

import android.view.View;

/**
 * Created by xuejike on 2014/12/21.
 */
public interface BindUtil<T> {
    View createView(T data);

    View createView();

    View bind2View(View view, T data);
}

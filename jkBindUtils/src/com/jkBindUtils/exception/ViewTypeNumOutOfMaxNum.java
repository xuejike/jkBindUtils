package com.jkBindUtils.exception;

/**
 * Created by xuejike on 2014/12/21.
 */
public class ViewTypeNumOutOfMaxNum extends BindUtilsException {
    public ViewTypeNumOutOfMaxNum(int size,int index) {
        super("视图类型数量超出设定的最大值：最大值="+size+";当前值:"+index);
    }
}

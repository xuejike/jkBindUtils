jkBindUtils介绍
=================

jkBindUtils是一个android的视图绑定工具，它可以实现将java bean中的字段绑定到视图中，数据绑定只要分两种方式：
1. 可以将一个java bean 对象中的字段的数据通过Getter，Setter方法绑定到一个视图的属性中。
2. 可以将一个java bean 对象中的字段的数据通过Getter，Setter方法绑定到指定Id控件上，类似于系统自带的SimpleAdapter。

## 现在功能主要分两大模块 ##


*  单视图数据绑定
  > * 基于Annotation 注解的数据绑定，分三种：基于View的Id(√)，基于View的属性(√)，基于Id和属性的混合绑定（编写中...）
  > * 基于java bean字段的数据绑定，java bean字段名要与view中的一致，并且都要要Getter，Setter方法。（√）
  > * 基于JSON的数据绑定，前提是JSON字段名要与view中的一致，并且view要有Getter、Setter方法。（编写中...）
  > * 可以将View中数据转换成Java bean（编写中...）

* 数据绑定适配器
  > *  基于Annotation注解的单视图数据绑定适配器：分三种：基于View的Id(√)，基于View的属性(√)，基于Id和属性的混合绑定 （编写中）
  > * 基于Annotation注解的多视图数据绑定适配器：基于View的Id，基于View的属性（√），基于Id和属性的混合绑定 （编写中）

打勾的功能是已经完成了的。

## 单视图数据-使用方法 ##
* PropertyBindUtil 使用方法








## 数据绑定适配器-使用方法 ##

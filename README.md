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
* Annotation 说明
  
```java
	@BindView(viewClass=要绑定View的Class对象,layout=要绑定布局文件的layout)	
		绑定Class对象(主要针对自定义组合控件)
		@BindView(viewClass=TextView.class)
        绑定布局文件
		@BindView(layout=R.layout.main)
	@BindViewId(要绑定控件的Id)
	@BindViewProperty(对应View的属性名)  必须要有Getter，Setter方法
	@BindDisregard   忽略指定的字段



```



## 数据绑定适配器-使用方法 ##

* ViewIdBindAdapter 使用说明

```java

JavaBean

	//指定对应的Layout
	@BindView(layout = R.layout.book_item)
	public class IdVBook {
	
	    //使用@BindViewId(控件的Id) 可以将 属性 绑定到对应的 控件上
	    @BindViewId(R.id.book_name)
	    private String title;
	
	
	    @BindViewId(R.id.book_img)
	    private Drawable image;
	
	
	    public IdVBook() {
	    }
	
	    public IdVBook(String title, Drawable image) {
	        this.title = title;
	        this.image = image;
	    }
	
	    public Drawable getImage() {
	        return image;
	    }
	
	
	    public void setImage(Drawable image) {
	        this.image = image;
	    }
	
	
	
	    public String getTitle() {
	        return title;
	    }
	
	    public void setTitle(String title) {
	        this.title = title;
	    }
	}

使用ViewIdBindAdapter进行数据绑定

	//如果JavaBean 未指定@BindView(layout) 可以在创建适配器的时候指定
	ViewIdBindAdapter adapter = new ViewIdBindAdapter(context,R.layout.book_item,list);

	//如果JavaBean 已经指定@BindView(layout)了，可以直接进行数据绑定
	ViewIdBindAdapter adapter = new ViewIdBindAdapter(context,list);

```

* ViewPropertyBindAdapter 使用说明

```java


javaBean
		/**
	 * 使用@BindView(viewClass = view的class) 直接将java bean与view进行绑定
	 */
	@BindView(viewClass = BookItemView.class)
	public class PVBook {
	
	    //当属性名与view的属性一致时，可以直接绑定，
	    private String title;
	
	    //当属性名与view的属性不一致时，可以使用@BindViewProperty(view属性) 进行绑定
	    @BindViewProperty("img")
	    private Drawable image;
	
	    public PVBook() {
	    }
	
	    public PVBook(String title, Drawable image) {
	        this.title = title;
	        this.image = image;
	    }
	
	    public Drawable getImage() {
	        return image;
	    }
	  
	
	    public void setImage(Drawable image) {
	        this.image = image;
	    }
	
	
	
	    public String getTitle() {
	        return title;
	    }
	
	    public void setTitle(String title) {
	        this.title = title;
	    }
	}



使用 ViewPropertyBindAdapter 进行数据绑定
	//如果JavaBean 未指定@BindView(viewClass) 可以在创建适配器的时候指定
	ViewPropertyBindAdapter adapter =new ViewPropertyBindAdapter(context,BookItemView.class,list);
	//如果JavaBean 已经指定@BindView(viewClass)了，可以直接进行数据绑定
	ViewPropertyBindAdapter adapter =new ViewPropertyBindAdapter(context,list);

```

* ViewPropertyMapBindUtil 使用

```java
	//当你不能修改 要绑定数据的对象的时候，可以使用ViewPropertyMapBindUtil 进行数据绑定映射
		
		//定义 数据对象与view的属性映射关系
		Map<String,String> map=new HashMap<String, String>();
        map.put("title","title");
        map.put("image","img");
		
		//把映射关系 传入ViewPropertyMapBindAdapter适配器中进行数据绑定
        ViewPropertyMapBindAdapter adapter =
                new ViewPropertyMapBindAdapter(context,BookItemView.class,map,list);

```


* MultiViewIdBindAdapter 和 MultiViewPropertyBindAdapter 使用说明
  

```java

MultiViewIdBindAdapter 和 MultiViewPropertyBindAdapter
 是一个多视图绑定适配器可以在一个listView中绑定多个视图



 MultiViewIdBindAdapter adapter =new MultiViewIdBindAdapter(context,viewTypeMax,list);

 MultiViewPropertyBindAdapter adapter =new MultiViewPropertyBindAdapter(context,viewTypeMax,list);

viewTypeMax:视图类型的最大数量

```
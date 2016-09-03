# CustomShapeImageView

### 本控件主要提供了下面几种类型的ImageView

> * 圆形
> * 圆角矩形
> * 正六边形
> * 菱形
> * 椭圆形

### 使用方式如下
#### Android Studio 直接添加如下引用即可
```
compile 'org.lenve:customshapeimageview:1.0.0'
```
> * 正六边形
```
        <org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_margin="20dp"
            app:rotate="90"
            android:src="@drawable/p1"
            app:shape="hexagon"/>
```
> * 圆形
```
<org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_margin="20dp"
            android:src="@drawable/p1"
            app:shape="circle"/>
```
> * 椭圆形
```
<org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="300dp"
            android:layout_height="150dp"
            android:layout_margin="20dp"
            android:src="@drawable/p1"
            app:shape="oval"/>
```
> * 椭圆形
```
<org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="150dp"
            android:layout_height="300dp"
            android:layout_margin="20dp"
            android:src="@drawable/p1"
            app:shape="oval"/>
```
> * 菱形
```
<org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_margin="20dp"
            android:src="@drawable/p1"
            app:shape="rhombus"/>
```
> * 圆角矩形
```
<org.lenve.customshapeimageview.CustomShapeImageView
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_margin="20dp"
            android:src="@drawable/p1"
            app:corner="30dp"
            app:shape="rounrectangle"/>
```
### 显示效果
![1](https://cloud.githubusercontent.com/assets/6023444/18171596/3294cbf2-7095-11e6-9b7d-3d05e18247a6.gif)

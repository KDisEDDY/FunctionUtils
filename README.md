# FunctionUtils
该project是作为个人积累和总结用的项目，主要有两个module：app和SlidingMenu，Module app是一些简单功能点的测试，如service，mediaplayer的简单应用，6.0运行时权限的设置等，皆为简单的测试；
而Module SlidingMenu是学习自定义侧滑菜单控件的所创建的，其中包含自定义侧滑菜单控件和滑动删除控件。
## Module app 的介绍
### bgplayer
bgplayer的主要难点是对player的状态理解，下图为其状态图：![image](https://github.com/KDisEDDY/FunctionUtils/picture/mediaplayer_state.gif)
一般播放器是使用Service来控制Mediaplayer的状态的，这样可以实现后台播放功能
### fragmenttest
测试Fragment的onActivityResult方法的调用
### listpopup
利用recycleView + dialog实现底层弹出滑动选择框
### notification
### permission
封装了一个6.0运行时权限工具类，本想使用枚举类来设置权限数组的，但感觉增加了复杂度，所以没有用到，需要在activity中配合使用onRequestPermissionsResult方法，来回调获取权限厚度操作
## Moudle SlidingMenu 的介绍
SlidingMenu是我在刚毕业时练手自定义view的一个控件，项目里主要有两个控件：
1.  SlidingMenu
    参考了鸿洋的自定义SlidingMenu的博客，地址是<http://blog.csdn.net/lmj623565791/article/details/39185641>
2.  SlideLayout
    这个控件是滑动LinearLayout控件，实现了横向滑动的功能，其内部View可自定义，适合扩展。

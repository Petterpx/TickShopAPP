# 嘀嗒电商

一个基于Fragmentation的单Activity的app,运用了模块化，插件式的思想。

内部封装了如下组件：

- 全局唯一配置入口；
- RecyclerView二次封装，适配多holder；
- WebView的混合应用封装；
- 缓存，文件，动态权限处理;
- 使用ZBar封装高性能二维码扫描组件；
- 集成极光推送，Mobtech一键分享；
- 支付宝支付沙盒版集成；
- 微信包名绕过实践；

...



### 效果图：

![image-20190904003930627](https://tva1.sinaimg.cn/large/006y8mN6ly1g6mt3rzan2j31fj0u0aru.jpg)



##### 项目基础框架：

https://github.com/Petterpx/BaseFrameUtils

##### 项目网络请求库：

https://github.com/Petterpx/RxRetifoitUtils
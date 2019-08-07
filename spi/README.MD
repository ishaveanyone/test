java SPI机制的约定：
1)         在META-INF/services/目录中创建以接口全限定名命名的文件该文件内容为Api具体实现类的全限定名
2)         使用ServiceLoader类动态加载META-INF中的实现类
3)         如SPI的实现类为Jar则需要放在主程序classPath中
4)         Api具体实现类必须有一个不带参数的构造方法

dubbo spi 
1)         在META-INF/services/目录中创建以接口全限定名命名的文件该文件内容为Api具体实现类的全限定名
2)         使用ExtensionLoader类动态加载META-INF中的实现类
3)         如SPI的实现类为Jar则需要放在主程序classPath中
4)         Api具体实现类必须有一个不带参数的构造方法
5)         接口必须有@spi注解

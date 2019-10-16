# 关于pg数据库的角色 
## 关于数据库postgres
为了引导数据库系统，一个刚刚被初始化好的系统总是包含一个预定义角色。
这个角色总是一个超级用户，并且默认情况下（除非在运行initdb时修改）
它的名字和初始化数据库集簇的操作系统用户相同。习惯上，这个角色将被命
名为postgres。每一个到数据库服务器的连接都是使用某个特定角色名建立的，
并且这个角色决定发起连接的命令的初始访问权限。

## 超级用户 记录
CREATE USER默认有LOGIN权限，而CREATE ROLE则没有。
一个数据库超级用户会绕开所有权限检查，所以最好用一个
不是超级用户的角色来完成你的大部分工作。一个角色必须
被显式给予权限才能创建数据库（除了超级用户）。要创建
这样一个角色，使用以下命令：

超级用户：类似于其他系统的sa、root、sysdba

initdb.exe 初始化是建立的第一个superuser 的名字 和 
执行initdb.exe 时所用的os帐号相同

第一个superuser的oid=10

superuser是不受postgresq的对象权限系统控制的，
可以在系统里做任何事情！危险！！

## 模式
首先介绍一下postgresql中的schema,postgresql中的schema和
其它关系型数据库中的schema含义是一致的,
在oracle中叫schema或者用户,只是oracle中schema和用户是始终一一对应.
.postgresql中user和schema是可以不一致的,相对比其它数据库复杂一点.
在创建schema的时候,可以指定schema的所属用户,默认的只有所属用户（角色）和超级
用户才能在此schema进行对象操作,否则就需要授权.

## 角色

postgresql自8.1后不再区分用户、组了。称之为角色role

role是和数据库无关的
role就是权限的集合
role可以互相交叉、继承、被赋于对象权限
 

## 不同模式会涉及权限问题 么 
首先,如果某个用户需要访问某张表,那么
用户首先需要有访问该表所在schema的权限.
默认只有schema的所属可以直接操作该schema,
其它用户需要授权(public schma除外)

## 关于springboot使用 中的 几点 疑问 
1. 同一个用户不同模式的 对象访问 （@table指定模式名解决）




## 关于角色权限猜想 
当一个用户 想访问 不属于自己的模式的 某一个对象 ，比如这里a用户 要访问 schmea1 属于b用户
那么就 用模式权限 进行授权


## 关于模式的 权限 schema


## 关于public 模式 
all:user
在介绍后面的内容之前，这里我们需要先解释一下public模式。
每当我们创建一个新的数据库时，
PostgreSQL都会为我们自动创建该模式。当登录到该数据库时，
如果没有特殊的指定，我们将以该模式(public)的形式操作各种数据对象，如：

缺省时，用户看不到模式中不属于他们所有的对象。为了让他们看得见，模式的所有者需要在模式上赋予USAGE权限。为了让用户使用模式中的对象，我们可能需要赋予额外的权限，只要是适合该对象的。PostgreSQL根据不同的对象提供了不同的权限类型，如：
    GRANT ALL ON SCHEMA myschema TO public;
    上面的ALL关键字将包含CREATE和USAGE两种权限。如果public模式拥有了myschema模式的CREATE权限，那么登录到该模式的用户将可以在myschema模式中创建任意对象，如：
    CREATE TABLE myschema.products (
        product_no integer,
        name text,
        price numeric CHECK (price > 0),
    );
    在为模式下的所有表赋予权限时，需要将权限拆分为各种不同的表操作，如：
    ALTER DEFAULT PRIVILEGES IN SCHEMA myschema
    GRANT INSERT, SELECT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER ON TABLES TO public;
    在为模式下的所有Sequence序列对象赋予权限时，需要将权限拆分为各种不同的Sequence操作，如：
    ALTER DEFAULT PRIVILEGES IN SCHEMA myschema
    GRANT SELECT, UPDATE, USAGE ON SEQUENCES TO public;
    在为模式下的所有函数赋予权限时，仅考虑执行权限，如：
    ALTER DEFAULT PRIVILEGES IN SCHEMA myschema
    GRANT EXECUTE ON FUNCTIONS TO public;

下面是一开始自己的理解 但是现在 看来有点问题 
    小范围的权限赋予 模式.表明 to 用户（角色）
    较大范围的      模式 userage/create to 用户（角色）
    最大范围的      用户（角色） to 用户（角色）---用户可能包含很多的 模式的操作权限 将一个角色直接赋予给另外一个角色那么那个角色也拥有了这些 模式的操作权限
奇怪 如果 u1用户 进去 创建 了 两个模式 s1 然后 创建一个表t1，如果我在用u2登录 我要访问 u1的 s1模式的 t1表 ，必须得把 模式s1 的 usage赋予给 u2 角色 然后 还得执行 t1表select权限给u2
我看了下pg的 用户权限 我以为是这样的 

小范围的权限赋予 模式.表明 to 用户（角色）
较大范围的      模式 userage/create to 用户（角色）
最大范围的      用户（角色） to 用户（角色）---用户可能包含很多的 模式的操作权限 将一个角色直接赋予给另外一个角色那么那个角色也拥有了这些 模式的操作权限
许平平(2292281407)  10:04:23
但是好像必须先把一个模式的usage 给一个角色以后，然后还得执行这个模式下面的对象的 具体的权限 比如select update 给角色 ，这个角色在登录之后才有权限进行select 和update
在使用 navicat 的 时候 发现一个坑 就是 我们 正常 创建用户创建 模式 ，然后 指定模式 拥有者，但是 并没有 把这个模式的 对象的 操作权限赋予给这个拥有者 


# pg 权限
http://www.freeoa.net/osuport/db/the-postgres-privileges_3112.html
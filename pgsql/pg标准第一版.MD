# 很有必要先了解
## pg 权限
http://www.freeoa.net/osuport/db/the-postgres-privileges_3112.html

## 关于超级用户
        超级用户：类似于其他系统的sa、root、sysdba
        pg中的superuser是不受postgresq的对象权限系统控制的，一旦登录就可以做任何事情
        所以创建角色（用户）不要指定 是超级用户
   
## 关于模式
        postgresql中的schema,postgresql中的schema和其它关系型数据库中的
        schema含义是一致的,在oracle中叫schema或者用户,只是oracle中schema和用户是始终一一对应.
        postgresql中user和schema是可以不一致的,相对比其它数据库复杂一点.
        在创建schema的时候,可以指定schema的所属用户,默认的只有所属用户（角色）
        和超级用户才能在此schema进行对象操作,否则就需要授权.

## 关于角色 
        postgresql自8.1后不再区分用户、组了。称之为角色role
        role是和数据库无关的
        role就是权限的集合
        role可以互相交叉、继承、被赋于对象权限

## 关于对象访问权限的问题
        如果某个用户需要访问某张表,那么用户首先需要有访问该表所在schema的权限.默认只有schema
        的所属可以直接操作该schema,其它用户需要授权(public schma除外)
        举例 ：
        如果 我有用户u1 不是超级用户 可以登录，登录之后创建模式s1，在s1下创建表t1，
        然后使用u2 登录 不是超级用户 可以登录 ，如果 要访问 表t1 就需要先
        grant usage on schema s1 to u2;
        先保证用户拥有 模式的使用权限 （不太理解 正常解释是 对于Schema来说，允许查找该Schema下
        的对象，但是并不是如此，还需要对对象进行精确的授权）
        grant select s1.t1 to u2 
        然后将t1 表的 查询权限赋予 u2 这样u2 才能对t1表进行数据查询

# 决定国土的数据存储规范
创建一个 用户 ，创建一个同名的模式，然后存储所有的数据对象 推荐直接使用 navicat进行操作
在springboot使用jpa 基础上 可以 通过 @Table(name = "t1",schema = "tschema") 
来指定 具体 schema 进行数据访问

        可以解决：
        同一个用户 不同 模式的访问（不用授权）
        不同用户不同模式的访问（授权可以访问）
        还没解决：
        跨库访问（没有尝试）



# 创建数据库，CREATE DATABASE IF NOT EXISTS xxx
CREATE DATABASE IF NOT EXISTS crazyjava /*!40100 COLLATE 'utf8_general_ci' */;

# 创建test表
create table test (test_id int, 
test_price decimal,
test_name varchar(255) default 'xxx',
test_desc text,
test_img blob,
test_date datetime
);

# 用子查询建表(create table xxx as subquery)
create table test2 
as
	select * from test;

# 为test表增加字段，alter、add
alter table test
add
	hehe_id int;

# 为test表一次增加多个字段
alter table test
add (
	aaa varchar(255) default 'xxx',
	bbb varchar(255),
	ttt text
);

# 修改列定义(modify)
alter table test
modify ttt varchar(255);

# 删除数据列(MySQL不支持一次删除多列)
alter table test
drop aaa;
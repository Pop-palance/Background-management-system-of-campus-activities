-- ----------------------------
-- 1、学生表
-- ----------------------------
drop table if exists sys_stu;
create table sys_stu (
                          sno       varchar(10)  not null    comment '学号',
                          spwd      varchar(30)  not null    comment '密码',
                          sname     varchar(30)   not null   comment '姓名',
                          sdegree   char(1)     default '0' comment '学历',
                          sacademy  varchar(30) not null comment '学院',
                          sclass    varchar(30) not null comment '班级',
                          snum      varchar(11) default '' comment '手机号',
                          semail    varchar(30) default '' comment '邮箱',
                          sgender   char(1)     default '0' comment '性别',
                          sstatus   char(1)     default '0' comment '状态',
                          sjob      varchar(30) default '' comment '职务',
                          srole     char(1)     default '0' comment '用户角色',
                          sremark   varchar(500) default '' comment '备注',
                          loginip   varchar(50)  default '' comment '最后登录IP',
                          logintime datetime             comment '最后登录时间',
                   primary key (sno)
) engine=innodb  comment = '学生表';         



insert into sys_stu values('2017040307', '123456','周梦','0','信息学院','计科1702','18801245135','410184240@qq.com','1','0','学习委员','0','无备注','120.0.0.1',sysdate());



-- ----------------------------
-- 2、组织表
-- ----------------------------
drop table if exists sys_org;
create table sys_org(
  oid      bigint(20)   not null auto_increment  comment '组织id',
  opwd     varchar(30)  not null              comment '密码',
  oname    varchar(30)  not null              comment '名称',
  parentid bigint(20)   default 0             comment '父部门id',
  ancestors varchar(50) default ''            comment '祖级列表',
  createtime datetime                         comment '创建时间',
  onum       varchar(11) not null             comment '手机号',
  oemail     varchar(30) default ''           comment '邮箱',
  ostatus    char(1)     default '0'          comment '状态',
  ojob       varchar(30) default ''           comment '职务',
  orole      char(1)     default '1'          comment '角色',
  onote      varchar(500) default ''          comment '备注',
primary key(oid)

  
)engine=innodb comment='组织表';

insert into sys_org values(1,'123456','后勤保障部',0,'',sysdate(),'18801245135','410184240@qq.com','0','部长','1','组织者');

-- ----------------------------
-- 3、学生活动成绩表
-- ----------------------------
drop table if exists sys_stu_act;
create table sys_stu_act(                       
  sno varchar(10) not null comment '学号',
  aid bigint(20) not null comment '活动id',
  grade int(10) default 0 comment '活动分数',
  primary key(sno,aid)
)engine=innodb comment='学生活动成绩表';

insert into sys_stu_act values('2017040307','1',30);


                          
                          
                

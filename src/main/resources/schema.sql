drop
database skill_master;
CREATE
DATABASE IF NOT EXISTS skill_master DEFAULT CHARACTER SET utf8;
USE
skill_master;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    account   int primary key auto_increment COMMENT '账号',
    password  Varchar(30)  NOT NULL COMMENT '密码',
    username  Varchar(20)  NOT NULL COMMENT '用户昵称',
    gender    enum ('M','F','O') NOT NULL COMMENT '性别',
    signature varchar(200) NOT NULL default '该用户未设置签名' COMMENT '用户签名',
    avatar    varchar(200) NOT NULL default '/DefaultAvatar.png' COMMENT '用户头像',
    time      timestamp             default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '用户创建时间',
    state     enum ('N','E','C') NOT NULL COMMENT '账号状态'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS follow;

CREATE TABLE follow
(
    blogger  int not NULL COMMENT '博主',
    follower int not NULL COMMENT '关注者',
    time     timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '关注时间',
    foreign key (blogger) references user (account),
    foreign key (follower) references user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS blogcomments;

CREATE TABLE blogcomments
(
    uid       int primary key auto_increment COMMENT '唯一标识',
    receiver  int NOT NULL COMMENT '回复的对象',
    commentor int NOT NULL COMMENT '评论者',
    commentee int NOT NULL COMMENT '被回复者',
    time      timestamp    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '评论时间',
    content   varchar(200) COMMENT '评论内容',
    likes     int NOT NULL default 0 COMMENT '点赞数量',
    comment   int NOT NULL default 0 COMMENT '评论数量',
    state     enum ('N','C','D') NOT NULL COMMENT '评论的状态',
    foreign key (commentor) references user (account),
    foreign key (commentee) references user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
DROP TABLE IF EXISTS ccomments;

CREATE TABLE ccomments
(
    uid       int primary key auto_increment COMMENT '唯一标识',
    receiver  int NOT NULL COMMENT '回复的对象',
    commentor int NOT NULL COMMENT '评论者',
    commentee int NOT NULL COMMENT '被回复者',
    time      timestamp    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '评论时间',
    content   varchar(200) COMMENT '评论内容',
    likes     int NOT NULL default 0 COMMENT '点赞数量',
    comment   int NOT NULL default 0 COMMENT '评论数量',
    state     enum ('N','C','D') NOT NULL COMMENT '评论的状态',
    foreign key (commentor) references user (account),
    foreign key (commentee) references user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS blogs;

CREATE TABLE blogs
(
    uid     int primary key auto_increment COMMENT '唯一标识',
    owner   int NOT NULL COMMENT '博主',
    time    timestamp    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '发表时间',
    likes   int NOT NULL default 0 COMMENT '点赞数量',
    comment int NOT NULL default 0 COMMENT '评论数量',
    state   enum ('N','C','D') NOT NULL COMMENT '博客的状态',
    content varchar(200) COMMENT '博客内容',
    foreign key (owner) references user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS keywords;

CREATE TABLE keywords
(
    uid     int         NOT NULL COMMENT '博客',
    keyword varchar(20) NOT NULL COMMENT '关键词',
    foreign key (uid) references blogs (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS tags;

CREATE TABLE tags
(
    uid   int         NOT NULL COMMENT '博客',
    tag   varchar(20) NOT NULL COMMENT '标签',
    time  timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '设置时间',
    state enum ('N','C','D') NOT NULL COMMENT '标签的状态',
    foreign key (uid) references blogs (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
drop table if exists test;
create table test
(
    id int primary key auto_increment,
    a  int,
    b  timestamp default current_timestamp on update current_timestamp,
    c  enum ('a','b','c')
);
create table if not exists yyq_universe.user
(
    id           bigint auto_increment comment '用户id'
    primary key,
    username     varchar(255) charset latin1 default ''                not null comment '用户名',
    userAccount  varchar(255) charset latin1                           null comment '账号',
    gender       tinyint                     default 0                 not null comment '性别',
    userPassword varchar(255) charset latin1                           null comment '密码',
    avatarUrl    varchar(1024) charset latin1                          null comment '头像',
    phone        varchar(128) charset latin1                           null comment '电话',
    email        varchar(255) charset latin1                           null comment '邮箱',
    userStatus   int                         default 0                 not null comment '状态',
    createTime   timestamp                   default CURRENT_TIMESTAMP not null comment '创建时间',
    isDelete     tinyint                     default 0                 null comment '逻辑删除',
    updateTime   datetime                                              null on update CURRENT_TIMESTAMP comment '更新时间',
    userRole     int                         default 0                 not null comment '权限, 0普通用户, 1管理员',
    planetCode   varchar(255)                                          null comment '用户唯一Code'
    )
    charset = utf8;
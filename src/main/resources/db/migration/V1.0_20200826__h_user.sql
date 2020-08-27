CREATE TABLE h_user
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `username` varchar(200)  DEFAULT NULL COMMENT '用户名',
    `password`               text          DEFAULT NULL COMMENT '密码',
    `status`               char(1)       DEFAULT '0' COMMENT '状态 0:使用中 1:停用 默认是0',
    `create_time`          timestamp     DEFAULT NULL COMMENT '创建时间',
    `del_flag`             char(1)       DEFAULT '0' COMMENT '删除标记 0:未删除 1:删除 默认0 ',
    PRIMARY KEY (`id`)
) COMMENT ='用户表';

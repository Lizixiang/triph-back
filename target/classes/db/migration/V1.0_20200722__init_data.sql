CREATE TABLE h_personal_summary_technique
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `question_description` varchar(200)  DEFAULT NULL COMMENT '问题描述',
    `answer`               text          DEFAULT NULL COMMENT '解决方案',
    `status`               char(1)       DEFAULT '0' COMMENT '状态 0:未完成 1:进行中 2:已完成 默认是0',
    `refer_url`            varchar(1000) DEFAULT '' COMMENT '参考链接',
    `technique_id`         bigint        DEFAULT NULL COMMENT '技术分类id',
    `create_time`          timestamp     DEFAULT NULL COMMENT '创建时间',
    `create_name`          varchar(64)   DEFAULT NULL COMMENT '创建人',
    `update_time`          timestamp     DEFAULT NULL COMMENT '修改时间',
    `update_name`          varchar(64)   DEFAULT NULL COMMENT '修改人',
    `del_flag`             char(1)       DEFAULT '0' COMMENT '删除标记 0:未删除 1:删除 默认0 ',
    PRIMARY KEY (`id`)
) COMMENT ='个人总结技术表';


CREATE TABLE h_technique
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `technique_name` varchar(200) DEFAULT NULL COMMENT '技术名称',
    `nick_name`      varchar(64)  DEFAULT NULL COMMENT '简称',
    `create_time`    timestamp    DEFAULT NULL COMMENT '创建时间',
    `create_name`    varchar(64)  DEFAULT NULL COMMENT '创建人',
    `update_time`    timestamp    DEFAULT NULL COMMENT '修改时间',
    `update_name`    varchar(64)  DEFAULT NULL COMMENT '修改人',
    `del_flag`       char(1)      DEFAULT '0' COMMENT '删除标记 0:未删除 1:删除 默认0 ',
    PRIMARY KEY (`id`)
) COMMENT ='技术表';

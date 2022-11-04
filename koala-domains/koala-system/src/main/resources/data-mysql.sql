insert into t_department
values ('1', '1', '考拉公司', '公司午餐暂不提供桉树叶', null);
insert into t_department
values ('101', '101', '技术部', '技术部', '1');

insert into t_permission
values ('0', 'personal', '个人服务', '个人服务', 1),
       ('101', 'dictionary:write', '字典读取', '字典读取', 1),
       ('102', 'dictionary:read', '字典写入', '字典写入', 1),
       ('201', 'department:read', '部门读取', '部门读取', 1),
       ('202', 'department:write', '部门写入', '部门写入', 1),
       ('301', 'role:read', '角色读取', '角色读取', 1),
       ('302', 'role:write', '角色写入', '角色写入', 1),
       ('401', 'permission:read', '权限读取', '权限读取', 1),
       ('402', 'permission:write', '权限写入', '权限写入', 1),
       ('501', 'user:read', '用户读取', '用户读取', 1),
       ('502', 'user:write', '用户写入', '用户写入', 1);

insert into t_role
values ('1', 'admin', '系统管理员', null, 1);

insert into t_user
values ('1', 'admin', '{bcrypt}$2a$10$hG82.yWypJvj0jjJWrwACu166QkM.uLkpk/Yr8vxtKdAyqyd5wUJi', '系统管理员', null, 1);

insert into t_user_department
values ('1', '1'),
       ('1', '101');

insert into t_user_role
values ('1', '1');

insert into t_role_permission
values ('1', '0'),
       ('1', '101'),
       ('1', '102'),
       ('1', '201'),
       ('1', '202'),
       ('1', '301'),
       ('1', '302'),
       ('1', '401'),
       ('1', '402'),
       ('1', '501'),
       ('1', '502');

insert into oauth2_registered_client
values ('vue', 'vue', null, '{bcrypt}$2a$10$t0TBFeS0FuIiHxlwb0bfzuEEjmo7EQXjJykRi8UN/MHMTEW3hsPpW', null, 'vue',
        'client_secret_post,client_secret_basic', 'refresh_token,client_credentials,password,authorization_code',
        'http://localhost:9999/login/oauth2/code/client', 'all,read,write',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}')

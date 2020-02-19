INSERT INTO member (idx, email, name, password, updated_time, created_time)
SELECT 1, 'admin@admin.com', 'Admin', '$2a$10$uMdYR4t1txJiEbbK6HfE8OAyfSQ0/GKEGUmYUTGABNaO6vEpgUO7m', current_timestamp, current_timestamp
WHERE NOT EXISTS(SELECT * from member WHERE idx = 1);

INSERT INTO role (idx, role)
SELECT 1, 'ROLE_ADMIN'
WHERE NOT EXISTS(SELECT * from role WHERE idx = 1);

INSERT INTO member_role (member_idx, role_idx)
SELECT 1, 1
WHERE NOT EXISTS(SELECT * from member_role WHERE member_idx = 1);


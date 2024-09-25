BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('ale100','$2a$10$SrB5LYkHxNv5DLINsubPU.WN0i6z0ZyhwvuHuSvuE2M/gxJFJUMiK','ROLE_USER');

INSERT INTO departments (department_id,department_name)
VALUES (1,'Production'),
       (2,'QA'),
       (3,'Scheduling/Planning');


INSERT INTO mbr_reviews (review_status, review_description)
VALUES  (1, 'Pending'),
        (2, 'Approved'),
        (3, 'Rejected');

INSERT INTO mbr (product_name, total_quantity_issued, total_quantity_made, review_status,date_created, date_time_started, date_time_finished)
VALUES ('Product A', 100, 95, 1, '2024-09-01', '2024-09-01 08:00:00', '2024-09-01 16:00:00'),
       ('Product B', 200, 198, 2, '2024-09-02', '2024-09-02 09:00:00', '2024-09-02 17:00:00'),
       ('Product C', 150, 140, 3, '2024-09-03', '2024-09-03 07:30:00', '2024-09-03 15:30:00');

COMMIT TRANSACTION;

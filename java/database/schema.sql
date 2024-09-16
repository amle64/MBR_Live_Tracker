BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_details CASCADE;
DROP TABLE IF EXISTS mbr_reviews CASCADE;
DROP TABLE IF EXISTS mbr CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- Sequence for MBR IDs
CREATE SEQUENCE seq_mbr_id
    INCREMENT BY 1
    START WITH 1000
    NO MAXVALUE;

-- User Details Table
CREATE TABLE user_details (
    user_id INT NOT NULL,
    email_address VARCHAR(50),
    is_supervisor BOOLEAN NOT NULL,
    department_id int NOT NULL,
    CONSTRAINT pk_user_details PRIMARY KEY (user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- MBR Reviews Table (Create this before mbr table)
CREATE TABLE mbr_reviews (
    review_status INT NOT NULL,
    review_description VARCHAR(25),
    CONSTRAINT pk_review_status PRIMARY KEY (review_status)
);

-- MBR Table
CREATE TABLE mbr (
    mbr_id INT NOT NULL DEFAULT nextval('seq_mbr_id'),
    product_name VARCHAR(50),
    total_quantity_issued INT NOT NULL,
    total_quantity_made INT NULL,
    review_status INT NOT NULL,
    date_created DATE NOT NULL,
    date_time_started TIMESTAMP NULL,
    date_time_finished TIMESTAMP NULL,
    CONSTRAINT pk_mbr_id PRIMARY KEY (mbr_id),
    CONSTRAINT fk_review_status FOREIGN KEY (review_status) REFERENCES mbr_reviews (review_status)
);
COMMIT TRANSACTION;

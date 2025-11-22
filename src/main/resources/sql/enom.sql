USE enomdeul_db;

CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(20),
                      gender VARCHAR(5),
                      password VARCHAR(30),
                      login_id VARCHAR(30),
                      email VARCHAR(30),
                      organization VARCHAR(30),
                      age INT,
                      job_group VARCHAR(30),
                      intro VARCHAR(255),
                      portfolio_url VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE skill (
                       skill_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       skill_name VARCHAR(50),
                       job_group VARCHAR(30),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_skill (
                            user_id BIGINT NOT NULL,
                            skill_id BIGINT NOT NULL,
                            level VARCHAR(30),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                            PRIMARY KEY (user_id, skill_id),

                            FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
                            FOREIGN KEY (skill_id) REFERENCES skill(skill_id) ON DELETE CASCADE
);

CREATE TABLE desired_skill (
                               skill_id BIGINT NOT NULL,
                               user_id BIGINT NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                               PRIMARY KEY (skill_id, user_id),

                               FOREIGN KEY (skill_id) REFERENCES skill(skill_id) ON DELETE CASCADE,
                               FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

CREATE TABLE offer (
                       offerer BIGINT NOT NULL,
                       offeree BIGINT NOT NULL,
                       offer_status TINYINT(1),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (offerer, offeree),

                       FOREIGN KEY (offerer) REFERENCES user(user_id) ON DELETE CASCADE,
                       FOREIGN KEY (offeree) REFERENCES user(user_id) ON DELETE CASCADE
);

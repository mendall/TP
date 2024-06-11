CREATE TABLE IF NOT EXISTS test_project_db.user (
    id int AUTO_INCREMENT,
    name varchar(255),
    PRIMARY KEY (id)
                                                );
INSERT INTO test_project_db.user (name) VALUES ('John Doe');
INSERT INTO test_project_db.user (name) VALUES ('Jane Doe');
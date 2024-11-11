CREATE TABLE quiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cate VARCHAR(50) NOT NULL,
    subcate VARCHAR(50),
    quiz TEXT NOT NULL,
    ans TEXT NOT NULL,
    UNIQUE (ans)
);

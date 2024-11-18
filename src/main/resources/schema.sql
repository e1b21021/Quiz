CREATE TABLE quiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cate VARCHAR(50) NOT NULL,
    subcate VARCHAR(50),
    quiz TEXT NOT NULL,
    ans TEXT NOT NULL,
    UNIQUE (ans)
);

CREATE TABLE users (
    id IDENTITY,
    name VARCHAR(100) NOT NULL
);

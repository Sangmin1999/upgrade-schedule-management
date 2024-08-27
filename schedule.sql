CREATE TABLE Schedules (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           user_name VARCHAR(100) NOT NULL,      -- 작성 유저명
                           title VARCHAR(255) NOT NULL,          -- 할일 제목
                           content TEXT NOT NULL,                -- 할일 내용
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 작성일
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 수정일
);
CREATE TABLE Comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          schedule_id INT NOT NULL,             -- 댓글이 달린 일정의 ID (외래 키)
                          user_name VARCHAR(100) NOT NULL,      -- 작성 유저명
                          content TEXT NOT NULL,                -- 댓글 내용
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 작성일
                          updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일
                          FOREIGN KEY (schedule_id) REFERENCES Schedules(id) ON DELETE CASCADE  -- 일정과의 연관 관계 (외래 키)
);

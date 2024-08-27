### 나만의 일정 관리 앱 서버 만들기

| **Method** | **Endpoint**                                | **설명**                         | **Request Body** | **Response** | **Status Codes**     |
|------------|---------------------------------------------|----------------------------------|------------------|--------------|----------------------|
| **POST**   | `/schedules`                                | 새로운 일정 생성                 | `user_name`, `title`, `content` | 생성된 일정 정보 | `201 Created`, `400 Bad Request` |
| **GET**    | `/schedules/{id}`                           | 특정 일정 단건 조회              | N/A              | 일정 정보    | `200 OK`, `404 Not Found` |
| **PUT**    | `/schedules/{id}`                           | 특정 일정 수정                   | `title`, `content` | 수정된 일정 정보 | `200 OK`, `400 Bad Request`, `404 Not Found` |
| **POST**   | `/schedules/{schedule_id}/comments`         | 특정 일정에 댓글 생성            | `user_name`, `content` | 생성된 댓글 정보 | `201 Created`, `400 Bad Request` |
| **GET**    | `/schedules/{schedule_id}/comments`         | 특정 일정에 달린 모든 댓글 조회   | N/A              | 댓글 목록    | `200 OK`, `404 Not Found` |
| **GET**    | `/comments/{id}`                            | 댓글 단건 조회                   | N/A              | 댓글 정보    | `200 OK`, `404 Not Found` |
| **PUT**    | `/comments/{id}`                            | 특정 댓글 수정                   | `content`        | 수정된 댓글 정보 | `200 OK`, `400 Bad Request`, `404 Not Found` |
| **DELETE** | `/comments/{id}`                            | 특정 댓글 삭제                   | N/A              | N/A          | `204 No Content`, `404 Not Found` |

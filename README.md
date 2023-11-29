# SPRING & JPA 기반 테스트
***
## Persistence Layer 테스트
* Data Access의 역할
* 비즈니스 가공 로직이 포함되면 안된다. Data에 대한 CRUD에만 집중한 레이어
***
## Business Layer
* 비즈니스 로직을 구현하는 역할
* Persistence Layer와의 상호작용(Data를 읽고 쓰는 행위)을 통해 비즈니스 로직을 전개시킨다.
* `트랜잭션`을 보장해야 한다.
# 1. JDK 21 이미지를 사용합니다
FROM openjdk:21-jdk-slim

# 2. 컨테이너 내부의 /app 디렉토리를 작업 디렉토리로 설정합니다
WORKDIR /app

# 3. 호스트의 build/libs 디렉토리에 있는 JAR 파일을 컨테이너의 /app 디렉토리로 복사합니다
COPY build/libs/ggogit-0.0.1-SNAPSHOT.jar app.jar

# 4. 컨테이너 실행 시 실행할 명령어를 설정합니다
ENTRYPOINT ["java", "-jar", "app.jar"]

# 포트 설정 (Spring Boot 기본 포트)
EXPOSE 8080

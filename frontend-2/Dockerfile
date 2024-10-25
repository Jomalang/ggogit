# 빌드 단계
FROM node:21 as build-stage

# 작업 디렉토리 설정
WORKDIR /app

# package.json 복사 및 의존성 설치
COPY package*.json ./

# 의존성 설치
RUN npm install

# 소스코드 복사
COPY . .

ARG VITE_BACKEND_URL
ENV VITE_BACKEND_URL=$VITE_BACKEND_URL

# 빌드
RUN npm run build

# 배포 단계
FROM nginx:stable-alpine as production-stage

# Nginx 설정 파일 복사
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Nginx 설정 파일 복사
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
#!/bin/bash

# Docker Compose를 사용하여 컨테이너 중지 및 삭제
docker-compose down

# Docker 이미지 삭제 (필요에 따라)
docker rmi client_front:latest

# Docker Compose를 사용하여 컨테이너 실행
docker-compose up -d
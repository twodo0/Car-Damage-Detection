# Car-Damage-Detection

## 목표(한 줄)
웹에서 차량 손상 이미지를 업로드하면 모델 추론 + Heatmap 업로드까지 비동기로 처리하고, 결과(원본/히트맵 URL, 바운딩박스, 클래스 확률)를 돌려주는 Spring Boot 서비스입니다.
내부 스토리지는 MinIO(S3 compatible), 메타데이터는 MySQL, 추론은 FastAPI(or Mock)로 연결됩니다.

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE SCHEMA IF NOT EXISTS hub_schema;

INSERT INTO hub_schema.p_hub (id, name, address, latitude, longitude, status, created_at, updated_at) VALUES
(gen_random_uuid(), '서울특별시 센터', '서울특별시 송파구 송파대로 55', 37.514467, 127.106177, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '경기 북부 센터', '경기도 고양시 덕양구 권율대로 570', 37.6901, 126.8715, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '경기 남부 센터', '경기도 이천시 덕평로 257-21', 37.2345, 127.3888, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '부산광역시 센터', '부산 동구 중앙대로 206', 35.11499, 129.04208, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '대구광역시 센터', '대구 북구 태평로 161', 35.87138, 128.60138, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '인천광역시 센터', '인천 남동구 정각로 29', 37.456133, 126.7062117, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '광주광역시 센터', '광주 서구 내방로 111', 35.1600, 126.8514, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '대전광역시 센터', '대전 서구 둔산로 100', 36.35, 127.385, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '울산광역시 센터', '울산 남구 중앙로 201', 35.53833, 129.31138, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '세종특별자치시 센터', '세종특별자치시 한누리대로 2130', 36.480058, 127.289039, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '강원특별자치도 센터', '강원특별자치도 춘천시 중앙로 1', 37.880072, 127.727908, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '전라남도 센터', '전남 무안군 삼향읍 오룡길 1', 35.027, 126.822, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '충청북도 센터', '충북 청주시 상당구 상당로 82', 36.6358, 127.4913, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '충청남도 센터', '충남 홍성군 홍북읍 충남대로 21', 36.6586, 126.6738, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '전북특별자치도 센터', '전북특별자치도 전주시 완산구 효자로 225', 35.8207, 127.1087, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '경상북도 센터', '경북 안동시 풍천면 도청대로 455', 36.5721, 128.5056, 'OPEN', NOW(), NOW()),
(gen_random_uuid(), '경상남도 센터', '경남 창원시 의창구 중앙대로 300', 35.2380, 128.6923, 'OPEN', NOW(), NOW());
CREATE SCHEMA IF NOT EXISTS company_schema;
COMMENT ON SCHEMA company_schema IS '업체 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS product_schema;
COMMENT ON SCHEMA product_schema IS '상품 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS order_schema;
COMMENT ON SCHEMA order_schema IS '주문 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS hub_schema;
COMMENT ON SCHEMA hub_schema IS '허브 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS user_schema;
COMMENT ON SCHEMA user_schema IS '사용자 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS auth_schema;
COMMENT ON SCHEMA auth_schema IS '인증 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS delivery_schema;
COMMENT ON SCHEMA delivery_schema IS '배송 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS ai_schema;
COMMENT ON SCHEMA ai_schema IS 'AI 서비스 스키마';

CREATE SCHEMA IF NOT EXISTS alarm_schema;
COMMENT ON SCHEMA alarm_schema IS '알림 서비스 스키마';
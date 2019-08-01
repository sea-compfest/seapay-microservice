SEAPAY_VERSION=0.0.1
DB_NAME="sea_pay_dev"
TEST_DB_NAME="sea_pay_dev_test"
DB_PORT=5432
TEST_DB_PORT=5432

all: clean db-setup testdb-setup build test

db-setup: db-create db-migrate

db-drop:
	dropdb -p $(DB_PORT) --if-exists -Upostgres $(DB_NAME)

db-create:
	createdb -p $(DB_PORT) -Opostgres -Eutf8 $(DB_NAME)

db-migrate:
	./gradlew migrateDb

testdb-setup: testdb-create testdb-migrate

testdb-create: testdb-drop
	createdb  -p $(TEST_DB_PORT) -Opostgres -Eutf8 $(TEST_DB_NAME)

testdb-migrate:
	APP_ENVIRONMENT=test ./gradlew migrateTestDb

testdb-drop:
	dropdb -p $(TEST_DB_PORT) --if-exists -Upostgres $(TEST_DB_NAME)

.PHONY: test
test:
	APP_ENVIRONMENT=TEST ./gradlew test

build:
	./gradlew build

clean:
	./gradlew clean

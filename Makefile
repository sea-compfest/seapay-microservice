SHELL=/bin/bash
SEAPAY_VERSION=0.0.1

all: clean db-setup build test

db-setup: db-create db-migrate

db-drop:
	dropdb -h ${DB_HOST} -p ${DB_PORT} --if-exists -Upostgres ${DB_NAME}

db-create: db-drop
	createdb -h ${DB_HOST} -p ${DB_PORT} -Upostgres -Eutf8 ${DB_NAME}

db-migrate:
	./gradlew migrateDb

run-monolith:
	bash -c "set -a && source ./environtment.monolith.sample && set +a && java -jar ./seapay-monolith/build/libs/seapay-monolith-$(SEAPAY_VERSION)-all.jar"

run-gateway:
	bash -c "set -a && source ./environtment.gateway.sample && set +a && java -jar ./seapay-gateway-service/build/libs/seapay-gateway-service-$(SEAPAY_VERSION)-all.jar"

run-user:
	bash -c "set -a && source ./environtment.user.sample && set +a && java -jar ./seapay-user-service/build/libs/seapay-user-service-$(SEAPAY_VERSION)-all.jar"

run-wallet:
	bash -c "set -a && source ./environtment.wallet.sample && set +a && java -jar ./seapay-wallet-service/build/libs/seapay-wallet-service-$(SEAPAY_VERSION)-all.jar"

run-transaction:
	bash -c "set -a && source ./environtment.transaction.sample && set +a && java -jar ./seapay-transaction-service/build/libs/seapay-transaction-service-$(SEAPAY_VERSION)-all.jar"

.PHONY: test
test:
	./gradlew test check jacocoTestReport coverageReport

test-ci:
	bash -c "set -a && source ci.env && make db-migrate build && ./gradlew test check jacocoTestReport coverageReport"

build:
	./gradlew build

clean:
	./gradlew clean

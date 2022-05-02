build-vehicle-service:
	@cd vehicle-service && ./gradlew ass

docker-vehicle-service: build-vehicle-service
	@docker build -t lreimer/vehicle-service:default --target default -f vehicle-service/src/main/docker/Dockerfile.jvm vehicle-service/
	@docker build -t lreimer/vehicle-service:undo --target undo -f vehicle-service/src/main/docker/Dockerfile.jvm vehicle-service/
	@docker push lreimer/vehicle-service:default
	@docker push lreimer/vehicle-service:undo

build-parts-service:
	@cd parts-service && ./gradlew ass

docker-parts-service: build-parts-service
	@docker build -t lreimer/parts-service:default --target default parts-service/
	@docker build -t lreimer/parts-service:undo --target undo parts-service/
	@docker push lreimer/parts-service:default
	@docker push lreimer/parts-service:undo
DEMO_HOST ?= localhost
VEHICLE_LR4J_HOST ?= localhost
PARTS_LR4J_HOST ?= localhost

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

start-recordings:
	@echo "Start recording"
	@curl -X POST http://$(VEHICLE_LR4J_HOST):18081/api/lr4j
	@curl -X POST http://$(PARTS_LR4J_HOST):18080/api/lr4j
	@sleep 3

save-recordings:
	@echo "Save recordings"
	@curl -X PUT http://$(VEHICLE_LR4J_HOST):18081/api/lr4j/vehicle.undo
	@curl -X PUT http://$(PARTS_LR4J_HOST):18080/api/lr4j/parts.undo

get-recordings:
	@echo "Get recordings"
	@curl -o replay-image/vehicle.undo http://$(VEHICLE_LR4J_HOST):18081/api/lr4j/vehicle.undo
	@curl -o replay-image/parts.undo http://$(PARTS_LR4J_HOST):18080/api/lr4j/parts.undo

stop-recordings:
	@echo "Stop recording"
	@curl -X DELETE http://$(VEHICLE_LR4J_HOST):18081/api/lr4j
	@curl -X DELETE http://$(PARTS_LR4J_HOST):18080/api/lr4j

demo-calls:
	@echo "Getting parts for known VIN17"
	@curl http://$(DEMO_HOST):18080/api/parts?vin17=WVWZZZ1JZ3W386752
	@echo
	@echo "Getting parts for unkown VIN17"
	@curl http://$(DEMO_HOST):18080/api/parts?vin17=WVWZZZ1JZ3W386753
	@echo
	@echo "Getting parts for known VIN17"
	@curl http://$(DEMO_HOST):18080/api/parts?vin17=WVWZZZ1JZ3W386752
	@echo
	@sleep 3

demo-full: start-recording demo-calls save-recordings stop-recordings get-recordings

docker-lr4j-base:
	@docker build -t lreimer/lr4j-replay:base --target base replay-image/

docker-lr4j-parts:
	@docker build -t lreimer/lr4j-replay:parts --target parts replay-image/

docker-lr4j-vehicle:
	@docker build -t lreimer/lr4j-replay:vehicle --target vehicle replay-image/

docker-lr4j-all: docker-lr4j-base docker-lr4j-parts docker-lr4j-vehicle

replay-lr4j-parts:
	@docker run -it --rm -p 9000:9000 lreimer/lr4j-replay:parts

replay-lr4j-vehicle:
	@docker run -it --rm -p 9001:9001 lreimer/lr4j-replay:vehicle

echo "Start recording"
curl -X POST http://localhost:18081/api/lr4j
curl -X POST http://localhost:18080/api/lr4j
sleep 5
echo "Do good call"
curl http://localhost:18080/api/parts?vin17=WVWZZZ1JZ3W386752; echo
echo "Do bad call"
curl http://localhost:18080/api/parts?vin17=WVWZZZ1JZ3W386753; echo
echo "Do another good call"
curl http://localhost:18080/api/parts?vin17=WVWZZZ1JZ3W386752; echo
sleep 5
echo "Save recordings"
curl -X PUT http://localhost:18081/api/lr4j/vehicle.undo
curl -X PUT http://localhost:18080/api/lr4j/parts.undo
echo "Get recordings"
curl http://localhost:18081/api/lr4j/vehicle.undo > vehicle.undo
curl http://localhost:18080/api/lr4j/parts.undo > parts.undo

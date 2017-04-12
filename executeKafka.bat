start %~dp0kafka_2.11-0.10.2.0\bin\windows\zookeeper-server-start.bat %~dp0kafka_2.11-0.10.2.0\config\zookeeper.properties
cmd start /K %~dp0kafka_2.11-0.10.2.0\bin\windows\kafka-server-start.bat %~dp0kafka_2.11-0.10.2.0\config\server.properties
timeout 5
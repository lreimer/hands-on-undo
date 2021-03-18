FROM qaware/zulu-centos-payara-micro:11.0.9-5.2020.5

COPY build/libs/hands-on-undo.war /opt/payara/deployments/
COPY libs/lr4j-record-1.0.so $PAYARA_PATH

ENTRYPOINT ["java", "-agentpath:/opt/payara/lr4j-record-1.0.so", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-XX:ThreadStackSize=256", "-XX:MaxMetaspaceSize=128m", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-jar", "/opt/payara/payara-micro.jar"]
CMD ["--nocluster", "--disablephonehome", "--deploymentDir", "/opt/payara/deployments"]

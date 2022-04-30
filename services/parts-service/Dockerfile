FROM qaware/zulu-centos-payara-micro:11.0.9-5.2020.5

RUN mkdir $PAYARA_PATH/replay
ENV LR4J_DIRECTORY=$PAYARA_PATH/replay

ENTRYPOINT ["java", "-agentpath:/opt/payara/lr4j-record-1.0.so", "-XX:-Inline", "-XX:TieredStopAtLevel=1", "-XX:UseAVX=2", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-XX:ThreadStackSize=256", "-XX:MaxMetaspaceSize=128m", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-jar", "/opt/payara/payara-micro.jar"]
CMD ["--nocluster", "--disablephonehome", "--deploymentDir", "/opt/payara/deployments"]

# we use a volume to mount the replay/ directory into the container
# COPY replay/ $LR4J_DIRECTORY

COPY libs/lr4j-record-1.0.so $PAYARA_PATH
COPY build/libs/parts-service.war $DEPLOY_DIR

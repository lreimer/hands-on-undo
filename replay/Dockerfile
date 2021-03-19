FROM qaware/zulu-centos-payara-micro:11.0.9-5.2020.5

USER root

RUN mkdir -p /opt/lr4j && yum -y install unzip
WORKDIR /opt/

COPY lr4j-replay-6.3.1.160-6.3-b0709c71-openjdk11.zip .
RUN unzip lr4j-replay-6.3.1.160-6.3-b0709c71-openjdk11.zip

WORKDIR /opt/lr4j
ENTRYPOINT [ "./lr4j_replay"]
CMD ["-i", "test.undo", "-p", "9000"]

COPY test.undo .
FROM azul/zulu-openjdk-centos:11.0.12 as base

EXPOSE 9000

RUN mkdir /opt/undo/
COPY lr4j/ /opt/undo/lr4j

WORKDIR /opt/undo
CMD [ "/opt/undo/lr4j/lr4j_replay" ]

FROM base as parts
CMD [ "/opt/undo/lr4j/lr4j_replay", "-p", "9000" , "-i", "/opt/undo/parts.undo" ]
COPY parts.undo /opt/undo/

FROM base as vehicle
CMD [ "/opt/undo/lr4j/lr4j_replay", "-p", "9001" , "-i", "/opt/undo/vehicle.undo" ]
COPY vehicle.undo /opt/undo/
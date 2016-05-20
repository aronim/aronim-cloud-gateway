FROM hub.aronim.com/java:8

VOLUME /tmp

ADD kungfudev-cloud-gateway-1.0-SNAPSHOT.jar app.jar

RUN bash -c 'touch /data/app.jar'

ENTRYPOINT exec java \
    -Xmx64m -Xms64m \
    -Deureka.instance.hostname=$HOST
    -Deureka.instance.nonSecurePort=$PORT0
    -Djava.security.egd=file:/dev/./urandom \
    -Dspring.cloud.config.uri=http://ac-configuration.marathon.mesos:8888 \
    -Dspring.profiles.active=marathon \
    -jar /data/app.jar
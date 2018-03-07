FROM registry.cn-hangzhou.aliyuncs.com/hc/jdk8
ADD user-0.0.1-SNAPSHOT.jar demo.jar
RUN bash -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","demo.jar"]
FROM openjdk:17

WORKDIR /root/employee

COPY * /root/employee

RUN javac -d . *.java

ENV CLASSPATH .:./postgresql-42.7.5.jar

ENTRYPOINT java Assignment_29012025.EmployeeManagement
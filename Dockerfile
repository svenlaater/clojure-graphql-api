FROM clojure:alpine
MAINTAINER Sven Laater <sven.laater@gmail.com>
LABEL Description="Clojure GraphQL API"

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app-standalone.jar

EXPOSE 8888

CMD ["java", "-jar", "app-standalone.jar"]
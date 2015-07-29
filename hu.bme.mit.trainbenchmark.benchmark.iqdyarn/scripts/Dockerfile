FROM n3ziniuka5/ubuntu-oracle-jdk:14.04-JDK7

MAINTAINER IncQuery-D Team

USER root

# install tools
RUN apt-get update && apt-get -y install curl tar sudo openssh-server openssh-client rsync nano telnet autossh

# passwordless ssh
RUN ssh-keygen -q -N "" -t rsa -f /root/.ssh/id_rsa
RUN cp /root/.ssh/id_rsa.pub /root/.ssh/authorized_keys
RUN cp /etc/ssh/ssh_host_rsa_key.pub /root/.ssh/known_hosts

# JAVA_HOME setup
ENV JAVA_HOME /usr/lib/jvm/java-7-oracle
ENV PATH $PATH:$JAVA_HOME/bin

ADD write-auth-keys.sh /etc/write-auth-keys.sh
RUN chown root:root /etc/write-auth-keys.sh
RUN chmod 700 /etc/write-auth-keys.sh

RUN mkdir -p /usr/local/trainbenchmark/runtime
RUN mkdir -p /usr/local/trainbenchmark/models
RUN mkdir -p /usr/local/trainbenchmark/results/json/

ADD run_benchmark.sh /usr/local/trainbenchmark/runtime/run_benchmark.sh
RUN chown root:root /usr/local/trainbenchmark/runtime/run_benchmark.sh
RUN chmod 700 /usr/local/trainbenchmark/runtime/run_benchmark.sh

ADD iqdyarn-tb.tar.gz /usr/local/trainbenchmark/runtime/

EXPOSE 22

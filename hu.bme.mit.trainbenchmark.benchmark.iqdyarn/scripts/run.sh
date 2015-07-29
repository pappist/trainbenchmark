#!/bin/bash

set -e
cd "$( cd "$( dirname "$0" )" && pwd )"

source profile.sh

cd ../target
tar -cvf ../scripts/$TMP_PACK_NAME hu.bme.mit.trainbenchmark.benchmark.iqdyarn-1.0.0-SNAPSHOT.jar lib 

cd ../scripts
docker build -t $TB_IMAGE .

rm $TMP_PACK_NAME

if [ "$1" = "-with_weave" ]; then
	weave run $TB_WEAVE_IP/24 --dns 10.0.0.100 --name $TB_NAME --hostname $TB_HOSTNAME -i -t -d $TB_IMAGE
else
	docker run --name $TB_NAME --hostname $TB_HOSTNAME -i -t -d $TB_IMAGE
fi

TB_IP=$(docker inspect --format="{{.NetworkSettings.IPAddress}}" $TB_NAME)

sudo sed -i "/$TB_HOSTNAME/d" /etc/hosts
sudo printf $TB_IP' '$TB_HOSTNAME'\n' >> /etc/hosts

HOST_RSA=$(cat ~/.ssh/id_rsa.pub)
docker exec $TB_NAME /etc/write-auth-keys.sh $HOST_RSA

docker exec $TB_NAME service ssh start

./upload_models.sh
./update_rsa.sh

# docker exec $TB_NAME /usr/local/trainbenchmark/run_benchmark.sh

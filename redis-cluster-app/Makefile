
redis_cluster_create:
	redis-cli --cluster create localhost:7000 localhost:7001 localhost:7002 localhost:7003 localhost:7004 localhost:7005 --cluster-replicas 1

all_chmod_sh:
	chmod 777 ./redis-7000/redis-run-7000.sh
	chmod 777 ./redis-7001/redis-run-7001.sh
	chmod 777 ./redis-7002/redis-run-7002.sh
	chmod 777 ./redis-7003/redis-run-7003.sh
	chmod 777 ./redis-7004/redis-run-7004.sh
	chmod 777 ./redis-7005/redis-run-7005.sh
	chmod 777 ./redis-7005/redis-run-7006.sh
	chmod 777 ./redis-7005/redis-run-7007.sh

redis_install:
	brew install redis


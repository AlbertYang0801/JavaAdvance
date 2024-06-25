source ~/.bash_profile
nohup java -jar rocketmq-dashboard-1.0.1-SNAPSHOT.jar \
--server.port=8081 \
--rocketmq.config.namesrvAddr=10.10.102.83:9876 \
> logs/dashboard.log &
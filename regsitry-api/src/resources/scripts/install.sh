echo 'Installing Science_Gateway_Apex to Maven'
cd '/home/ec2-user/regsitry-api'
mvn -e clean install
mvn exec:java >> /var/log/sga-omega-api-spark.log 2>&1 &
sleep 60

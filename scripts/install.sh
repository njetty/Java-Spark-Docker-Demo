echo 'Running the downloaded container'
docker run -d -p 4567:4567 njetty/registry
sleep 60

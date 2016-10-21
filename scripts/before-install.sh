command_exists() {
        command -v "$@" > /dev/null 2>&1
}
if command_exists docker; then
                version="$(docker -v | awk -F '[ ,]+' '{ print $3 }')"
                MAJOR_W=1
                MINOR_W=10

                cat >&2 <<-'EOF'
                        Info: "docker" command already exists on this system.

                EOF
else
                cat >&2 <<-'EOF'
                        Warning: the "docker" command is not installed
                        Info: Starting the installation of Docker
                EOF
                sudo yum update -y
                sudo yum install -y docker
                sudo service docker start
fi

echo 'Killing any container of the old Docker image'
docker rm $(docker stop $(docker ps -a -q --filter ancestor=njetty/registry --format="{{.ID}}"))

echo 'Pulling a new image from docker'
docker pull njetty/registry
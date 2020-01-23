#!/bin/sh

echo -e "Running mail service......"
exec java -Xms512m -Xmx1024M -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -jar -Dspring.profiles.active="$PROFILE" /docker/moa/war/mail-service.war

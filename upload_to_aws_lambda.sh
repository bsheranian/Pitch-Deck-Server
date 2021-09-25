#!/bin/bash

#   IMPORTANT:
#     - run `mvn package` to update the pitchdeck-server-1.0-SNAPSHOT.jar
#       before updating lambda functions.
#     - run 'aws configure' before executing to make
#       sure you are using the correct credentials.
#   USAGES:
#       ./upload_to_aws_lambda.sh [function1] [function2] ...
#       ./upload_to_aws_lambda.sh -all
#       ./upload_to_aws_lambda.sh (hard code desired functions below)


lamdaFunctions=()

for func in "$@"
do
    lamdaFunctions+=($func)
done

case "$1" in
  -all)
    lamdaFunctions=(
      "createTeamSlide"
      "sendEmail"
    ) ;;
esac

jarFile="target/pitchdeck-server-1.0-SNAPSHOT.jar"
outputFile="output.txt"

for func in "${lamdaFunctions[@]}";
do
  aws lambda update-function-code --function-name "$func" --zip-file fileb://"$jarFile" >> "$outputFile"
done

declare -i numSuccessfulUploads=0

if grep -q "Successful" "$outputFile";
then
  for func in "${lamdaFunctions[@]}";
  do
    if grep -q $func $outputFile;
    then
      numSuccessfulUploads+=1
    fi
  done
fi


if grep -q "Successful" "$outputFile";
then
  echo
  echo "SUCCESSFUL UPLOADS: ($numSuccessfulUploads)"
  for func in "${lamdaFunctions[@]}";
  do
    if grep -q $func $outputFile;
    then
      echo "  - $func"
    fi
  done
fi

echo

rm "$outputFile"
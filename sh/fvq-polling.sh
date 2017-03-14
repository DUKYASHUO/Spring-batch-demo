#!/bin/sh

# trap termination signal
########################################
trap "stop_polling" 2 15


# polling
########################################
stop_flg=0
sleep_time=300
while :
do
   # execute job
   run_job

   # sleep until receiving a termination signal
   sleep $sleep_time &
   wait
   sleep 1
   if [ $stop_flg -eq 1 ]; then
     echo "terminate the fvq polling."
     exit 0
   fi
done
exit 0

########################################
# stop function
########################################
function stop_polling() {
  echo "received teminate signal."
  stop_flg=1
}

########################################
# run the job 
########################################
function run_job() {
  fvq_home=/home/ec2-user/FVQ
  lib=$fvq_home/lib
  logs=$fvq_home/logs
  target=$fvq_home/target/fvq.jar
  job_runner=org.springframework.batch.core.launch.support.CommandLineJobRunner

  java -cp $target:$lib/* $job_runner classpath:/batch-context.xml FVQ_MagiLinkJob >> $logs/fvq-magi-link-job.log 2>&1
}
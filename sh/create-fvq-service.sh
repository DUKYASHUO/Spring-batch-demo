#!/bin/sh
cd ../target
java -cp spring-batch.jar:./lib/* org.springframework.batch.core.launch.support.CommandLineJobRunner classpath:/batch-context.xml FVQ_MagiLinkJob >> ../bash/fvq-magi-link-job.log 2>&1
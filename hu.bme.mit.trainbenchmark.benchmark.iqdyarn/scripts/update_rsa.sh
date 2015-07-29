#!/bin/bash

scp root@yarn-rm.docker:~/.ssh/id_rsa* .
scp id_rsa* root@iqdyarn-tb.docker:~/.ssh/
rm id_rsa*

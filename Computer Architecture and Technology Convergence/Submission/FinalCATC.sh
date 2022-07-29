#!/bin/bash -i

touch /home/lucmcauley/Tutorial/lucMcAuley.txt


# for the opening paragraph


introduction () {

echo 
echo this is the output for the following commands for the CATC course
echo

}

introduction >> /home/lucmcauley/Tutorial/lucMcAuley.txt

# used to append each command to the file

dividor () {
	echo
	echo
	echo
	echo ------------------------------
	echo output  for $1
	echo ------------------------------
	echo
	$1
}

# run each command through the dividor function

dividor date >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor hostname >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor arch >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor "uname -a" >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor uptime >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor whoami >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor who >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor finger >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor w >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor "top -bn1" >> /home/lucmcauley/Tutorial/lucMcAuley.txt

dividor history >> /home/lucmcauley/Tutorial/lucMcAuley.txt

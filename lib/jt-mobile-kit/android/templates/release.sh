#!/bin/bash -e
export TERM=xterm #rvm is designed to run in a terminal where the TERM environment variable is set
export LANG=en_US.UTF-8

[[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm"

rvm use 1.9.3-p286@moode-sms --create
bundle install

cd assets/www/
echo "render html...."
ruby index_render.rb

echo "build apk"
cd ../../
ant release

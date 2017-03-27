#!/bin/bash

rm -rf apidoc

rm -rf resources
mkdir resources

for file in `find ../ -name *Resource.java`
do
    cp $file resources
done

apidoc -i resources/ -o apidoc/ -f ".*\\Resource.java$" --encoding utf-8
#!/bin/bash
# StepConter

echo "Enter the path of the directory containing the files: "
read ProjectName


xml_file="C:/AndroidProject/$ProjectName/app/src/main/res/layout/activity_main.xml"


# Read the XML content from the file
xml_content=$(cat "$xml_file")

# Extract the second tag name using regex
if [[ $xml_content =~ \<([^\s>]+) ]]; then
    first_tag="${BASH_REMATCH[1]}"
    if [[ $xml_content =~ \<([^\s>]+) ]]; then
        second_tag="${BASH_REMATCH[1]}"
        echo "Second Tag name: $second_tag"
    else
        echo "Second Tag name not found."
    fi
else
    echo "First Tag name not found."
fi

# Check if the attribute is present in the root tag
if grep -q 'xmlns:app="http://schemas.android.com/apk/res-auto"' "$xml_file"; then
    echo "Attribute already exists in the root tag."
else
    # Add the attribute to the root tag
    sed -i '2i\<root_tag_name xmlns:app="http://schemas.android.com/apk/res-auto">' "$xml_file"
    echo "Attribute added to the root tag."
fi



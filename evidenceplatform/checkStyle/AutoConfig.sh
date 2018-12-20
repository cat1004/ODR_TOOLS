#!/bin/sh

CURRENT_DIR=`pwd`
PARENT="$(dirname "$CURRENT_DIR")"

GIT_DIRECTORY="$PARENT/.git"
while [ ! -d "$GIT_DIRECTORY" ]; do
	PARENT="$(dirname "$PARENT")"
	GIT_DIRECTORY="$PARENT/.git"
done

echo $GIT_DIRECTORY

HOOK_DIR="$GIT_DIRECTORY/hooks/"

cp $CURRENT_DIR/pre-commit $HOOK_DIR

git config checkstyle.checkfile $CURRENT_DIR/check-style.xml
git config checkstyle.jar $CURRENT_DIR/checkstyle-8.9-all.jar
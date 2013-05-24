DIR='./'
count=`git rev-list HEAD | wc -l | sed -e 's/ *//g' | xargs -n1 printf %04d`
commit=`git show --abbrev-commit HEAD | grep '^commit' | sed -e 's/commit //'`
buildno=b"$count.$commit"

echo $buildno > "${DIR}/version.txt"

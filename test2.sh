#获取HEAD文件里面内容的方法，记住，shell脚本不可以返回String
#所以，里面不可以有其他的echo,只能最好输出的时候有echo
#这个方法输出的是：当前分支的head code
function getHeadHashCode(){
	headFileName='HEAD'
	hashCode=''
	if [[ -f "$headFileName" ]]; then
		#读取HEAD文件
		for line in $(cat "$headFileName"); do
			# echo $line
			hashCode="$line"
		done
	fi
	echo "$hashCode"	
}

#这个方法返回的是根据headcode 读取到的FEACH_HEAD 行信息，他必须给予getBranchName处理
function readBranchInfoFromFile(){
	# echo "读取到参数：$1"
	branchFileName='FETCH_HEAD'
	branchInfoLine=''
	if [[ -f "$branchFileName" ]]; then
		#读取HEAD文件
		num=0
		dNum=0	
		for line in $(cat "$branchFileName"); do
			# echo "第${num}行：$line"
			result=$(echo $line | grep "$1")
			# echo "匹配结果：$result"
			if [[ "$result" != "" ]]; then
				#statements
				let dNum=num+2
			fi

			if [[ $dNum == $num && $dNum != 0 ]]; then
				# echo "匹配到第 $dNum 行是分支名"
				# echo "分支名是：$line"
				branchInfoLine=$line
				break
			fi

			let num=num+1
		done
	fi
	echo "*$branchInfoLine"
}

#通过获取到的行信息提取出branchName
function getBranchName(){
	info=`readBranchInfoFromFile $1 | grep \*`
	info=${info:2}
	let endindex=${#info}-1
	info=${info:0:$endindex}
	echo $info
}

#获取到branchName
echo "======================BEGIN 获取到branchName=============="
#这里写死代码文件夹的路径
cd './'
echo "进入到目录：$(pwd)"

hasGitDir=false
gitDir=".git"
branchName=''

if [[ -d $gitDir ]]; then
	cd "$gitDir"
	echo "进入到目录：$(pwd)"
	hasGitDir=true
else
	echo "错误，找不到 .git 文件"
fi

if [[ "$hasGitDir" = true ]]; then
	echo "在.git目录中"
	headCode=$(getHeadHashCode)
	echo "headCode是：$headCode"

	branchName=`getBranchName $headCode`
	echo "方法打印结果：$branchName"
else
	echo "没有在.git目录中"
fi
echo "======================END 获取到branchName=============="

echo "======================BEGIN 回桌面创建文件夹=============="

cd '/Users/cestbon/Desktop'
echo "获得的目标地址是：$(pwd)"

#创建主文件夹
sourceDir='TestApk'
if [ ! -d "$sourceDir" ];then
	mkdir $sourceDir
else
	echo "dir ${sourceDir} exit!"
fi
cd "$sourceDir"

#根据分支名创建文件夹
if [ -z "$branchName" ]; then 
    echo "branchName 是空的" 
    exit
fi

if [[ ! -d $branchName ]]; then
	mkdir $branchName
	cd $branchName
	echo "创建并进入文件夹：$(pwd)"
else
	cd $branchName
	echo "文件夹${branchName}已存在，进入：$(pwd)"
fi

#根据时间戳创建文件夹
time=$(date +"%Y%m%d_%H%M%S")
echo "打印时间：$time"
if [[ ! -d $time ]]; then
	mkdir $time
	cd $time
	echo "创建并进入文件夹：$(pwd)"
else
	cd $time
	echo "文件夹$(time)已存在，进入：$(pwd)"
fi

echo "======================END 回桌面创建文件夹=============="

echo "======================BEGIN 进入目标路径进行复制=============="
cd ~
cpSourceDir='/Users/cestbon/.jenkins/workspace/app/build/outputs/apk'
cp -r -f "${cpSourceDir}" "/Users/cestbon/Desktop/SaleHelperApk/${branchName}/${time}"
echo "======================END 进入目标路径进行复制=============="


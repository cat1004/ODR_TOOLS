### 版本迭代规范

1. db变更文件目录、文件名规范

```
changelogs
├── change.xml
├── version1.0.0
│   ├── User.table.xml
│   ├── data
│   │   ├── prod
│   │   │   ├── User.data.prod.xml
│   │   │   └── data.change.xml
│   │   └── test
│   │       ├── User.data.test.xml
│   │       └── data.change.xml
│   └── table.change.xml
└── version1.0.1

```
	
2. 提交变更文件约定
   
   原则上为push到远程仓库后不允许再次修改已有changeset，如有需修改，按新增changeset方式处理
		
### 变更数据库命令

1. 清库命令 

>mvn liquibase:dropAll -Pdev   
*注:-P指定数据库环境，如果不带-P参数 则默认更新dev环境

2. 更新数据库

>mvn liquibase:update -Pdev   
*注:-P指定数据库环境，如果不带-P参数 则默认更新dev环境


### changeset简要说明

1. 参数说明

 参数名 				|  是否必填	| 	描述
 -------------------|-----------|---------------------------
 id 				|	 是		| 	每次变更的标识，允许字母和数字
 author 			|	 是		| 	变更人
 dbms 				|	 否		| 	[数据库类型](https://www.liquibase.org/databases.html)
 runAlways 			|	 否		| 	每次执行更新命令，都会执行该changeset，默认false
 runOnChange 		|	 否		| 	执行更新命令时，如检测到changeset内容有变更，则重新执行，默认false
 context 			|	 否		| 	changeset对应的上下文环境(测试、生产等)
 runInTransaction 	|	 否		| 	是否使用事务，默认为true
 failOnError 		|	 否		| 	执行失败时，是否中断更新数据库，默认为true

2. 子标签

 标签名				|	描述
 -------------------|---------------------------
 comment			| 	变更描述信息
 preConditions		|	执行变更前先验证
 rollback			|	回滚操作

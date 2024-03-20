updated on 21/08/14

test.cpp 为排座位源码，data.txt 为当前座位数据库，共32行，每行都是从左到右从前到后的顺序座位号上的同学姓名，最后一行为占位名，座位表在 seating.txt，test.o 为编译文件，可以忽略。

data.txt 内从上到下输入当前学生每个座位号所对应姓名，按照 seating.txt 内从左到右第一行为1~5号，第二行为6~10号的顺序，依此类推。

windows 用户：点开 test.exe，再打开 seating.txt，即可获得座位表

macOS 用户：点开 test，再打开 seating.txt，即可获得座位表

------

操作方法：

先用现有座位表的 data.txt 替换这个文件夹里的 data.txt，

然后运行 test.exe，再打开 seating.txt 就是随机换一次座位之后的座位表。

test.exe 可能运行之后一闪而过，这不是什么问题，如果真要想确认是否更改座位表，可以运行前记住当前 seating.txt 的布局，运行 test.exe 一次后再打开 seating.txt，座位表应该会变化。

理论上运行一次之后 seating.txt 和 data.txt 都会自动更改成当前座位情况，所以建议备份每次座位表的 data.txt，如果不满意这次的座位分布，就只能用备份的 data.txt 替换当前 data.txt。


串行GC java -XX:+PrintGCDetails -XX:+UseSerialGC -Xmx1024m GCLogAnalysis
生成对象2434次 YGC平均时长在0.05秒左右  FGC平均时长在0.06秒左右

并行GCjava -XX:+PrintGCDetails -XX:+UseParallelGC -Xmx1024m GCLogAnalysis
生成对象6393次 YGC平均时长0.03秒左右 FGC时长0.05秒左右

CMSGC java -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m  GCLogAnalysis
生成对象7300左右 YGC平均时间0.02秒左右 FGC平均时长0.06秒左右

G1GC java -XX:+PrintGCDetails -XX:+UseG1GC -Xmx512m -Xms512m  GCLogAnalysis
生成对象8000左右 YGC平均时长0.004秒 FGC平均时长0.01秒左右
在本机环境下可以看出GC的性能是G1>CMS>并行>串行

压力测试
并行GC java -jar -Xmx1024m -Xms1024m gateway-server-0.0.1-SNAPSHOT.jar
最大时长392ms  每秒请求数6150 每秒数据量750KB
串行GC java -jar -XX:+UseSerialGC -Xmx1024m -Xms1024m  gateway-server-0.0.1-SNAPSHOT.jar
最大时长363ms 每秒请求数8000 每秒数据量950KB
G1GC java -jar -XX:+UseG1GC -Xmx1024m -Xms1024m  gateway-server-0.0.1-SNAPSHOT.jar
最大时长524ms  每秒请求数6632 每秒数据量810KB

本地测试居然是串行GC的数据最好，G1GC对于运行的内存的要求比较高，本地无法测试出真实数据
开发环境上我测试过并行GC以及G1GC，G1GC的反应速度是并行GC的几倍，但是会存在一个问题，
G1GC运行后CPU使用率会稍微提升。
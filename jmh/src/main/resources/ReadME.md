JMH，即Java Microbenchmark Harness，这是专门用于进行代码的微基准测试的一套工具API。
 
JMH 是一个由 OpenJDK/Oracle 里面那群开发了 Java 编译器的大牛们所开发的 Micro Benchmark Framework 。何谓 Micro Benchmark 呢？简单地说就是在 method 层面上的 benchmark，精度可以精确到微秒级。可以看出 JMH 主要使用在当你已经找出了热点函数，而需要对热点函数进行进一步的优化时，就可以使用 JMH 对优化的效果进行定量的分析。

比较典型的使用场景还有：
* 当你已经找出了热点函数，而需要对热点函数进行进一步的优化时，就可以使用 JMH 对优化的效果进行定量的分析。
* 想定量地知道某个函数需要执行多长时间，以及执行时间和输入 n 的相关性
* 一个函数有两种不同实现（例如实现 A 使用了 FixedThreadPool，实现 B 使用了 ForkJoinPool），不知道哪种实现性能更好

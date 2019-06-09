package benchmarking;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarking for the concatenation of strings.
 *
 * Tested methods:
 * - "concatString" = String.concat()
 * - "plainString" = String + String
 * - "regularStringBuffer" = StringBuffer.append()
 * - "regularStringBuilder" = StringBuilder.append()
 *
 * Results for short strings (l < 10):
 * Benchmark                                     Mode  Cnt   Score   Error   Units
 * StringBuilderBenchmark.concatString          thrpt   10   0.056 ± 0.003  ops/ms
 * StringBuilderBenchmark.plainString           thrpt   10   0.054 ± 0.002  ops/ms
 * StringBuilderBenchmark.regularStringBuffer   thrpt   10  11.158 ± 0.699  ops/ms
 * StringBuilderBenchmark.regularStringBuilder  thrpt   10  11.333 ± 0.269  ops/ms
 *
 * Results for medium strings (10 < l < 100):
 * Benchmark                                     Mode  Cnt  Score    Error   Units
 * StringBuilderBenchmark.concatString          thrpt   10  0.011 ±  0.001  ops/ms
 * StringBuilderBenchmark.plainString           thrpt   10  0.009 ±  0.005  ops/ms
 * StringBuilderBenchmark.regularStringBuffer   thrpt   10  4.189 ±  0.123  ops/ms
 * StringBuilderBenchmark.regularStringBuilder  thrpt   10  4.159 ±  0.109  ops/ms
 *
 * Results for long strings (l > 100):
 * Benchmark                                     Mode  Cnt  Score    Error   Units
 * StringBuilderBenchmark.concatString          thrpt   10  0.005 ±  0.001  ops/ms
 * StringBuilderBenchmark.plainString           thrpt   10  0.003 ±  0.003  ops/ms
 * StringBuilderBenchmark.regularStringBuffer   thrpt   10  2.701 ±  0.130  ops/ms
 * StringBuilderBenchmark.regularStringBuilder  thrpt   10  2.800 ±  0.057  ops/ms
 *
 * @author Samuel Klein
 */
public class StringBuilderBenchmark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan  {
        public List<String> names;

        @Setup
        public void setUp(){
            names = new ArrayList<>();
            try {
                // read file with first names into list
                String path = StringBuilderBenchmark.class.getClassLoader().getResource("first-names.txt").getPath();
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;
                List<String> tmp = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    tmp.add(line);
                }
                br.close();
                // modify the size of the list
                for (int i = 0; i < 1; i++) {
                    for (String s : tmp) {
                        String t = "";
                        // modify the length of the strings
                        for (int j = 0; j < 1; j++) {
                            t = t.concat(s);
                        }
                        names.add(t);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/*
    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public String plainString(ExecutionPlan plan){
        String toAdd = "";
        for(String s : plan.names){
            toAdd = toAdd + s;
        }
        return toAdd;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public String concatString(ExecutionPlan plan){
        String toAdd = "";
        for(String s : plan.names){
            toAdd = toAdd.concat(s);
        }
        return toAdd;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public String regularStringBuilder(ExecutionPlan plan){
        StringBuilder sb = new StringBuilder();
        for(String s : plan.names){
            sb.append(s);
        }
        return sb.toString();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public String regularStringBuffer(ExecutionPlan plan){
        StringBuffer sb = new StringBuffer();
        for(String s : plan.names) {
            sb.append(s);
        }
        return sb.toString();
    }
*/
}
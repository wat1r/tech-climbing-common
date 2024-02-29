package spark;


import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.*;

public class SparkChargePoints {

    final static String input = "data/input/electric-chargepoints-2017.csv";
    //    final static String input = "D:\\Dev\\Data\\input\\electric-chargepoints-2017.csv";
    final static String output = "data/output/chargepoints-2017-analysis";

    final static SparkSession spark = SparkSession.builder()
            .master("local[1]")
            .appName("SparkChargePoints")
            .getOrCreate();

    public static void main(String[] args) {
        load(transform(extract()));
    }

    static Dataset<Row> extract() {
        Dataset<Row> result = spark.read()
                .format("csv").option("header", "true")
                .csv(input);
        return result;
    }

    static Dataset<Row> transform(Dataset<Row> df) {
//        df = df.groupBy("CPID").agg(max("PluginDuration").alias("max_duration"),
//                avg("PluginDuration").alias("avg_duration"))
//                .withColumnRenamed("CPID", "chargepoint_id")
//                .select("chargepoint_id",
//                        round(new Column("max_duration"), 2).alias("max_duration"),
//                        round(new Column("avg_duration"), 2).alias("avg_duration"))
//                .drop_duplicates();
        return df;
    }

    static void load(Dataset<Row> df) {
        df.coalesce(1).write().format("parquet")
                .mode("overwrite").save(output);
    }

}


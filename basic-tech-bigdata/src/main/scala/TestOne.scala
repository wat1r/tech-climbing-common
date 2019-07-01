import org.apache.spark.sql.SparkSession

/**
  *
  * Created by FrankCooper
  * Date 2019/7/1 20:50
  * Description
  */
object TestOne {


  val spark = SparkSession.builder.master("local").appName("HdfsTest").getOrCreate()


  def main(args: Array[String]): Unit = {
    val words = Array("one", "two", "two", "three", "three", "three")

    val sc = spark.sparkContext
    val wordPairsRDD = sc.parallelize(words).map(word => (word, 1))
    val rdd1 = wordPairsRDD.reduceByKey(_ + _).collect()
    println("*" * 100)
    println(rdd1.toList)
    println("*" * 100)
    val rdd2 = wordPairsRDD.groupByKey().map(t => (t._1, t._2.sum)).collect()
    println("=" * 100)
    println(rdd2.toList)
    println("=" * 100)
  }

}

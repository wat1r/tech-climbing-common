import org.apache.spark.sql.SparkSession

/**
  *
  * Created by FrankCooper
  * Date 2019/7/1 20:50
  * Description
  */
object TestOne {


  val spark = SparkSession.builder.master("local").appName("HdfsTest").getOrCreate()
  val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {
    //    diff_between_reduce_and_group()
    rdd_operation_one()


  }

  def rdd_operation_one(): Unit = {
    val lines = sc.textFile("test.txt")
    val words = lines.flatMap(line => line.split(" "))
    val wordOne = words.map(word => (word, 1))
    val wordCount = wordOne.reduceByKey(_ + _, 3)
//    wordCount.foreach(println)
//    val resultArr = wordCount.collect()
//    println(wordCount.toList)
    println(wordOne)
  }


  /**
    * reduceByKey与groupByKey的不同点比较
    */
  def diff_between_reduce_and_group(): Unit = {
    val words = Array("one", "two", "two", "three", "three", "three")

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

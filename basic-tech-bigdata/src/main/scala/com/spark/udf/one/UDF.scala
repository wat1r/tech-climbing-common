package com.spark.udf.one

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  * Created by FrankCooper
  * Date 2019/7/2 21:44
  * Description
  */
object UDF {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("UDF")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 构造模拟数据
    val names = Array("Leo", "Marry", "Jack", "Tom")
    val namesRDD = sc.parallelize(names, 5)
    val namesRowRDD = namesRDD.map { name => Row(name) }
    val structType = StructType(Array(StructField("name", StringType, true)))
    val namesDF = sqlContext.createDataFrame(namesRowRDD, structType)

    // 注册一张names表
    namesDF.registerTempTable("names")

    // 定义和注册自定义函数
    // 定义函数：自己写匿名函数
    // 注册函数：SQLContext.udf.register()
    sqlContext.udf.register("strLen", (str: String) => str.length())

    // 使用自定义函数
    sqlContext.sql("select name,strLen(name) from names")
      .collect()
      .foreach(println)

    /**
      * [Leo,3]
      * [Marry,5]
      * [Jack,4]
      * [Tom,3]
      */
  }
}

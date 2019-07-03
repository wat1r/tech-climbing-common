package com.spark.rdd

import org.apache.spark.sql.SparkSession

/**
  *
  * Created by FrankCooper
  * Date 2019/7/3 20:42
  * Description
  */
object RDDOne {
  val spark = SparkSession.builder.master("local").appName("TestOne").getOrCreate()
  val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {

    diffBetweenMapAndFlatmap()
  }


  /**
    * map 与 flatmap的区别
    */
  def diffBetweenMapAndFlatmap(): Unit = {

    val rdd = sc.parallelize(List("coffee panda", "happy panda", "happiest panda party"))
    rdd.map(x => x).collect.foreach(line => println(line))

    println("###" * 100)


    var li = List(1, 2, 3, 4)
    var res = li.flatMap(x => x match {
      case 3 => List(3.1, 3.2)
      case _ => List(x * 2)
    })
    println(res)

    li = List(1, 2, 3, 4)
    var res2 = li.map(x => x match {
      case 3 => List(3.1, 3.2)
      case _ => x * 2
    })
    println(res2)
  }

}

package com.spark.rdd

import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ListBuffer

/**
  *
  * Created by FrankCooper
  * Date 2019/10/12 23:28
  * Description
  */
object RDDTwo {
  val spark = SparkSession.builder.master("local").appName("RDDTwo").getOrCreate()
  val sc = spark.sparkContext


  def main(args: Array[String]): Unit = {
    //    mapParations()
    //    mapPartitionsWithIndex()
    //    reduce()
//    reduceByKey()
    groupByKey()
  }

  def mapParations(): Unit = {
    val list = List(1, 2, 3, 4, 5, 6)
    val listRDD = sc.parallelize(list, 2)

    listRDD.mapPartitions(iterator => {
      val newList: ListBuffer[String] = ListBuffer()
      while (iterator.hasNext) {
        newList.append("hello " + iterator.next())
      }
      newList.toIterator
    }).foreach(name => println(name))
  }


  def mapPartitionsWithIndex(): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    sc.parallelize(list).mapPartitionsWithIndex((index, iterator) => {
      val listBuffer: ListBuffer[String] = new ListBuffer
      while (iterator.hasNext) {
        listBuffer.append(index + "_" + iterator.next())
      }
      listBuffer.iterator
    }, true)
      .foreach(println(_))
  }

  def reduce(): Unit = {
    val list = List(1, 2, 3, 4, 5, 6)
    val listRDD = sc.parallelize(list)
    val result = listRDD.reduce((x, y) => x + y)
    println(result)
  }

  def reduceByKey(): Unit = {
    val list = List(("武当", 99), ("少林", 97), ("武当", 89), ("少林", 77))
    val mapRDD = sc.parallelize(list)

    val resultRDD = mapRDD.reduceByKey(_ + _)
    resultRDD.foreach(tuple => println("门派: " + tuple._1 + "->" + tuple._2))
  }

  def groupByKey(): Unit = {
    val list = List(("武当", "张三丰"), ("峨眉", "灭绝师太"), ("武当", "宋青书"), ("峨眉", "周芷若"))
    val listRDD = sc.parallelize(list)
    val groupByKeyRDD = listRDD.groupByKey()
    groupByKeyRDD.foreach(t => {
      val menpai = t._1
      val iterator = t._2.iterator
      var people = ""
      while (iterator.hasNext) people = people + iterator.next + " "
      println("门派:" + menpai + "-->人员:" + people)
    })
  }


}

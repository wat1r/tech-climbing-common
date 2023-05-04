package com.frankcooper.spark.catalyst

import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf

object SparkCatalystTest {

  val conf = new SQLConf
  val parser = new SparkSqlParser(conf)


  def main(args: Array[String]): Unit = {

    val sql= "WITH nv AS (SELECT courseId FROM course_info WHERE courseName = 'Biology') SELECT DISTINCT courseId FROM nv"

//    val plan = parser.parsePlan(sql)


    val structType = parser.parseTableSchema(sql)

    println("======================end======================")

  }
}

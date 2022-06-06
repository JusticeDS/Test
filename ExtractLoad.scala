package com.justice.ike.task

import org.apache.log4j._
import org.apache.spark.sql._

/*Objective: Extract Json files and restructure the files for ingestion into "datalake" storage,
taking advantage of multiple cluster worker nodes, and Scala (Dataset) compile time optimization
for parallel processing.*/

/*Immediate scope: Restructure the json files on local machine and write output files */


object ExtractLoad {


  def main(args: Array[String]): Unit = {

    // Set error logging
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Define Spark Session for local execution (Not on the worker cluster nodes)
    val spark = SparkSession
      .builder
      .appName("ExtractLoad")
      .master("local[*]")
      .getOrCreate()

    print("####### OFAC SECTION ##############\n")
    // Create Ofac json object
    val jsonofac = spark.read.json("data_source/ofac.jsonl")

    // Print Ofac json schema
    jsonofac.printSchema()

    // Create a temporary view.
    jsonofac.createOrReplaceTempView("ofacview")

    // SQL statement to extract some columns
    val ofacDF = spark.sql("SELECT id, name, type FROM ofacview")

    // Show only six records on the screen
    ofacDF.show(6, truncate = false)


    print("####### GBR SECTION ##############\n")
    // Create the json object
    val jsonogbr = spark.read.json("data_source/gbr.jsonl")

    // Print Gbr json schema
    jsonogbr.printSchema()

    // Create a temporary view
    jsonogbr.createOrReplaceTempView("gbrview")

    // SQL statement to extact some columns
    val gbrDF = spark.sql("SELECT id, name, type FROM gbrview")

    // Show few records
    gbrDF.show(6, truncate = false)


    print("####### OFAC and GBR (STRUCTURED) SECTION ##############\n")

    // Union -concatenate ofac and gbr datasets
    val ofacgbr = jsonofac.union(jsonogbr)

    // Creates a temporary view.
    ofacgbr.createOrReplaceTempView("groupview")

    // SQL statement to extract some columns
    val groupDF = spark.sql("SELECT id, name, type FROM groupview")

    // Show few records
    groupDF.show(10, truncate = false)

    // Extract Entity data
    print("####### Entity Data only Section ##############\n")

    // SQL statements can be run by using the sql methods provided by spark
    val entityDF = spark.sql("SELECT id, name, type FROM groupview WHERE type = 'Entity'")

    // Show few records
    entityDF.show(30, truncate = false)

    // Write Entity data to CSV file
    entityDF.write.mode(SaveMode.Overwrite).csv("entity.csv")


    print("####### Individual Data only Section ##############\n")

    // SQL statement to extract few columns
    val indDF = spark.sql("SELECT id, name, type FROM groupview WHERE type = 'Individual'")

    // Show few columns
    indDF.show(30, truncate = false)

    // Write Individual data to CSV file
    indDF.write.mode(SaveMode.Overwrite).csv("individual.csv")


    // Stop spark session
    spark.stop()
  }
}


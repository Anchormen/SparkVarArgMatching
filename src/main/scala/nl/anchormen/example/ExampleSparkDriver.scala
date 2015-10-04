package nl.anchormen.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * A toy example driver that creates 3 RDDs with integers and combines them via a union.
 * 
 * @author Jeroen Vlek <j.vlek@anchormen.nl>
 */
class ExampleSparkDriver(sparkContext : SparkContext) {
  def drive() : Unit = {
    val rdd1 = sparkContext.parallelize(1 to 10)
		val rdd2 = sparkContext.parallelize(1 to 10)
		val rdd3 = sparkContext.parallelize(1 to 10)
		
		val unionRdd = sparkContext.union(rdd1, rdd2, rdd3)
  }
}
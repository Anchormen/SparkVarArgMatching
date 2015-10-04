package nl.anchormen.example

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import org.mockito.Matchers.{eq => mockEq, any}
import org.mockito.Mockito._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException


/**
 * Several test that outline how (and how not) to test the @{SparkContext#union} method.
 * 
 * @author Jeroen Vlek <j.vlek@anchormen.nl>
 */
class ExampleSparkDriverTest extends FlatSpec with Matchers with MockitoSugar {
  "An Example Spark Driver" should "fail this test, because of missing evidence" in {
  	val rdd1, rdd2, rdd3 = mock[RDD[Int]]
  	val sparkContext = mock[SparkContext]
  	when(sparkContext.parallelize(any[Seq[Int]]))
  	    .thenReturn(rdd1)
  	    .thenReturn(rdd2)
  	    .thenReturn(rdd3)
  	    
	  val testSubject = new ExampleSparkDriver(sparkContext)
  	testSubject.drive()
  	
  	verify(sparkContext).union(any[RDD[Int]], mockEq(rdd2), mockEq(rdd3))
  }

  "An Example Spark Driver" should "fail this test, because only testing for mock equality doesn't suffice" in {
    val rdd1, rdd2, rdd3 = mock[RDD[Int]]
    val sparkContext = mock[SparkContext]
    when(sparkContext.parallelize(any[Seq[Int]]))
      .thenReturn(rdd1)
      .thenReturn(rdd2)
      .thenReturn(rdd3)

    val testSubject = new ExampleSparkDriver(sparkContext)
    testSubject.drive()

    import scala.reflect._
    val ct = classTag[Int]
    verify(sparkContext).union(any[RDD[Int]], mockEq(rdd2), mockEq(rdd3))(mockEq(ct))
  }
}
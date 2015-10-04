package nl.anchormen.example

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import org.mockito.Matchers.{ eq => mockEq, any, anyInt, argThat }
import org.mockito.Mockito._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException
import nl.anchormen.test.VarArgMatcher

/**
 * Several test that outline how (and how not) to test the @{SparkContext#union} method.
 *
 * @author Jeroen Vlek <j.vlek@anchormen.nl>
 */
class ExampleSparkDriverTest extends FlatSpec with Matchers with MockitoSugar {
    "An Example Spark Driver" should "fail this test, because of the default parameter for numPartitions" in {
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

  it should "fail this test, because of missing evidence" in {
    val rdd1, rdd2, rdd3 = mock[RDD[Int]]
    val sparkContext = mock[SparkContext]
    
    when(sparkContext.parallelize(any[Seq[Int]], anyInt))
      .thenReturn(rdd1)
      .thenReturn(rdd2)
      .thenReturn(rdd3)
      
    val testSubject = new ExampleSparkDriver(sparkContext)
    testSubject.drive()

    verify(sparkContext).union(any[RDD[Int]], mockEq(rdd2), mockEq(rdd3))
  }

    it should "fail this test, because simply testing for mock equality doesn't suffice" in {
      val rdd1, rdd2, rdd3 = mock[RDD[Int]]
      val sparkContext = mock[SparkContext]
		  import scala.reflect._
		  val ct = classTag[Int]
      when(sparkContext.parallelize(any[Seq[Int]], anyInt)(mockEq(ct)))
        .thenReturn(rdd1)
        .thenReturn(rdd2)
        .thenReturn(rdd3)
  
      val testSubject = new ExampleSparkDriver(sparkContext)
      testSubject.drive()
  
      verify(sparkContext).union(any[RDD[Int]], mockEq(rdd2), mockEq(rdd3))(mockEq(ct))
    }
  
    it should "pass this test, with the custom VarargMatcher" in {
      val rdd1, rdd2, rdd3 = mock[RDD[Int]]
      val sparkContext = mock[SparkContext]
		  import scala.reflect._
		  val ct = classTag[Int]
      when(sparkContext.parallelize(any[Seq[Int]], anyInt)(mockEq(ct)))
        .thenReturn(rdd1)
        .thenReturn(rdd2)
        .thenReturn(rdd3)
  
      val testSubject = new ExampleSparkDriver(sparkContext)
      testSubject.drive()
  
      verify(sparkContext).union(mockEq(rdd1), argThat(new VarArgMatcher(rdd2, rdd3)))(mockEq(ct))
    }
}
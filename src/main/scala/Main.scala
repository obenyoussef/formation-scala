import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
object Main {

  val api = new Api

  //TODO implemente avec les methodes de Api
  def getTextsOfPostsCommentedByUser(userId: Int): Future[Seq[String]] = ???

  def main(args: Array[String]): Unit = {
    val texts = Await.result(getTextsOfPostsCommentedByUser(1), 10.seconds)
    println("Resultats :")
    texts.foreach(println)
  }

}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
object Main {

  val api = new BlogApi

  // TODO passer en français
  // TODO faire versions for-expression
  // TODO écrire les résultats attendus

  // A implémenter en se basant sur les méthode de Api
  def getTextsOfPostsCommentedByUser(userId: Int): Future[Seq[String]] =
    api.getCommentsByUserId(userId).flatMap { userComments =>
      api.getAllPosts.map { posts =>
        val userCommentsIds = userComments.map(_.id)
        val postsCommentedByUser = posts.filter { post =>
          userCommentsIds.contains(post.id)
        }
        postsCommentedByUser.map(_.text)
      }
    }


  def getNameOfUsersWhoCommentedOnPost(postId: Int): Future[Seq[String]] =
    api.getAllUsers().flatMap { users =>
      users.map { user =>

      }
    }

  def main(args: Array[String]): Unit = {
    val texts = Await.result(getTextsOfPostsCommentedByUser(1), 10.seconds)
    println("Resultats :")
    texts.foreach(println)
  }

}

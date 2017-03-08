import Api.{Comment, Post, User}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Api {

  def getAllUsers: Future[Seq[User]] = Future {
    Seq(
      User(1, "Lulu"),
      User(2, "Bibi"),
      User(3, "Tata")
    )
  }

  def getCommentsByUserId(userId: Int): Future[Seq[Comment]] = Future {
    Seq(
      Comment(1, userId = 3, postId = 1, "Comment 1"),
      Comment(2, userId = 3, postId = 2, "Comment 2"),
      Comment(3, userId = 2, postId = 5, "Comment 3"),
      Comment(4, userId = 1, postId = 5, "Comment 4"),
      Comment(5, userId = 1, postId = 4, "Comment 5"),
      Comment(6, userId = 2, postId = 4, "Comment 6"),
      Comment(7, userId = 3, postId = 4, "Comment 7"),
      Comment(8, userId = 1, postId = 4, "Comment 8")
    ).filter(_.userId == userId)
  }

  def getAllPosts: Future[Seq[Post]] = Future {
    Seq(
      Post(1, userId = 1, "Post 1"),
      Post(2, userId = 2, "Post 2"),
      Post(3, userId = 1, "Post 3"),
      Post(4, userId = 3, "Post 4"),
      Post(5, userId = 3, "Post 5"),
      Post(6, userId = 2, "Post 6"),
      Post(7, userId = 2, "Post 7")
    )
  }

}


object Api {

  case class User(id: Int, name: String)

  case class Post(id: Int, userId: Int, text: String)

  case class Comment(id: Int, userId: Int, postId: Int, content: String)

}

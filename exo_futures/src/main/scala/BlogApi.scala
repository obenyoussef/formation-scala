import BlogApi.{Comment, Post, User}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BlogApi {

  def getAllUsers(): Future[Seq[User]] = Future {
    Seq(
      User(1, "Mickey"),
      User(2, "Donald"),
      User(3, "Winnie")
    )
  }

  def getCommentsByUserId(userId: Int): Future[Seq[Comment]] = Future {
    Seq(
      Comment(1, userId = 3, postId = 1, "Text of comment 1"),
      Comment(2, userId = 3, postId = 2, "Text of comment 2"),
      Comment(3, userId = 2, postId = 5, "Text of comment 3"),
      Comment(4, userId = 1, postId = 5, "Text of comment 4"),
      Comment(5, userId = 1, postId = 4, "Text of comment 5"),
      Comment(6, userId = 2, postId = 4, "Text of comment 6"),
      Comment(7, userId = 3, postId = 4, "Text of comment 7"),
      Comment(8, userId = 1, postId = 4, "Text of comment 8")
    ).filter(_.userId == userId)
  }

  def getAllPosts: Future[Seq[Post]] = Future {
    Seq(
      Post(1, userId = 1, "Text of post 1"),
      Post(2, userId = 2, "Text of post 2"),
      Post(3, userId = 1, "Text of post 3"),
      Post(4, userId = 3, "Text of post 4"),
      Post(5, userId = 3, "Text of post 5"),
      Post(6, userId = 2, "Text of post 6"),
      Post(7, userId = 2, "Text of post 7")
    )
  }

}


object BlogApi {

  case class User(id: Int, name: String)

  case class Post(id: Int, userId: Int, text: String)

  case class Comment(id: Int, userId: Int, postId: Int, content: String)

}

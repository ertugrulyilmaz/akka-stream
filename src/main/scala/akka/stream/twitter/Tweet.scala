package akka.stream.twitter

case class Author(name: String)

case class HashTag(name: String) {
  require(name.startsWith("#"), "Hash tag must start with #")
}

case class Tweet(author: Author, body: String) {

  def getHashTags(): Set[HashTag] = {
    body.split(" ").collect {
      case w if w.startsWith("#") => HashTag(w)
    }.toSet
  }

}

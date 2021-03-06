package lila
package security

sealed abstract class Permission(val name: String) {

  val children: List[Permission] = Nil

  final def is(p: Permission): Boolean = 
    this == p || (children exists (_ is p))
}

object Permission {

  case object ViewBlurs extends Permission("ROLE_VIEW_BLURS")
  case object MutePlayer extends Permission("ROLE_CHAT_BAN")
  case object MarkEngine extends Permission("ROLE_ADJUST_CHEATER")

  case object Admin extends Permission("ROLE_ADMIN") {
    override val children = List(ViewBlurs, MutePlayer, MarkEngine)
  }
  case object SuperAdmin extends Permission("ROLE_SUPER_ADMIN") {
    override val children = List(Admin)
  }

  val all: List[Permission] = List(SuperAdmin, Admin)
  val allByName: Map[String, Permission] = all map { p ⇒
    (p.name, p)
  } toMap

  def apply(name: String): Option[Permission] = allByName get name

  def apply(names: List[String]): List[Permission] = (names map apply).flatten
}

package lila
package game

import chess.{ Color, Variant, Status }
import user.User

import com.novus.salat._
import com.novus.salat.dao._
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.Imports._
import org.joda.time.DateTime
import org.scala_tools.time.Imports._

object Query {
  
  val all = DBObject()

  val rated: DBObject = DBObject("isRated" -> true)

  val started: DBObject = ("status" $gte Status.Started.id)

  val playing: DBObject = "updatedAt" $gt (DateTime.now - 15.seconds)

  val mate: DBObject = DBObject("status" -> Status.Mate.id)

  val draw: DBObject = "status" $in List(Status.Draw.id, Status.Stalemate.id)

  val finished: DBObject = "status" $in List(Status.Mate.id, Status.Resign.id, Status.Outoftime.id, Status.Timeout.id)

  def clock(c: Boolean): DBObject = "clock.l" $exists c

  def user(u: User): DBObject = DBObject("userIds" -> u.idString)

  def started(u: User): DBObject = user(u) ++ started

  def rated(u: User): DBObject = user(u) ++ rated

  def win(u: User): DBObject = DBObject("winnerUserId" -> u.idString)

  def draw(u: User): DBObject = user(u) ++ draw

  def loss(u: User): DBObject = user(u) ++ finished ++ ("winnerUserId" $ne u.idString)

  def playing(u: User): DBObject = user(u) ++ playing

  def opponents(u1: User, u2: User) = "userIds" $all List(u1.idString, u2.idString)
}

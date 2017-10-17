package gameoflife.traits

import scala.collection.mutable.Stack

/**
 * CareTaker é o objeto que sabe quando e onde o Originator necessita ser
 * salvo ou restaurado
 */
trait CareTaker {
  var stack = Stack[Memento]();

  def addMemento(mem: Memento): Unit
  def getMemento: Memento
}

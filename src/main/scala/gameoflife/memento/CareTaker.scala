package gameoflife.memento

/**
  * CareTaker é o objeto que sabe quando e onde o Originator necessita ser salvo ou restaurado
  */
trait CareTaker {
  var stack=scala.collection.mutable.Stack[Memento]();
  def addMemento(m:Memento)
  def getMemento():Memento
}

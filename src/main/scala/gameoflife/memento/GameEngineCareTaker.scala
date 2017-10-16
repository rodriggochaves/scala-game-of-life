package gameoflife.memento

abstract class GameEngineCareTaker extends CareTaker {

  def addMemento(m: Memento) = {
    stack.push(m)
  }

  def getMemento(): Memento = {
    stack.pop();
  }
}

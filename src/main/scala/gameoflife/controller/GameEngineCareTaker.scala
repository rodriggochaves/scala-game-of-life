package gameoflife.controller

import gameoflife.controller.CareTaker

object GameEngineCareTaker extends CareTaker {
  def addMemento(m: Memento) = {
    stack.push(m)
  }

  def getMemento(): Memento = {
    stack.pop();
  }
}

package gameoflife.controller

import gameoflife.traits.Memento
import gameoflife.traits.CareTaker

object GameEngineCareTaker extends CareTaker {
  def addMemento(m: Memento) = {
    stack.push(m)
  }

  def getMemento(): Memento =
    stack.pop()
}

package gameoflife.memento

import gameoflife.model.Cell

case class GameEngineOriginator(var cells: Array[Array[Cell]]) extends Originator {
  /**
    * Salva a grid atual no memento
    */
  def save():Memento={
    return new GameEngineMemento(this.cells);
  }
  /**
    * Restaura do memento a grid anterior
    */
  def restore(m:Memento)={
    this.cells=m.cells();
  }
}

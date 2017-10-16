package gameoflife.controller

import gameoflife.traits.Memento
import gameoflife.model.Cell

/**
 * Memento guarda as informações da grid. É escrito e lido pelo Originator, e coordenado pelo Caretaker.
 */
class GameEngineMemento(val from: Array[Array[Cell]]) extends Memento {
  val height = from.length
  val width = from(0).length
  val data = Array.ofDim[Cell](height, width)

  for (i <- 0 until height) {
    for (j <- 0 until width) {
      data(i)(j) = from(i)(j).copy()
    }
  }

  def cells = data;
}

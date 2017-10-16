package gameoflife.controller

import gameoflife.model.Cell

/**
 * Originator é o canal de comunicação com o Memento,
 * é através dele que poderemos armazenar e recuperar os
 * estados via Memento.
 */
trait Originator {
  def save(): Memento
  def restore(m: Memento)

  def cells_$eq(cells: Array[Cell])
}

package gameoflife

import scala.collection.mutable.ListBuffer

import gameoflife.controller.GameController
import gameoflife.controller.GameEngine

/**
 * Programa principal do GoL.
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object Main extends App {
  val height = 10
  val width = 10

  val ctrl = GameController
  ctrl.setEngine(Conway)
  ctrl.start
}

object Conway extends GameEngine {
  /* verifica se uma celula deve ser mantida viva */
  def shouldKeepAlive(i: Int, j: Int): Boolean = (
    (cells(i)(j).isAlive) &&
    (
      numberOfNeighborhoodAliveCells(i, j) == 2 ||
      numberOfNeighborhoodAliveCells(i, j) == 3
    )
  )

  /* verifica se uma celula deve (re)nascer */
  def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3)
  }
}

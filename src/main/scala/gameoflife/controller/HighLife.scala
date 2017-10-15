package gameoflife.controller

import gameoflife.model.Statistics
import gameoflife.controller.GameEngine
object Teste extends GameEngine{
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {

    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 1 || numberOfNeighborhoodAliveCells(i, j) == 2)

  }



  override def shouldRevive(i: Int, j: Int): Boolean = {

    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 6)
  }
}
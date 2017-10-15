package gameoflife.controller

import gameoflife.model.Statistics
import gameoflife.controller.GameEngine
object HighLife extends GameEngine{
    override def name:String = "HighLife"
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {

    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 1 || numberOfNeighborhoodAliveCells(i, j) == 2)

  }



  override def shouldRevive(i: Int, j: Int): Boolean = {

    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 6)
  }
}
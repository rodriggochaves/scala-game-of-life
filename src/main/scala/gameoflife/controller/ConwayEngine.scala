package gameoflife.controller

import gameoflife.view.GameView
import gameoflife.model.Statistics

object ConwayEngine extends GameEngine{
  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
  }

  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3)
  }
}
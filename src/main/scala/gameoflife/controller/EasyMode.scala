package gameoflife.controller

import gameoflife.model.Statistics

object EasyMode extends GameEngine {

	override def name:String = "EasyMode"

  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 1 || numberOfNeighborhoodAliveCells(i, j) == 2)
  }

  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 0)
  }
}
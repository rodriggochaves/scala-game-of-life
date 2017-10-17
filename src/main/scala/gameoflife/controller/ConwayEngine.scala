package gameoflife.controller

object ConwayEngine extends GameEngine {
  
  override def name:String = "ConwayEngine"

  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
  }

  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3)
  }
}
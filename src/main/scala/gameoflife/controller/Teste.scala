package gameoflife.controller

object Teste extends GameEngine {
  override def name:String = "Teste"
  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 1 || numberOfNeighborhoodAliveCells(i, j) == 2)
  }

  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 1)
  }
}
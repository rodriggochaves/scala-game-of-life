package br.unb.cic.poo.gol
object Conway extends GameEngine{
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {

    cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)

  }



  override def shouldRevive(i: Int, j: Int): Boolean = {

    (!cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3)

  }
}
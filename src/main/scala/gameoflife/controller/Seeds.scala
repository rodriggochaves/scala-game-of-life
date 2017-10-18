package gameoflife.controller

import gameoflife.model.Statistics

object Seeds extends GameEngine{
  
  override def name:String = "Seeds"

  override def shouldKeepAlive(i: Int, j: Int): Boolean = false

  override def shouldRevive(i: Int, j: Int): Boolean = {
    if((!cells(i)(j).isAlive) && (numberOfNeighborhoodAliveCells(i, j) == 2)){
      return true
    }else{
      return false
    }
  }
}
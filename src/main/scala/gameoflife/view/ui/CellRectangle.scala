package gameoflife.view.ui

import scalafx.Includes._
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color._

import gameoflife.controller.GameEngine

class CellRectangle( i: Int, j: Int, gameEngine: GameEngine ) extends Rectangle {
  
  x = (i * 20 + 10) 
  y = (j * 20 + 10)
  width = 16
  height = 16
  fill = Black
  handleEvent(MouseEvent.Any) {
    me: MouseEvent => {
      me.eventType match {
        case MouseEvent.MousePressed => {
          gameEngine.makeCellAlive(i, j)
        }
        case _                       => {}
      }
    }
  }

  def isAlive( live: Boolean ) {
    if( live ) {
      fill = LawnGreen
    } else {
      fill = Black     
    }
  }

}
package gameoflife.view.ui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.event.EventHandler

import gameoflife.controller.GameEngine

class GameView( gameEngine: GameEngine ) extends JFXApp {

  var cells = Array[CellRectangle]()

  stage = new PrimaryStage {
    width = 500
    height = 500 
    scene = new Scene {
      content = initializeBoard
    }
    handleEvent(MouseEvent.Any) {
      me: MouseEvent => {
        me.eventType match {
          case MouseEvent.MousePressed => {
            updateBoard
          }
          case _                       => {}
        }
      }
    }
  }

  def initializeBoard(): Array[CellRectangle] = {
    for(i <- (0 until gameEngine.height)) {
      for(j <- (0 until gameEngine.width)) {
        cells = cells :+ new CellRectangle( i, j, gameEngine )
      }
    }
    return cells
  }

  def makeCellAlive( i: Int, j: Int ) {
    var currentCell = listCell((i * width) + j)
    currentCell.fill_=(Red)
  }
}
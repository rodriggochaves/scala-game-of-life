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

  val height = Main.height
  val width = Main.width
  var listCell = Array[Rectangle]()

  stage = new PrimaryStage {
    width = 500
    height = 500 
    scene = new Scene {
      content = initializaBoard
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

  private def initializaBoard(): Array[Rectangle] = {
    for(i <- (0 until height)) {
      for(j <- (0 until width)) {
        listCell = listCell :+ new Rectangle {
          x = (i * 20 + 10) 
          y = (j * 20 + 10)
          width = 16
          height = 16
          fill = Black
        }
      }
    }
    return listCell
  }

  def makeCellAlive( i: Int, j: Int ) {
    var currentCell = listCell((i * width) + j)
    currentCell.fill_=(Red)
  }
}
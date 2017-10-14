package gameoflife.view.ui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.SubScene
import scalafx.scene.control.Label
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.event.EventHandler
import scalafx.scene.control.Button

import gameoflife.controller.GameEngine

class GameView( gameEngine: GameEngine ) extends JFXApp {

  var cells = Array[CellRectangle]()

  stage = new PrimaryStage {
    width = 500
    height = 500
    scene = new Scene {
      content = List(initializeBoard, controlBoard)
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

  def initializeBoard(): SubScene = {
    for(i <- (0 until gameEngine.height)) {
      for(j <- (0 until gameEngine.width)) {
        cells = cells :+ new CellRectangle( i, j, gameEngine )
      }
    }
    val scene = new SubScene(300, 300)
    scene.content = cells
    return scene
  }

  def updateBoard() {
    for(i <- (0 until gameEngine.height)) {
      for(j <- (0 until gameEngine.width)) {
        val cell = cells(i * gameEngine.width + j)
        cell.isAlive(gameEngine.isCellAlive(i, j))
      }
    }
  }

  private def controlBoard(): SubScene = {
    val scene = new SubScene(200, 200)
    scene.content = new Button {
      text = "next generation"
      onAction = handle { 
        gameEngine.nextGeneration 
        updateBoard
      }
    }
    scene.layoutX = 350
    scene.layoutY = 10
    return scene
  }
}
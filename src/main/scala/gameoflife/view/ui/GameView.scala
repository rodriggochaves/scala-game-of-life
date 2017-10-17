package gameoflife.view.ui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.SubScene
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.event.EventHandler
import scalafx.scene.control.Button
import scala.collection.mutable.MutableList

import gameoflife.controller.GameEngine
import gameoflife.controller.GameController

class GameView( var gameEngine: GameEngine, modes: MutableList[GameEngine] ) extends JFXApp {

  var cells = Array[CellRectangle]()

  stage = new PrimaryStage {
    width = 1000
    height = 1000
    scene = new Scene {
      content = List(initializeBoard, controlBoard, modeBoard)
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

  def setGameEngine(g: GameEngine) {
    gameEngine = g
  }

  private def modeBoard(): SubScene = {
    var buttons = List[Button]()
    for(index <- 0 until modes.length) {
      buttons = buttons :+ new Button {
        text = modes(index).name
        onAction = handle {
          GameController.changeGameMode( index )
          cells.foreach(cell => cell.setGameEngine(modes(index)))
          updateBoard
        }
      }
    }
    val vBox = new VBox(10)
    vBox.children = buttons
    val scene = new SubScene(200, 200)
    scene.layoutX = 350
    scene.layoutY = 50
    scene.content = vBox
    return scene
  }

  def stop() {
    stopApp()
  }
}
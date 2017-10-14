package gameoflife.view.ui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._

import gameoflife.Main

class GameView extends JFXApp {

  val height = Main.height
  val width = Main.width
  var listCell = Array[Rectangle]()

  stage = new PrimaryStage {
    width = 500
    height = 500 
    scene = new Scene {
      content = initializaBoard
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
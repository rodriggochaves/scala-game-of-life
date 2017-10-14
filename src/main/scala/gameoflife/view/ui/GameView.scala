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

  stage = new PrimaryStage {
    width = 500
    height = 500 
    scene = new Scene {
      content = initializaBoard
    }
  }

  private def initializaBoard(): List[Rectangle] = {
    var listCell = List[Rectangle]()
    for(i <- (0 until height)) {
      for(j <- (0 until width)) {
        listCell = listCell ::: List(new Rectangle {
          x = (i * 20 + 10) 
          y = (j * 20 + 10)
          width = 16
          height = 16
          fill = Black
        })
      }
    }
    print(listCell.size)
    return listCell
  }
}
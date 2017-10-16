package gameoflife.controller

import gameoflife.view.GameView
import gameoflife.model.Statistics
import gameoflife.controller.GameEngine

/**
 * Relaciona o componente View com o componente Model.
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  def start {
    GameView.update
  }

  def halt() {
    // oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
      GameEngineCareTaker.addMemento(Conway.save)
      Conway.makeCellAlive(i, j)
      GameView.update
    } catch {
      case ex: IllegalArgumentException => {
        println(ex.getMessage)
      }
    }
  }

  def nextGeneration {
    GameEngineCareTaker.addMemento(Conway.save)
    Conway.nextGeneration
    GameView.update
  }

  def undo {
    try {
      Conway.restore(GameEngineCareTaker.getMemento)
    } catch {
      case ex: NoSuchElementException => {}
    }
    GameView.update
  }
}

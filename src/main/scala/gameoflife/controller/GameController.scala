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
  var engine: GameEngine = null

  def setEngine(eng: GameEngine) =
    engine = eng

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
      engine.makeCellAlive(i, j)
      GameView.update
    } catch {
      case ex: IllegalArgumentException => {
        println(ex.getMessage)
      }
    }
  }

  def nextGeneration {
    engine.nextGeneration
    GameView.update
  }
}

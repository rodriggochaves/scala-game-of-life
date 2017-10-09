package gameoflife.controller

import gameoflife.view.GameView
import gameoflife.model.Statistics
import gameoflife.controller.Conway

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  gameEngine: GameEngine =>

  val gameView = new GameView with Conway
  
  def start {
    gameView.update
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			gameEngine.makeCellAlive(i, j)
			gameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    gameEngine.nextGeneration
    gameView.update
  }
  
}
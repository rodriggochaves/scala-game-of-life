package gameoflife.controller

import gameoflife.view.GameView
import gameoflife.model.Statistics
import gameoflife.controller.ConwayEngine

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {

  var gameView = new GameView( ConwayEngine );
  
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
			ConwayEngine.makeCellAlive(i, j)
			gameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    ConwayEngine.nextGeneration
    gameView.update
  }
  
}
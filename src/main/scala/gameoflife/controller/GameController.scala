package gameoflife.controller

import gameoflife.view.ui.GameView
import gameoflife.model.Statistics


/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {

  val gameView = new GameView
  
  def start {
    gameView.main(Array())
    // GameView.update
    // GameView.update
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			Conway.makeCellAlive(i, j)
			// GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    Conway.nextGeneration
    // GameView.update
  }
  
}
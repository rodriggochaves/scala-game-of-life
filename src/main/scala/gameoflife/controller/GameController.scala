package gameoflife.controller

import gameoflife.view.GameView
import gameoflife.view.terminal.GameWriter
import gameoflife.view.terminal.GameListener
import gameoflife.model.Statistics
import gameoflife.controller.ConwayEngine

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {

  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val HALT = 3

  var gameWriter: GameWriter = new GameWriter( ConwayEngine )
  var gameListener: GameListener = new GameListener( ConwayEngine )
  
  def start {
    // chama o update do listener
    update
  }

  def update() {
    gameWriter.update
    gameListener.printOptions match {
      case MAKE_CELL_ALIVE => makeCellAlive; update
      case NEXT_GENERATION => nextGeneration; update
      case HALT => halt
    }
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive {

    val (i, j): (Int, Int) = gameListener.makeCellAlive

    try {
			ConwayEngine.makeCellAlive(i, j)
			gameWriter.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    ConwayEngine.nextGeneration
    gameWriter.update
  }
  
}
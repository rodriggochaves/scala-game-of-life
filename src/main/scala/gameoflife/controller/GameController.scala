package gameoflife.controller
import scala.collection.mutable.MutableList
import gameoflife.view.terminal.GameWriter
import gameoflife.view.terminal.GameListener
import gameoflife.model.Statistics

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {

  private var modes :MutableList[GameEngine] = new MutableList[GameEngine]
  modes += ConwayEngine
  modes += Conway

  //Option é a escolha do usuário
  var option = 0
  def getMode(i:Int):GameEngine = {
    return modes(i)
  }

  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val HALT = 3

  var gameWriter: GameWriter = new GameWriter( getMode(option) )
  var gameListener: GameListener = new GameListener( getMode(option) )
  
  def start {
    // chama o update do listener
    update
  }

    
  def update() {
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

			getMode(option).makeCellAlive(i, j)
			gameWriter.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    getMode(option).nextGeneration
    gameWriter.update
  }
  
}
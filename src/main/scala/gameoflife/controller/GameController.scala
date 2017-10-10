package gameoflife.controller
import scala.collection.mutable.MutableList
import gameoflife.view.GameView
import gameoflife.model.Statistics

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {

  private var modes :MutableList[GameEngine] = new MutableList[GameEngine]
  modes += Conway
  modes += ConwayEngine

  var option = 0
  def getMode(i:Int):GameEngine = {
    return modes(i)
  }
  //Option é a escolha do usuário
  var gameView = new GameView( getMode(option) )

  
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
			getMode(option).makeCellAlive(i, j)
			gameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    getMode(option).nextGeneration
    gameView.update
  }
  
}
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
  def addGameMode(gameMode:GameEngine){
    modes += gameMode
  }
  addGameMode(ConwayEngine)
  addGameMode(Conway)
  addGameMode(Teste)
  

  //Option é a escolha do usuário
  var currentMode:Int = 1
  def getMode(i:Int):GameEngine = {
    return modes(i)
  }

  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val CHANGE_GAME_MODE = 3
  private final val HALT = 4

  var gameWriter: GameWriter = new GameWriter( getMode(currentMode) )
  var gameListener: GameListener = new GameListener( getMode(currentMode) )
  
  def start {
    // chama o update do listener
    gameWriter.update
    update
  }

    
  def update() {
    gameListener.printOptions match {
      case MAKE_CELL_ALIVE => makeCellAlive; update
      case NEXT_GENERATION => nextGeneration; update
      case CHANGE_GAME_MODE => changeGameMode();  
      case HALT => halt
    }
    
  }

  def changeGameMode(){
    
    if(currentMode == 0){
      currentMode = 1
    }else if(currentMode == 1){
      currentMode = 2
    }
    else{
      currentMode = 0
    }
    gameWriter = new GameWriter( getMode(currentMode) )
    gameListener = new GameListener( getMode(currentMode) )
    start
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive {

      gameWriter.update
    val (i, j): (Int, Int) = gameListener.makeCellAlive

    try {

      getMode(currentMode).makeCellAlive(i, j)
      gameWriter.update
    }
    catch {
      case ex: IllegalArgumentException => {
        println(ex.getMessage)
      }
    }
  }
  
  def nextGeneration {
    getMode(currentMode).nextGeneration
    gameWriter.update
  }
  
}
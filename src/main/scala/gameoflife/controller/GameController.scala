package gameoflife.controller

import scala.collection.mutable.MutableList
import gameoflife.view.ui.GameView
import gameoflife.view.terminal.GameListener
import gameoflife.model.Statistics

/**
 * Relaciona o componente View com o componente Model.
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  
  private var modes:MutableList[GameEngine] = new MutableList[GameEngine]
  
  def addGameMode(gameMode:GameEngine) {
    modes += gameMode
  }

  addGameMode(ConwayEngine)
  addGameMode(EasyMode)
  addGameMode(HighLife)
  addGameMode(Teste)

  //currentMode é a escolha do usuário
  var currentMode:Int = 0

  def getMode( i:Int ):GameEngine = {
    return modes(i)
  }

  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val CHANGE_GAME_MODE = 3
  private final val UNDO = 4
  private final val HALT = 5

  var gameListener: GameListener = new GameListener( getMode(currentMode) )
  var gameView: GameView = new GameView( getMode(currentMode), modes )

  def start {
    // chama o update do listener
    val uiThread = new Thread {
      setDaemon(true)
      override def run = {
        gameView.main(Array())
      }
    }
    uiThread.start()
    update
  }

  def update() {
    gameListener.printOptions match {
      // case MAKE_CELL_ALIVE => makeCellAlive; update
      case NEXT_GENERATION => nextGeneration; update
      // case CHANGE_GAME_MODE => printOptionsGameMode; changeGameMode()
      case UNDO => undo; update
      case HALT => halt
    }
  }

  def printOptionsGameMode {
    var option = 0
    println("\n\n")

      println("Select one game modes: \n \n");
      var indice = 1
      for(rule <- modes){
        println(s"[${indice}] - ${rule.name}")
        indice += 1
      }
      println("[-1] - Exit")

      print("\n \n Option: ");

      option = parseOptionGameMode(readLine)

    currentMode = option
  }

  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    gameView.stop
    // why? why???
    // System.exit(0)
  }

  private def parseOptionGameMode(option: String): Int = option match {
    case "1" => return 0
    case "2" => return 1
    case "3" => return 2
    case "4" => return 3
    case "-1" => return -1
  }


  def changeGameMode(  modeNumber: Int ){
    currentMode = modeNumber
    gameView.setGameEngine( getMode(currentMode) )
  }

  def makeCellAlive( i: Int, j: Int ) {
    // val (i, j): (Int, Int) = gameListener.makeCellAlive
    try {
      val mode = getMode(currentMode)
      GameEngineCareTaker.addMemento(mode.save)
      getMode(currentMode).makeCellAlive(i, j)
      gameView.updateBoard
    } catch {
      case ex: IllegalArgumentException => {
        println(ex.getMessage)
      }
    }
  }

  def nextGeneration {
    val mode = getMode( currentMode )
    GameEngineCareTaker.addMemento(mode.save)
    getMode(currentMode).nextGeneration
    gameView.updateBoard
  }

  def undo {
    val mode = getMode(currentMode)
    print(GameEngineCareTaker.stack.size)
    try {
      mode.restore(GameEngineCareTaker.getMemento)
    } catch {
      case ex: NoSuchElementException => {}
    }
  }
}

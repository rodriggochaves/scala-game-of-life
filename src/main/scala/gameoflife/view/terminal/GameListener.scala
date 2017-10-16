package gameoflife.view.terminal

import scala.io.StdIn.{readInt, readLine}

import gameoflife.controller.GameController
import gameoflife.controller.GameEngine

/**
 * Representa o componente View do GoL
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */

class GameListener(gameEngine: GameEngine) {

  // private final val LINE = "+-----+"
  // private final val DEAD_CELL = "|     |"
  // private final val ALIVE_CELL = "|  o  |"

  private final val INVALID_OPTION = 0
  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val CHANGE_GAME_MODE = 3
  private final val UNDO = 4
  private final val HALT = 5

  def printOptions(): Int = {

    var option = 0
    println("\n\n")

    do {
      println("Select one of the options: \n \n");
      println("[1] Make a cell alive");
      println("[2] Next generation");
      println("[3] Change game mode");
      println("[4] Undo change");
      println("[5] Halt");

      print("\n \n Option: ");

      option = parseOption(readLine)
    } while (option == 0)

    return option
  }

  def makeCellAlive(): (Int, Int) = {

    var i = 0
    var j = 0

    do {
      print("\n Inform the row number (0 - " + (gameEngine.height - 1) + "): ")
      i = readInt

      print("\n Inform the column number (0 - " + (gameEngine.width - 1) + "): ")
      j = readInt

    } while(!validPosition(i,j))

    (i, j)
  }

  // private def nextGeneration = GameController.nextGeneration
  // private def halt = GameController.halt

  private def validPosition(i: Int, j: Int): Boolean = {
    println(i);
    println(j);
    i >= 0 && i < gameEngine.height && j >= 0 && j < gameEngine.width
  }

  private def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => CHANGE_GAME_MODE
    case "4" => UNDO
    case "5" => HALT
    case _ => INVALID_OPTION
  }

}

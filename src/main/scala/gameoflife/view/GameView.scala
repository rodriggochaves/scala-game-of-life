package gameoflife.view

import scala.io.StdIn.{readInt, readLine}

import gameoflife.controller.GameEngine
import gameoflife.controller.GameController
import gameoflife.controller.Conway

/**
 * Representa o componente View do GoL
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameView {
  private final val LINE = "+-----+"
  private final val DEAD_CELL = "|     |"
  private final val ALIVE_CELL = "|  o  |"

  private final val INVALID_OPTION = 0
  private final val MAKE_CELL_ALIVE = 1
  private final val NEXT_GENERATION = 2
  private final val HALT = 3
  private final val UNDO = 4

  /**
   * Atualiza o componente view (representado pela classe GameBoard),
   * possivelmente como uma resposta a uma atualizacao do jogo.
   */
  def update {
    printFirstRow
    printLine

    for(i <- (0 until Conway.height)) {
      for(j <- (0 until Conway.width)) {
        print(if (Conway.isCellAlive(i, j)) ALIVE_CELL else DEAD_CELL);
      }
      println("   " + i)
      printLine
    }
    printOptions
  }

  private def printOptions {
    var option = 0
    println("\n\n")

    do {
      println("Select one of the options: \n \n");
      println("[1] Make a cell alive");
      println("[2] Next generation");
      println("[3] Halt");
      println("[4] Undo");

      print("\n \n Option: ");

      option = parseOption(readLine)
    } while(option == 0)

    option match {
      case MAKE_CELL_ALIVE => makeCellAlive
      case NEXT_GENERATION => nextGeneration
      case HALT => halt
      case UNDO => undo
    }
  }

  private def makeCellAlive {
    var i = 0
    var j = 0

    do {
      print("\n Inform the row number (0 - " + (Conway.height - 1) + "): ")
      i = readInt

      print("\n Inform the column number (0 - " + (Conway.width - 1) + "): ")
      j = readInt

    } while(!validPosition(i,j))

    GameController.makeCellAlive(i, j)
  }

  private def nextGeneration = GameController.nextGeneration

  private def halt = GameController.halt
  private def undo = GameController.undo

  private def validPosition(i: Int, j: Int): Boolean = {
    println(i);
    println(j);
    i >= 0 && i < Conway.height && j >= 0 && j < Conway.width
  }

  def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => HALT
    case "4" => UNDO
    case _ => INVALID_OPTION
  }

  /* Imprime uma linha usada como separador das linhas do tabuleiro */
  private def printLine() {
    for(j <- (0 until Conway.width)) {
      print(LINE)
    }
    println()
  }

  /*
   * Imprime os identificadores das colunas na primeira linha do tabuleiro
   */
  private def printFirstRow {
    println("\n \n");

    for(j <- (0 until Conway.width)) {
      print("   " + j + "   ")
    }
    println()
  }
}
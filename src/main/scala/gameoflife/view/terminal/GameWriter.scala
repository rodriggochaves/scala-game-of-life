package gameoflife.view.terminal

import scala.io.StdIn.{readInt, readLine}

import gameoflife.controller.GameController
import gameoflife.controller.GameEngine

/**
 * Representa o componente View do GoL
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */

class GameWriter( gameEngine: GameEngine ) {
  
  private final val LINE = "+-----+"
  private final val DEAD_CELL = "|     |"
  private final val ALIVE_CELL = "|  o  |"
  
  /**
   * Atualiza o componente view (representado pela classe GameBoard),
   * possivelmente como uma resposta a uma atualizacao do jogo.
   */
  def update {
    printFirstRow
    printLine
    
    for(i <- (0 until gameEngine.height)) {
      for(j <- (0 until gameEngine.width)) {
        print( if ( gameEngine.isCellAlive(i, j)) ALIVE_CELL else DEAD_CELL );
      }
      println("   " + i)
      printLine
    }
  }
  
  /* Imprime uma linha usada como separador das linhas do tabuleiro */
  private def printLine() {
    for(j <- (0 until gameEngine.width)) {
      print(LINE)
    }
    println()
  }
  
  
  // / * Imprime os identificadores das colunas na primeira linha do tabuleiro 
  private def printFirstRow {
    println("\n \n");
    
    for(j <- (0 until gameEngine.width)) {
      print("   " + j + "   ")
    }
    println()
  }
  
}
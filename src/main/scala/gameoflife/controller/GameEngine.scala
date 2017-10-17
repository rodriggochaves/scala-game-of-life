package gameoflife.controller

import scala.collection.mutable.ListBuffer
import scala.util.control.TailCalls.TailRec
import scala.annotation.tailrec

import gameoflife.model.Cell
import gameoflife.model.Statistics
import gameoflife.traits.Memento
import gameoflife.traits.Originator
import gameoflife.Main

/**
 * Representa a Game Engine do GoL
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
abstract class GameEngine extends Originator {
  val height = Main.height
  val width = Main.width

  def name: String
  val cells = Array.ofDim[Cell](height, width)

  for(i <- (0 until height)) {
    for(j <- (0 until width)) {
      cells(i)(j) = new Cell
    }
  }

  /**
   * Salva a grid atual no memento
   */
  def save() : Memento = {
    return new GameEngineMemento(this.cells);
  }

  /**
   * Restaura do memento a grid anterior
   */
  def restore( m: Memento ) = {
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        this.cells(i)(j) = m.cells(i)(j)
      }
    }
  }

  /**
   * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
   * algoritmo do Conway, ou seja:
   *
   * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
   * uma celula viva.
   *
   * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
   * viva.
   *
   * c) em todos os outros casos a celula morre ou continua morta.
   */
  def nextGeneration {
    val mustRevive = new ListBuffer[Cell]
    val mustKill = new ListBuffer[Cell]

    for(i <- (0 until height)) {
      for(j <- (0 until width)) {
        if(shouldRevive(i, j)) {
          mustRevive += cells(i)(j)
        }
        else if((!shouldKeepAlive(i, j)) && cells(i)(j).isAlive) {
          mustKill += cells(i)(j)
        }
      }
    }

    for(cell <- mustRevive) {
      cell.revive
      Statistics.recordRevive
    }

    for(cell <- mustKill) {
      cell.kill
      Statistics.recordKill
    }
  }

  /*
   * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
   */
  private def validPosition(i: Int, j: Int) =
    i >= 0 && i < height && j >= 0 && j < width;

  /**
   * Torna a celula de posicao (i, j) viva
   *
   * @param i posicao vertical da celula
   * @param j posicao horizontal da celula
   *
   * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def makeCellAlive(i: Int, j: Int) = {
    require(validPosition(i, j))

    cells(i)(j).revive
    Statistics.recordRevive
  }

  /**
   * Verifica se uma celula na posicao (i, j) estah viva.
   *
   * @param i Posicao vertical da celula
   * @param j Posicao horizontal da celula
   * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
   *
   * @throws InvalidParameterException caso a posicao (i,j) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def isCellAlive(i: Int, j: Int): Boolean = {
    require(validPosition(i, j))

    cells(i)(j).isAlive
  }

  /**
   * Retorna o numero de celulas vivas no ambiente.
   * Esse metodo eh particularmente util para o calculo de
   * estatisticas e para melhorar a testabilidade.
   *
   * @return  numero de celulas vivas.
   */
  def numberOfAliveCells {
    var aliveCells = 0

    for(i <- (0 until height)) {
      for(j <- (0 until width)) {
        if(isCellAlive(i, j)) aliveCells += 1
      }
    }
  }

  //calcula o mod para mundo infinito em relacao as linhas
  def returnLineMod(a : Int) : Int = {
    var result : Int = ((a % height) + height) % height
    return result
  }

  //calcula o mod para mundo infinito em relacao as colunas
  def returnColumnMod(b : Int) : Int = {
    var result : Int = ((b % width) + width) % width
    return result
  }

  def verifyToCountCell(a : Int, b : Int, i : Int, j : Int) : Boolean = {
    return (validPosition(a, b)  && (!(a==i && b == j)) && cells(a)(b).isAlive)
  }

  // verifica se uma celula deve ser mantida viva
  def shouldKeepAlive(i: Int, j: Int) : Boolean

  // verifica se uma celula deve (re)nascer
  def shouldRevive(i: Int, j: Int) : Boolean


  //  /* Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
  //  * de referencia identificada pelos argumentos (i,j).
  //  *//
  protected def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    var x = 0
    var z = 0

    if((i==0 || i==height-1) && (j>=1 && j< width-1)){
      x = returnLineMod(i - 1)
      for(a <- x to height-1) {
        for(b <- (returnColumnMod(j - 1) to returnColumnMod(j + 1))) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

      x = returnLineMod(i + 1)
      for(a <- 0 to x) {
        for(b <- (returnColumnMod(j - 1) to returnColumnMod(j + 1))) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

    } else if ( ( j==0 || j==width-1) && (i>=1 && i<height-1)) {
      x = returnColumnMod(j - 1)
      for(a <- (returnLineMod(i - 1) to returnLineMod(i + 1))) {
        for(b <- x to width-1) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

      x = returnColumnMod(j + 1)
      for(a <- (returnLineMod(i - 1) to returnLineMod(i + 1))) {
        for(b <- 0 to x) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

    } else if((i==0 || i==height-1) && (j==0 || j==width-1)){
      x = returnLineMod(i - 1)
      for(a <- x to height-1) {
        z = returnColumnMod(j - 1)
        for(b <- z to width-1) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }

        z = returnColumnMod(j + 1)
        for(b <- 0 to z) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

      x = returnLineMod(i + 1)
      for(a <- 0 to x) {
        z = returnColumnMod(j - 1)
        for(b <- z to width-1) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }

        z = returnColumnMod(j + 1)
        for(b <- 0 to z) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }

    } else {
      for(a <- (returnLineMod(i - 1) to returnLineMod(i + 1))) {
        for(b <- (returnColumnMod(j - 1) to returnColumnMod(j + 1))) {
          if (verifyToCountCell(a,b,i,j)) {
            alive += 1
          }
        }
      }
    }

    alive
  }
}

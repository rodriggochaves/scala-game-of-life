package gameoflife.model

/**
 * Rerepsentacao de uma celula do GoL
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
class Cell {
  private var alive = false

  def isAlive = alive
  def kill { alive = false }
  def revive { alive = true }
  def copy(): Cell = {
    val r = new Cell()

    if (alive) {
      r.revive
    }

    return r
  }
}

package gameoflife.memento

import gameoflife.model.Cell

/**
  * Memento guarda as informações da grid. É escrito e lido pelo Originator, e coordenado pelo Caretaker.
  */
trait Memento {
 def cells(): Array[Array[Cell]];
}
package gameoflife.memento

import gameoflife.model.Cell

class GameEngineMemento(var cells: Array[Array[Cell]]) extends Memento{
}

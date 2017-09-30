abstract class VerifyCell{
	def verificar(){

	}
	private def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    	var alive = 0
    	for(a <- (i - 1 to i + 1)) {
      		for(b <- (j - 1 to j + 1)) {
        		if (validPosition(a, b)  && (!(a==i && b == j)) && cells(a)(b).isAlive) {
					alive += 1
				}
      		}
    	}
    	alive
  	}
}
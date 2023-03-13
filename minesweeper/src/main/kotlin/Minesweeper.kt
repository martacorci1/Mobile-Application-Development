data class MinesweeperBoard(val inputBoard: List<String>) {
    private val rows : Int = inputBoard.size
    private val cols : Int = if(inputBoard.isNotEmpty()) inputBoard[0].length else 0
    private var inputBoardNumbers = Array(rows) { IntArray(cols) {0} }
    private var newBoard : MutableList<String> = inputBoard.toMutableList()
    fun withNumbers(): List<String> {
        if(inputBoard.isEmpty()){
            return emptyList<String>()
        }
        for ((index, item) in inputBoard.withIndex()) {
            for ((index1, item1) in item.withIndex()) {
                if(item1 != '*') {
                    if(index1 != 0){
                        if(item[index1 - 1] == '*')
                            inputBoardNumbers[index][index1]++
                        if(index != 0) {
                            if (inputBoard[index - 1][index1 - 1] == '*')
                                inputBoardNumbers[index][index1]++
                        }
                        if(index != rows -1) {
                            if (inputBoard[index + 1][index1 - 1] == '*')
                                inputBoardNumbers[index][index1]++
                        }
                    }
                    if ( index1 != cols-1 ) {
                        if(item[index1 + 1] == '*')
                            inputBoardNumbers[index][index1]++
                        if(index != 0) {
                            if (inputBoard[index - 1][index1 + 1] == '*')
                                inputBoardNumbers[index][index1]++
                        }
                        if(index != rows -1) {
                            if (inputBoard[index + 1][index1 + 1] == '*')
                                inputBoardNumbers[index][index1]++
                        }
                    }
                    if(index != 0){
                        if(inputBoard[index - 1][index1] == '*' )
                            inputBoardNumbers[index][index1]++
                    }
                    if (index != rows-1) {
                        if(inputBoard[index + 1][index1] == '*' )
                            inputBoardNumbers[index][index1]++
                    }
                }
            }
        }
        for ((index, item) in inputBoardNumbers.withIndex()) {
            for ((index1, item1) in item.withIndex()) {
                if(item1>0){
                    newBoard[index] = newBoard[index].replaceRange(index1..index1, item1.toString())
                }
            }
        }
        return newBoard
    }
}

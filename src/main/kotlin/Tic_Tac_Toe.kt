fun createBoard(): MutableList<MutableList<Char>> = MutableList (3) { MutableList (3) {' '} }

fun printBoard(board: MutableList<MutableList<Char>>) {
    println("---------")
    (1..3).forEach {it -> println("| ${board[it-1].joinToString(" ")} |")}
    println("---------")
}

fun matchH(list: List<Char>): Boolean{
    val predicate: (Char) -> Boolean = {it == 'X'}
    val result1 = list.count(predicate)
    val predicate1: (Char) -> Boolean = {it == 'O'}
    val result2 = list.count(predicate1)
    return (list[0] == list[1]) && (list[1] == list[2]) && ((result1 == 3) || (result2 == 3))
}

fun result(board: MutableList<MutableList<Char>>): Boolean{
    when{
        matchH(board[0]) -> {
            println("${board[0][0]} wins")
            return false
        }
        matchH(board[1]) && (board[0][0] != ' ') -> {
            println("${board[1][0]} wins")
            return false
        }
        matchH(board[2]) && (board[0][0] != ' ') -> {
            println("${board[2][0]} wins")
            return false
        }
        (board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && (board[0][0] != ' ') -> {
            println("${board[0][0]} wins")
            return false
        }
        (board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && (board[0][1] != ' ') -> {
            println("${board[0][1]} wins")
            return false
        }
        (board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && (board[0][2] != ' ') -> {
            println("${board[2][2]} wins")
            return false
        }
        (board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != ' ') -> {
            println("${board[1][1]} wins")
            return false
        }
        (board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && (board[2][0] != ' ') -> {
            println("${board[1][1]} wins")
            return false
        }
        else -> return true
    }
}
var count = 1
var piece = 'X'
var flag: Boolean = true
fun userInput(list: MutableList<MutableList<Char>>){
    if (count % 2 == 0){
        piece = 'O'
    }
    else{
        piece = 'X'
    }
    try {
        val (a, b) = readln().split(" ")
        val row = a.toInt() - 1
        val col = b.toInt() - 1
        if (list[row][col] != ' ') {
            println("This cell is occupied! Choose another one!")
            userInput(list)

        }
        else {
            list[a.toInt() - 1][b.toInt() - 1] = piece
            printBoard(list)
            ++count
            flag = result(list)
        }
    }catch (e: NumberFormatException) {
        println("You should enter numbers!")
        userInput(list)
    }catch (e: IndexOutOfBoundsException) {
        println("Coordinates should be from 1 to 3!")
        userInput(list)
    }
}

fun main(){
    val board = createBoard()
    printBoard(board)
    do {
        userInput(board)
    } while(flag && count < 10)
    if (count == 10 && flag)
        println("Draw")
}
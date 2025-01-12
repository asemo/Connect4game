package connect4;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dallal, Z
 */
public class Controller {

    char computer = 'o';
    char human = 'x';
    Connect4Game board = new Connect4Game(3, 3, 3);

    public void play() {
        System.out.println(board);
        while (true) {
            humanPlay();
            System.out.println(board);

            if (board.isWin(human)) {
                System.out.println("Human wins");
                break;
            }
            if (board.isWithdraw()) {
                System.out.println("Draw");
                break;
            }
            computerPlay();
            System.out.println("_____Computer Turn______");
            System.out.println(board);
            if (board.isWin(computer)) {
                System.out.println("Computer wins!");
                break;
            }
            if (board.isWithdraw()) {
                System.out.println("Draw");
                break;
            }
        }

    }

    //         ************** YOUR CODE HERE ************            \\

    private void computerPlay() {
        List<Object> bestMove = maxMove(board);

        Connect4Game bestState = (Connect4Game) bestMove.get(1);

        if (bestState != null) {
            board = bestState;
        } else {
            System.out.println("No valid moves for the computer!");
        }
    }

    /**
     * Human plays
     *
     * @return the column the human played in
     */
    private void humanPlay() {
        Scanner s = new Scanner(System.in);
        int col;
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            System.out.println();
            if ((col > 0) && (col - 1 < board.getWidth())) {
                if (board.play(human, col - 1)) {
                    return;
                }
                System.out.println("Invalid Column: Column " + col + " is full!, try agine");
            }
            System.out.println("Invalid Column: out of range " + board.getWidth() + ", try agine");
        }
    }



    private List<Object> maxMove1(Connect4Game b) {
        // the fuction returns list of object the first object is the evaluation (type Integer), the second is the state with the max evaluation
//         ************** YOUR CODE HERE ************            \\
        return null;


    }

    private List<Object> maxMove(Connect4Game b) {
        // The function returns a list where:
        // - The first element is the evaluation score (Integer)
        // - The second element is the state with the maximum evaluation
        List<Object> result = new ArrayList<> ();
        if(b.isFinished ()) {
            result.add ( b );
        }
        int maxEvaluation = Integer.MIN_VALUE;
        Connect4Game bestState = null;

        for (Connect4Game nextState : b.allNextMoves(computer)) {
            int evaluation = nextState.evaluate(computer);
            if (evaluation > maxEvaluation) {
                maxEvaluation = evaluation;
                bestState = nextState;
            }
        }

        result.add(maxEvaluation);
        result.add(bestState);
        return result;
    }
    private List<Object> minMove(Connect4Game b) {


        List<Object> result = new ArrayList<>();


        if(b.isFinished ()) {
            result.add ( b );
        }
        int minEvaluation = Integer.MAX_VALUE;
        Connect4Game bestState = null;

        for (Connect4Game nextState : b.allNextMoves(human)) {
            int evaluation = nextState.evaluate(human);
            if (evaluation < minEvaluation) {
                minEvaluation = evaluation;
                bestState = nextState;
            }
        }

        result.add(minEvaluation);
        result.add(bestState);
        return result;
    }

    public static void main(String[] args) {
        Controller g = new Controller();
        g.play();
    }

}






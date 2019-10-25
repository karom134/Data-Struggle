public class MissingTile {
  public static void tileGrid(Grid board) {
    // TODO: implement me!
    helper(board,board,0,0);
    }
    public static void helper(Grid newboard, Grid board, int shiftx, int shifty){
        int paintedx=newboard.getPaintedCellX();
        int paintedy=newboard.getPaintedCellY();
        int size=newboard.size();
        if(newboard.size()==2){
            if(paintedx==0&&paintedy==0){
                board.setTile(paintedx+shiftx,paintedy+shifty,3);
            }
            if(paintedx==0&&paintedy==1){
              board.setTile(paintedx+shiftx,paintedy+shifty,2);
            }
            if(paintedx==1&&paintedy==1){
              board.setTile(paintedx+shiftx,paintedy+shifty,1);
            }
            if(paintedx==1&&paintedy==0){
              board.setTile(paintedx+shiftx,paintedy+shifty,0);
            }
        }
        else{
            if(paintedx<size/2&&paintedy<size/2){
              board.setTile(size/2-1+shiftx,size/2-1+shifty,3);
              Grid board1= new BasicBoard(size/2,paintedx,paintedy);
              helper(board1,board,shiftx,shifty);
              Grid board2= new BasicBoard(size/2,size/2-1,0);
              helper(board2,board,shiftx,shifty+size/2);
              Grid board3 = new BasicBoard(size/2,0,0);
              helper(board3,board,shiftx+size/2,shifty+size/2);
              Grid board4 = new BasicBoard(size/2,0,size/2-1);
              helper(board4,board,shiftx+size/2,shifty);
            }
          if(paintedx<size/2&&paintedy>=size/2){
            board.setTile(size/2-1+shiftx,size/2+shifty,2);
            Grid board1= new BasicBoard(size/2,size/2-1,size/2-1);
            helper(board1,board,shiftx,shifty);
            Grid board2= new BasicBoard(size/2,paintedx,paintedy-size/2);
            helper(board2,board,shiftx,shifty+size/2);
            Grid board3 = new BasicBoard(size/2,0,0);
            helper(board3,board,shiftx+size/2,shifty+size/2);
            Grid board4 = new BasicBoard(size/2,0,size/2-1);
            helper(board4,board,shiftx+size/2,shifty);
          }
          if(paintedx>=size/2&&paintedy>=size/2){
            board.setTile(size/2+shiftx,size/2+shifty,1);
            Grid board1= new BasicBoard(size/2,size/2-1,size/2-1);
            helper(board1,board,shiftx,shifty);
            Grid board2= new BasicBoard(size/2,size/2-1,0);
            helper(board2,board,shiftx,shifty+size/2);
            Grid board3 = new BasicBoard(size/2,paintedx-size/2,paintedy-size/2);
            helper(board3,board,shiftx+size/2,shifty+size/2);
            Grid board4 = new BasicBoard(size/2,0,size/2-1);
            helper(board4,board,shiftx+size/2,shifty);
          }
          if(paintedx>=size/2&&paintedy<size/2){
            board.setTile(size/2+shiftx,size/2-1+shifty,0);
            Grid board1= new BasicBoard(size/2,size/2-1,size/2-1);
            helper(board1,board,shiftx,shifty);
            Grid board2= new BasicBoard(size/2,size/2-1,0);
            helper(board2,board,shiftx,shifty+size/2);
            Grid board3 = new BasicBoard(size/2,0,0);
            helper(board3,board,shiftx+size/2,shifty+size/2);
            Grid board4 = new BasicBoard(size/2,paintedx-size/2,paintedy);
            helper(board4,board,shiftx+size/2,shifty);
        }
    }
}
}
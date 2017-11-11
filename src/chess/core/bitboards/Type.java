package chess.core.bitboards;

import chess.core.bitboards.moves.pieces.Bishop;
import chess.core.bitboards.moves.pieces.King;
import chess.core.bitboards.moves.pieces.Knight;
import chess.core.bitboards.moves.pieces.Pawn;
import chess.core.bitboards.moves.pieces.Queen;
import chess.core.bitboards.moves.pieces.Rook;

public class Type {
	private static Pawn p = new Pawn();
	private static Knight n = new Knight();
	private static Rook r = new Rook();
	private static Bishop b = new Bishop();
	private static Queen q = new Queen();
	private static King k = new King();
	
	public static final int PAWN = 0; //These indexes are used to identify the pieces throughout the program
	public static final int BISHOP = 1;
	public static final int KNIGHT = 2;
	public static final int QUEEN = 3;
	public static final int ROOK = 4;
	public static final int KING = 5;
	
	public static final String[] tPieceString = {"", "B", "N", "Q", "R", "K"}; //Used when compiling fen or algebraic move notation
	public static final String[] tPieceChar = {"P", "B", "N", "Q", "R", "K"};
	
	public static final int SCORE[] = {100, 330, 320, 900, 500, 20000}; //Score for each piece
	
	public static long getPieceCapAndMove(int player, long empty, long wOccupied, long bOccupied, long pieceBB, int index) {
		if (player == 0) {
			switch(index) { //Based on the index passed, get the accoring moves and captures
			case PAWN: return  p.wGetPossiblePieces(pieceBB, empty, bOccupied); 
			case BISHOP: return b.wGetPossiblePieces(pieceBB, empty, bOccupied);
			case KNIGHT: return n.wGetPossiblePieces(pieceBB, empty, bOccupied);
			case QUEEN: return q.wGetPossiblePieces(pieceBB, empty, bOccupied);
			case ROOK:	return r.wGetPossiblePieces(pieceBB, empty, bOccupied);
			case KING: return k.wGetPossiblePieces(pieceBB, empty, bOccupied);
			}
		} else {
			switch(index) {
			case PAWN: return p.bGetPossiblePieces(pieceBB, empty, wOccupied);
			case BISHOP: return b.bGetPossiblePieces(pieceBB, empty, wOccupied);
			case KNIGHT: return n.bGetPossiblePieces(pieceBB, empty, wOccupied);
			case QUEEN: return q.bGetPossiblePieces(pieceBB, empty, wOccupied);
			case ROOK: return r.bGetPossiblePieces(pieceBB, empty, wOccupied);
			case KING: return k.bGetPossiblePieces(pieceBB, empty, wOccupied);
			}
		}
		return 0L;
	}
	public static boolean getMoves(long bb, int index, int from, int to, long empty, int player) {
		if (player == 0) {
			switch (index) {//Based on the index passed, get the accoring moves
			case PAWN: return isPositionOccupied(p.wGetPossibleTargets(bb, empty), to);
			case BISHOP: return isPositionOccupied(b.wGetPossibleTargets(bb, empty), to);
			case KNIGHT: return isPositionOccupied(n.wGetPossibleTargets(bb, empty), to);
			case QUEEN: return isPositionOccupied(q.wGetPossibleTargets(bb, empty), to);
			case ROOK: return isPositionOccupied(r.wGetPossibleTargets(bb, empty), to);
			case KING: return isPositionOccupied(k.wGetPossibleTargets(bb, empty), to);
			}			
		} else {
			switch (index) {
			case PAWN: return isPositionOccupied(p.bGetPossibleTargets(bb, empty), to);
			case BISHOP: return isPositionOccupied(b.bGetPossibleTargets(bb, empty), to);
			case KNIGHT: return isPositionOccupied(n.bGetPossibleTargets(bb, empty), to);
			case QUEEN: return isPositionOccupied(q.bGetPossibleTargets(bb, empty), to);
			case ROOK: return isPositionOccupied(r.bGetPossibleTargets(bb, empty), to);
			case KING: return isPositionOccupied(k.bGetPossibleTargets(bb, empty), to);
			}
		}		
		return false;
	}
	public static boolean getCaptures(long bb, int index, int from, int to, long empty, long wOccupied, long bOccupied, int player) {
		if (player == 0) {
			switch (index) {//Based on the index passed, get the accoring captures
			case PAWN: return isPositionOccupied(p.wGetPossibleCaptures(bb, bOccupied, empty), to);
			case BISHOP: return isPositionOccupied(b.wGetPossibleCaptures(bb, bOccupied, empty), to);
			case KNIGHT: return isPositionOccupied(n.wGetPossibleCaptures(bb, bOccupied, empty), to);
			case QUEEN: return isPositionOccupied(q.wGetPossibleCaptures(bb, bOccupied, empty), to);
			case ROOK: return isPositionOccupied(r.wGetPossibleCaptures(bb, bOccupied, empty), to);
			case KING: return isPositionOccupied(k.wGetPossibleCaptures(bb, bOccupied, empty), to);
			}			
		} else {
			switch (index) {
			case PAWN: return isPositionOccupied(p.bGetPossibleCaptures(bb, wOccupied, empty), to);
			case BISHOP: return isPositionOccupied(b.bGetPossibleCaptures(bb, wOccupied, empty), to);
			case KNIGHT: return isPositionOccupied(n.bGetPossibleCaptures(bb, wOccupied, empty), to);
			case QUEEN: return isPositionOccupied(q.bGetPossibleCaptures(bb, wOccupied, empty), to);
			case ROOK: return isPositionOccupied(r.bGetPossibleCaptures(bb, wOccupied, empty), to);
			case KING: return isPositionOccupied(k.bGetPossibleCaptures(bb, wOccupied, empty), to);
			}
		}
		return false;
	}
	public static long getUnsafe(int player, long empty) {
		long unsafe = 0L;
		if (player == 0) { //Get the unsafe positions for a specific player
			unsafe |= p.bGetPossiblePieces(CBoard.pieces[Type.PAWN][1], empty, CBoard.getWOcc());
			unsafe |= n.bGetPossiblePieces(CBoard.pieces[Type.KNIGHT][1], empty, CBoard.getWOcc());
			unsafe |= r.bGetPossiblePieces(CBoard.pieces[Type.ROOK][1], empty, CBoard.getWOcc());
			unsafe |= b.bGetPossiblePieces(CBoard.pieces[Type.BISHOP][1], empty, CBoard.getWOcc());
			unsafe |= q.bGetPossiblePieces(CBoard.pieces[Type.QUEEN][1], empty, CBoard.getWOcc());
			unsafe |= k.bGetPossiblePieces(CBoard.pieces[Type.KING][1], empty, CBoard.getWOcc());
		} else {
			unsafe |= p.wGetPossiblePieces(CBoard.pieces[Type.PAWN][0], empty, CBoard.getBOcc());
			unsafe |= n.wGetPossiblePieces(CBoard.pieces[Type.KNIGHT][0], empty, CBoard.getBOcc());
			unsafe |= r.wGetPossiblePieces(CBoard.pieces[Type.ROOK][0], empty, CBoard.getBOcc());
			unsafe |= b.wGetPossiblePieces(CBoard.pieces[Type.BISHOP][0], empty, CBoard.getBOcc());
			unsafe |= q.wGetPossiblePieces(CBoard.pieces[Type.QUEEN][0], empty, CBoard.getBOcc());
			unsafe |= k.wGetPossiblePieces(CBoard.pieces[Type.KING][0], empty, CBoard.getBOcc());
		}
		return unsafe;
	}
	private static boolean isPositionOccupied(long bb, int shift) {
		return ((bb >> shift) & 1) == 1; //Checks if the specfied position is currently occupied
	}
}

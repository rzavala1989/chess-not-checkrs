package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import piece.*;
import util.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Player implements Cloneable {

    private static final Logger logger = LogManager.getLogger();

    protected boolean isBot;

    protected Set<Piece> pieces;


    public Player(boolean isBot) {
        this.isBot = isBot;
        this.pieces = new HashSet<>();

        final int PAWN_ROW = isBot ? 1 : 6;

        final int NUM_PAWNS = 8;
        for (int j = 0; j < NUM_PAWNS; j++) {
            this.pieces.add(new Pawn(this, new Position(j, PAWN_ROW)));
        }

        final int PIECE_ROW = isBot ? 0 : 7;

        final int BISHOP1_COL = 2;
        final int BISHOP2_COL = 5;
        final int KNIGHT1_COl = 1;
        final int KNIGHT2_COL = 6;
        final int ROOK1_COL = 0;
        final int ROOK2_COL = 7;
        final int KING_COL = 3;
        final int QUEEN_COL = 4;

        this.pieces.addAll(List.of(
                new Bishop(this, new Position(BISHOP1_COL, PIECE_ROW)),
                new Bishop(this, new Position(BISHOP2_COL, PIECE_ROW)),
                new Knight(this, new Position(KNIGHT1_COl, PIECE_ROW)),
                new Knight(this, new Position(KNIGHT2_COL, PIECE_ROW)),
                new Rook(this, new Position(ROOK1_COL, PIECE_ROW)),
                new Rook(this, new Position(ROOK2_COL, PIECE_ROW)),
                new King(this, new Position(KING_COL, PIECE_ROW)),
                new Queen(this, new Position(QUEEN_COL, PIECE_ROW))
        ));
    }

    public Player(boolean isBot, Set<Piece> pieces) {
        this.isBot = isBot;
        this.pieces = pieces;
    }

    /**
     * Check if this player is bot
     * @return true if it is, false otherwise
     */
    public boolean isBot() {
        return this.isBot;
    }

    /**
     * Get alive pawns of the player
     * @return the pawns
     */
    protected Collection<Pawn> getPawns() {
        return this.pieces.stream()
                .filter(p -> p instanceof Pawn)
                .map(p -> (Pawn) p)
                .toList();
    }

    /**
     * Return the number of pawns alive
     * @return the number of pawns
     */
    public int countPawns() {
        return this.getPawns().size();
    }

    /**
     * Get alive bishops of the player
     * @return the bishops
     */
    protected Collection<Bishop> getBishops() {
        return this.pieces.stream()
                .filter(p -> p instanceof Bishop)
                .map(p -> (Bishop) p)
                .toList();
    }

    /**
     * Return the number of bishops alive
     * @return the number of bishops
     */
    public int countBishops() {
        return this.getBishops().size();
    }

    /**
     * Get alive knights of the player
     * @return the knights
     */
    protected Collection<Knight> getKnights() {
        return this.pieces.stream()
                .filter(p -> p instanceof Knight)
                .map(p -> (Knight) p)
                .toList();
    }

    /**
     * Return the number of knights alive
     * @return the number of knights
     */
    public int countKnights() {
        return this.getKnights().size();
    }

    /**
     * Get alive rooks of the player
     * @return the rooks
     */
    protected Collection<Rook> getRooks() {
        return this.pieces.stream()
                .filter(p -> p instanceof Rook)
                .map(p -> (Rook) p)
                .toList();
    }

    /**
     * Return the number of rooks alive
     * @return the number of rooks
     */
    public int countRooks() {
        return this.getRooks().size();
    }

    /**
     * Get the queen of the player
     * @return the queen
     */
    protected Collection<Queen> getQueens() {
        return this.pieces.stream()
                .filter(p -> p instanceof Queen)
                .map(p -> (Queen) p)
                .toList();
    }

    /**
     * Return the number of queens alive
     * @return the number of queens
     */
    public int countQueens() {
        return this.getQueens().size();
    }

    /**
     * Get the king of the player
     * @return the king
     */
    protected Optional<King> getKing() {
        return this.pieces.stream()
                .filter(p -> p instanceof King)
                .map(p -> (King) p)
                .findAny();
    }


    /**
     * Return the number of kings alive
     * @return the number of kings
     */
    public int countKings() {
        return this.getKing().isPresent() ? 1 : 0;
    }

    /**
     * Return the number of doubled pawns
     * @return the number
     */
    public int countDoubledPawns() {
        List<Position> positions = this.getPawns().stream()
                .map(Piece::getPosition)
                .sorted(Comparator.comparing(Position::x))
                .toList();

        int count = 0;
        int prevX = -1;
        for (Position position : positions) {
            if (position.x() == prevX) {
                count += 1;
            }
            prevX = position.x();
        }

        return count;
    }

    /**
     * Return the number of blocked pawns
     * @param opponent the opponent of the player
     * @return the number
     */
    public int countBlockedPawns(Player opponent) {
        return this.getPawns().stream()
                .map(p -> new Position(p.getX(), this.isBot() ? p.getY()+1 : p.getY()-1))
                .filter(p -> this.isOccupied(p) || opponent.isOccupied(p))
                .toArray().length;
    }

    /**
     * Return the number of isolated pawns
     * @return the number
     */
    public int countIsolatedPawns() {
        Collection<Pawn> alivePawns = this.getPawns();

        int count = 0;
        for (Pawn p1 : alivePawns) {
            boolean foundNeighbor = false;
            for (Pawn p2 : alivePawns) {
                if (p1 == p2) continue;

                if (Math.abs(p1.getX() - p2.getX()) <= 1) {
                    foundNeighbor = true;
                    break;
                }
            }

            if (!foundNeighbor) {
                count += 1;
            }
        }

        return count;
    }

    /**
     * Return the pawns of this player that are promotable
     * @return the pawns
     */
    public Collection<Pawn> getPromotablePawns() {
        final int PROMOTABLE_ROW = this.isBot() ? 7 : 0;
        return this.getPawns().stream()
                .filter(pawn -> pawn.getY() == PROMOTABLE_ROW)
                .toList();
    }

    /**
     * Add the piece that the icon indicates to the position
     * @param icon the icon of the piece. It can be either black or white
     * @param position the position of the piece
     */
    public void addPiece(char icon, Position position) {
        if (this.isOccupied(position)) {
            throw new IllegalArgumentException(String.format("The position at (%s) is already occupied", position));
        }

        switch (icon) {
            case Bishop.BLACK_ICON, Bishop.WHITE_ICON -> this.pieces.add(new Bishop(this, position));
            case King.BLACK_ICON, King.WHITE_ICON -> this.pieces.add(new King(this, position));
            case Knight.BLACK_ICON, Knight.WHITE_ICON -> this.pieces.add(new Knight(this, position));
            case Pawn.BLACK_ICON, Pawn.WHITE_ICON -> this.pieces.add(new Pawn(this, position));
            case Queen.BLACK_ICON, Queen.WHITE_ICON -> this.pieces.add(new Queen(this, position));
            case Rook.BLACK_ICON, Rook.WHITE_ICON -> this.pieces.add(new Rook(this, position));
            default -> throw new IllegalArgumentException(String.format("The icon %s is invalid", icon));
        }
    }

    /**
     * Return all alive pieces of the player
     */
    public Collection<Piece> allPieces() {
        return this.pieces;
    }

    /**
     * Kill the piece at the given position if present
     * @param position the position
     */
    public void killPieceAt(Position position) {
        this.findPieceAt(position).ifPresent(this.pieces::remove);
    }

    /**
     * Find the piece at the given position
     * @param position the position
     * @return the piece
     */
    public Optional<Piece> findPieceAt(Position position) {
        return this.pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findAny();
    }

    /**
     * Check if the given x and y are occupied by any piece of the player
     */
    public boolean isOccupied(int x, int y) {
        return this.isOccupied(new Position(x, y));
    }

    /**
     * Check if the given position is occupied by any piece of the player
     */
    public boolean isOccupied(Position position) {
        return this.findPieceAt(position).isPresent();
    }

    /**
     * Return all possible actions of the player
     * @return the actions
     */
    public Collection<Action> actions(Player opponent) {
        List<Action> actions = new LinkedList<>();

        for (Piece piece : this.pieces) {
            for (Position movement : piece.movements(opponent)) {
                actions.add(new Action(piece, movement));
            }
        }

        return actions;
    }

    @Override
    public Player clone() {
        try {
            Player newPlayer = (Player) super.clone();
            this.pieces = this.pieces.stream().map(p -> p.clone(newPlayer)).collect(Collectors.toSet());
            return newPlayer;
        } catch (CloneNotSupportedException e) {
            throw logger.throwing(new RuntimeException(e));
        }
    }
}

import Piece from "./models/Piece";

export const AI_SERVER_HOST = "http://localhost:5555/";

export const BOARD_SIZE = 8;

export const DEFAULT_INTELLIGENCE_LEVEL = 2;

export const MIN_TIME_LIMIT_IN_SECONDS = 5;

export const PIECE_URL = {
    "blackBishop": "assets/black_bishop.png",
    "whiteBishop": "assets/white_bishop.png",
    "blackKing": "assets/black_king.png",
    "whiteKing": "assets/white_king.png",
    "blackKnight": "assets/black_knight.png",
    "whiteKnight": "assets/white_knight.png",
    "blackPawn": "assets/black_pawn.png",
    "whitePawn": "assets/white_pawn.png",
    "blackQueen": "assets/black_queen.png",
    "whiteQueen": "assets/white_queen.png",
    "blackRook": "assets/black_rook.png",
    "whiteRook": "assets/white_rook.png"
}

export const PIECE_ICON = {
    "blackBishop": "♝",
    "whiteBishop": "♗",
    "blackKing": "♚",
    "whiteKing": "♔",
    "blackKnight": "♞",
    "whiteKnight": "♘",
    "blackPawn": "♟",
    "whitePawn": "♙",
    "blackQueen": "♛",
    "whiteQueen": "♕",
    "blackRook": "♜",
    "whiteRook": "♖",
    "none": "□"
}

export const DEFAULT_PROMOTING_ICON = PIECE_ICON.whiteQueen;

/**
 * Check if the given icon is the default promoting icon
 * @param icon the icon
 * @return true if it is, false otherwise
 */
export function isDefaultPromotingIcon(icon: string): boolean {
    return icon === DEFAULT_PROMOTING_ICON;
}

/**
 * Covert the given icon to the image url
 * @param icon the icon
 * @return the url
 */
export function imageUrlOf(icon: string): string {
    switch (icon) {
        case PIECE_ICON.blackBishop: return PIECE_URL.blackBishop;
        case PIECE_ICON.whiteBishop: return PIECE_URL.whiteBishop;
        case PIECE_ICON.blackKing: return PIECE_URL.blackKing;
        case PIECE_ICON.whiteKing: return PIECE_URL.whiteKing;
        case PIECE_ICON.blackKnight: return PIECE_URL.blackKnight;
        case PIECE_ICON.whiteKnight: return PIECE_URL.whiteKnight;
        case PIECE_ICON.blackPawn: return PIECE_URL.blackPawn;
        case PIECE_ICON.whitePawn: return PIECE_URL.whitePawn;
        case PIECE_ICON.blackQueen: return PIECE_URL.blackQueen
        case PIECE_ICON.whiteQueen: return PIECE_URL.whiteQueen;
        case PIECE_ICON.blackRook: return PIECE_URL.blackRook;
        case PIECE_ICON.whiteRook: return PIECE_URL.whiteRook;
        default: throw new Error(`Invalid icon: ${icon}`);
    }
}

/**
 * Covert the given image url to the icon
 * @param imageUrl the image url
 * @return the icon
 */
export function iconOf(imageUrl: string): string {
    switch (imageUrl) {
        case PIECE_URL.blackBishop: return PIECE_ICON.blackBishop;
        case PIECE_URL.whiteBishop: return PIECE_ICON.whiteBishop;
        case PIECE_URL.blackKing: return PIECE_ICON.blackKing;
        case PIECE_URL.whiteKing: return PIECE_ICON.whiteKing;
        case PIECE_URL.blackKnight: return PIECE_ICON.blackKnight;
        case PIECE_URL.whiteKnight: return PIECE_ICON.whiteKnight;
        case PIECE_URL.blackPawn: return PIECE_ICON.blackPawn;
        case PIECE_URL.whitePawn: return PIECE_ICON.whitePawn;
        case PIECE_URL.blackQueen: return PIECE_ICON.blackQueen;
        case PIECE_URL.whiteQueen: return PIECE_ICON.whiteQueen;
        case PIECE_URL.blackRook: return PIECE_ICON.blackRook;
        case PIECE_URL.whiteRook: return PIECE_ICON.whiteRook;
        default: throw new Error(`Invalid imageUrl: ${imageUrl}`);
    }
}

/**
 * Check if the given piece is for white player
 * @param piece the piece
 * @return true if it is, false otherwise
 */
export function isWhite(piece: Piece): boolean {
    return [PIECE_ICON.whiteBishop,
        PIECE_ICON.whiteKing,
        PIECE_ICON.whiteKnight,
        PIECE_ICON.whitePawn,
        PIECE_ICON.whiteQueen,
        PIECE_ICON.whiteRook]
        .includes(iconOf(piece.imageUrl));
}

/**
 * Check if the given piece is for black player
 * @param piece the piece
 * @return true if it is, false otherwise
 */
export function isBlack(piece: Piece): boolean {
    return [PIECE_ICON.blackBishop,
        PIECE_ICON.blackKing,
        PIECE_ICON.blackKnight,
        PIECE_ICON.blackPawn,
        PIECE_ICON.blackQueen,
        PIECE_ICON.blackRook]
        .includes(iconOf(piece.imageUrl));
}

package Lexer;

/**
 * Created by chusm on 6/18/2017.
 */
public enum TokenTypes {
    ID,             //identifier
    LIT_INT,        //literal int
    RW_PRINT,       //reserved word "print"
    RW_INT,         //reserved word "int"
    OPEN_PAR,       //left parenthesis
    CLOSE_PAR,      //right parenthesis
    OP_SUM,         //sum
    OP_SUB,         //subtraction
    OP_MULT,        //multiplication
    OP_DIV,         //division
    OP_ASSIGN,      // '='
    END_STATEMENT,  // ';'
    EOF             // End of File
}

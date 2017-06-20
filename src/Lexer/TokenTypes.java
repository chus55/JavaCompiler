package Lexer;

/**
 * Created by chusm on 6/18/2017.
 */
public enum TokenTypes {
    ID,             //identifier
    LIT_INT,        //literal int
    LIT_BOOLEAN,    //literal boolean
    LIT_STRING,     //literal string
    RW_PRINT,       //reserved word "print"
    RW_INT,         //reserved word "int"
    RW_BOOLEAN,     //reserved word "boolean"
    RW_STRING,      //reserved word "string"
    OPEN_PAR,       //left parenthesis
    CLOSE_PAR,      //right parenthesis
    OPEN_CRLY_BRKT, //left curly bracket
    CLOSE_CRLY_BRKT,//right curly bracket
    OP_SUM,         //sum
    OP_SUB,         //subtraction
    OP_MULT,        //multiplication
    OP_DIV,         //division
    OP_ASSIGN,      // '='
    OP_EQUALS,      // '=='
    OP_DIFFERS,     // '!='
    END_STATEMENT,  // ';'
    EOF             // End of File
}

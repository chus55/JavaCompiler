package Parser;

import Lexer.TokenTypes;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class ParserDictionaries {

    List<TokenTypes> ExpressionPossibilities;
    List<TokenTypes> AdditivePossibilities;
    List<TokenTypes> MultiplicativePossibilites;
    List<TokenTypes> ReservedWordsPossibilites;
    List<TokenTypes> StatementPossibilites;
    List<TokenTypes> Types;

    ParserDictionaries() {
        initTypes();
        initStatements();
        initAdditives();
        initMultiplicatives();
        initReservedWords();
        initExpressions();
    }

    private void initTypes() {
        Types = new ArrayList<>();
        Types.add(TokenTypes.RW_INT);
    }

    private void initStatements() {
        StatementPossibilites = new ArrayList<>();
        StatementPossibilites.add(TokenTypes.RW_PRINT);
        StatementPossibilites.addAll(Types);
    }

    private void initReservedWords() {
        ReservedWordsPossibilites = new ArrayList<>();
        ReservedWordsPossibilites.add(TokenTypes.RW_PRINT);
    }

    private void initAdditives() {
        AdditivePossibilities = new ArrayList<>();
        AdditivePossibilities.add(TokenTypes.OP_SUB);
        AdditivePossibilities.add(TokenTypes.OP_SUM);
    }

    private void initExpressions() {
        ExpressionPossibilities = new ArrayList<>();
        ExpressionPossibilities.add(TokenTypes.OPEN_PAR);
        ExpressionPossibilities.addAll(AdditivePossibilities);
        ExpressionPossibilities.addAll(MultiplicativePossibilites);
    }

    private void initMultiplicatives() {
        MultiplicativePossibilites =  new ArrayList<>();
        MultiplicativePossibilites.add(TokenTypes.OP_MULT);
        MultiplicativePossibilites.add(TokenTypes.OP_DIV);
    }


}
// $ANTLR : "oql.g" -> "OQLParser.java"$

package com.gemstone.gemfire.cache.query.internal.parse;
import java.util.*;
import com.gemstone.gemfire.cache.query.internal.types.*;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

/***************************** OQL PARSER *************************************/
public class OQLParser extends com.gemstone.gemfire.cache.query.internal.parse.UtilParser       implements OQLLexerTokenTypes
 {

protected OQLParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public OQLParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected OQLParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public OQLParser(TokenStream lexer) {
  this(lexer,2);
}

public OQLParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void queryProgram() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST queryProgram_AST = null;
		
		{
		if ((LA(1)==TOK_LT) && (LA(2)==LITERAL_trace)) {
			traceCommand();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if ((_tokenSet_2.member(LA(1)))) {
			{
			declaration();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5085:
			do {
				if ((LA(1)==TOK_SEMIC) && (_tokenSet_2.member(LA(2)))) {
					match(TOK_SEMIC);
					declaration();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5085;
				}
				
			} while (true);
			}
			{
			match(TOK_SEMIC);
			query();
			astFactory.addASTChild(currentAST, returnAST);
			}
			}
			{
			if ((LA(1)==TOK_SEMIC)) {
				match(TOK_SEMIC);
			}
			else if ((LA(1)==EOF)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		else if ((_tokenSet_3.member(LA(1)))) {
			query();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==TOK_SEMIC)) {
				match(TOK_SEMIC);
			}
			else if ((LA(1)==EOF)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(Token.EOF_TYPE);
		if ( inputState.guessing==0 ) {
			queryProgram_AST = (AST)currentAST.root;
			queryProgram_AST =
			(AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(QUERY_PROGRAM,"queryProgram","com.gemstone.gemfire.cache.query.internal.parse.GemFireAST")).add(queryProgram_AST));
			currentAST.root = queryProgram_AST;
			currentAST.child = queryProgram_AST!=null &&queryProgram_AST.getFirstChild()!=null ?
				queryProgram_AST.getFirstChild() : queryProgram_AST;
			currentAST.advanceChildToEnd();
		}
		queryProgram_AST = (AST)currentAST.root;
		returnAST = queryProgram_AST;
	}
	
	public final void traceCommand() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST traceCommand_AST = null;
		
		{
		match(TOK_LT);
		com.gemstone.gemfire.cache.query.internal.parse.ASTTrace tmp7_AST = null;
		tmp7_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTTrace)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTTrace");
		astFactory.makeASTRoot(currentAST, tmp7_AST);
		match(LITERAL_trace);
		match(TOK_GT);
		}
		traceCommand_AST = (AST)currentAST.root;
		returnAST = traceCommand_AST;
	}
	
	public final void declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaration_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_define:
		{
			defineQuery();
			astFactory.addASTChild(currentAST, returnAST);
			declaration_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_import:
		{
			importQuery();
			astFactory.addASTChild(currentAST, returnAST);
			declaration_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_undefine:
		{
			undefineQuery();
			astFactory.addASTChild(currentAST, returnAST);
			declaration_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_declare:
		{
			paramTypeDecl();
			astFactory.addASTChild(currentAST, returnAST);
			declaration_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = declaration_AST;
	}
	
	public final void query() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST query_AST = null;
		
		{
		if ((LA(1)==TOK_LT||LA(1)==LITERAL_select)) {
			selectExpr();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_4.member(LA(1)))) {
			expr();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		query_AST = (AST)currentAST.root;
		returnAST = query_AST;
	}
	
	public final void loneFromClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST loneFromClause_AST = null;
		
		iteratorDef();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5093:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				iteratorDef();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5093;
			}
			
		} while (true);
		}
		match(Token.EOF_TYPE);
		if ( inputState.guessing==0 ) {
			loneFromClause_AST = (AST)currentAST.root;
			loneFromClause_AST =
			(AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(LITERAL_from,"from","com.gemstone.gemfire.cache.query.internal.parse.ASTCombination")).add(loneFromClause_AST));
			currentAST.root = loneFromClause_AST;
			currentAST.child = loneFromClause_AST!=null &&loneFromClause_AST.getFirstChild()!=null ?
				loneFromClause_AST.getFirstChild() : loneFromClause_AST;
			currentAST.advanceChildToEnd();
		}
		loneFromClause_AST = (AST)currentAST.root;
		returnAST = loneFromClause_AST;
	}
	
	public final void iteratorDef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST iteratorDef_AST = null;
		AST id1_AST = null;
		AST ex1_AST = null;
		AST t1_AST = null;
		AST ex2_AST = null;
		AST id2_AST = null;
		AST t2_AST = null;
		
		boolean synPredMatched5131 = false;
		if (((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==LITERAL_in))) {
			int _m5131 = mark();
			synPredMatched5131 = true;
			inputState.guessing++;
			try {
				{
				identifier();
				match(LITERAL_in);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched5131 = false;
			}
			rewind(_m5131);
inputState.guessing--;
		}
		if ( synPredMatched5131 ) {
			identifier();
			id1_AST = (AST)returnAST;
			match(LITERAL_in);
			expr();
			ex1_AST = (AST)returnAST;
			{
			if ((LA(1)==LITERAL_type)) {
				match(LITERAL_type);
				type();
				t1_AST = (AST)returnAST;
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				iteratorDef_AST = (AST)currentAST.root;
				iteratorDef_AST = (AST)astFactory.make( (new ASTArray(4)).add((AST)astFactory.create(ITERATOR_DEF,"iterDef","com.gemstone.gemfire.cache.query.internal.parse.ASTIteratorDef")).add(ex1_AST).add(id1_AST).add(t1_AST));
				currentAST.root = iteratorDef_AST;
				currentAST.child = iteratorDef_AST!=null &&iteratorDef_AST.getFirstChild()!=null ?
					iteratorDef_AST.getFirstChild() : iteratorDef_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else if ((_tokenSet_4.member(LA(1))) && (_tokenSet_6.member(LA(2)))) {
			expr();
			ex2_AST = (AST)returnAST;
			{
			if ((LA(1)==QuotedIdentifier||LA(1)==Identifier||LA(1)==LITERAL_as)) {
				{
				if ((LA(1)==LITERAL_as)) {
					match(LITERAL_as);
				}
				else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				identifier();
				id2_AST = (AST)returnAST;
			}
			else if ((_tokenSet_7.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==LITERAL_type)) {
				match(LITERAL_type);
				type();
				t2_AST = (AST)returnAST;
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				iteratorDef_AST = (AST)currentAST.root;
				iteratorDef_AST = (AST)astFactory.make( (new ASTArray(4)).add((AST)astFactory.create(ITERATOR_DEF,"iterDef","com.gemstone.gemfire.cache.query.internal.parse.ASTIteratorDef")).add(ex2_AST).add(id2_AST).add(t2_AST));
				currentAST.root = iteratorDef_AST;
				currentAST.child = iteratorDef_AST!=null &&iteratorDef_AST.getFirstChild()!=null ?
					iteratorDef_AST.getFirstChild() : iteratorDef_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = iteratorDef_AST;
	}
	
	public final void loneProjectionAttributes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST loneProjectionAttributes_AST = null;
		
		projectionAttributes();
		astFactory.addASTChild(currentAST, returnAST);
		match(Token.EOF_TYPE);
		loneProjectionAttributes_AST = (AST)currentAST.root;
		returnAST = loneProjectionAttributes_AST;
	}
	
	public final void projectionAttributes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST projectionAttributes_AST = null;
		
		{
		if ((_tokenSet_4.member(LA(1)))) {
			projection();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5142:
			do {
				if ((LA(1)==TOK_COMMA)) {
					match(TOK_COMMA);
					projection();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5142;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				projectionAttributes_AST = (AST)currentAST.root;
				projectionAttributes_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(PROJECTION_ATTRS,"projectionAttrs","com.gemstone.gemfire.cache.query.internal.parse.ASTCombination")).add(projectionAttributes_AST));
				currentAST.root = projectionAttributes_AST;
				currentAST.child = projectionAttributes_AST!=null &&projectionAttributes_AST.getFirstChild()!=null ?
					projectionAttributes_AST.getFirstChild() : projectionAttributes_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else if ((LA(1)==TOK_STAR)) {
			AST tmp17_AST = null;
			tmp17_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp17_AST);
			match(TOK_STAR);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		projectionAttributes_AST = (AST)currentAST.root;
		returnAST = projectionAttributes_AST;
	}
	
	public final void defineQuery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST defineQuery_AST = null;
		
		AST tmp18_AST = null;
		tmp18_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp18_AST);
		match(LITERAL_define);
		{
		if ((LA(1)==LITERAL_query)) {
			match(LITERAL_query);
		}
		else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==TOK_LPAREN)) {
			match(TOK_LPAREN);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5113:
			do {
				if ((LA(1)==TOK_COMMA)) {
					match(TOK_COMMA);
					type();
					astFactory.addASTChild(currentAST, returnAST);
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5113;
				}
				
			} while (true);
			}
			match(TOK_RPAREN);
		}
		else if ((LA(1)==LITERAL_as)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(LITERAL_as);
		query();
		astFactory.addASTChild(currentAST, returnAST);
		defineQuery_AST = (AST)currentAST.root;
		returnAST = defineQuery_AST;
	}
	
	public final void importQuery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST importQuery_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTImport tmp24_AST = null;
		tmp24_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTImport)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTImport");
		astFactory.makeASTRoot(currentAST, tmp24_AST);
		match(LITERAL_import);
		qualifiedName();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==LITERAL_as)) {
			AST tmp25_AST = null;
			tmp25_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp25_AST);
			match(LITERAL_as);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==EOF||LA(1)==TOK_SEMIC)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		importQuery_AST = (AST)currentAST.root;
		returnAST = importQuery_AST;
	}
	
	public final void undefineQuery() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST undefineQuery_AST = null;
		
		AST tmp26_AST = null;
		tmp26_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp26_AST);
		match(LITERAL_undefine);
		{
		if ((LA(1)==LITERAL_query)) {
			match(LITERAL_query);
		}
		else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		undefineQuery_AST = (AST)currentAST.root;
		returnAST = undefineQuery_AST;
	}
	
	public final void paramTypeDecl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST paramTypeDecl_AST = null;
		
		AST tmp28_AST = null;
		tmp28_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp28_AST);
		match(LITERAL_declare);
		{
		queryParam();
		astFactory.addASTChild(currentAST, returnAST);
		}
		{
		_loop5105:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				queryParam();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5105;
			}
			
		} while (true);
		}
		type();
		astFactory.addASTChild(currentAST, returnAST);
		paramTypeDecl_AST = (AST)currentAST.root;
		returnAST = paramTypeDecl_AST;
	}
	
	public final void qualifiedName() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST qualifiedName_AST = null;
		
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5108:
		do {
			if ((LA(1)==TOK_DOT)) {
				match(TOK_DOT);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5108;
			}
			
		} while (true);
		}
		qualifiedName_AST = (AST)currentAST.root;
		returnAST = qualifiedName_AST;
	}
	
	public final void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		Token  q = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier q_AST = null;
		
		if ((LA(1)==Identifier)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier tmp31_AST = null;
			tmp31_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier");
			astFactory.addASTChild(currentAST, tmp31_AST);
			match(Identifier);
			identifier_AST = (AST)currentAST.root;
		}
		else if ((LA(1)==QuotedIdentifier)) {
			q = LT(1);
			q_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier)astFactory.create(q,"com.gemstone.gemfire.cache.query.internal.parse.ASTIdentifier");
			astFactory.addASTChild(currentAST, q_AST);
			match(QuotedIdentifier);
			if ( inputState.guessing==0 ) {
				q_AST.setType(Identifier);
			}
			identifier_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = identifier_AST;
	}
	
	public final void loneImports() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST loneImports_AST = null;
		
		importQuery();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5100:
		do {
			if ((LA(1)==TOK_SEMIC) && (LA(2)==LITERAL_import)) {
				match(TOK_SEMIC);
				importQuery();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5100;
			}
			
		} while (true);
		}
		{
		if ((LA(1)==TOK_SEMIC)) {
			match(TOK_SEMIC);
		}
		else if ((LA(1)==EOF)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(Token.EOF_TYPE);
		if ( inputState.guessing==0 ) {
			loneImports_AST = (AST)currentAST.root;
			loneImports_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(IMPORTS,"imports","com.gemstone.gemfire.cache.query.internal.parse.GemFireAST")).add(loneImports_AST));
			currentAST.root = loneImports_AST;
			currentAST.child = loneImports_AST!=null &&loneImports_AST.getFirstChild()!=null ?
				loneImports_AST.getFirstChild() : loneImports_AST;
			currentAST.advanceChildToEnd();
		}
		loneImports_AST = (AST)currentAST.root;
		returnAST = loneImports_AST;
	}
	
	public final void queryParam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST queryParam_AST = null;
		
		AST tmp35_AST = null;
		tmp35_AST = astFactory.create(LT(1));
		match(TOK_DOLLAR);
		AST tmp36_AST = null;
		tmp36_AST = astFactory.create(LT(1));
		match(NUM_INT);
		if ( inputState.guessing==0 ) {
			queryParam_AST = (AST)currentAST.root;
			queryParam_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(QUERY_PARAM,"queryParam","com.gemstone.gemfire.cache.query.internal.parse.ASTParameter")).add(tmp36_AST));
			currentAST.root = queryParam_AST;
			currentAST.child = queryParam_AST!=null &&queryParam_AST.getFirstChild()!=null ?
				queryParam_AST.getFirstChild() : queryParam_AST;
			currentAST.advanceChildToEnd();
		}
		returnAST = queryParam_AST;
	}
	
	public final void type() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_AST = null;
		Token  typ00 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ00_AST = null;
		Token  typ01 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ01_AST = null;
		Token  typ02 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ02_AST = null;
		Token  typ03 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ03_AST = null;
		Token  typ04 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ04_AST = null;
		Token  typ05 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ05_AST = null;
		Token  typ06 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ06_AST = null;
		Token  typ07 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ07_AST = null;
		Token  typ08 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ08_AST = null;
		Token  typ09 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ09_AST = null;
		Token  typ10 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ10_AST = null;
		Token  typ11 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ11_AST = null;
		Token  typ12 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ12_AST = null;
		Token  typ13 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ13_AST = null;
		Token  typ14 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ14_AST = null;
		Token  typ15 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ15_AST = null;
		Token  typ16 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ16_AST = null;
		Token  typ17 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ17_AST = null;
		Token  typ18 = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTType typ18_AST = null;
		AST id_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_short:
		{
			typ00 = LT(1);
			typ00_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ00,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ00_AST);
			match(LITERAL_short);
			if ( inputState.guessing==0 ) {
				typ00_AST.setJavaType(TypeUtils.getObjectType(Short.class));
			}
			break;
		}
		case LITERAL_long:
		{
			typ01 = LT(1);
			typ01_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ01,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ01_AST);
			match(LITERAL_long);
			if ( inputState.guessing==0 ) {
				typ01_AST.setJavaType(TypeUtils.getObjectType(Long.class));
			}
			break;
		}
		case LITERAL_int:
		{
			typ02 = LT(1);
			typ02_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ02,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ02_AST);
			match(LITERAL_int);
			if ( inputState.guessing==0 ) {
				typ02_AST.setJavaType(TypeUtils.getObjectType(Integer.class));
			}
			break;
		}
		case LITERAL_float:
		{
			typ03 = LT(1);
			typ03_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ03,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ03_AST);
			match(LITERAL_float);
			if ( inputState.guessing==0 ) {
				typ03_AST.setJavaType(TypeUtils.getObjectType(Float.class));
			}
			break;
		}
		case LITERAL_double:
		{
			typ04 = LT(1);
			typ04_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ04,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ04_AST);
			match(LITERAL_double);
			if ( inputState.guessing==0 ) {
				typ04_AST.setJavaType(TypeUtils.getObjectType(Double.class));
			}
			break;
		}
		case LITERAL_char:
		{
			typ05 = LT(1);
			typ05_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ05,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ05_AST);
			match(LITERAL_char);
			if ( inputState.guessing==0 ) {
				typ05_AST.setJavaType(TypeUtils.getObjectType(Character.class));
			}
			break;
		}
		case LITERAL_string:
		{
			typ06 = LT(1);
			typ06_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ06,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ06_AST);
			match(LITERAL_string);
			if ( inputState.guessing==0 ) {
				typ06_AST.setJavaType(TypeUtils.getObjectType(String.class));
			}
			break;
		}
		case LITERAL_boolean:
		{
			typ07 = LT(1);
			typ07_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ07,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ07_AST);
			match(LITERAL_boolean);
			if ( inputState.guessing==0 ) {
				typ07_AST.setJavaType(TypeUtils.getObjectType(Boolean.class));
			}
			break;
		}
		case LITERAL_byte:
		{
			typ08 = LT(1);
			typ08_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ08,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ08_AST);
			match(LITERAL_byte);
			if ( inputState.guessing==0 ) {
				typ08_AST.setJavaType(TypeUtils.getObjectType(Byte.class));
			}
			break;
		}
		case LITERAL_octet:
		{
			typ09 = LT(1);
			typ09_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ09,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ09_AST);
			match(LITERAL_octet);
			if ( inputState.guessing==0 ) {
				typ09_AST.setJavaType(TypeUtils.getObjectType(Byte.class));
			}
			break;
		}
		case LITERAL_enum:
		{
			AST tmp37_AST = null;
			tmp37_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp37_AST);
			match(LITERAL_enum);
			{
			if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==TOK_DOT)) {
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp38_AST = null;
				tmp38_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp38_AST);
				match(TOK_DOT);
			}
			else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (_tokenSet_8.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_date:
		{
			typ10 = LT(1);
			typ10_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ10,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ10_AST);
			match(LITERAL_date);
			if ( inputState.guessing==0 ) {
				typ10_AST.setJavaType(TypeUtils.getObjectType(java.sql.Date.class));
			}
			break;
		}
		case LITERAL_time:
		{
			typ11 = LT(1);
			typ11_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ11,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ11_AST);
			match(LITERAL_time);
			if ( inputState.guessing==0 ) {
				typ11_AST.setJavaType(TypeUtils.getObjectType(java.sql.Time.class));
			}
			break;
		}
		case LITERAL_interval:
		{
			AST tmp39_AST = null;
			tmp39_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp39_AST);
			match(LITERAL_interval);
			break;
		}
		case LITERAL_timestamp:
		{
			typ12 = LT(1);
			typ12_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ12,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.addASTChild(currentAST, typ12_AST);
			match(LITERAL_timestamp);
			if ( inputState.guessing==0 ) {
				typ12_AST.setJavaType(TypeUtils.getObjectType(java.sql.Timestamp.class));
			}
			break;
		}
		case LITERAL_set:
		{
			typ13 = LT(1);
			typ13_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ13,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.makeASTRoot(currentAST, typ13_AST);
			match(LITERAL_set);
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			if ( inputState.guessing==0 ) {
				typ13_AST.setJavaType(new CollectionTypeImpl(Set.class, TypeUtils.OBJECT_TYPE /*resolved later*/));
			}
			break;
		}
		case LITERAL_collection:
		{
			typ14 = LT(1);
			typ14_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ14,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.makeASTRoot(currentAST, typ14_AST);
			match(LITERAL_collection);
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			if ( inputState.guessing==0 ) {
				typ14_AST.setJavaType(new CollectionTypeImpl(Collection.class, TypeUtils.OBJECT_TYPE /*resolved later*/));
			}
			break;
		}
		case LITERAL_bag:
		{
			AST tmp44_AST = null;
			tmp44_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp44_AST);
			match(LITERAL_bag);
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			break;
		}
		case LITERAL_list:
		{
			typ15 = LT(1);
			typ15_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ15,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.makeASTRoot(currentAST, typ15_AST);
			match(LITERAL_list);
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			if ( inputState.guessing==0 ) {
				typ15_AST.setJavaType(new CollectionTypeImpl(List.class, TypeUtils.OBJECT_TYPE /*resolved later*/));
			}
			break;
		}
		case LITERAL_array:
		{
			typ16 = LT(1);
			typ16_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ16,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
			astFactory.makeASTRoot(currentAST, typ16_AST);
			match(LITERAL_array);
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			if ( inputState.guessing==0 ) {
				typ16_AST.setJavaType(new CollectionTypeImpl(Object[].class, TypeUtils.OBJECT_TYPE /*resolved later*/));
			}
			break;
		}
		case LITERAL_dictionary:
		case LITERAL_map:
		{
			{
			if ((LA(1)==LITERAL_dictionary)) {
				typ17 = LT(1);
				typ17_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ17,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
				astFactory.makeASTRoot(currentAST, typ17_AST);
				match(LITERAL_dictionary);
				if ( inputState.guessing==0 ) {
					typ17_AST.setJavaType(new MapTypeImpl(Map.class, TypeUtils.OBJECT_TYPE , TypeUtils.OBJECT_TYPE /*resolved later*/));
				}
			}
			else if ((LA(1)==LITERAL_map)) {
				typ18 = LT(1);
				typ18_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTType)astFactory.create(typ18,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
				astFactory.makeASTRoot(currentAST, typ18_AST);
				match(LITERAL_map);
				if ( inputState.guessing==0 ) {
					typ18_AST.setJavaType(new MapTypeImpl(Map.class, TypeUtils.OBJECT_TYPE , TypeUtils.OBJECT_TYPE /*resolved later*/));
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(TOK_LT);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_COMMA);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_GT);
			break;
		}
		case QuotedIdentifier:
		case Identifier:
		{
			identifier();
			id_AST = (AST)returnAST;
			if ( inputState.guessing==0 ) {
				type_AST = (AST)currentAST.root;
				String txt = id_AST.getText();
				type_AST = (AST)astFactory.create(Identifier,txt,"com.gemstone.gemfire.cache.query.internal.parse.ASTType");
				((ASTType)type_AST).setTypeName(txt);
				currentAST.root = type_AST;
				currentAST.child = type_AST!=null &&type_AST.getFirstChild()!=null ?
					type_AST.getFirstChild() : type_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		type_AST = (AST)currentAST.root;
		returnAST = type_AST;
	}
	
	public final void selectExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST selectExpr_AST = null;
		
		{
		if ((LA(1)==TOK_LT)) {
			hintCommand();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==LITERAL_select)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		com.gemstone.gemfire.cache.query.internal.parse.ASTSelect tmp54_AST = null;
		tmp54_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTSelect)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTSelect");
		astFactory.makeASTRoot(currentAST, tmp54_AST);
		match(LITERAL_select);
		{
		if ((LA(1)==LITERAL_distinct) && (_tokenSet_9.member(LA(2)))) {
			AST tmp55_AST = null;
			tmp55_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp55_AST);
			match(LITERAL_distinct);
		}
		else if ((LA(1)==LITERAL_all)) {
			AST tmp56_AST = null;
			tmp56_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp56_AST);
			match(LITERAL_all);
		}
		else if ((_tokenSet_9.member(LA(1))) && (_tokenSet_10.member(LA(2)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if (((LA(1) >= LITERAL_sum && LA(1) <= LITERAL_count)) && (LA(2)==TOK_LPAREN)) {
			aggregateExpr();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_9.member(LA(1))) && (_tokenSet_10.member(LA(2)))) {
			projectionAttributes();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		fromClause();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==LITERAL_where)) {
			whereClause();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_11.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if ((LA(1)==LITERAL_group)) {
			groupClause();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_12.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if ((LA(1)==LITERAL_order)) {
			orderClause();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_13.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if ((LA(1)==LITERAL_limit)) {
			limitClause();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==EOF||LA(1)==TOK_RPAREN||LA(1)==TOK_SEMIC)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		selectExpr_AST = (AST)currentAST.root;
		returnAST = selectExpr_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		castExpr();
		astFactory.addASTChild(currentAST, returnAST);
		expr_AST = (AST)currentAST.root;
		returnAST = expr_AST;
	}
	
	public final void hintCommand() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST hintCommand_AST = null;
		
		match(TOK_LT);
		com.gemstone.gemfire.cache.query.internal.parse.ASTHint tmp58_AST = null;
		tmp58_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTHint)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTHint");
		astFactory.makeASTRoot(currentAST, tmp58_AST);
		match(LITERAL_hint);
		hintIdentifier();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5149:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				hintIdentifier();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5149;
			}
			
		} while (true);
		}
		match(TOK_GT);
		hintCommand_AST = (AST)currentAST.root;
		returnAST = hintCommand_AST;
	}
	
	public final void aggregateExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST aggregateExpr_AST = null;
		
		{
		if (((LA(1) >= LITERAL_sum && LA(1) <= LITERAL_avg))) {
			{
			switch ( LA(1)) {
			case LITERAL_sum:
			{
				AST tmp61_AST = null;
				tmp61_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp61_AST);
				match(LITERAL_sum);
				break;
			}
			case LITERAL_min:
			{
				AST tmp62_AST = null;
				tmp62_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp62_AST);
				match(LITERAL_min);
				break;
			}
			case LITERAL_max:
			{
				AST tmp63_AST = null;
				tmp63_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp63_AST);
				match(LITERAL_max);
				break;
			}
			case LITERAL_avg:
			{
				AST tmp64_AST = null;
				tmp64_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp64_AST);
				match(LITERAL_avg);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(TOK_LPAREN);
			query();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
		}
		else if ((LA(1)==LITERAL_count)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTCount tmp67_AST = null;
			tmp67_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCount)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCount");
			astFactory.makeASTRoot(currentAST, tmp67_AST);
			match(LITERAL_count);
			match(TOK_LPAREN);
			{
			if ((_tokenSet_3.member(LA(1)))) {
				query();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==TOK_STAR)) {
				AST tmp69_AST = null;
				tmp69_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp69_AST);
				match(TOK_STAR);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(TOK_RPAREN);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		aggregateExpr_AST = (AST)currentAST.root;
		returnAST = aggregateExpr_AST;
	}
	
	public final void fromClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fromClause_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTCombination tmp71_AST = null;
		tmp71_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCombination)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCombination");
		astFactory.makeASTRoot(currentAST, tmp71_AST);
		match(LITERAL_from);
		iteratorDef();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5128:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				iteratorDef();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5128;
			}
			
		} while (true);
		}
		fromClause_AST = (AST)currentAST.root;
		returnAST = fromClause_AST;
	}
	
	public final void whereClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST whereClause_AST = null;
		
		match(LITERAL_where);
		expr();
		astFactory.addASTChild(currentAST, returnAST);
		whereClause_AST = (AST)currentAST.root;
		returnAST = whereClause_AST;
	}
	
	public final void groupClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST groupClause_AST = null;
		
		AST tmp74_AST = null;
		tmp74_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp74_AST);
		match(LITERAL_group);
		match(LITERAL_by);
		fieldList();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==LITERAL_having)) {
			match(LITERAL_having);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_12.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		groupClause_AST = (AST)currentAST.root;
		returnAST = groupClause_AST;
	}
	
	public final void orderClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST orderClause_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTOrderBy tmp77_AST = null;
		tmp77_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTOrderBy)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTOrderBy");
		astFactory.makeASTRoot(currentAST, tmp77_AST);
		match(LITERAL_order);
		match(LITERAL_by);
		sortCriterion();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5153:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				sortCriterion();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5153;
			}
			
		} while (true);
		}
		orderClause_AST = (AST)currentAST.root;
		returnAST = orderClause_AST;
	}
	
	public final void limitClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST limitClause_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		match(LITERAL_limit);
		{
		if ((LA(1)==TOK_DOLLAR)) {
			AST tmp81_AST = null;
			tmp81_AST = astFactory.create(LT(1));
			match(TOK_DOLLAR);
			AST tmp82_AST = null;
			tmp82_AST = astFactory.create(LT(1));
			match(NUM_INT);
			if ( inputState.guessing==0 ) {
				limitClause_AST = (AST)currentAST.root;
				limitClause_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(LIMIT,"limitParam","com.gemstone.gemfire.cache.query.internal.parse.ASTParameter")).add(tmp82_AST));
				currentAST.root = limitClause_AST;
				currentAST.child = limitClause_AST!=null &&limitClause_AST.getFirstChild()!=null ?
					limitClause_AST.getFirstChild() : limitClause_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else if ((LA(1)==NUM_INT)) {
			n = LT(1);
			n_AST = astFactory.create(n);
			match(NUM_INT);
			if ( inputState.guessing==0 ) {
				limitClause_AST = (AST)currentAST.root;
				limitClause_AST =(AST)astFactory.create(LIMIT,n.getText(),"com.gemstone.gemfire.cache.query.internal.parse.ASTLimit") ;
				currentAST.root = limitClause_AST;
				currentAST.child = limitClause_AST!=null &&limitClause_AST.getFirstChild()!=null ?
					limitClause_AST.getFirstChild() : limitClause_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		returnAST = limitClause_AST;
	}
	
	public final void projection() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST projection_AST = null;
		AST lb1_AST = null;
		AST ex1_AST = null;
		AST ex2_AST = null;
		AST lb2_AST = null;
		
		if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==TOK_COLON)) {
			identifier();
			lb1_AST = (AST)returnAST;
			match(TOK_COLON);
			expr();
			ex1_AST = (AST)returnAST;
			if ( inputState.guessing==0 ) {
				projection_AST = (AST)currentAST.root;
				projection_AST = (AST)astFactory.make( (new ASTArray(3)).add((AST)astFactory.create(PROJECTION,"projection","com.gemstone.gemfire.cache.query.internal.parse.ASTProjection")).add(ex1_AST).add(lb1_AST));
				currentAST.root = projection_AST;
				currentAST.child = projection_AST!=null &&projection_AST.getFirstChild()!=null ?
					projection_AST.getFirstChild() : projection_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else if ((_tokenSet_4.member(LA(1))) && (_tokenSet_14.member(LA(2)))) {
			expr();
			ex2_AST = (AST)returnAST;
			{
			if ((LA(1)==LITERAL_as)) {
				match(LITERAL_as);
				identifier();
				lb2_AST = (AST)returnAST;
			}
			else if ((LA(1)==EOF||LA(1)==TOK_COMMA||LA(1)==LITERAL_from)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				projection_AST = (AST)currentAST.root;
				projection_AST = (AST)astFactory.make( (new ASTArray(3)).add((AST)astFactory.create(PROJECTION,"projection","com.gemstone.gemfire.cache.query.internal.parse.ASTProjection")).add(ex2_AST).add(lb2_AST));
				currentAST.root = projection_AST;
				currentAST.child = projection_AST!=null &&projection_AST.getFirstChild()!=null ?
					projection_AST.getFirstChild() : projection_AST;
				currentAST.advanceChildToEnd();
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = projection_AST;
	}
	
	public final void fieldList() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fieldList_AST = null;
		
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		match(TOK_COLON);
		expr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5242:
		do {
			if ((LA(1)==TOK_COMMA)) {
				match(TOK_COMMA);
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
				match(TOK_COLON);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5242;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			fieldList_AST = (AST)currentAST.root;
			fieldList_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(COMBO,"fieldList","com.gemstone.gemfire.cache.query.internal.parse.ASTCombination")).add(fieldList_AST));
			currentAST.root = fieldList_AST;
			currentAST.child = fieldList_AST!=null &&fieldList_AST.getFirstChild()!=null ?
				fieldList_AST.getFirstChild() : fieldList_AST;
			currentAST.advanceChildToEnd();
		}
		fieldList_AST = (AST)currentAST.root;
		returnAST = fieldList_AST;
	}
	
	public final void hintIdentifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST hintIdentifier_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		n = LT(1);
		n_AST = astFactory.create(n);
		astFactory.addASTChild(currentAST, n_AST);
		match(StringLiteral);
		if ( inputState.guessing==0 ) {
			hintIdentifier_AST = (AST)currentAST.root;
			hintIdentifier_AST =(AST)astFactory.create(HINT,n.getText(),"com.gemstone.gemfire.cache.query.internal.parse.ASTHintIdentifier") ;
			currentAST.root = hintIdentifier_AST;
			currentAST.child = hintIdentifier_AST!=null &&hintIdentifier_AST.getFirstChild()!=null ?
				hintIdentifier_AST.getFirstChild() : hintIdentifier_AST;
			currentAST.advanceChildToEnd();
		}
		hintIdentifier_AST = (AST)currentAST.root;
		returnAST = hintIdentifier_AST;
	}
	
	public final void sortCriterion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sortCriterion_AST = null;
		AST tok_AST = null;
		
		expr();
		tok_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			sortCriterion_AST = (AST)currentAST.root;
			sortCriterion_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(SORT_CRITERION,"asc","com.gemstone.gemfire.cache.query.internal.parse.ASTSortCriterion")).add(tok_AST));
			currentAST.root = sortCriterion_AST;
			currentAST.child = sortCriterion_AST!=null &&sortCriterion_AST.getFirstChild()!=null ?
				sortCriterion_AST.getFirstChild() : sortCriterion_AST;
			currentAST.advanceChildToEnd();
		}
		{
		switch ( LA(1)) {
		case LITERAL_asc:
		{
			match(LITERAL_asc);
			if ( inputState.guessing==0 ) {
				sortCriterion_AST = (AST)currentAST.root;
				sortCriterion_AST.setText("asc");
			}
			break;
		}
		case LITERAL_desc:
		{
			match(LITERAL_desc);
			if ( inputState.guessing==0 ) {
				sortCriterion_AST = (AST)currentAST.root;
				sortCriterion_AST.setText("desc");
			}
			break;
		}
		case EOF:
		case TOK_RPAREN:
		case TOK_COMMA:
		case TOK_SEMIC:
		case LITERAL_limit:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		sortCriterion_AST = (AST)currentAST.root;
		returnAST = sortCriterion_AST;
	}
	
	public final void castExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST castExpr_AST = null;
		Token  lp = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTTypeCast lp_AST = null;
		
		boolean synPredMatched5159 = false;
		if (((LA(1)==TOK_LPAREN) && (_tokenSet_15.member(LA(2))))) {
			int _m5159 = mark();
			synPredMatched5159 = true;
			inputState.guessing++;
			try {
				{
				match(TOK_LPAREN);
				type();
				match(TOK_RPAREN);
				castExpr();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched5159 = false;
			}
			rewind(_m5159);
inputState.guessing--;
		}
		if ( synPredMatched5159 ) {
			lp = LT(1);
			lp_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTTypeCast)astFactory.create(lp,"com.gemstone.gemfire.cache.query.internal.parse.ASTTypeCast");
			astFactory.makeASTRoot(currentAST, lp_AST);
			match(TOK_LPAREN);
			if ( inputState.guessing==0 ) {
				lp_AST.setType(TYPECAST); lp_AST.setText("typecast");
			}
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
			castExpr();
			astFactory.addASTChild(currentAST, returnAST);
			castExpr_AST = (AST)currentAST.root;
		}
		else if ((_tokenSet_4.member(LA(1))) && (_tokenSet_16.member(LA(2)))) {
			orExpr();
			astFactory.addASTChild(currentAST, returnAST);
			castExpr_AST = (AST)currentAST.root;
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		returnAST = castExpr_AST;
	}
	
	public final void orExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST orExpr_AST = null;
		boolean cmplx = false;
		
		orelseExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5162:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				orelseExpr();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else {
				break _loop5162;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			orExpr_AST = (AST)currentAST.root;
			if (cmplx) {
			orExpr_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(LITERAL_or,"or","com.gemstone.gemfire.cache.query.internal.parse.ASTOr")).add(orExpr_AST)); }
			currentAST.root = orExpr_AST;
			currentAST.child = orExpr_AST!=null &&orExpr_AST.getFirstChild()!=null ?
				orExpr_AST.getFirstChild() : orExpr_AST;
			currentAST.advanceChildToEnd();
		}
		orExpr_AST = (AST)currentAST.root;
		returnAST = orExpr_AST;
	}
	
	public final void orelseExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST orelseExpr_AST = null;
		boolean cmplx = false;
		
		andExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5165:
		do {
			if ((LA(1)==LITERAL_orelse)) {
				match(LITERAL_orelse);
				andExpr();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else {
				break _loop5165;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			orelseExpr_AST = (AST)currentAST.root;
			if (cmplx) { orelseExpr_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LITERAL_orelse,"or")).add(orelseExpr_AST)); }
			currentAST.root = orelseExpr_AST;
			currentAST.child = orelseExpr_AST!=null &&orelseExpr_AST.getFirstChild()!=null ?
				orelseExpr_AST.getFirstChild() : orelseExpr_AST;
			currentAST.advanceChildToEnd();
		}
		orelseExpr_AST = (AST)currentAST.root;
		returnAST = orelseExpr_AST;
	}
	
	public final void andExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST andExpr_AST = null;
		boolean cmplx = false;
		
		quantifierExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5168:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				quantifierExpr();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else {
				break _loop5168;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			andExpr_AST = (AST)currentAST.root;
			if (cmplx) {
			andExpr_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(LITERAL_and,"and","com.gemstone.gemfire.cache.query.internal.parse.ASTAnd")).add(andExpr_AST)); }
			currentAST.root = andExpr_AST;
			currentAST.child = andExpr_AST!=null &&andExpr_AST.getFirstChild()!=null ?
				andExpr_AST.getFirstChild() : andExpr_AST;
			currentAST.advanceChildToEnd();
		}
		andExpr_AST = (AST)currentAST.root;
		returnAST = andExpr_AST;
	}
	
	public final void quantifierExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST quantifierExpr_AST = null;
		
		if ((LA(1)==LITERAL_for)) {
			AST tmp94_AST = null;
			tmp94_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp94_AST);
			match(LITERAL_for);
			match(LITERAL_all);
			inClause();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_COLON);
			andthenExpr();
			astFactory.addASTChild(currentAST, returnAST);
			quantifierExpr_AST = (AST)currentAST.root;
		}
		else {
			boolean synPredMatched5171 = false;
			if (((LA(1)==LITERAL_exists) && (LA(2)==QuotedIdentifier||LA(2)==Identifier))) {
				int _m5171 = mark();
				synPredMatched5171 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_exists);
					identifier();
					match(LITERAL_in);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched5171 = false;
				}
				rewind(_m5171);
inputState.guessing--;
			}
			if ( synPredMatched5171 ) {
				AST tmp97_AST = null;
				tmp97_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp97_AST);
				match(LITERAL_exists);
				inClause();
				astFactory.addASTChild(currentAST, returnAST);
				match(TOK_COLON);
				andthenExpr();
				astFactory.addASTChild(currentAST, returnAST);
				quantifierExpr_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_18.member(LA(2)))) {
				andthenExpr();
				astFactory.addASTChild(currentAST, returnAST);
				quantifierExpr_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			returnAST = quantifierExpr_AST;
		}
		
	public final void inClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST inClause_AST = null;
		
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		match(LITERAL_in);
		expr();
		astFactory.addASTChild(currentAST, returnAST);
		inClause_AST = (AST)currentAST.root;
		returnAST = inClause_AST;
	}
	
	public final void andthenExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST andthenExpr_AST = null;
		boolean cmplx = false;
		
		equalityExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5175:
		do {
			if ((LA(1)==LITERAL_andthen)) {
				match(LITERAL_andthen);
				equalityExpr();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else {
				break _loop5175;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			andthenExpr_AST = (AST)currentAST.root;
			if (cmplx) { andthenExpr_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LITERAL_andthen,"andthen")).add(andthenExpr_AST)); }
			currentAST.root = andthenExpr_AST;
			currentAST.child = andthenExpr_AST!=null &&andthenExpr_AST.getFirstChild()!=null ?
				andthenExpr_AST.getFirstChild() : andthenExpr_AST;
			currentAST.advanceChildToEnd();
		}
		andthenExpr_AST = (AST)currentAST.root;
		returnAST = andthenExpr_AST;
	}
	
	public final void equalityExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST equalityExpr_AST = null;
		
		relationalExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==TOK_EQ||LA(1)==TOK_NE)) {
			{
			int _cnt5181=0;
			_loop5181:
			do {
				if ((LA(1)==TOK_EQ||LA(1)==TOK_NE)) {
					{
					if ((LA(1)==TOK_EQ)) {
						com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp101_AST = null;
						tmp101_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
						astFactory.makeASTRoot(currentAST, tmp101_AST);
						match(TOK_EQ);
					}
					else if ((LA(1)==TOK_NE)) {
						com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp102_AST = null;
						tmp102_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
						astFactory.makeASTRoot(currentAST, tmp102_AST);
						match(TOK_NE);
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					switch ( LA(1)) {
					case LITERAL_all:
					{
						AST tmp103_AST = null;
						tmp103_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp103_AST);
						match(LITERAL_all);
						break;
					}
					case LITERAL_any:
					{
						AST tmp104_AST = null;
						tmp104_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp104_AST);
						match(LITERAL_any);
						break;
					}
					case LITERAL_some:
					{
						AST tmp105_AST = null;
						tmp105_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp105_AST);
						match(LITERAL_some);
						break;
					}
					case TOK_LPAREN:
					case TOK_PLUS:
					case TOK_MINUS:
					case TOK_DOLLAR:
					case QuotedIdentifier:
					case Identifier:
					case RegionPath:
					case NUM_INT:
					case StringLiteral:
					case LITERAL_distinct:
					case LITERAL_exists:
					case LITERAL_abs:
					case LITERAL_not:
					case LITERAL_listtoset:
					case LITERAL_element:
					case LITERAL_flatten:
					case LITERAL_nvl:
					case LITERAL_to_date:
					case LITERAL_first:
					case LITERAL_last:
					case LITERAL_unique:
					case LITERAL_sum:
					case LITERAL_min:
					case LITERAL_max:
					case LITERAL_avg:
					case LITERAL_count:
					case LITERAL_is_undefined:
					case LITERAL_is_defined:
					case LITERAL_struct:
					case LITERAL_array:
					case LITERAL_set:
					case LITERAL_bag:
					case LITERAL_list:
					case LITERAL_char:
					case LITERAL_date:
					case LITERAL_time:
					case LITERAL_timestamp:
					case LITERAL_nil:
					case LITERAL_null:
					case LITERAL_undefined:
					case LITERAL_true:
					case LITERAL_false:
					case NUM_LONG:
					case NUM_FLOAT:
					case NUM_DOUBLE:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					relationalExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt5181>=1 ) { break _loop5181; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt5181++;
			} while (true);
			}
		}
		else if ((_tokenSet_19.member(LA(1)))) {
			{
			_loop5183:
			do {
				if ((LA(1)==LITERAL_like)) {
					com.gemstone.gemfire.cache.query.internal.parse.ASTLike tmp106_AST = null;
					tmp106_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLike)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLike");
					astFactory.makeASTRoot(currentAST, tmp106_AST);
					match(LITERAL_like);
					relationalExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5183;
				}
				
			} while (true);
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		equalityExpr_AST = (AST)currentAST.root;
		returnAST = equalityExpr_AST;
	}
	
	public final void relationalExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST relationalExpr_AST = null;
		
		additiveExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5189:
		do {
			if ((_tokenSet_20.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case TOK_LT:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp107_AST = null;
					tmp107_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
					astFactory.makeASTRoot(currentAST, tmp107_AST);
					match(TOK_LT);
					break;
				}
				case TOK_GT:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp108_AST = null;
					tmp108_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
					astFactory.makeASTRoot(currentAST, tmp108_AST);
					match(TOK_GT);
					break;
				}
				case TOK_LE:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp109_AST = null;
					tmp109_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
					astFactory.makeASTRoot(currentAST, tmp109_AST);
					match(TOK_LE);
					break;
				}
				case TOK_GE:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp tmp110_AST = null;
					tmp110_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTCompareOp");
					astFactory.makeASTRoot(currentAST, tmp110_AST);
					match(TOK_GE);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((_tokenSet_17.member(LA(1)))) {
					additiveExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==LITERAL_all||LA(1)==LITERAL_any||LA(1)==LITERAL_some)) {
					{
					switch ( LA(1)) {
					case LITERAL_all:
					{
						AST tmp111_AST = null;
						tmp111_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp111_AST);
						match(LITERAL_all);
						break;
					}
					case LITERAL_any:
					{
						AST tmp112_AST = null;
						tmp112_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp112_AST);
						match(LITERAL_any);
						break;
					}
					case LITERAL_some:
					{
						AST tmp113_AST = null;
						tmp113_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp113_AST);
						match(LITERAL_some);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					additiveExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else {
				break _loop5189;
			}
			
		} while (true);
		}
		relationalExpr_AST = (AST)currentAST.root;
		returnAST = relationalExpr_AST;
	}
	
	public final void additiveExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST additiveExpr_AST = null;
		
		multiplicativeExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5193:
		do {
			if ((_tokenSet_21.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case TOK_PLUS:
				{
					AST tmp114_AST = null;
					tmp114_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp114_AST);
					match(TOK_PLUS);
					break;
				}
				case TOK_MINUS:
				{
					AST tmp115_AST = null;
					tmp115_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp115_AST);
					match(TOK_MINUS);
					break;
				}
				case TOK_CONCAT:
				{
					AST tmp116_AST = null;
					tmp116_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp116_AST);
					match(TOK_CONCAT);
					break;
				}
				case LITERAL_union:
				{
					AST tmp117_AST = null;
					tmp117_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp117_AST);
					match(LITERAL_union);
					break;
				}
				case LITERAL_except:
				{
					AST tmp118_AST = null;
					tmp118_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp118_AST);
					match(LITERAL_except);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				multiplicativeExpr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5193;
			}
			
		} while (true);
		}
		additiveExpr_AST = (AST)currentAST.root;
		returnAST = additiveExpr_AST;
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST multiplicativeExpr_AST = null;
		
		inExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5197:
		do {
			if ((_tokenSet_22.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case TOK_STAR:
				{
					AST tmp119_AST = null;
					tmp119_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp119_AST);
					match(TOK_STAR);
					break;
				}
				case TOK_SLASH:
				{
					AST tmp120_AST = null;
					tmp120_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp120_AST);
					match(TOK_SLASH);
					break;
				}
				case LITERAL_mod:
				{
					AST tmp121_AST = null;
					tmp121_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp121_AST);
					match(LITERAL_mod);
					break;
				}
				case LITERAL_intersect:
				{
					AST tmp122_AST = null;
					tmp122_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp122_AST);
					match(LITERAL_intersect);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				inExpr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop5197;
			}
			
		} while (true);
		}
		multiplicativeExpr_AST = (AST)currentAST.root;
		returnAST = multiplicativeExpr_AST;
	}
	
	public final void inExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST inExpr_AST = null;
		
		unaryExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==LITERAL_in)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTIn tmp123_AST = null;
			tmp123_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTIn)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTIn");
			astFactory.makeASTRoot(currentAST, tmp123_AST);
			match(LITERAL_in);
			unaryExpr();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((_tokenSet_23.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		inExpr_AST = (AST)currentAST.root;
		returnAST = inExpr_AST;
	}
	
	public final void unaryExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unaryExpr_AST = null;
		
		{
		_loop5203:
		do {
			if ((_tokenSet_24.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case TOK_PLUS:
				{
					match(TOK_PLUS);
					break;
				}
				case TOK_MINUS:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTUnary tmp125_AST = null;
					tmp125_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTUnary)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTUnary");
					astFactory.makeASTRoot(currentAST, tmp125_AST);
					match(TOK_MINUS);
					break;
				}
				case LITERAL_abs:
				{
					AST tmp126_AST = null;
					tmp126_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp126_AST);
					match(LITERAL_abs);
					break;
				}
				case LITERAL_not:
				{
					com.gemstone.gemfire.cache.query.internal.parse.ASTUnary tmp127_AST = null;
					tmp127_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTUnary)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTUnary");
					astFactory.makeASTRoot(currentAST, tmp127_AST);
					match(LITERAL_not);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			else {
				break _loop5203;
			}
			
		} while (true);
		}
		postfixExpr();
		astFactory.addASTChild(currentAST, returnAST);
		unaryExpr_AST = (AST)currentAST.root;
		returnAST = unaryExpr_AST;
	}
	
	public final void postfixExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST postfixExpr_AST = null;
		boolean cmplx = false;
		
		primaryExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop5208:
		do {
			if ((LA(1)==TOK_LBRACK)) {
				match(TOK_LBRACK);
				index();
				astFactory.addASTChild(currentAST, returnAST);
				match(TOK_RBRACK);
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else if ((LA(1)==TOK_DOT||LA(1)==TOK_INDIRECT)) {
				{
				if ((LA(1)==TOK_DOT)) {
					match(TOK_DOT);
				}
				else if ((LA(1)==TOK_INDIRECT)) {
					match(TOK_INDIRECT);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==TOK_LPAREN)) {
					methodInvocation(false);
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (_tokenSet_25.member(LA(2)))) {
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					cmplx = true;
				}
			}
			else {
				break _loop5208;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			postfixExpr_AST = (AST)currentAST.root;
			if (cmplx) {
			postfixExpr_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(POSTFIX,"postfix","com.gemstone.gemfire.cache.query.internal.parse.ASTPostfix")).add(postfixExpr_AST));
			}
			
			currentAST.root = postfixExpr_AST;
			currentAST.child = postfixExpr_AST!=null &&postfixExpr_AST.getFirstChild()!=null ?
				postfixExpr_AST.getFirstChild() : postfixExpr_AST;
			currentAST.advanceChildToEnd();
		}
		postfixExpr_AST = (AST)currentAST.root;
		returnAST = postfixExpr_AST;
	}
	
	public final void primaryExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST primaryExpr_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_distinct:
		case LITERAL_listtoset:
		case LITERAL_element:
		case LITERAL_flatten:
		case LITERAL_nvl:
		case LITERAL_to_date:
		{
			conversionExpr();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_exists:
		case LITERAL_first:
		case LITERAL_last:
		case LITERAL_unique:
		{
			collectionExpr();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_sum:
		case LITERAL_min:
		case LITERAL_max:
		case LITERAL_avg:
		case LITERAL_count:
		{
			aggregateExpr();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_is_undefined:
		case LITERAL_is_defined:
		{
			undefinedExpr();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_struct:
		{
			structConstruction();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_array:
		case LITERAL_set:
		case LITERAL_bag:
		case LITERAL_list:
		{
			collectionConstruction();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case TOK_DOLLAR:
		{
			queryParam();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case NUM_INT:
		case StringLiteral:
		case LITERAL_char:
		case LITERAL_date:
		case LITERAL_time:
		case LITERAL_timestamp:
		case LITERAL_nil:
		case LITERAL_null:
		case LITERAL_undefined:
		case LITERAL_true:
		case LITERAL_false:
		case NUM_LONG:
		case NUM_FLOAT:
		case NUM_DOUBLE:
		{
			literal();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case TOK_LPAREN:
		{
			match(TOK_LPAREN);
			query();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
			break;
		}
		case RegionPath:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTRegionPath tmp134_AST = null;
			tmp134_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTRegionPath)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTRegionPath");
			astFactory.addASTChild(currentAST, tmp134_AST);
			match(RegionPath);
			break;
		}
		default:
			boolean synPredMatched5222 = false;
			if (((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==TOK_LPAREN))) {
				int _m5222 = mark();
				synPredMatched5222 = true;
				inputState.guessing++;
				try {
					{
					identifier();
					match(TOK_LPAREN);
					identifier();
					match(TOK_COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched5222 = false;
				}
				rewind(_m5222);
inputState.guessing--;
			}
			if ( synPredMatched5222 ) {
				objectConstruction();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (LA(2)==TOK_LPAREN)) {
				methodInvocation(true);
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==QuotedIdentifier||LA(1)==Identifier) && (_tokenSet_25.member(LA(2)))) {
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		primaryExpr_AST = (AST)currentAST.root;
		returnAST = primaryExpr_AST;
	}
	
	public final void index() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST index_AST = null;
		
		{
		if ((_tokenSet_4.member(LA(1)))) {
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==TOK_COMMA||LA(1)==TOK_RBRACK)) {
				{
				_loop5214:
				do {
					if ((LA(1)==TOK_COMMA)) {
						match(TOK_COMMA);
						expr();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop5214;
					}
					
				} while (true);
				}
			}
			else if ((LA(1)==TOK_COLON)) {
				AST tmp136_AST = null;
				tmp136_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp136_AST);
				match(TOK_COLON);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		else if ((LA(1)==TOK_STAR)) {
			AST tmp137_AST = null;
			tmp137_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp137_AST);
			match(TOK_STAR);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			index_AST = (AST)currentAST.root;
			index_AST = (AST)astFactory.make( (new ASTArray(2)).add((AST)astFactory.create(TOK_LBRACK,"index","com.gemstone.gemfire.cache.query.internal.parse.ASTCombination")).add(index_AST));
			
			currentAST.root = index_AST;
			currentAST.child = index_AST!=null &&index_AST.getFirstChild()!=null ?
				index_AST.getFirstChild() : index_AST;
			currentAST.advanceChildToEnd();
		}
		index_AST = (AST)currentAST.root;
		returnAST = index_AST;
	}
	
	public final void methodInvocation(
		boolean implicitReceiver
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST methodInvocation_AST = null;
		AST methodName_AST = null;
		AST args_AST = null;
		
		identifier();
		methodName_AST = (AST)returnAST;
		argList();
		args_AST = (AST)returnAST;
		if ( inputState.guessing==0 ) {
			methodInvocation_AST = (AST)currentAST.root;
			methodInvocation_AST =
			(AST)astFactory.make( (new ASTArray(3)).add((AST)astFactory.create(METHOD_INV,"methodInv","com.gemstone.gemfire.cache.query.internal.parse.ASTMethodInvocation")).add(methodName_AST).add(args_AST)); 
			((ASTMethodInvocation)methodInvocation_AST).setImplicitReceiver(implicitReceiver);
			currentAST.root = methodInvocation_AST;
			currentAST.child = methodInvocation_AST!=null &&methodInvocation_AST.getFirstChild()!=null ?
				methodInvocation_AST.getFirstChild() : methodInvocation_AST;
			currentAST.advanceChildToEnd();
		}
		returnAST = methodInvocation_AST;
	}
	
	public final void argList() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST argList_AST = null;
		Token  t = null;
		com.gemstone.gemfire.cache.query.internal.parse.ASTCombination t_AST = null;
		
		t = LT(1);
		t_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTCombination)astFactory.create(t,"com.gemstone.gemfire.cache.query.internal.parse.ASTCombination");
		astFactory.makeASTRoot(currentAST, t_AST);
		match(TOK_LPAREN);
		{
		if ((_tokenSet_4.member(LA(1)))) {
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5218:
			do {
				if ((LA(1)==TOK_COMMA)) {
					match(TOK_COMMA);
					expr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5218;
				}
				
			} while (true);
			}
		}
		else if ((LA(1)==TOK_RPAREN)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(TOK_RPAREN);
		argList_AST = (AST)currentAST.root;
		returnAST = argList_AST;
	}
	
	public final void conversionExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conversionExpr_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_distinct:
		case LITERAL_listtoset:
		case LITERAL_element:
		case LITERAL_flatten:
		{
			{
			switch ( LA(1)) {
			case LITERAL_listtoset:
			{
				AST tmp140_AST = null;
				tmp140_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp140_AST);
				match(LITERAL_listtoset);
				break;
			}
			case LITERAL_element:
			{
				com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr tmp141_AST = null;
				tmp141_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr");
				astFactory.makeASTRoot(currentAST, tmp141_AST);
				match(LITERAL_element);
				break;
			}
			case LITERAL_distinct:
			{
				AST tmp142_AST = null;
				tmp142_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp142_AST);
				match(LITERAL_distinct);
				break;
			}
			case LITERAL_flatten:
			{
				AST tmp143_AST = null;
				tmp143_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp143_AST);
				match(LITERAL_flatten);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(TOK_LPAREN);
			query();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
			break;
		}
		case LITERAL_nvl:
		{
			{
			{
			com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr tmp146_AST = null;
			tmp146_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr");
			astFactory.makeASTRoot(currentAST, tmp146_AST);
			match(LITERAL_nvl);
			}
			match(TOK_LPAREN);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_COMMA);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
			}
			break;
		}
		case LITERAL_to_date:
		{
			{
			{
			com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr tmp150_AST = null;
			tmp150_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTConversionExpr");
			astFactory.makeASTRoot(currentAST, tmp150_AST);
			match(LITERAL_to_date);
			}
			match(TOK_LPAREN);
			stringLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_COMMA);
			stringLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			match(TOK_RPAREN);
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		conversionExpr_AST = (AST)currentAST.root;
		returnAST = conversionExpr_AST;
	}
	
	public final void collectionExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST collectionExpr_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_first:
		{
			AST tmp154_AST = null;
			tmp154_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp154_AST);
			match(LITERAL_first);
			break;
		}
		case LITERAL_last:
		{
			AST tmp155_AST = null;
			tmp155_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp155_AST);
			match(LITERAL_last);
			break;
		}
		case LITERAL_unique:
		{
			AST tmp156_AST = null;
			tmp156_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp156_AST);
			match(LITERAL_unique);
			break;
		}
		case LITERAL_exists:
		{
			AST tmp157_AST = null;
			tmp157_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp157_AST);
			match(LITERAL_exists);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(TOK_LPAREN);
		query();
		astFactory.addASTChild(currentAST, returnAST);
		match(TOK_RPAREN);
		collectionExpr_AST = (AST)currentAST.root;
		returnAST = collectionExpr_AST;
	}
	
	public final void undefinedExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST undefinedExpr_AST = null;
		
		{
		if ((LA(1)==LITERAL_is_undefined)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr tmp160_AST = null;
			tmp160_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr");
			astFactory.makeASTRoot(currentAST, tmp160_AST);
			match(LITERAL_is_undefined);
		}
		else if ((LA(1)==LITERAL_is_defined)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr tmp161_AST = null;
			tmp161_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTUndefinedExpr");
			astFactory.makeASTRoot(currentAST, tmp161_AST);
			match(LITERAL_is_defined);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(TOK_LPAREN);
		query();
		astFactory.addASTChild(currentAST, returnAST);
		match(TOK_RPAREN);
		undefinedExpr_AST = (AST)currentAST.root;
		returnAST = undefinedExpr_AST;
	}
	
	public final void objectConstruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST objectConstruction_AST = null;
		Token  t = null;
		AST t_AST = null;
		
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		t = LT(1);
		t_AST = astFactory.create(t);
		astFactory.makeASTRoot(currentAST, t_AST);
		match(TOK_LPAREN);
		if ( inputState.guessing==0 ) {
			t_AST.setType(OBJ_CONSTRUCTOR);
		}
		fieldList();
		astFactory.addASTChild(currentAST, returnAST);
		match(TOK_RPAREN);
		objectConstruction_AST = (AST)currentAST.root;
		returnAST = objectConstruction_AST;
	}
	
	public final void structConstruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST structConstruction_AST = null;
		
		AST tmp165_AST = null;
		tmp165_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp165_AST);
		match(LITERAL_struct);
		match(TOK_LPAREN);
		fieldList();
		astFactory.addASTChild(currentAST, returnAST);
		match(TOK_RPAREN);
		structConstruction_AST = (AST)currentAST.root;
		returnAST = structConstruction_AST;
	}
	
	public final void collectionConstruction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST collectionConstruction_AST = null;
		
		{
		if (((LA(1) >= LITERAL_array && LA(1) <= LITERAL_bag))) {
			{
			switch ( LA(1)) {
			case LITERAL_array:
			{
				AST tmp168_AST = null;
				tmp168_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp168_AST);
				match(LITERAL_array);
				break;
			}
			case LITERAL_set:
			{
				com.gemstone.gemfire.cache.query.internal.parse.ASTConstruction tmp169_AST = null;
				tmp169_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTConstruction)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTConstruction");
				astFactory.makeASTRoot(currentAST, tmp169_AST);
				match(LITERAL_set);
				break;
			}
			case LITERAL_bag:
			{
				AST tmp170_AST = null;
				tmp170_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp170_AST);
				match(LITERAL_bag);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			argList();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==LITERAL_list)) {
			AST tmp171_AST = null;
			tmp171_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp171_AST);
			match(LITERAL_list);
			AST tmp172_AST = null;
			tmp172_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp172_AST);
			match(TOK_LPAREN);
			{
			if ((_tokenSet_4.member(LA(1)))) {
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				{
				if ((LA(1)==TOK_DOTDOT)) {
					AST tmp173_AST = null;
					tmp173_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp173_AST);
					match(TOK_DOTDOT);
					expr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==TOK_RPAREN||LA(1)==TOK_COMMA)) {
					{
					_loop5249:
					do {
						if ((LA(1)==TOK_COMMA)) {
							AST tmp174_AST = null;
							tmp174_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp174_AST);
							match(TOK_COMMA);
							expr();
							astFactory.addASTChild(currentAST, returnAST);
						}
						else {
							break _loop5249;
						}
						
					} while (true);
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else if ((LA(1)==TOK_RPAREN)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			AST tmp175_AST = null;
			tmp175_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp175_AST);
			match(TOK_RPAREN);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		collectionConstruction_AST = (AST)currentAST.root;
		returnAST = collectionConstruction_AST;
	}
	
	public final void literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST literal_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_nil:
		case LITERAL_null:
		case LITERAL_undefined:
		{
			objectLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_true:
		case LITERAL_false:
		{
			booleanLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case NUM_INT:
		case NUM_LONG:
		case NUM_FLOAT:
		case NUM_DOUBLE:
		{
			numericLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_char:
		{
			charLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case StringLiteral:
		{
			stringLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_date:
		{
			dateLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_time:
		{
			timeLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_timestamp:
		{
			timestampLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			literal_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = literal_AST;
	}
	
	public final void stringLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stringLiteral_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp176_AST = null;
		tmp176_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
		astFactory.addASTChild(currentAST, tmp176_AST);
		match(StringLiteral);
		stringLiteral_AST = (AST)currentAST.root;
		returnAST = stringLiteral_AST;
	}
	
	public final void objectLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST objectLiteral_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_nil:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp177_AST = null;
			tmp177_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp177_AST);
			match(LITERAL_nil);
			objectLiteral_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_null:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp178_AST = null;
			tmp178_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp178_AST);
			match(LITERAL_null);
			objectLiteral_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_undefined:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp179_AST = null;
			tmp179_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp179_AST);
			match(LITERAL_undefined);
			objectLiteral_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = objectLiteral_AST;
	}
	
	public final void booleanLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST booleanLiteral_AST = null;
		
		{
		if ((LA(1)==LITERAL_true)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp180_AST = null;
			tmp180_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp180_AST);
			match(LITERAL_true);
		}
		else if ((LA(1)==LITERAL_false)) {
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp181_AST = null;
			tmp181_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp181_AST);
			match(LITERAL_false);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		booleanLiteral_AST = (AST)currentAST.root;
		returnAST = booleanLiteral_AST;
	}
	
	public final void numericLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST numericLiteral_AST = null;
		
		{
		switch ( LA(1)) {
		case NUM_INT:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp182_AST = null;
			tmp182_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp182_AST);
			match(NUM_INT);
			break;
		}
		case NUM_LONG:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp183_AST = null;
			tmp183_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp183_AST);
			match(NUM_LONG);
			break;
		}
		case NUM_FLOAT:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp184_AST = null;
			tmp184_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp184_AST);
			match(NUM_FLOAT);
			break;
		}
		case NUM_DOUBLE:
		{
			com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp185_AST = null;
			tmp185_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
			astFactory.addASTChild(currentAST, tmp185_AST);
			match(NUM_DOUBLE);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		numericLiteral_AST = (AST)currentAST.root;
		returnAST = numericLiteral_AST;
	}
	
	public final void charLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST charLiteral_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp186_AST = null;
		tmp186_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
		astFactory.makeASTRoot(currentAST, tmp186_AST);
		match(LITERAL_char);
		AST tmp187_AST = null;
		tmp187_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp187_AST);
		match(StringLiteral);
		charLiteral_AST = (AST)currentAST.root;
		returnAST = charLiteral_AST;
	}
	
	public final void dateLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dateLiteral_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp188_AST = null;
		tmp188_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
		astFactory.makeASTRoot(currentAST, tmp188_AST);
		match(LITERAL_date);
		AST tmp189_AST = null;
		tmp189_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp189_AST);
		match(StringLiteral);
		dateLiteral_AST = (AST)currentAST.root;
		returnAST = dateLiteral_AST;
	}
	
	public final void timeLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST timeLiteral_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp190_AST = null;
		tmp190_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
		astFactory.makeASTRoot(currentAST, tmp190_AST);
		match(LITERAL_time);
		AST tmp191_AST = null;
		tmp191_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp191_AST);
		match(StringLiteral);
		timeLiteral_AST = (AST)currentAST.root;
		returnAST = timeLiteral_AST;
	}
	
	public final void timestampLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST timestampLiteral_AST = null;
		
		com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral tmp192_AST = null;
		tmp192_AST = (com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral)astFactory.create(LT(1),"com.gemstone.gemfire.cache.query.internal.parse.ASTLiteral");
		astFactory.makeASTRoot(currentAST, tmp192_AST);
		match(LITERAL_timestamp);
		AST tmp193_AST = null;
		tmp193_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp193_AST);
		match(StringLiteral);
		timestampLiteral_AST = (AST)currentAST.root;
		returnAST = timestampLiteral_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"TOK_RPAREN",
		"TOK_LPAREN",
		"TOK_COMMA",
		"TOK_SEMIC",
		"TOK_DOTDOT",
		"TOK_COLON",
		"TOK_DOT",
		"TOK_INDIRECT",
		"TOK_CONCAT",
		"TOK_EQ",
		"TOK_PLUS",
		"TOK_MINUS",
		"TOK_SLASH",
		"TOK_STAR",
		"TOK_LE",
		"TOK_GE",
		"TOK_NE",
		"TOK_NE_ALT",
		"TOK_LT",
		"TOK_GT",
		"TOK_LBRACK",
		"TOK_RBRACK",
		"TOK_DOLLAR",
		"LETTER",
		"DIGIT",
		"ALL_UNICODE",
		"NameFirstCharacter",
		"NameCharacter",
		"RegionNameCharacter",
		"QuotedIdentifier",
		"Identifier",
		"RegionPath",
		"NUM_INT",
		"EXPONENT",
		"FLOAT_SUFFIX",
		"HEX_DIGIT",
		"QUOTE",
		"StringLiteral",
		"WS",
		"SL_COMMENT",
		"ML_COMMENT",
		"QUERY_PROGRAM",
		"QUALIFIED_NAME",
		"QUERY_PARAM",
		"ITERATOR_DEF",
		"PROJECTION_ATTRS",
		"PROJECTION",
		"TYPECAST",
		"COMBO",
		"METHOD_INV",
		"POSTFIX",
		"OBJ_CONSTRUCTOR",
		"IMPORTS",
		"SORT_CRITERION",
		"LIMIT",
		"HINT",
		"\"trace\"",
		"\"import\"",
		"\"as\"",
		"\"declare\"",
		"\"define\"",
		"\"query\"",
		"\"undefine\"",
		"\"select\"",
		"\"distinct\"",
		"\"all\"",
		"\"from\"",
		"\"in\"",
		"\"type\"",
		"\"where\"",
		"\"limit\"",
		"\"group\"",
		"\"by\"",
		"\"having\"",
		"\"hint\"",
		"\"order\"",
		"\"asc\"",
		"\"desc\"",
		"\"or\"",
		"\"orelse\"",
		"\"and\"",
		"\"for\"",
		"\"exists\"",
		"\"andthen\"",
		"\"any\"",
		"\"some\"",
		"\"like\"",
		"\"union\"",
		"\"except\"",
		"\"mod\"",
		"\"intersect\"",
		"\"abs\"",
		"\"not\"",
		"\"listtoset\"",
		"\"element\"",
		"\"flatten\"",
		"\"nvl\"",
		"\"to_date\"",
		"\"first\"",
		"\"last\"",
		"\"unique\"",
		"\"sum\"",
		"\"min\"",
		"\"max\"",
		"\"avg\"",
		"\"count\"",
		"\"is_undefined\"",
		"\"is_defined\"",
		"\"struct\"",
		"\"array\"",
		"\"set\"",
		"\"bag\"",
		"\"list\"",
		"\"short\"",
		"\"long\"",
		"\"int\"",
		"\"float\"",
		"\"double\"",
		"\"char\"",
		"\"string\"",
		"\"boolean\"",
		"\"byte\"",
		"\"octet\"",
		"\"enum\"",
		"\"date\"",
		"\"time\"",
		"\"interval\"",
		"\"timestamp\"",
		"\"collection\"",
		"\"dictionary\"",
		"\"map\"",
		"\"nil\"",
		"\"null\"",
		"\"undefined\"",
		"\"true\"",
		"\"false\"",
		"NUM_LONG",
		"NUM_FLOAT",
		"NUM_DOUBLE"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -6917526699697455072L, 297237573265260573L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2327970839714L, -50577222L, 32767L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -6917529027641081856L, 5L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2327943626784L, 297237573265260568L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 2327939432480L, 297237573265260560L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 210L, 36352L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 4611688346398227698L, -50557000L, 32767L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 210L, 36608L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 25778192594L, 36352L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 2327939563552L, 297237573265260560L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 4611688346398228064L, -50593544L, 32767L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 146L, 35840L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 146L, 33792L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 146L, 1024L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 4611688346398227554L, -50593544L, 32767L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 25769803776L, -562949953421312L, 127L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 4611688346431782898L, 297237575356100600L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 2327939432480L, 297237573263163408L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 4611688346431782898L, 297237575356100568L, 32651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 4611686044230747090L, 77573952L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { 13369344L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { 53248L, 402653184L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { 196608L, 1610612736L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { 4611686044245423058L, 2090839872L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { 49152L, 6442450944L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { 4611686044262203346L, 2090840000L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	
	}

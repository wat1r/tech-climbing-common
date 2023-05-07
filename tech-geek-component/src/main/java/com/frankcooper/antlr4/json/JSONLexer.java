// Generated from java-escape by ANTLR 4.11.1

    package com.frankcooper.antlr4.json;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JSONLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STRING=10, NUMBER=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"STRING", "ESC", "UNICODE", "HEX", "NUMBER", "INT", "EXP", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "':'", "'['", "']'", "'true'", "'false'", 
			"'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STRING", 
			"NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JSONLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JSON.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\f\u0086\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0005\tC\b\t\n\t\f\tF\t\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0003\nM\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0003\rX\b\r\u0001"+
		"\r\u0001\r\u0001\r\u0004\r]\b\r\u000b\r\f\r^\u0001\r\u0003\rb\b\r\u0001"+
		"\r\u0003\re\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\rk\b\r\u0001\r\u0003"+
		"\rn\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000es\b\u000e\n\u000e"+
		"\f\u000ev\t\u000e\u0003\u000ex\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f"+
		"|\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0004\u0010\u0081\b\u0010"+
		"\u000b\u0010\f\u0010\u0082\u0001\u0010\u0001\u0010\u0000\u0000\u0011\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u0000\u0017\u0000\u0019\u0000\u001b\u000b"+
		"\u001d\u0000\u001f\u0000!\f\u0001\u0000\b\u0002\u0000\"\"\\\\\b\u0000"+
		"\"\"//\\\\bbffnnrrtt\u0003\u000009AFaf\u0001\u000009\u0001\u000019\u0002"+
		"\u0000EEee\u0002\u0000++--\u0003\u0000\t\n\r\r  \u008e\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0003"+
		"%\u0001\u0000\u0000\u0000\u0005\'\u0001\u0000\u0000\u0000\u0007)\u0001"+
		"\u0000\u0000\u0000\t+\u0001\u0000\u0000\u0000\u000b-\u0001\u0000\u0000"+
		"\u0000\r/\u0001\u0000\u0000\u0000\u000f4\u0001\u0000\u0000\u0000\u0011"+
		":\u0001\u0000\u0000\u0000\u0013?\u0001\u0000\u0000\u0000\u0015I\u0001"+
		"\u0000\u0000\u0000\u0017N\u0001\u0000\u0000\u0000\u0019T\u0001\u0000\u0000"+
		"\u0000\u001bm\u0001\u0000\u0000\u0000\u001dw\u0001\u0000\u0000\u0000\u001f"+
		"y\u0001\u0000\u0000\u0000!\u0080\u0001\u0000\u0000\u0000#$\u0005{\u0000"+
		"\u0000$\u0002\u0001\u0000\u0000\u0000%&\u0005,\u0000\u0000&\u0004\u0001"+
		"\u0000\u0000\u0000\'(\u0005}\u0000\u0000(\u0006\u0001\u0000\u0000\u0000"+
		")*\u0005:\u0000\u0000*\b\u0001\u0000\u0000\u0000+,\u0005[\u0000\u0000"+
		",\n\u0001\u0000\u0000\u0000-.\u0005]\u0000\u0000.\f\u0001\u0000\u0000"+
		"\u0000/0\u0005t\u0000\u000001\u0005r\u0000\u000012\u0005u\u0000\u0000"+
		"23\u0005e\u0000\u00003\u000e\u0001\u0000\u0000\u000045\u0005f\u0000\u0000"+
		"56\u0005a\u0000\u000067\u0005l\u0000\u000078\u0005s\u0000\u000089\u0005"+
		"e\u0000\u00009\u0010\u0001\u0000\u0000\u0000:;\u0005n\u0000\u0000;<\u0005"+
		"u\u0000\u0000<=\u0005l\u0000\u0000=>\u0005l\u0000\u0000>\u0012\u0001\u0000"+
		"\u0000\u0000?D\u0005\"\u0000\u0000@C\u0003\u0015\n\u0000AC\b\u0000\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000BA\u0001\u0000\u0000\u0000CF\u0001\u0000"+
		"\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EG\u0001"+
		"\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000GH\u0005\"\u0000\u0000H\u0014"+
		"\u0001\u0000\u0000\u0000IL\u0005\\\u0000\u0000JM\u0007\u0001\u0000\u0000"+
		"KM\u0003\u0017\u000b\u0000LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000"+
		"\u0000M\u0016\u0001\u0000\u0000\u0000NO\u0005u\u0000\u0000OP\u0003\u0019"+
		"\f\u0000PQ\u0003\u0019\f\u0000QR\u0003\u0019\f\u0000RS\u0003\u0019\f\u0000"+
		"S\u0018\u0001\u0000\u0000\u0000TU\u0007\u0002\u0000\u0000U\u001a\u0001"+
		"\u0000\u0000\u0000VX\u0005-\u0000\u0000WV\u0001\u0000\u0000\u0000WX\u0001"+
		"\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0003\u001d\u000e\u0000"+
		"Z\\\u0005.\u0000\u0000[]\u0007\u0003\u0000\u0000\\[\u0001\u0000\u0000"+
		"\u0000]^\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000"+
		"\u0000\u0000_a\u0001\u0000\u0000\u0000`b\u0003\u001f\u000f\u0000a`\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bn\u0001\u0000\u0000\u0000"+
		"ce\u0005-\u0000\u0000dc\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"ef\u0001\u0000\u0000\u0000fg\u0003\u001d\u000e\u0000gh\u0003\u001f\u000f"+
		"\u0000hn\u0001\u0000\u0000\u0000ik\u0005-\u0000\u0000ji\u0001\u0000\u0000"+
		"\u0000jk\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000ln\u0003\u001d"+
		"\u000e\u0000mW\u0001\u0000\u0000\u0000md\u0001\u0000\u0000\u0000mj\u0001"+
		"\u0000\u0000\u0000n\u001c\u0001\u0000\u0000\u0000ox\u00050\u0000\u0000"+
		"pt\u0007\u0004\u0000\u0000qs\u0007\u0003\u0000\u0000rq\u0001\u0000\u0000"+
		"\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000wo\u0001"+
		"\u0000\u0000\u0000wp\u0001\u0000\u0000\u0000x\u001e\u0001\u0000\u0000"+
		"\u0000y{\u0007\u0005\u0000\u0000z|\u0007\u0006\u0000\u0000{z\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0003"+
		"\u001d\u000e\u0000~ \u0001\u0000\u0000\u0000\u007f\u0081\u0007\u0007\u0000"+
		"\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000"+
		"\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0006\u0010\u0000"+
		"\u0000\u0085\"\u0001\u0000\u0000\u0000\u000e\u0000BDLW^adjmtw{\u0082\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
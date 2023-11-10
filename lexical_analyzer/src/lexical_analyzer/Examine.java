package lexical_analyzer;

/* 
 * 작성자 : 김상민
 * 작성일 : 22.05.09
 */

public class Examine {		
	/*
	examine class는 token 검사를 위한 method들로 구성
	1. 각 method는 str를 입력받아 symbol단위로 transtition table에 str이 partition될 때까지 적용한다.
	2. 만약 final state이면 partition한 str을 return
	3. 만약 final state가 아니면 "not <token_type>"이란 str을 return 
	*/
	
	public static String type_int(String s) { //INT 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {			//transition table
			case 0:
				state = 0;
				switch(c) {
				case 'i':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'n':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 't':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 3) {
					return ps;			//final state
				}else {
					return "not int";	//non-final state
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String type_char(String s) { //CHAR 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'c':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'h':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'a':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'r':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 4) {
					return ps;
				}else {
					return "not char";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String type_boolean(String s) { //BOOLEAN 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'b':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'o':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'o':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'l':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 'e':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				switch(c) {
				case 'a':
					next_state = 6;
					break;
				default:
					next_state = -1;
				}
				break;
			case 6:
				state = 6;
				switch(c) {
				case 'n':
					next_state = 7;
					break;
				default:
					next_state = -1;
				}
				break;
			case 7:
				state = 7;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 7) {
					return ps;
				}else {
					return "not boolean";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String type_string(String s) { //STRING 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 's':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 't':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'r':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'i':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 'n':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				switch(c) {
				case 'g':
					next_state = 6;
					break;
				default:
					next_state = -1;
				}
				break;
			case 6:
				state = 6;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 6) {
					return ps;
				}else {
					return "not string";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String integer(String s) { //INTEGER 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				if(c == '0') {
					next_state = 3;
				}else if(1<=Character.getNumericValue(c) && Character.getNumericValue(c)<=9) {
					next_state = 2;
				}else if(c == '-') {
					next_state = 1;
				}else {
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				if(1<=Character.getNumericValue(c) && Character.getNumericValue(c)<=9) {
					next_state = 2;
				}else {
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				if(0<=Character.getNumericValue(c) && Character.getNumericValue(c)<=9) {
					next_state = 2;
				}else {
					next_state = -1;
				}
				break;
			case 3:
				if(0<=Character.getNumericValue(c) && Character.getNumericValue(c)<=9) {
					next_state = 4;
				}else {
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				if(0<=Character.getNumericValue(c) && Character.getNumericValue(c)<=9) {
					next_state = 4;
				}else {
					next_state = -1;
				}
				break;
			}
			if(next_state == -1) {
				if(state == 2 || state == 3) {
					return ps;
				}else {
					return "not integer";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String single_char(String s) { //SINGLE_CHAR 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				if(32<=c && c<=126){
					next_state = 1;
				}else {
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not single char";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}		
	public static String boolean_str(String s) { //BOOLEAN_STR 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 't':
					next_state = 1;
					break;
				case 'f':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'r':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'u':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'e':
					next_state = 8;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 'a':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				switch(c) {
				case 'l':
					next_state = 6;
					break;
				default:
					next_state = -1;
				}
				break;
			case 6:
				state = 6;
				switch(c) {
				case 's':
					next_state = 7;
					break;
				default:
					next_state = -1;
				}
				break;
			case 7:
				state = 7;
				switch(c) {
				case 'e':
					next_state = 8;
					break;
				default:
					next_state = -1;
				}
				break;
			case 8:
				state = 8;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 8) {
					return ps;
				}else {
					return "not boolean str";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}	
	public static String literal(String s) { //LITREAL 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				if(c == '"') {
					next_state = 1;
				}else {
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				if(((32<=c && c<=126)||c=='\t'||c=='\n') && (c!='"')) {
					next_state = 2;
				}else {
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				if(((32<=c && c<=126)||c=='\t'||c=='\n') && (c!='"')) {
					next_state = 2;
				}else if(c=='"') {
					next_state = 3;
				}else {
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 3) {
					return ps;
				}else {
					return "not literal";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String identifier(String s) { //ID 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				if(Character.isLetter(c)) {
					next_state = 1;
				}else if(Character.isDigit(c)) {
					next_state = -1;
				}else if(c == '_') {
					next_state = 1;
				}else {
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				if(Character.isLetter(c)) {
					next_state = 2;
				}else if(Character.isDigit(c)) {
					next_state = 2;
				}else if(c == '_') {
					next_state = 2;
				}else {
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				if(Character.isLetter(c)) {
					next_state = 2;
				}else if(Character.isDigit(c)) {
					next_state = 2;
				}else if(c == '_') {
					next_state = 2;
				}else {
					next_state = -1;
				}
				break;
			}
			if(next_state == -1) {
				if(state == 1 || state == 2) {
					return ps;
				}else {
					return "not identifier";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}	
	public static String keyword_if(String s) { //IF 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'i':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'f':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 2) {
					return ps;
				}else {
					return "not if";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}	
	public static String keyword_else(String s) { //ELSE 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'e':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'l':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 's':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'e':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 4) {
					return ps;
				}else {
					return "not else";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String keyword_while(String s) { //WHILE 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'w':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'h':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'i':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'l':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 'e':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 5) {
					return ps;
				}else {
					return "not while";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String keyword_class(String s) { //CLASS 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'c':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'l':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 'a':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 's':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 's':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 5) {
					return ps;
				}else {
					return "not class";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String keyword_return(String s) { //RETURN 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case 'r':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case 'e':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case 't':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				switch(c) {
				case 'u':
					next_state = 4;
					break;
				default:
					next_state = -1;
				}
				break;
			case 4:
				state = 4;
				switch(c) {
				case 'r':
					next_state = 5;
					break;
				default:
					next_state = -1;
				}
				break;
			case 5:
				state = 5;
				switch(c) {
				case 'n':
					next_state = 6;
					break;
				default:
					next_state = -1;
				}
				break;
			case 6:
				state = 6;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 6) {
					return ps;
				}else {
					return "not return";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String arithmetic(String s) { //ARITHMETIC 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '+':
					next_state = 1;
					break;
				case '-':
					next_state = 1;
					break;
				case '*':
					next_state = 1;
					break;
				case '/':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not arithmetic";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}	
	public static String assignment(String s) { //ASSIGNMENT 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '=':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not assignment";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}		
	public static String comparison(String s) { //COMPARISON 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '<':
					next_state = 1;
					break;
				case '>':
					next_state = 1;
					break;
				case '!':
					next_state = 2;
					break;
				case '=':
					next_state = 2;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				switch(c) {
				case '=':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 2:
				state = 2;
				switch(c) {
				case '=':
					next_state = 3;
					break;
				default:
					next_state = -1;
				}
				break;
			case 3:
				state = 3;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1 || state == 3) {
					return ps;
				}else {
					return "not comparison";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}			
	public static String semicolon(String s) { //SEMICOLON 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case ';':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not semicolon";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String lparen(String s) { //LPAREN 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '(':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not lparen";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String rparen(String s) { //RPAREN 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case ')':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not rparen";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String lbrace(String s) { //LBRACE 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '{':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not lbrace";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String rbrace(String s) { //RBRACE 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '}':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not rbrace";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}	
	public static String lbracket(String s) { //LBRACKET 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '[':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not lbracket";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String rbracket(String s) { //RBRACKET 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case ']':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not rbracket";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	public static String comma(String s) { //COMMA 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case ',':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not comma";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}		
	public static String whitespace(String s) { //WHITESPACE 토큰인지 검사하는 메소드
		String ps = "";
		int state = 0;
		int next_state = 0;
		int i = 0;
		while(true) {
			state = next_state;
			char c;
			if(s.length()==i) {
				c = 0;
			}else {
				c = s.charAt(i);
			}
			switch(state) {
			case 0:
				state = 0;
				switch(c) {
				case '\t':
					next_state = 1;
					break;
				case '\n':
					next_state = 1;
					break;
				case ' ':
					next_state = 1;
					break;
				default:
					next_state = -1;
				}
				break;
			case 1:
				state = 1;
				next_state = -1;
				break;
			}
			if(next_state == -1) {
				if(state == 1) {
					return ps;
				}else {
					return "not whitespace";
				}
			}else {
				ps = ps.concat(String.valueOf(c));
			}
			i++;
		}
	}
	

}

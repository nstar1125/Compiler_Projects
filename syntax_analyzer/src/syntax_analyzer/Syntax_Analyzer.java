/*
 * 작성자 : 김상민
 * 작성일 : 22.06.19
 */


package syntax_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Production{		//production class: from -> to의 형태이다.
	String from;
	String to;
	public Production(String f, String t){
		from = f;
		to = t;
	}	
}

public class Syntax_Analyzer {

	public static String toTerminal(String str) {		//어휘분석기의 토큰들을 구문분석기용 터미널로 변경해주는 메소드이다.
		String[] sAry;
		String terminal;
		sAry = str.split("\t");
		if(sAry[0].equals("INT")||sAry[0].equals("CHAR")||sAry[0].equals("BOOLEAN")||sAry[0].equals("STRING")) {
			terminal = "vtype";
		}else if(sAry[0].equals("INTEGER")) {
			terminal = "num";
		}else if(sAry[0].equals("SINGLE_CHAR")) {
			terminal = "character";
		}else if(sAry[0].equals("BOOLEAN_STR")) {
			terminal = "boolstr";
		}else if(sAry[0].equals("LITERAL")) {
			terminal = "literal";
		}else if(sAry[0].equals("ID")) {
			terminal = "id";
		}else if(sAry[0].equals("IF")) {
			terminal = "if";
		}else if(sAry[0].equals("ELSE")) {
			terminal = "else";
		}else if(sAry[0].equals("WHILE")) {
			terminal = "while";
		}else if(sAry[0].equals("RETURN")) {
			terminal = "return";
		}else if(sAry[0].equals("CLASS")) {
			terminal = "class";
		}else if(sAry[0].equals("ARITHMETIC")) {
			if(sAry[1].equals("+")||sAry[1].equals("-")) {
				terminal = "addsub";
			}else if(sAry[1].equals("*")||sAry[1].equals("/")) {
				terminal = "multdiv";
			}else {
				terminal = "error";
			}
		}else if(sAry[0].equals("ASSIGNMENT")) {
			terminal = "assign";
		}else if(sAry[0].equals("COMPARISON")) {
			terminal = "comp";
		}else if(sAry[0].equals("SEMICOLON")) {
			terminal = "semi";
		}else if(sAry[0].equals("COMMA")) {
			terminal = "comma";
		}else if(sAry[0].equals("LPAREN")) {
			terminal = "lparen";
		}else if(sAry[0].equals("RPAREN")) {
			terminal = "rparen";
		}else if(sAry[0].equals("LBRACE")) {
			terminal = "lbrace";
		}else if(sAry[0].equals("RBRACE")) {
			terminal = "rbrace";
		}else if(sAry[0].equals("LBRACKET")) {
			terminal = "lbrack";
		}else if(sAry[0].equals("RBRACKET")) {
			terminal = "rbrack";
		}else {
			terminal = "error";
		}
		return terminal;
	}
	public static Production[] initializeP(){			//production들의 집합을 초기설정하는 메소드이다.
		Production p[] = new Production[39];
		p[0] = new Production("S","CODE");
		p[1] = new Production("CODE","VDECL CODE");
		p[2] = new Production("CODE","FDECL CODE");
		p[3] = new Production("CODE","CDECL CODE");
		p[4] = new Production("CODE","epsilon");
		p[5] = new Production("VDECL","vtype id semi");
		p[6] = new Production("VDECL","vtype ASSIGN semi");
		p[7] = new Production("ASSIGN","id assign RHS");
		p[8] = new Production("RHS","EXPR");
		p[9] = new Production("RHS","literal");
		p[10] = new Production("RHS","character");
		p[11] = new Production("RHS","boolstr");
		p[12] = new Production("EXPR","TERM addsub EXPR");
		p[13] = new Production("EXPR","TERM");
		p[14] = new Production("TERM","FACTOR multdiv TERM");
		p[15] = new Production("TERM","FACTOR");
		p[16] = new Production("FACTOR","lparen EXPR rparen");
		p[17] = new Production("FACTOR","id");
		p[18] = new Production("FACTOR","num");
		p[19] = new Production("FDECL","vtype id lparen ARG rparen lbrace BLOCK RETURN rbrace");
		p[20] = new Production("ARG","vtype id MOREARGS");
		p[21] = new Production("ARG","epsilon");
		p[22] = new Production("MOREARGS","comma vtype id MOREARGS");
		p[23] = new Production("MOREARGS","epsilon");
		p[24] = new Production("BLOCK","STMT BLOCK");
		p[25] = new Production("BLOCK","epsilon");
		p[26] = new Production("STMT","VDECL");
		p[27] = new Production("STMT","ASSIGN semi");
		p[28] = new Production("STMT","if lparen COND rparen lbrace BLOCK rbrace ELSE");
		p[29] = new Production("STMT","while lparen COND rparen lbrace BLOCK rbrace");
		p[30] = new Production("COND","FACTOR comp FACTOR");
		p[31] = new Production("COND","boolstr");
		p[32] = new Production("ELSE","else lbrace BLOCK rbrace");
		p[33] = new Production("ELSE","epsilon");
		p[34] = new Production("RETURN","return RHS semi");
		p[35] = new Production("CDECL","class id lbrace ODECL rbrace");
		p[36] = new Production("ODECL","VDECL ODECL");
		p[37] = new Production("ODECL","FDECL ODECL");
		p[38] = new Production("ODECL","epsilon");
		return p;
	}
	public static String[][] initializeT(){		//SLR parsing table에 대한 정보를 데이터파일에서 받아와 이차원 배열에 저장한다.
		String ary[][] = new String[87][40];
		FileReader fin = null;
		try {
			fin = new FileReader("data/table_data.txt");
			int c;
			int i = 0, j = 0;
			String temp = "";
			while((c = fin.read())!= -1){
				temp = temp.concat(String.valueOf((char)c));
				if((char)c=='\t') {
					ary[i][j] = temp.substring(0,temp.length()-1);
					j++;
					temp = "";
				}
				if((char)c=='\n') {
					ary[i][j] = temp.substring(0,temp.length()-2);
					i++;
					j=0;
					temp = "";
				}
			}
		}catch(IOException e) {
			System.out.println("IOException has occured !!");
		}
		return ary;
	}
	public static String getNextInput(int splitter, String str) {			//splitter를 기준으로 next input symbol을 구한다.
		String next = null;
		for(int i = 0, space_met=0; space_met<=splitter ;i++) {			//문자열에서 스페이스바의 개수가 splitter와 같을 때까지 i를 증가시킨다.
			if(str.charAt(i)==' ') {
				space_met++;
			}
			if(space_met==splitter) {			//i가 현재 splitter의 위치라면,
				String word = "";
				int j;
				if(space_met == 0)
					j=0;
				else
					j=1;
				for(; str.charAt(i+j)!=' '; j++) {			//splitter 다음에 오는 문자열을 저장한다.
					word = word.concat(String.valueOf(str.charAt(i+j)));
					if(str.charAt(i+j)=='$') {		//end of string에 도달 시, break
						break;
					}
				}
				next = word;
				break;
			}
		
		}
		return next;			//다음에 오는 문자열을 return한다.
	}
	public static int getTopIndex(String str) {			//SLR parsing table의 윗 부분에서 index의 위치를 찾아 return한다.
		String top = "vtype	id	semi	assign	literal	character	boolstr	addsub	multdiv	lparen	rparen	num	lbrace	rbrace	comma	if	while	comp	else	return	class	$	S	CODE	VDECL	ASSIGN	RHS	EXPR	TERM	FACTOR	FDECL	ARG	MOREARGS	BLOCK	STMT	COND	ELSE	RETURN	CDECL	ODECL";
		String tabletop[] = top.split("\t");
		int index = -1;
		for(int i=0; i<tabletop.length; i++) {
			if(tabletop[i].equals(str)) {
				index = i;
			}
		}
		return index;
	}
	public static String reduce(int splitter, String str, Production p) {			//splitter를 기준으로 left substring의 rightmost 값을 production p로 reduction을 진행한다.
		
		for(int i = 0, space_met=0; space_met<=splitter ;i++) {
			if(str.charAt(i)==' ') {
				space_met++;
			}
			if(space_met==splitter) {
				String end = str.substring(i);	//splitter 이후 string
				if(p.to.equals("epsilon")) {	//production의 RHS가 입실론이면
					str = str.substring(0,i+1)+p.from+end;	//splitter이전 string+" "+splitter 이후 string
				}else {							//아니면
					str = str.substring(0,i-p.to.length())+p.from+end;	//production RHS제거한 left substring+production RHS+splitter 이후 string
				}
				break;
			}
		
		}
		
		return str;
	}
	public static void main(String[] args) {
		File fileIn = null;
		try {
			
			String input_file_name = "lexical_output";
			String filename_extension = ".txt";
			fileIn = new File(input_file_name+filename_extension);		
			BufferedReader fin = new BufferedReader(new FileReader(fileIn));
			fin.readLine();
			fin.readLine();
			String str = "";
			String input_line = "";
			while((input_line = fin.readLine())!=null) {
				str = str.concat(toTerminal(input_line)+" ");		//str에 토큰 정보에 따른 터미널 연결
			}
			str = str.concat("$");			//마지막에 '$' 연결
			
			Production p[] = initializeP();			//production set 생성
			String table[][] = initializeT();			//SLR parsing table 생성
			
			ArrayList<Integer> stack = new ArrayList<Integer>();	//optimization 위한 stack
			int splitter = 0;		//splitter
			int cur_state = 0;		//current state
			stack.add(cur_state);	//초기 state stack에 push
			String cmd = null;
			
			while(true) {		//accept or reject될 때까지 반복
				
				if(stack.size()>0)
					cur_state = stack.get(stack.size()-1);		//get current state(stack top)
				String next_input = getNextInput(splitter, str);		//splitter 기준 다음 next input symbol 구함
				cmd = table[cur_state][getTopIndex(next_input)];		//parsing table에 따른 decision
				
				
				if(cmd.equals("acc")) {			//accept
					System.out.println("accept");
					break;
				}
				if(cmd.equals("")) {			//reject
					System.out.println("reject");
					System.out.println();
					System.out.println("current sentienl form:");
					for(int i = 0, space_met=0; space_met<=splitter ;i++) {
						if(str.charAt(i)==' ') {
							space_met++;
						}
						if(space_met==splitter) {
							str = str.substring(0,i)+"|"+str.substring(i+1,str.length());
							break;
						}
					}
					System.out.println(str);
					System.out.println();
					System.out.println("Unable to find matching production for next input symbol : "+next_input);
					break;
				}else if(cmd.charAt(0) == 's') {			//shift and goto
					stack.add(Integer.parseInt(cmd.substring(1)));		//stack push
					splitter++;		//shift
				}else if(cmd.charAt(0) == 'r') {			//reduction
					int pNum = Integer.parseInt(cmd.substring(1));

					str = reduce(splitter, str, p[pNum]);		//reduce by p[n]
					
					int rhs_n;
					if(p[pNum].to.equals("epsilon")) {			//RHS의 symbol 수 계산
						rhs_n=0;
					}else {
						rhs_n=1;
						for(int i = 0; i<p[pNum].to.length(); i++) {
							if(p[pNum].to.charAt(i)==' ') {
								rhs_n++;
							}
						}
					}
					for(int i = 0; i<rhs_n; i++) {			//stack pop
						if(stack.size()>0)
							stack.remove(stack.size()-1);
					}
					if(rhs_n==0) {			//splitter 조정
						splitter=splitter+1;
					}else {
						splitter=splitter-rhs_n+1;
					}
						
					if(stack.size()>0)			//stack top 확인
						cur_state = stack.get(stack.size()-1);
				
					stack.add(Integer.parseInt(table[cur_state][getTopIndex(p[pNum].from)]));	//stack에 goto(cur_state,LHS) 추가
					
					
				}else {		//parsing table을 제대로 construct 안한 경우
					System.out.println("parsing table error");
					break;
				}
			}
			fin.close();
			
		}catch (IOException e){
			System.out.println("IOException has occured !!");
		}
	}

}


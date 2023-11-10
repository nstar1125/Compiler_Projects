package lexical_analyzer;

/*
 * 작성자 : 김상민
 * 작성일 : 22.05.09
 */



import java.io.*;
import java.util.ArrayList;

class Token {		//token 클래스
	int size;			//partition할 str의 크기
	String name;			//token 이름
	String info;			//token 값
	Token(int s, String n, String i){
		this.size = s;
		this.name = n;
		this.info = i;
	}
}

public class Lexical_Analyzer {

	public static void main(String[] args) {
		FileReader fin = null;
		FileWriter fout = null;
		
		try {
			String input_stream = "";
			String input_file_name = "lexical";
			String output_file_name = input_file_name+"_output";
			String filename_extension = ".txt";
			fin = new FileReader(input_file_name+filename_extension);
			fout = new FileWriter(output_file_name+filename_extension);
			int a;
			while((a = fin.read())!= -1){
				input_stream = input_stream.concat(String.valueOf((char)a));			//input 파일을 입력받아 input_stream이라는 하나의 str으로 저장
			}
			String str = input_stream;
			
			ArrayList<Token> token_list = new ArrayList<Token>();			//토큰들로 이뤄진 리스트 생성
			int top = 0, line_num = 1;
		
			
			do {
				Token lexeme = getToken(str);			//str에서 처음으로 오는 토큰 fetch
				if(lexeme.name.equals("ERROR")) {			//만약 에러토큰이면 에러 정보 토큰 리스트에 추가 후 break
					token_list.add(lexeme);
					top++;
					break;
				}
				if(lexeme.name.equals("EOI")) {			//만약 end-of-input 토큰이면 추가하지 않고 break
					break;
				}
				if(!(lexeme.name.equals("WHITESPACE"))) {			//만약 토큰이 WHITESPACE가 아니면
					if(top>0) {
						if(token_list.get(top-1).name.equals("ID")||token_list.get(top-1).name.equals("INTEGER")) {
							if(str.charAt(0)=='-' && lexeme.name.equals("INTEGER")) {		//직전 토큰이 ID나 INT이면서 현재 토큰이 -로 시작하는 INTEGER라면
								lexeme = new Token(1,"ARITHMETIC","-");						//현재 토큰을 <ARITHMETIC,'-'>으로 교체
							}
						}
					}
					token_list.add(lexeme);			//토큰리스트에 토큰 추
					top++;
				}else {					
					if(lexeme.info.equals("\n")) {			//만약 토큰이 WHITESPACE 중 줄바꿈 문자이면 line_num++
						line_num++;
					}
				}
				str = str.substring(lexeme.size);			//str partition
			}while(str.length()>0);
			
			
			if(top>0) {
				if(token_list.get(top-1).name.equals("ERROR")) {			//토큰 리스트의 top이 에러토큰이면 에러 리포트 출력
					fout.write("     <Error Report>\n");
					fout.write("\n");
					fout.write("Error has occured at line "+line_num+" !!\n");
					fout.write(token_list.get(top-1).info);
				}else {														//정상적인 토큰 테이블 출력
					fout.write("     <Token Table>\n");
					fout.write("\n");
					for(int i=0; i<top; i++) {
						String tn = token_list.get(i).name;
						if(tn.equals("ARITHMETIC")||tn.equals("COMPARISON")||tn.equals("BOOLEAN_STR")||tn.equals("SINGLE_CHAR")) { //토큰 테이블 줄맞춤
							fout.write(token_list.get(i).name+"\t"+token_list.get(i).info+"\n");
						}else {
							fout.write(token_list.get(i).name+"\t\t"+token_list.get(i).info+"\n");
						}
					}
				}
			}else {														//토큰리스트 empty 출력
				fout.write("     <Token Table>\n");
				fout.write("\n");
				fout.write("There was no tokens to be classified !!");
			}
			
			fin.close();
			fout.close();
		}
		catch (IOException e){
			System.out.println("IOException has occured !!");
		}

	}
	
	public static Token getToken(String str) {			//str에서 토큰 recognize해서 classify
		int t_size = 0;
		String t_name = null;
		String t_info = "";
		if(str.length()==0){			//str 길이가 0이면 end-of-input 토큰
			t_size = 0;
			t_name = "EOI";
		}else if(!(Examine.identifier(str).equals("not identifier"))) {			//ID 토큰이면
			if(!(Examine.type_int(str).equals("not int"))) {
				if(Examine.identifier(str).length()==3) {			//INT 토큰이면
					t_size = 3;
					t_name = "INT";
				}else {												//ID 토큰이면 (ex. internet처럼 'int'로 시작하는 경우)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.type_char(str).equals("not char"))) {
				if(Examine.identifier(str).length()==4) {			//CHAR 토큰이면
					t_size = 4;
					t_name = "CHAR";
				}else {												//ID 토큰이면 (ex. chart처럼 'char'으로 시작하는 경우)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.type_boolean(str).equals("not boolean"))) {
				if(Examine.identifier(str).length()==7) {			//BOOLEAN 토큰이면
					t_size = 7;
					t_name = "BOOLEAN";
				}else {												//ID 토큰이면 ('boolean'으로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.type_string(str).equals("not string"))) {
				if(Examine.identifier(str).length()==6) {			//STRING 토큰이면
					t_size = 6;
					t_name = "STRING";
				}else {												//ID 토큰이면 ('string'으로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.boolean_str(str).equals("not boolean str"))) {
				if(Examine.identifier(str).length()==Examine.boolean_str(str).length()) {			//BOOLEAN_STR 토큰이면
					t_size = Examine.boolean_str(str).length();
					t_name = "BOOLEAN_STR";
					t_info = str.substring(0,t_size);
				}else {										//ID 토큰이면 ('true나 false'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.keyword_if(str).equals("not if"))) {			//IF 토큰이면
				if(Examine.identifier(str).length()==2) {
					t_size = 2;
					t_name = "IF";	
				}else {														//ID 토큰이면 ('if'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.keyword_else(str).equals("not else"))) {		//ELSE 토큰이면
				if(Examine.identifier(str).length()==4) {
					t_size = 4;
					t_name = "ELSE";
				}else {														//ID 토큰이면 ('else'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.keyword_while(str).equals("not while"))) {
				if(Examine.identifier(str).length()==5) {					//WHILE 토큰이면
					t_size = 5;
					t_name = "WHILE";
				}else {														//ID 토큰이면 ('while'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.keyword_class(str).equals("not class"))) {	//CLASS 토큰이면
				if(Examine.identifier(str).length()==5) {
					t_size = 5;
					t_name = "CLASS";
				}else {														//ID 토큰이면 ('class'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}else if(!(Examine.keyword_return(str).equals("not return"))) {
				if(Examine.identifier(str).length()==6) {					//RETURN 토큰이면
					t_size = 6;
					t_name = "RETURN";
				}else {														//ID 토큰이면 ('return'로 시작)
					t_size = Examine.identifier(str).length();
					t_name = "ID";
					t_info = str.substring(0,t_size);
				}
			}
			else {															//그 외 나머지 ID 토큰이면
				t_size = Examine.identifier(str).length();
				t_name = "ID";
				t_info = str.substring(0,t_size);
			}
		}else if(!(Examine.semicolon(str).equals("not semicolon"))) {		//SEMICOLON 토큰이면 (;)
			t_size = 1;
			t_name = "SEMICOLON";
		}else if(!(Examine.lparen(str).equals("not lparen"))) {				//LPAREN 토큰이면 ( ( )
			t_size = 1;
			t_name = "LPAREN";
		}else if(!(Examine.rparen(str).equals("not rparen"))) {				//RPAREN 토큰이면 ( ) )
			t_size = 1;
			t_name = "RPAREN";
		}else if(!(Examine.lbrace(str).equals("not lbrace"))) {				//LBRACE 토큰이면 ( { )
			t_size = 1;
			t_name = "LBRACE";
		}else if(!(Examine.rbrace(str).equals("not rbrace"))) {				//RBRACE 토큰이면 ( } )
			t_size = 1;
			t_name = "RBRACE";
		}else if(!(Examine.lbracket(str).equals("not lbracket"))) {			//LBRACKET 토큰이면 ( [ )
			t_size = 1;
			t_name = "LBRACKET";
		}else if(!(Examine.rbracket(str).equals("not rbracket"))) {			//RBRACKET 토큰이면 ( ] )
			t_size = 1;
			t_name = "RBRACKET";
		}else if(!(Examine.comma(str).equals("not comma"))) {				//COMMA 토큰이면 ( , )
			t_size = 1;
			t_name = "COMMA";
		}else if(!(Examine.whitespace(str).equals("not whitespace"))) {		//WHITESPACE 토큰이면
			t_size = 1;
			t_name = "WHITESPACE";
			t_info = str.substring(0,t_size);
		}else if(!(Examine.literal(str).equals("not literal"))) {			//LITERAL 토큰이면
			t_size = Examine.literal(str).length();
			t_name = "LITERAL";
			t_info = str.substring(0,t_size);
		}else if(!(Examine.comparison(str).equals("not comparison"))) {		//COMPARISON 토큰이면 (<, >, <=, >=, !=, ==)
			t_size = Examine.comparison(str).length();
			t_name = "COMPARISON";
			t_info = str.substring(0,t_size);
		}else if(!(Examine.assignment(str).equals("not assignment"))) {		//ASSIGNMENT 토큰이면 ( = )
			t_size = 1;
			t_name = "ASSIGNMENT";
		}else if(!(Examine.integer(str).equals("not integer"))) {			//INTEGER 토큰이면
			t_size = Examine.integer(str).length();
			t_name = "INTEGER";
			t_info = str.substring(0,t_size);
		}else if(str.charAt(0)=='0') {										//ERROR 토큰이면 (0으로 시작하는 integer)
			t_size = 0;
			t_name = "ERROR";
			t_info = "integer starting with zero";
		}else if(!(Examine.arithmetic(str).equals("not arithmetic"))) {		//ARITHMETIC 토큰이면 (+, -, *, /)
			t_size = 1;
			t_name = "ARITHMETIC";
			t_info = str.substring(0,t_size);
		}else if(!(Examine.single_char(str).equals("not single char"))){	//SINGLE_CHAR 토큰이면
			t_size = 1;
			t_name = "SINGLE_CHAR";
			t_info = str.substring(0,t_size);
		}else{																//error 토큰이면 (정의되지 않은 토)
			t_size = 0;
			t_name = "ERROR";
			t_info = "unidentified token";
		}
			
		return new Token(t_size, t_name, t_info);					//생성된 토큰 리턴 

	}
	
}

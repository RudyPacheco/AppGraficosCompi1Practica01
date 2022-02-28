/* Primer seccion : codigo de usuario*/
package lexico;
import java_cup.runtime.*;
import sintactico.*;
%% //separador de area


/* Segunda seccion : configuracion*/

%class AnalizadorLexico
%public
%line
%column
%unicode
%cup
%state COMENTARIO_LINEA

/*delcaracion para los tokens*/
WhiteSpace = [\r|\n|\r\n] | [ \t\f]
LETRA = [a-zA-Z]
ENTERO = [0-9]+
//DECIMAL = [0-9]+ . [0-9]+
DECIMAL = ([0-9]+[.]([0-9]+))
SIGNO_MENOS = "-"
SIGNO_MAS = "+"
SIGNO_POR = "*"
SIGNO_DIVISION = "/"
SIGNO_PARENTESISA ="\("
SIGNO_PARENTESISC ="\)"
SIGNO_LLAVEA = "\{"
SIGNO_LLAVEC = "\}"
SIGNO_CORCHETEA= "\["
SIGNO_CORCHETEC= "\]"
SIGNO_COMILLAS= "\""
SIGNO_PUNTO = "."
SIGNO_COMA = ","
SIGNO_PUNTOCOMA = ";"
SIGNO_DOPUNTO = ":"


INICIO_COMENTARIO_BLOQUE = "#"


DEF= ("Def" | "def")
BARRAS = "Barras"
PIE = "Pie"
TITULO = "titulo"
EJEX = "ejex"
EJEY = "ejey"
ETIQUETAS = "etiquetas"
VALORES = "valores"
UNIR = "unir"
TIPO = "tipo"
CANTIDAD = "Cantidad"
PORCENTAJE = "Porcentaje"
TOTAL = "total"
EXTRA = "extra" 
EJECUTAR ="Ejecutar"

ID = ( \" ({LETRA})({LETRA}|{ENTERO}|\s)* \")
PALABRAN =  ({LETRA})({LETRA}|{ENTERO}|\s)*

%{
  
  private ArrayList<errorE> erroresLex = new ArrayList<>();
  
  private void error(String message) {
    System.out.println("Error en linea: "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
  }




    private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }

     //Retorna list de errores
        public ArrayList getListErroresLex(){ 
	        return erroresLex;
	    }


%}


%%

/*tercer seccion : reglas lexicas*/


/* reglas lexicas */
<YYINITIAL> {
{WhiteSpace} 	{/* ignoramos */}
{INICIO_COMENTARIO_BLOQUE} {yybegin(COMENTARIO_LINEA);} 
{SIGNO_MENOS} {System.out.println("Lex - "+yytext());return symbol(sym.MENOS,yytext());}
{SIGNO_POR} {System.out.println("Lex * "+yytext());return symbol(sym.MULTIPLICACION,yytext() );}
{SIGNO_DIVISION} {System.out.println("Lex / "+yytext());return symbol(sym.DIVISION,yytext() );}
{SIGNO_MAS} {System.out.println("Lex + "+yytext());return symbol(sym.MAS,yytext() );}
//{SIGNO_POR} {System.out.println("Lex  "+yytext());return symbol(sym.MULTIPLICACION,yytext() );}
//{SIGNO_DIVISION} {System.out.println("Lex"+yytext());return symbol(sym.DIVISION,yytext() );}
{SIGNO_LLAVEA} {System.out.println("Lex { "+yytext());return symbol(sym.LLAVEA,yytext() );}
{SIGNO_LLAVEC} {System.out.println("Lex } "+yytext());return symbol(sym.LLAVEC,yytext() );}
{SIGNO_PARENTESISA} {System.out.println("Lex ( "+yytext());return symbol(sym.PARENTESISA,yytext() );}
{SIGNO_PARENTESISC} {System.out.println("Lex ) "+yytext());return symbol(sym.PARENTESISC,yytext() );}
{SIGNO_CORCHETEA} {System.out.println("Lex [ "+yytext());return symbol(sym.CORCHETEA,yytext() );}
{SIGNO_CORCHETEC} {System.out.println("Lex ] "+yytext());return symbol(sym.CORCHETEC,yytext() );}
{SIGNO_COMILLAS} {System.out.println("Lex \" "+yytext());return symbol(sym.COMILLAS,yytext() );}
{SIGNO_COMA} {System.out.println("Lex , "+yytext());return symbol(sym.COMA,yytext() );}
{SIGNO_PUNTOCOMA} {System.out.println("Lex ; "+yytext());return symbol(sym.PUNTOCOMA,yytext() );}
{SIGNO_DOPUNTO} {System.out.println("Lex : "+yytext());return symbol(sym.DOSPUNTOS, yytext());}
{DEF} {System.out.println("Lex"+yytext());return symbol(sym.DEF, yytext());}
{BARRAS} {System.out.println("Lex"+yytext());return symbol(sym.BARRAS,yytext() );}
{PIE} {System.out.println("Lex"+yytext());return symbol(sym.PIE,yytext() );}
{TITULO} {System.out.println("Lex"+yytext());return symbol(sym.TITULO,yytext() );}
{EJEX} {System.out.println("Lex"+yytext());return symbol(sym.EJEX,yytext());}
{EJEY} {System.out.println("Lex"+yytext());return symbol(sym.EJEY,yytext());}
{ETIQUETAS} {System.out.println("Lex"+yytext());return symbol(sym.ETIQUETAS,yytext() );}
{VALORES} {System.out.println("Lex"+yytext());return symbol(sym.VALORES,yytext() );}
{UNIR} {System.out.println("Lex"+yytext());return symbol(sym.UNIR, yytext());}
{TIPO} {System.out.println("Lex"+yytext());return symbol(sym.TIPO,yytext() );}
{CANTIDAD} {System.out.println("Lex"+yytext());return symbol(sym.CANTIDAD,yytext() );}
{PORCENTAJE} {System.out.println("Lex"+yytext());return symbol(sym.PORCENTAJE,yytext() );}
{TOTAL} {System.out.println("Lex"+yytext());return symbol(sym.TOTAL,yytext() );}
{EXTRA} {System.out.println("Lex"+yytext());return symbol(sym.EXTRA,yytext() );}
{EJECUTAR} {System.out.println("Lex"+yytext());return symbol(sym.EJECUTAR,yytext());}

{ID} {System.out.println("Lex idd "+yytext());return symbol(sym.ID,yytext() );}
{ENTERO} {System.out.println("Lex entero "+yytext());return symbol(sym.ENTERO,yytext());}
{DECIMAL} {System.out.println("Lex decimal "+yytext());return symbol(sym.DECIMAL,yytext());}

}

	/* estado de comentario bloque*/
<COMENTARIO_LINEA>{
(\n) {yybegin(YYINITIAL);}
[^] {;}
}
/* error fallback */
[^]                              {error("Simbolo invalido <"+ yytext()+">"); AnalizadorLexico.erroresLex.add(new errorE(yytext(),yyline+1,yycolumn+1,"Simbolo no existente en el lenguaje","lexico")); }


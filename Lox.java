package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox{
    static boolean hadError =false;
    run(new String(bytes,Charset.defaultCharset()));
    if(hadError) System.exit(65);
    


    public static void main(String[]  args) throws IOException{
        if(args.length > 1 ){
            System.out.println("Usage :jlox [Script]");
            System.exit(64);
        }
        else if(args.length==1){
            runFile(args[0]);
        }
        else{
            runPrompt();
        }
    }

//for starting jlox from the command line and give it a path to a file
private static void runFile(String path) throws IOException{
    byte[] bytes =Files.readAllBytes(Paths.get(path));
    run(new String (bytes,Charset.defaultCharset()));
}
// To run it interactively. Fire up jlox without any arguments, and it drops you into a prompt where you can enter and execute code one line at a time.
private static void runPrompt() throws IOException{
    InputStreamReader input =new InputStreamReader(System.in);
    BufferedReader reader= new BufferedReader(input);

    for(;;){
        System.out.print("> ");
        String line =reader.readLine();//readline function takes user input from the command line and returns the result
        if(line == null) break;
        run(line);
    }
}
private static void run(String source){
    Scanner scanner =new Scanner(source);
    List<Token> tokens =Scanner.scanTokens();
    //for now it just prints the tokens
    for(Token token:tokens){
        System.out.println(token);
    }
}
static void error(int line,String message){
    report(line,"",message);
}
private static void report(int line ,String where,String message){
    System.err.println( 
        "[line"+line +"] Error "+ where +":"+message);
        hadError=true;
}




}
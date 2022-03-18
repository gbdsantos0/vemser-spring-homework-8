package com.dbc.pessoaapi;

import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        File file = new File("src/main/java/com/dbc/pessoaapi/");
        for(String fileNames : file.list()) System.out.println(fileNames);
        File file1 = new File("src/main/java/com/dbc/pessoaapi/PessoaapiApplication.java");
        Scanner sc = new Scanner(file1);
        System.out.println(sc.nextLine());
    }
}

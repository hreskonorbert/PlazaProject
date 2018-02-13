package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.PlazaIsClosedException;

public class Main {
    public static void main(String[] args) {
        try{new CmdProgram(args).run();}catch(PlazaIsClosedException e){e.printStackTrace();}
    }
}

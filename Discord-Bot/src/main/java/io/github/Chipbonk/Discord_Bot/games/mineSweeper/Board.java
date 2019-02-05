package io.github.Chipbonk.Discord_Bot.games.mineSweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.Chipbonk.Discord_Bot.*;

public class Board {
	
	private static final int side = 9;
	private static Map<String, Command> commandMap = new HashMap<>();
	private static int[][] board = new int[9][9];
	
	static {
		
		commandMap.put("minesweeper", (event, args) -> {
			MainRunner.sendMessage(event.getChannel(), "Have fun!\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:gnomed:||||:bomb:||||:bomb:||\r\n" + 
					"||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||||:bomb:||");
		});
		
		commandMap.put("testsweeper", (event, args) -> {
			String TestBoard = generateMinesLocation(10).toString();
			
			MainRunner.sendMessage(event.getChannel(), TestBoard);
		});
	}
	
	public void plantMines(){
        ArrayList<Integer> loc = generateMinesLocation(10);
        for(int i : loc){
        	board..setValue(-1);
        }
    }
	
	private Object getCell(int board) {
		// TODO Auto-generated method stub
		ArrayList<Integer> theboard = new ArrayList<Integer>();
		return null;
	}

	public static ArrayList<Integer> generateMinesLocation(int q){
        ArrayList<Integer> loc = new ArrayList<Integer>();
        int random;
        for(int i = 0; i<q;){
            random = (int)(Math.random()* (side*side));
            if(!loc.contains(random)){
                loc.add(random);
                i++;
            }
        }
        return loc;
    }

}

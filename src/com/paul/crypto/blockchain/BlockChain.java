/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul.crypto.blockchain;

import com.paul.cryptocurrency.TransactionOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author h4ck3r
 */
public class BlockChain {
    public static ArrayList<Block> blockChain;
    public static Map<String,TransactionOutput> UTXOs; 

    public BlockChain() {
            BlockChain.UTXOs = new HashMap<String,TransactionOutput>();
            BlockChain.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
            BlockChain.blockChain.add(block);
    }

    public int size() {
            return BlockChain.blockChain.size();
    }

    @Override
    public String toString() {

            String blockChain = "";

            for(Block block : BlockChain.blockChain)
                    blockChain+=block.toString()+"\n";

            return blockChain;
    }
    
}

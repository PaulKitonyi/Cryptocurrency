/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul.cryptocurrency;

import com.paul.crypto.blockchain.Block;
import com.paul.crypto.blockchain.BlockChain;
import static com.paul.crypto.blockchain.BlockChain.blockChain;
import com.paul.crypto.constants.Constants;

/**
 *
 * @author h4ck3r
 */
public class Miner {
    private double reward;
	
    public void mine(Block block, BlockChain blockChain) {
            while(notGoldenHash(block)) {
                    block.generateHash();
                    block.incrementNonce();
            }

            System.out.println(block+" has just mined...");
            System.out.println("Hash is: "+block.getHash());


            blockChain.addBlock(block);
            reward+=Constants.MINER_REWARD;
    }

    public boolean notGoldenHash(Block block) {
            String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
            return !block.getHash().substring(0,Constants.DIFFICULTY).equals(leadingZeros);
    }

    public double getReward() {
            return this.reward;
    }
}

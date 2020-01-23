/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul.cryptocurrency;

/**
 *
 * @author h4ck3r
 */
public class TransactionInput {
	
	//every input has an output. This id is the transactionId of the TransactionOutput
	private String transactionOutputId; 
	//this is the unspent transaction output
	private TransactionOutput UTXO; 
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}

	public String getTransactionOutputId() {
		return transactionOutputId;
	}

	public void setTransactionOutputId(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}

	public TransactionOutput getUTXO() {
		return UTXO;
	}

	public void setUTXO(TransactionOutput uTXO) {
		UTXO = uTXO;
	}
}

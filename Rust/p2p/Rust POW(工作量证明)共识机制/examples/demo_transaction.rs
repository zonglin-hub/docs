use serde::{Deserialize, Serialize};
use sha2::{Digest, Sha256};
use std::time::Instant;

// 定义区块结构体（数据块）
#[derive(Debug, Serialize, Deserialize, Clone)]
struct Block {
    index: u64,
    timestamp: u64,
    transactions: Vec<Transaction>,
    prev_block_hash: Vec<u8>,
    nonce: u64,
}

// 定义交易结构体
#[derive(Debug, Serialize, Deserialize, Clone)]
struct Transaction {
    from: String,
    to: String,
    amount: u64,
}

// 定义区块链结构体
#[derive(Debug)]
struct Blockchain {
    blocks: Vec<Block>,
    unconfirmed_transactions: Vec<Transaction>,
    target_prefix: Vec<u8>,
}

impl Blockchain {
    // 创建新的区块链
    fn new() -> Self {
        Blockchain {
            blocks: vec![],
            unconfirmed_transactions: vec![],
            target_prefix: vec![0; 3], // 目标哈希前缀，此处设置为前3位为0
        }
    }

    // 添加交易记录
    fn add_transaction(&mut self, from: String, to: String, amount: u64) {
        self.unconfirmed_transactions
            .push(Transaction { from, to, amount });
    }

    // 挖矿
    fn mine_block(&mut self) {
        let start = Instant::now();
        let prev_block_hash = if let Some(last_block) = self.blocks.last() {
            self.hash_block(last_block)
        } else {
            vec![0; 32]
        };
        let mut block = Block {
            index: self.blocks.len() as u64,
            timestamp: start.elapsed().as_secs(),
            transactions: self.unconfirmed_transactions.clone(),
            prev_block_hash,
            nonce: 0,
        };
        let block_hash = self.find_hash(&mut block);
        block.nonce = block_hash.1;
        self.blocks.push(block.clone());
        self.unconfirmed_transactions.clear();
        let elapsed = start.elapsed();
        println!("Mined block {} in {:?}\n", block.index, elapsed);
    }

    // 查找合法哈希值
    fn find_hash(&self, block: &mut Block) -> (Vec<u8>, u64) {
        let mut hasher = Sha256::new();
        loop {
            block.nonce += 1;
            let block_bytes = bincode::serialize(&block).expect("");
            hasher.update(&block_bytes);
            let hash = hasher.finalize_reset();
            if hash.starts_with(&self.target_prefix) {
                return (hash.to_vec(), block.nonce);
            }
        }
    }

    // 计算区块哈希值
    fn hash_block(&self, block: &Block) -> Vec<u8> {
        let block_bytes = bincode::serialize(block).expect("");
        let mut hasher = Sha256::new();
        hasher.update(&block_bytes);
        hasher.finalize().to_vec()
    }
}

fn main() {
    let mut blockchain = Blockchain::new();
    blockchain.add_transaction("Alice".to_string(), "Bob".to_string(), 100);
    blockchain.add_transaction("Bob".to_string(), "Charlie".to_string(), 50);
    blockchain.mine_block();
    blockchain.add_transaction("Charlie".to_string(), "David".to_string(), 30);
    blockchain.mine_block();
    println!("Blockchain: {:?}", blockchain);
}
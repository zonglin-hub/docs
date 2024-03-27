use std::time::Instant;
use sha2::{Sha256, Digest};

const TARGET_PREFIX: &[u8] = &[0; 3]; // 目标哈希前缀，此处设置为前3位为0

fn main() {
    let start = Instant::now();
    let mut nonce: u64 = 0;
    let data = "Hello, world!".as_bytes();
    let mut hasher = Sha256::new();
    loop {
        // 拼接数据和nonce，计算哈希值
        let mut buf = Vec::new();
        buf.extend_from_slice(data);
        buf.extend_from_slice(&nonce.to_le_bytes());
        hasher.update(&buf);
        let hash = hasher.finalize_reset();
        // 如果哈希值符合目标前缀，则停止计算
        if hash.starts_with(TARGET_PREFIX) {
            println!("found nonce: {}", nonce);
            println!("hash: {:x}", hash);
            break;
        }
        nonce += 1;
    }
    let elapsed = start.elapsed();
    println!("elapsed: {:?}", elapsed);
}
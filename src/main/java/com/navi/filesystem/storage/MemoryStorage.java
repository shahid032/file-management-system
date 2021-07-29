package com.navi.filesystem.storage;

import com.navi.filesystem.exception.OutOfMemoryException;
import com.navi.filesystem.exception.UserDefinedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

public class MemoryStorage implements FileStorage {

  private final Map<Integer, MemoryBlock> allMemoryBlocks;

  private final Queue<MemoryBlock> priorityQueue;

  private Integer idCount;

  public MemoryStorage(Map<Integer, MemoryBlock> allMemoryBlocks, Queue<MemoryBlock> pq) {
    this.allMemoryBlocks = allMemoryBlocks;
    idCount = 1;
    this.priorityQueue = pq;
  }

  @Override
  public String addMemoryBlock(Integer totalMemBlock, Integer chunkSize, Integer memBlockSize) {

    IntStream.range(0, totalMemBlock)
        .forEach(i -> allMemoryBlocks.put(idCount, createMemoryBlock(chunkSize, memBlockSize / chunkSize, memBlockSize)));
    return String.format("Successfully created %s blocks", totalMemBlock);
  }

  @Override
  public MemoryBlock removeMemoryBlock(Integer id) throws Exception {
    if (!allMemoryBlocks.containsKey(id)) {
      throw new Exception("Not found");
    }
    MemoryBlock memBlock = allMemoryBlocks.remove(id);
    priorityQueue.remove(memBlock);
    return memBlock;
  }

  @Override
  public MemoryBlock pollMostAvailableBlock() {
    if (priorityQueue.isEmpty() || !priorityQueue.peek().isChunkAvailable()) {
      throw new OutOfMemoryException("No space available");
    }
    return priorityQueue.poll();
  }

  @Override
  public MemoryBlock getMemoryBlockById(Integer id){
    if (!allMemoryBlocks.containsKey(id)) {
      throw new UserDefinedException("Memory Block Not found");
    }
    return allMemoryBlocks.get(id);
  }

  @Override
  public void addMemoryBlockToPQ(MemoryBlock memBlock) {
    priorityQueue.add(memBlock);
  }
  
  private MemoryBlock createMemoryBlock(Integer chunkSize, Integer count, Integer memBlockSize) {

    List<Chunk> chunks = new ArrayList<>();
    IntStream.range(0, count).forEach(i -> chunks.add(Chunk.builder().memoryBlockId(idCount).size(chunkSize).build()));
    MemoryBlock memBlock = new MemoryBlock(idCount, memBlockSize, chunks);
    priorityQueue.add(memBlock);
    idCount++;
    return memBlock;
  }

}
